package com.ecommerce.config;

import com.ecommerce.entity.*;
import com.ecommerce.enums.Role;
import com.ecommerce.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // Create admin user
        User admin = User.builder()
                .email("admin@test.com")
                .password(passwordEncoder.encode("admin123"))
                .fullName("Admin User")
                .role(Role.ROLE_ADMIN)
                .build();
        userRepository.save(admin);

        // Create test user
        User user = User.builder()
                .email("user@test.com")
                .password(passwordEncoder.encode("user123"))
                .fullName("Test User")
                .role(Role.ROLE_USER)
                .build();
        userRepository.save(user);
        cartRepository.save(Cart.builder().user(user).build());

        // Create sample products
        productRepository.save(Product.builder()
                .name("iPhone 15 Pro")
                .description("Latest Apple smartphone with A17 Pro chip")
                .price(new BigDecimal("999.99"))
                .stock(50)
                .category("Electronics")
                .imageUrl("https://example.com/iphone15.jpg")
                .active(true)
                .build());

        productRepository.save(Product.builder()
                .name("MacBook Pro 14\"")
                .description("Apple M3 Pro laptop with 18GB RAM")
                .price(new BigDecimal("1999.99"))
                .stock(30)
                .category("Electronics")
                .imageUrl("https://example.com/macbook.jpg")
                .active(true)
                .build());

        productRepository.save(Product.builder()
                .name("Sony WH-1000XM5")
                .description("Premium noise-cancelling headphones")
                .price(new BigDecimal("349.99"))
                .stock(100)
                .category("Electronics")
                .imageUrl("https://example.com/sony.jpg")
                .active(true)
                .build());

        productRepository.save(Product.builder()
                .name("Nike Air Max")
                .description("Classic running shoes")
                .price(new BigDecimal("129.99"))
                .stock(200)
                .category("Shoes")
                .imageUrl("https://example.com/nike.jpg")
                .active(true)
                .build());

        productRepository.save(Product.builder()
                .name("Levi's 501 Jeans")
                .description("Original fit jeans")
                .price(new BigDecimal("79.99"))
                .stock(150)
                .category("Clothing")
                .imageUrl("https://example.com/levis.jpg")
                .active(true)
                .build());

        System.out.println("===========================================");
        System.out.println("Sample data loaded successfully!");
        System.out.println("Admin: admin@test.com / admin123");
        System.out.println("User:  user@test.com / user123");
        System.out.println("===========================================");
    }
}
