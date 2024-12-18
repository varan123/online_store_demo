CREATE TABLE role (
    id INTEGER NOT NULL auto_increment,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO role (id, name) VALUES (1, 'GUEST');
INSERT INTO role (id, name) VALUES (2, 'CUSTOMER');
INSERT INTO role (id, name) VALUES (3, 'MANAGER');
INSERT INTO role (id, name) VALUES (4, 'SUPERVISOR');

CREATE TABLE sys_user (
    id INTEGER NOT NULL auto_increment,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE user_role (
    user_id INTEGER NOT NULL,
    role_id INTEGER NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES sys_user (id),
    FOREIGN KEY (role_id) REFERENCES role (id)
);

CREATE TABLE product (
    id INTEGER NOT NULL auto_increment,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);


