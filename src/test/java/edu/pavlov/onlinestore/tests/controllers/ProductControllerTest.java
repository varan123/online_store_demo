package edu.pavlov.onlinestore.tests.controllers;

import edu.pavlov.onlinestore.controllers.ProductController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductController productController;

    @Test
    void voidTest() {
    }

    @Test
    void contextLoads() throws Exception {
        assert productController != null;
    }

    @Test
    void testGetProduct() throws Exception {
        mockMvc.perform(get("/product"))
                .andExpect(status().isOk());
    }

}
