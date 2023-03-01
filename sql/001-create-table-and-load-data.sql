DROP TABLE IF EXISTS users;

CREATE TABLE users (
 id int unsigned AUTO_INCREMENT,
 name VARCHAR(20) NOT NULL,
 PRIMARY KEY(id)
);

INSERT INTO users (id, name) VALUES
(1, '福田'),
(2, '鈴木'),
(3, '大関'),
(4, '牧田');
