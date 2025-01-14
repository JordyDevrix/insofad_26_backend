package com.juwelier.webshop.controllers;

import com.juwelier.webshop.config.JWTUtil;
import com.juwelier.webshop.dao.CustomerRepository;
import com.juwelier.webshop.dto.AuthenticationDTO;
import com.juwelier.webshop.dto.LoginResponse;
import com.juwelier.webshop.models.Customer;
import com.juwelier.webshop.models.Role;
import com.juwelier.webshop.services.CredentialValidator;
import com.juwelier.webshop.services.CustomerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.juwelier.webshop.dao.CustomerDAO;
import com.juwelier.webshop.dao.RoleRepository;

import java.security.Principal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://s1151166.student.inf-hsleiden.nl:11166"})
@RequestMapping("/account")
public class AuthController {
    private final CustomerRepository customerRepository;
    private final RoleRepository roleDAO;
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authManager;
    private final PasswordEncoder passwordEncoder;
    private CredentialValidator validator;
    private CustomerService customerService;
    private final CustomerDAO customerDAO;

    public AuthController(CustomerRepository customerRepository, CustomerDAO customerDAO, RoleRepository roleDAO, JWTUtil jwtUtil, AuthenticationManager authManager, PasswordEncoder passwordEncoder, CredentialValidator validator, CustomerService customerService) {
        this.customerRepository = customerRepository;
        this.customerDAO = customerDAO;
        this.roleDAO = roleDAO;
        this.jwtUtil = jwtUtil;
        this.authManager = authManager;
        this.passwordEncoder = passwordEncoder;
        this.validator = validator;
        this.customerService = customerService;
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(@RequestBody AuthenticationDTO authenticationDTO) {
        if (!validator.isValidEmail(authenticationDTO.email)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "No valid email provided"
            );
        }

        if (!validator.isValidPassword(authenticationDTO.password)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "No valid password provided"
            );
        }

        Customer customer = this.customerRepository.findByEmail(authenticationDTO.email);

        if (customer != null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Can not register with this email"
            );
        }
        String encodedPassword = passwordEncoder.encode(authenticationDTO.password);

        Role userRole = roleDAO.findByRoleName("USER");
        if (userRole == null) {
            userRole = new Role("USER");
            roleDAO.save(userRole);
        }
        Set<Role> roles = new HashSet<>(Collections.singletonList(userRole));

        Customer registeredCustomUser = new Customer(authenticationDTO.email, encodedPassword, roles);
        customerRepository.save(registeredCustomUser);
        String token = jwtUtil.generateToken(registeredCustomUser.getEmail(), registeredCustomUser.getRoles().stream().map(Role::getRoleName).collect(Collectors.toSet()));
        LoginResponse loginResponse = new LoginResponse(registeredCustomUser.getEmail(), token);
        return ResponseEntity.ok(loginResponse);
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody AuthenticationDTO body) {
        try {
            UsernamePasswordAuthenticationToken authInputToken =
                    new UsernamePasswordAuthenticationToken(body.email, body.password);

            authManager.authenticate(authInputToken);

            Customer customer = customerRepository.findByEmail(body.email);
            Set<String> roles = customer.getRoles().stream().map(Role::getRoleName).collect(Collectors.toSet());

            String token = jwtUtil.generateToken(body.email, roles);

            LoginResponse loginResponse = new LoginResponse(customer.getEmail(), token);


            return ResponseEntity.ok(loginResponse);

        } catch (AuthenticationException authExc) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "No valid credentials"
            );
        }
    }

//    WTF IS DIT????

//    @GetMapping("/logout")
//    public ResponseEntity<String> logout(HttpSession session, RedirectAttributes redirectAttributes){
//        session.invalidate();
//        redirectAttributes.addFlashAttribute("message", "You have been logged out successfully.");
//        return ResponseEntity.ok("You have been logged out successfully 2");
//    }

    @GetMapping
    public ResponseEntity<Customer> getCurrentUser(Principal principal) {
        String email = principal.getName();
        Customer customer = this.customerRepository.findByEmail(email);
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/{email}")
    public ResponseEntity<Customer> getCustomer(@PathVariable String email) {
        return ResponseEntity.ok(customerRepository.findByEmail(email));
    }

    @PutMapping("/{email}")
    public ResponseEntity<String> updateUser(@RequestBody AuthenticationDTO authenticationDTO, @PathVariable String email) {
        Customer updatedCustomer = this.customerRepository.findByEmail(email);
        updatedCustomer.setFirstName(authenticationDTO.firstName);
        updatedCustomer.setLastName(authenticationDTO.lastName);
        updatedCustomer.setEmail(authenticationDTO.email);
        updatedCustomer.setPassword(passwordEncoder.encode(authenticationDTO.password));
        this.customerRepository.save(updatedCustomer);
        return ResponseEntity.ok("Customer was succesfully updated.");
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String email) {
        this.customerRepository.delete(this.customerRepository.findByEmail(email));
        return ResponseEntity.ok("Customer was succesfully removed.");
    }
}

