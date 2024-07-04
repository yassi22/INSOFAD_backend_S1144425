package com.example.todoappdeel3.utils;


import com.example.todoappdeel3.dao.*;
import com.example.todoappdeel3.models.*;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class Seeder {
    private final ProductDAO productDAO;
    private final UserRepository userRepository;
    private final CategoryDAO categoryDAO;
    private final OrderRepository orderRepository;

    private final OptionsRepository optionsRepository;

    private final ProductVariantRepository productVariantRepository;

    public Seeder(ProductDAO productDAO, UserRepository userRepository, CategoryDAO categoryDAO, OrderRepository orderRepository,  ProductVariantRepository productVariantRepository, OptionsRepository optionsRepository) {
        this.productDAO = productDAO;
        this.userRepository = userRepository;
        this.categoryDAO = categoryDAO;
        this.orderRepository = orderRepository;
        this.productVariantRepository = productVariantRepository;
        this.optionsRepository = optionsRepository;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event){
        this.seedProducts();
        this.seedUser();
    }

    private void seedProducts(){
        // Luxe categorieën aanmaken
        Category categoryHoodies = new Category("Luxury Hoodies");
        Category categoryPants = new Category("Designer Pants");
        Category categoryAccessories = new Category("Exclusive Accessories");
        Category categoryCapsAndBeanies = new Category("Designer Headwear");
        Category categoryOuterwear = new Category("Premium Outerwear");

        // Luxe producten definiëren
        Product[] products = {
                new Product("Cashmere Hoodie", "Crafted from 100% pure cashmere, this hoodie offers unparalleled softness and warmth, ideal for those who seek both comfort and luxury.", 400.00, categoryHoodies, "A+", "Tailored Fit", "https://static.zara.net/assets/public/2ad8/5ad7/40244d4ea0c7/8717836b8a66/04087424712-e1/04087424712-e1.jpg?ts=1706808667117&w=422", 5),
                new Product("Silk Hoodie", "Experience the smooth touch of pure silk with this hoodie, combining casual design with luxury fabric for a sophisticated, relaxed look.", 350.00, categoryHoodies, "A++", "Tailored Fit", "https://static.zara.net/assets/public/8964/ebb9/05114de892b6/555f1f61e768/00761350251-e1/00761350251-e1.jpg?ts=1704355646092&w=422\n", 7),
                new Product("White Wool Hoodie", "Made from fine wool, this white hoodie blends comfort with elegance, perfect for a subtle, stylish statement.", 320.00, categoryHoodies, "A++",  "Tailored Fit", "https://static.zara.net/assets/public/d041/aa67/89824b0ea6ba/1882af5b7c83/05584416251-e1/05584416251-e1.jpg?ts=1710162403727&w=422\n", 8),
                new Product("Italian Leather Pants", "Tailored from premium Italian leather, these pants offer a perfect blend of luxury and durability, tailored for the fashion-forward.", 700.00, categoryPants, "A+", "Slim Fit", "https://static.zara.net/assets/public/b0f6/fda1/141c41149d2a/609b42cf42b2/08281651800-e1/08281651800-e1.jpg?ts=1706177879647&w=422\n", 1),
                new Product("Suede Cargo Pants", "Elevate your style with these soft suede cargo pants, offering both comfort and a bold fashion statement.", 650.00, categoryPants, "A+", "Relaxed Fit", "https://static.zara.net/assets/public/7e56/c869/2b5246768e80/b5d12a01882b/4000.jpg?ts=1692269651982&w=422\n", 3),
                new Product("Velvet Parachute Pants", "These parachute pants crafted from premium velvet redefine luxury in streetwear with their unique texture and comfort.", 600.00, categoryPants, "A++", "Over-sized", "https://static.zara.net/photos///2024/V/0/2/p/0108/403/518/2/w/422/0108403518_6_1_1.jpg?ts=1708337020339\n", 7),
                new Product("Leather Satchel Bag", "Handcrafted from the finest leather, this satchel is a masterpiece of luxury accessories, perfect for the discerning individual.", 950.00, categoryAccessories, "A+", "30cm x 35cm", "https://static.zara.net/assets/public/ed2f/5de5/324f4b2aaf9c/4d8af425baa0/16309310126-e1/16309310126-e1.jpg?ts=1710235010482&w=338\n", 1),
                new Product("Exotic Skin Clutch Bag", "This clutch made from exotic animal skin stands out as a symbol of luxury and exclusivity, perfect for high-class events.", 1100.00, categoryAccessories, "A+", "25cm x 15cm", "https://static.zara.net/assets/public/ed00/ad6b/b11d4e9aac89/6684aea4daef/16080310600-e1/16080310600-e1.jpg?ts=1715181778857&w=338\n", 1),
                new Product("Gold-Plated Keychain", "A stunning gold-plated keychain that combines utility with opulence, making it an essential luxury for everyday elegance.", 200.00, categoryAccessories, "A+", "8cm", "https://static.zara.net/assets/public/f7a3/c29a/b3944445808b/b5c404ef7b7d/01012302808-e1/01012302808-e1.jpg?ts=1716367368846&w=422\n", 7),
                new Product("Merino Wool Beanie", "Crafted from 100% merino wool, this beanie provides exceptional warmth and style, making it a must-have accessory for any luxury wardrobe.", 120.00, categoryCapsAndBeanies, "A++", "Fitted", "https://static.zara.net/assets/public/19cd/65f2/f952453e8d34/a12f7d99d3db/09065410712-e1/09065410712-e1.jpg?ts=1705495393576&w=422\n", 2),
                new Product("Cashmere Flat Cap", "Made from soft cashmere, this flat cap offers a timeless look with luxurious comfort, perfect for stylish outdoor adventures.", 150.00, categoryCapsAndBeanies, "A++", "Fitted", "https://static.zara.net/assets/public/37f1/c7f3/49484de48afd/17d840ba4d42/03920419523-e1/03920419523-e1.jpg?ts=1706108540123&w=422\n", 5),
                new Product("Silk Bucket Hat", "This silk bucket hat combines casual style with a touch of luxury, ideal for those who appreciate fine fabrics and contemporary design.", 180.00, categoryCapsAndBeanies, "A++", "Fitted", "https://static.zara.net/assets/public/eee9/f72e/4eda4062ac75/0f9ecdc1ed62/09065418737-e1/09065418737-e1.jpg?ts=1712313086169&w=422\n", 8)
        };

        // Producten opslaan
        for (Product product : products) {
            this.productDAO.createProduct(product);
        }

        // Categorieën opslaan
        this.categoryDAO.createCategory(categoryHoodies);
        this.categoryDAO.createCategory(categoryPants);
        this.categoryDAO.createCategory(categoryAccessories);
        this.categoryDAO.createCategory(categoryCapsAndBeanies);
        this.categoryDAO.createCategory(categoryOuterwear);

        // Varianten en opties definiëren
        String[] variantNames = {"Grootte", "Kleur", "Print"};
        String[][] options = {
                {"S", "M", "L"},
                {"Red", "Blue", "Black"},
                {"Grote Print", "Medium Print", "Small Print"}
        };

        for (Product product : products) {
            for (int i = 0; i < variantNames.length; i++) {
                ProductVariant variant = new ProductVariant(variantNames[i], "De " + variantNames[i].toLowerCase() + " van de " + product.getName(), product);
                this.productVariantRepository.save(variant);

                for (String optionName : options[i]) {
                    Options option = new Options(optionName, 100 + i * 50, variant);
                    this.optionsRepository.save(option);
                }
            }
        }
    }

    private void seedUser(){
        CustomUser customUser = new CustomUser();
        customUser.setEmail("bob@bobsluxuryenterprise.com");
        customUser.setPassword(new BCryptPasswordEncoder().encode("IreallyL0vePupp1es!"));
        customUser.setRole("ROLE_USER");
        userRepository.save(customUser);

        CustomUser customUserAdmin = new CustomUser();
        customUserAdmin.setEmail("bob@bobbyluxuryenterprise.com");
        customUserAdmin.setPassword(new BCryptPasswordEncoder().encode("k2C^QNP!dnx4ft"));
        customUserAdmin.setRole("ROLE_ADMIN");
        userRepository.save(customUserAdmin);

    }


}
