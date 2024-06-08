-- Insert dummy data into category table
insert into category (id, name, description) values
    (nextval('category_seq'), 'Electronics', 'Devices and gadgets'),
    (nextval('category_seq'), 'Books', 'Various kinds of books'),
    (nextval('category_seq'), 'Clothing', 'Apparel and accessories'),
    (nextval('category_seq'), 'Home Appliances', 'Appliances for home use'),
    (nextval('category_seq'), 'Sports', 'Sporting goods'),
    (nextval('category_seq'), 'Toys', 'Toys for kids'),
    (nextval('category_seq'), 'Beauty', 'Beauty products'),
    (nextval('category_seq'), 'Automotive', 'Car and motorcycle accessories'),
    (nextval('category_seq'), 'Groceries', 'Food and drinks'),
    (nextval('category_seq'), 'Furniture', 'Furniture items');

-- Insert dummy data into product table
insert into product (id, name, description, available_quantity, price, category_id) values
    (nextval('product_seq'), 'Smartphone', 'Latest model smartphone', 50, 699.99, (select id from category where name = 'Electronics')),
    (nextval('product_seq'), 'Laptop', 'High performance laptop', 30, 1299.99, (select id from category where name = 'Electronics')),
    (nextval('product_seq'), 'Novel', 'Bestselling novel', 100, 19.99, (select id from category where name = 'Books')),
    (nextval('product_seq'), 'Jeans', 'Comfortable denim jeans', 75, 49.99, (select id from category where name = 'Clothing')),
    (nextval('product_seq'), 'Microwave Oven', 'High power microwave', 25, 89.99, (select id from category where name = 'Home Appliances')),
    (nextval('product_seq'), 'Football', 'Official size football', 60, 29.99, (select id from category where name = 'Sports')),
    (nextval('product_seq'), 'Action Figure', 'Popular superhero action figure', 200, 14.99, (select id from category where name = 'Toys')),
    (nextval('product_seq'), 'Shampoo', 'Organic hair shampoo', 150, 9.99, (select id from category where name = 'Beauty')),
    (nextval('product_seq'), 'Car Charger', 'Fast car charger', 85, 19.99, (select id from category where name = 'Automotive')),
    (nextval('product_seq'), 'Dining Table', 'Wooden dining table', 10, 299.99, (select id from category where name = 'Furniture'));
