package edu.pavlov.onlinestore.controllers;

import edu.pavlov.onlinestore.dao.ProductsDAO;
import edu.pavlov.onlinestore.model.Product;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/product")
public class ProductController  {

    /**
     * The productsDAO field is used to get a product by its id.
     */
    @Autowired
    private ProductsDAO productsDAO;

    /**
     * The getProduct method returns a product by its id.
     * @param id The product's id.
     * @return String
     */
    @GetMapping
    public Product getProduct(
            @Parameter(description = "The product's id") final Long id) {
        return productsDAO.getProductById(id);
    }
}
