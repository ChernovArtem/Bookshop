-- Table: users
create table if not exists users
(
    id serial primary key,
    username varchar(50) not null,
    email varchar(50) not null,
    password varchar(255) not null
);


-- Table: roles
create table if not exists roles
(
    id serial primary key,
    name varchar(100) not null
);


-- Table for mapping user and roles: user_roles
create table if not exists user_roles
(
    user_id integer references users not null,
    role_id integer references roles not null,
    unique(user_id, role_id)
);


-- Table: Customers
create table if not exists customers
(
    user_id serial references users primary key,
    firstname varchar(30),
    lastname varchar(30),
    phone varchar(20),
    address varchar(255),
    street varchar(255),
    city varchar(30),
    state varchar(50),
    postal_code varchar(8),
    country varchar(30)
);


-- Table: Books
create table if not exists books
(
    isbn varchar(20) primary key,
    book_title text not null,
    book_type varchar(20) not null,
    publishing_year integer not null,
    pages integer,
    price integer not null
);


-- Table: Genres
create table if not exists genres
(
    isbn varchar(20) references books primary key,
    genre varchar(50) not null
);


-- Table: Authors
create table if not exists authors
(
    author_id serial primary key,
    name varchar(50) not null
);


-- Table: BooksByAuthors
create table if not exists books_by_authors
(
    author_id integer references authors not null,
    isbn varchar(20) references books not null
);


-- Table: Stock
create table if not exists stock
(
    isbn varchar(20) references books primary key,
    total integer not null,
    sold integer not null,
    reserve integer not null,
    available integer not null
);


-- Table: Cart
create table if not exists cart
(
    cart_id serial primary key,
    cart_customer integer references customers not null,
    cart_date date,
    cart_status smallint
);


-- Table: Cart_details
create table if not exists cart_details
(
    cart_id integer references cart not null,
    cart_detail_book varchar(20) references books not null,
    cart_detail_quantity integer,
    primary key (cart_id, cart_detail_book)
);


-- Table: Payments
create table if not exists payments
(
    income_payment serial not null
        constraint payment_billing_pkey
            primary key,
    order_number varchar(20) not null,
    payment_date timestamp default now(),
    amount numeric(9,2),
    payments_status varchar(20) default 'awaiting_payment'
);


-- Table: Orders
create table if not exists orders
(
    order_number serial primary key,
    order_date date not null,
    required_date date,
    shipped_date date,
    price float,
    delivery float,
    status smallint,
    comments varchar(50),
    customer_id integer references customers
);


-- Table: OrderDetails
create table if not exists order_details
(
    order_number integer references orders not null,
    product_id varchar(20) references books not null,
    quantity_ordered integer,
    price numeric(9,2) not null,
    primary key (order_number, product_id)
);

-- Table: Wishlist
create table if not exists wishlist
(
    id serial primary key,
    customer integer references customers not null
);


-- Table: Wishlist_book
create table if not exists wishlist_book
(
    id integer references wishlist not null,
    book varchar(20) references books not null,
    primary key (id, book)
);


--Table: Feedback
create table feedback
(
    feedback_id          serial not null
        constraint feedback_pk
            primary key,
    feedback_f_name      varchar(20),
    feedback_l_name      varchar(20),
    feedback_email       varchar(20),
    feedback_phone       varchar(20),
    feedback_subject     text,
    feedback_message     text,
    feedback_create_date date,
    feedback_status      smallint,
    feedback_comment     text,
    feedback_user        integer references users,
    feedback_upd_date    date

);
