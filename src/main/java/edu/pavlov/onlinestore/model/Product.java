package edu.pavlov.onlinestore.model;

import lombok.Data;

/**
 * The Product class represents a product entity.
 */
@Data
public class Product  {
    /**
     * The id field represents the product's id.
     */
    private Long id;

    /**
     * The name field represents the product's name.
     */
    private String name;

    /**
     * The price field represents the product's price.
     */
    private String description;
}
