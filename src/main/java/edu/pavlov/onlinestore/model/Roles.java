package edu.pavlov.onlinestore.model;

/**
 * The Roles enum represents user roles.
 */
public enum Roles {
    /**
     * The GUEST role is assigned to users who are not registered.
     */
    GUEST,

    /**
     * The CUSTOMER role is assigned to users with base permissions for
     * ordering.
     */
    CUSTOMER,

    /**
     * The MANAGER role is assigned to users with permissions to manage
     * products and orders.
     */
    MANAGER,

    /**
     * The SUPERVISOR role is assigned to users with full permissions.
     */
    SUPERVISOR
}
