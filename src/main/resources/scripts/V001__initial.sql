CREATE TABLE role (
    id INTEGER NOT NULL auto_increment,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE sys_user (
    id INTEGER NOT NULL auto_increment,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role_id INTEGER NOT NULL,
    FOREIGN KEY (role_id) REFERENCES role(id),
    PRIMARY KEY (id)
);

CREATE TABLE product (
    id INTEGER NOT NULL auto_increment,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);


