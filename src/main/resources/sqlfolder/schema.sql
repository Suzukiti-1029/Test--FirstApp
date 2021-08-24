DROP TABLE IF EXISTS message_table;

CREATE TABLE message_table (
  id INT NOT NULL AUTO_INCREMENT,
  message VARCHAR(256),
  PRIMARY KEY (id)
);