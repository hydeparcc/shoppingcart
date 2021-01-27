DROP TABLE IF EXISTS Product;

CREATE TABLE IF NOT EXISTS `Product` (
    `id` INTEGER  PRIMARY KEY AUTO_INCREMENT,
     `name` VARCHAR(250) NOT NULL,
     `desc` VARCHAR(250)  NOT NULL,
     `price` NUMERIC NOT NULL
);

INSERT INTO product (name, desc, price) VALUES
  ('iPhone X', 'XYZ', '599.00'),
  ('iPhone Xr', 'ABC', '699.00'),
  ('iPhone 6', 'XYZ', '99.00'),
  ('iPhone 6s', 'XYZ', '159.00'),
  ('iPhone Xs', 'ABC', '799.00'),
  ('iPhone 7', 'XYZ', '199.00'),
  ('iPhone 7plus', 'XYZ', '259.00'),
  ('iPhone 8', 'ABC', '299.00'),
  ('iPhone 8plus', 'XYZ', '359.00'),
  ('iPhone 11pro', 'XYZ', '999.00'),
  ('iPhone 12pro', 'XYZ', '1199.00'),
  ('iPhone 12', 'XYZ', '1099.00'),
  ('iPhone 11', 'JKL', '899.00');