package main.najah.test;

import main.najah.code.Product;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Product Tests")
public class ProductTest {
    Product product;

    @BeforeEach
    void setup() {
        product = new Product("Laptop", 1000);
    }

    @Test
    @Timeout(1)
    @DisplayName("Apply valid discount")
    void testValidDiscount() {
    	
        product.applyDiscount(10);
        assertEquals(900.0, product.getFinalPrice());
    }

    @Test
    @DisplayName("Apply invalid discount")
    void testInvalidDiscount() {
        assertThrows(IllegalArgumentException.class, () -> product.applyDiscount(60));
    } 

    @ParameterizedTest
    @ValueSource(doubles = {0, 25, 50})
    @DisplayName("Parameterized discount test")
    void testParameterizedDiscount(double discount) {
        product.applyDiscount(discount);
        assertTrue(product.getFinalPrice() <= 1000);
    }
}