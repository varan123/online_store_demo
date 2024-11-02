package edu.pavlov.onlinestore.tests.dao;

import edu.pavlov.onlinestore.dao.ProductsDAO;
import edu.pavlov.onlinestore.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductsDAOTest {

    @Autowired
    private ProductsDAO productsDAO;

    @Test
    void voidTest() {
    }

    @Test
    void contextLoads() {
        assert productsDAO != null;
    }

    @Test
    void testGetProductById() {
        Product product = productsDAO.getProductById(1L);
        assert product != null;
        assert product.getName().equals("product 1");
        assert product.getDescription().equals("description 1");
        assert product.getId() == 1L;
    }
}
