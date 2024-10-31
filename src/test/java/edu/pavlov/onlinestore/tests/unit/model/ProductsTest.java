package edu.pavlov.onlinestore.tests.unit.model;

import edu.pavlov.onlinestore.model.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ProductsTest {
    private static Product product;

    @BeforeAll
    public static void setUp() {
        product = new Product();
    }


    @Test
    public void testProduct() {
        product.setId(1L);
        product.setName("product");
        product.setDescription("description");

        assert product.getId() == 1L;
        assert product.getName().equals("product");
        assert product.getDescription().equals("description");
    }


}
