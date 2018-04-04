CREATE TABLE IF NOT EXISTS address (
       address_id INTEGER PRIMARY KEY,
       street_number TEXT,
       street_name TEXT,
       postal_code TEXT,
       postal_town TEXT,
       UNIQUE(street_number, street_name, postal_code, postal_town)
);

CREATE TABLE IF NOT EXISTS customer (
       customer_id INTEGER PRIMARY KEY,
       customer_name TEXT,
       address INTEGER,
       phone_number TEXT,
       billing_account TEXT,
       UNIQUE(billing_account),
       UNIQUE(phone_number),
       FOREIGN KEY(address) REFERENCES address(address_id)
);

CREATE TABLE IF NOT EXISTS category (
       category_id INTEGER PRIMARY KEY,
       category_name TEXT,
       UNIQUE(category_name)
);

CREATE TABLE IF NOT EXISTS product (
       product_id INTEGER PRIMARY KEY,
       product_name TEXT,
       description TEXT,
       price REAL,
       category INTEGER,
       FOREIGN KEY(category) REFERENCES category(category_id)
);

CREATE TABLE IF NOT EXISTS invoice (
       invoice_id INTEGER PRIMARY KEY,
       customer INTEGER,
       dato TEXT,
       FOREIGN KEY(customer) REFERENCES customer(customer_id)
);

CREATE TABLE IF NOT EXISTS invoice_items (
       invoice INTEGER,
       product INTEGER,
       FOREIGN KEY (invoice) REFERENCES invoice(invoice_id),
       FOREIGN KEY (product) REFERENCES product(product_id)
);

INSERT OR IGNORE INTO address (
       address_id,
       street_number,
       street_name,
       postal_code,
       postal_town
) VALUES (
     1,
     "6",
     "Fosswinckelsgate",
     "5007",
     "Bergen"
);

INSERT OR IGNORE INTO customer  (
       customer_id,
       customer_name,
       address,
       phone_number,
       billing_account
) VALUES (
  1,
  "Institutt for InfoMedia",
  1,
  "+47 55 58 91 00",
  "706741409023"
);

INSERT OR IGNORE INTO category (
       category_id,
       category_name
) VALUES (
       1,
       "books"
);

INSERT OR IGNORE INTO product (
       product_id,
       product_name,
       description,
       price,
       category
) VALUES (
  1,
  "Structure and interpretation of computer programs",
  "Book about programming",
  499.00,
  1
);

INSERT OR IGNORE INTO invoice (
       invoice_id,
       customer,
       dato
) VALUES (
  1, 1, "04.04.2018"
);

INSERT OR IGNORE INTO invoice_items (
   invoice,
   product
) VALUES (
  1, 1
);
