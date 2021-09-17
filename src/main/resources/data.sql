CREATE TABLE user
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    email VARCHAR(60) NOT NULL,
    password VARCHAR(20) NOT NULL
);

INSERT INTO user (name, email, password)
VALUES ('João Aparecido Santos', 'joao@hotmail.com', '152joao'),
       ('Carlos Alvaro Teixeira', 'carlos@hotmail.com', '152carlos'),
       ('Daniel Alves Moraes', 'daniel@hotmail.com', '152daniel'),
       ('Morata Omni Hashira', 'morata@hotmail.com', '152morata'),
       ('Jose Akira Tocantes', 'jose@hotmail.com', '152jose');
