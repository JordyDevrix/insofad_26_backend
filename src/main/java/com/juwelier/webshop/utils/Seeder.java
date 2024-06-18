package com.juwelier.webshop.utils;

import com.juwelier.webshop.dao.*;
import com.juwelier.webshop.models.*;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class Seeder {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;
    private PasswordEncoder passwordEncoder;
    private OrderRepository orderRepository;
    private ProductPropertiesRepository productPropertiesRepository;

    public Seeder(ProductRepository productRepository, CategoryRepository categoryRepository, CustomerRepository customerRepository, PasswordEncoder passwordEncoder, OrderRepository orderRepository, ProductPropertiesRepository productPropertiesRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        this.orderRepository = orderRepository;
        this.productPropertiesRepository = productPropertiesRepository;
    }

    @EventListener
    public void seeder(ContextRefreshedEvent event){
        Category category1 = new Category("Watches");
        Category category2 = new Category("Rings");
        Category category3 = new Category("Necklaces");
        Category category4 = new Category("Accessories");
        this.categoryRepository.save(category1);
        this.categoryRepository.save(category2);
        this.categoryRepository.save(category3);
        this.categoryRepository.save(category4);



        Product product1 = new Product(
                "Oyster perpetual",
                "https://schapi-products.s3.eu-central-1.amazonaws.com/3523842/m228238-0061_modelpage_front_facing_landscape.png",
                "Expensive ah collectors item",
                "Rolex",
                category1
        );
        this.productRepository.save(product1);
        ProductProperties prop1 = new ProductProperties("S", "Gold", "Gold", product1, 40000.00, 400);
        ProductProperties prop2 = new ProductProperties("M", "Gold", "Gold", product1,40001.00, 400);
        ProductProperties prop3 = new ProductProperties("X", "Gold", "Gold", product1,40002.00, 400);
        this.productPropertiesRepository.save(prop1);
        this.productPropertiesRepository.save(prop2);
        this.productPropertiesRepository.save(prop3);


        Product product2 = new Product(
                "Gold chain",
                "https://i.ebayimg.com/images/g/8j8AAOSwWktaaFRP/s-l1200.webp",
                "A big, heavy gold chain",
                null,
                category3
        );
        this.productRepository.save(product2);
        ProductProperties prop4 = new ProductProperties("S", "Gold", "Gold", product2, 15000.00, 400);
        ProductProperties prop5 = new ProductProperties("M", "Gold", "Gold", product2, 15000.00, 400);
        ProductProperties prop6 = new ProductProperties("X", "Gold", "Gold", product2, 15000.00, 400);
        this.productPropertiesRepository.save(prop4);
        this.productPropertiesRepository.save(prop5);
        this.productPropertiesRepository.save(prop6);

        Product product3 = new Product(
                "Diamond ring",
                "https://idjewelry.com/media/catalog/product/cache/6772f233b6ab10acdab5c36a45eb28cd/1/2/12baf843a62e47d3db2d9fe9820c37b7.jpg",
                "Thousands of tiny diamonds",
                null,
                category2
        );
        this.productRepository.save(product3);
        ProductProperties prop7 = new ProductProperties("S", "Diamond", "Diamond", product3, 125000.00, 400);
        ProductProperties prop8 = new ProductProperties("M", "Diamond", "Diamond", product3, 125000.00, 400);
        ProductProperties prop9 = new ProductProperties("X", "Diamond", "Diamond", product3, 125000.00, 400);
        ProductProperties prop10 = new ProductProperties("XL", "Diamond", "Diamond", product3, 125000.00, 400);
        this.productPropertiesRepository.save(prop7);
        this.productPropertiesRepository.save(prop8);
        this.productPropertiesRepository.save(prop9);
        this.productPropertiesRepository.save(prop10);

        Product product4 = new Product(
                "Vintage watch",
                "https://encrypted-tbn3.gstatic.com/shopping?q=tbn:ANd9GcQbutRSQI54h9aDmDKvLkGGfiZZlf3hlCdoZMK8qZ79J20A5ReXuwv9A2mWGVyyNYg9bBPlGFRAyT8ih6k7mnu8VydAh9UjfRPlfMtDvuw_jWbJdPTYZvm5&usqp=CAE",
                "Made in 1939",
                null,
                category1
        );
        this.productRepository.save(product4);
        ProductProperties prop11 = new ProductProperties("S", "Brown", "Leather", product4, 20000.00, 400);
        ProductProperties prop12 = new ProductProperties("M", "Brown", "Leather", product4, 20000.00, 400);
        ProductProperties prop13 = new ProductProperties("X", "Brown", "Leather", product4, 20000.00, 400);
        this.productPropertiesRepository.save(prop11);
        this.productPropertiesRepository.save(prop12);
        this.productPropertiesRepository.save(prop13);

        Product product5 = new Product(
                "L'incomparable",
                "https://4cs.gia.edu/wp-content/uploads/2013/04/Incomparable-400.png",
                "Worlds most expensive necklace",
                null,
                category3
        );
        this.productRepository.save(product5);
        ProductProperties prop14 = new ProductProperties("S", "Silver", "Silver", product5, 550000.00, 400);
        ProductProperties prop15 = new ProductProperties("M", "Silver", "Silver", product5, 550000.00, 400);
        ProductProperties prop16 = new ProductProperties("X", "Silver", "Silver", product5, 550000.00, 400);
        this.productPropertiesRepository.save(prop14);
        this.productPropertiesRepository.save(prop15);
        this.productPropertiesRepository.save(prop16);

        Product product6 = new Product(
                "Los lentes de sol",
                "https://eyeonoptics.com.au/media/nsrj5v1f/dolce-and-gabbana.jpg",
                "For classy sun-protection",
                "Dolce & Gabbana",
                category4
        );
        this.productRepository.save(product6);
        ProductProperties prop17 = new ProductProperties("S", "Gold", "Gold", product6, 95000.00, 400);
        ProductProperties prop18 = new ProductProperties("M", "Gold", "Gold", product6, 95000.00, 400);
        ProductProperties prop19 = new ProductProperties("X", "Gold", "Gold", product6, 95000.00, 400);
        this.productPropertiesRepository.save(prop17);
        this.productPropertiesRepository.save(prop18);
        this.productPropertiesRepository.save(prop19);

        Product product7 = new Product(
                "Geneva Magnificent",
                "https://robbreport.com/wp-content/uploads/2020/11/2020_GNV_18970_0354_000exceptional_pair_of_diamond_and_coloured_diamond_earrings061852-2.gif",
                "Three-colored piece of beauty",
                null,
                category4
        );
        this.productRepository.save(product7);
        ProductProperties prop20 = new ProductProperties("One size fits all", "Diamond", "Diamond", product7, 820000.00, 400);
        this.productPropertiesRepository.save(prop20);

        Product product8 = new Product(
                "Eagle belt",
                "https://i.pinimg.com/originals/c3/1b/43/c31b4383b95307094498912839164283.jpg",
                "Made from real crocodile leather",
                "Montblanc",
                category4
        );
        this.productRepository.save(product8);
        ProductProperties prop21 = new ProductProperties("S", "Black", "Leather", product8, 62000.00, 400);
        ProductProperties prop22 = new ProductProperties("M", "Black", "Leather", product8, 62000.00, 400);
        ProductProperties prop23 = new ProductProperties("X", "Black", "Leather", product8, 62000.00, 400);
        ProductProperties prop24 = new ProductProperties("XL", "Black", "Leather", product8, 62000.00, 400);
        this.productPropertiesRepository.save(prop21);
        this.productPropertiesRepository.save(prop22);
        this.productPropertiesRepository.save(prop23);
        this.productPropertiesRepository.save(prop24);

        Product product9 = new Product(
                "Crown ring",
                "https://nationaljeweler.com/uploads/ff952437c9c312c4e84ca8f31d155d6f.jpg",
                "To make you feel like a king",
                null,
                category2
        );
        this.productRepository.save(product9);
        ProductProperties prop25 = new ProductProperties("S", "Gold", "Gold", product9, 160000.00, 400);
        ProductProperties prop26 = new ProductProperties("M", "Gold", "Gold", product9, 160000.00, 400);
        ProductProperties prop27 = new ProductProperties("X", "Gold", "Gold", product9, 160000.00, 400);
        ProductProperties prop28 = new ProductProperties("XL", "Gold", "Gold", product9, 160000.00, 400);
        this.productPropertiesRepository.save(prop25);
        this.productPropertiesRepository.save(prop26);
        this.productPropertiesRepository.save(prop27);
        this.productPropertiesRepository.save(prop28);

        Product product10 = new Product(
                "Mysterious ring",
                "https://upload.wikimedia.org/wikipedia/commons/d/d4/One_Ring_Blender_Render.png",
                "The one to rule them all",
                null,
                category2
        );
        this.productRepository.save(product10);
        ProductProperties prop29 = new ProductProperties("S", "Gold", "Gold", product10, 0.00, 400);
        ProductProperties prop30 = new ProductProperties("M", "Gold", "Gold", product10, 0.00, 400);
        ProductProperties prop31 = new ProductProperties("X", "Gold", "Gold", product10, 0.00, 400);
        ProductProperties prop32 = new ProductProperties("XL", "Gold", "Gold", product10, 0.00, 400);
        this.productPropertiesRepository.save(prop29);
        this.productPropertiesRepository.save(prop30);
        this.productPropertiesRepository.save(prop31);
        this.productPropertiesRepository.save(prop32);



        String encodedPassword = passwordEncoder.encode("Test123!");
        Customer customer = new Customer(
                "Bob",
                "Webshop",
                "bob@bobsluxuryenterprise.com",
                encodedPassword
        );
        customer.setRole("ROLE_ADMIN");
        this.customerRepository.save(customer);
    }
}
