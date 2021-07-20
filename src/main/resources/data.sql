DROP TABLE IF EXISTS fruits;

CREATE TABLE fruits (
                              id LONG AUTO_INCREMENT  PRIMARY KEY,
                              name VARCHAR(250) NOT NULL,
                              color VARCHAR(250) NOT NULL,
                              note VARCHAR(250)
);

INSERT INTO fruits (id ,name, color, note) VALUES
(default, 'Banana', 'YELLOW', '...Joe.'),
(default, 'Strawberry', 'RED', '...fields forever!'),
(default, 'Kiwi', 'GREEN', null),
(default, 'Durian', 'YELLOW', 'The king of the Fruits.');