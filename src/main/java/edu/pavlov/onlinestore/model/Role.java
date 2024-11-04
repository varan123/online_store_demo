package edu.pavlov.onlinestore.model;

import lombok.Data;

@Data
public class Role {
    /**
     * The id field represents the role's id.
     */
    private Long id;

    /**
     * The name field represents the role's name.
     */
    private String name;

    /**
     * The default constructor.
     */
    public Role() {
    }

    /**
     * The constructor with parameters.
     * @param idParam The role's id.
     * @param nameParam The role's name.
     */
    public Role(final Long idParam, final String nameParam) {
        this.id = idParam;
        this.name = nameParam;
    }
}
