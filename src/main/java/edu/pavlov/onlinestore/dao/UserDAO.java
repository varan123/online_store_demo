package edu.pavlov.onlinestore.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class UserDAO {

    /**
     * The dataSource field is used to get a connection to the database.
     */
    @Autowired
    private DataSource dataSource;


}
