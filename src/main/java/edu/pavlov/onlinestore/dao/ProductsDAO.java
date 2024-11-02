package edu.pavlov.onlinestore.dao;

import edu.pavlov.onlinestore.model.Product;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


@Component
public class ProductsDAO {

    /**
     * The dataSource field is used to get a connection to the database.
     */
    @Autowired
    private DataSource dataSource;

    /**
     * The getProductById method returns a product by its id.
     * @param id The product's id.
     * @return Product
     */
    @SneakyThrows
    public Product getProductById(final Long id) {
        Product product = null;

        try (Connection connection = dataSource.getConnection()) {
                PreparedStatement statement = connection.prepareStatement(
                                "SELECT name, description FROM product "
                                        + "WHERE id = " + id);
                statement.execute();
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                product = new Product();
                product.setName(resultSet.getString("name"));
                product.setDescription(resultSet.getString("description"));
                product.setId(id);
            }
        }

        return product;
    }
}
