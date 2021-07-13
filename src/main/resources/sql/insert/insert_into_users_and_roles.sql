-- Для теста: Username - Vasya, pass: 12345678, роль - админ
-- Username - Petya, pass: 12345678, роль - кладовщик
-- Username - Kolya, pass: 123456, роль - клиент
-- Username - Oleg, pass: 123456, роль - клиент
INSERT INTO users (id, username, email, password)
  VALUES
  (DEFAULT, 'Vasya', 'admin@bookshop.ru', '$2a$11$uSXS6rLJ91WjgOHhEGDx..VGs7MkKZV68Lv5r1uwFu7HgtRn3dcXG'),
  (DEFAULT, 'Petya', 'storekeeper@bookshop.ru', '$2a$11$uSXS6rLJ91WjgOHhEGDx..VGs7MkKZV68Lv5r1uwFu7HgtRn3dcXG'),
  (DEFAULT, 'Kolya', 'kolya@mail.ru', '$2a$11$eWcBN1uDFrCZNzYopPs4n.dZjhhWCTqkAZgZZNWMWcuClKjCR1tsW'),
  (DEFAULT, 'Oleg', 'oleg@mail.ru', '$2a$11$eWcBN1uDFrCZNzYopPs4n.dZjhhWCTqkAZgZZNWMWcuClKjCR1tsW');

INSERT INTO roles (id, name)
  VALUES
  (DEFAULT, 'ROLE_ADMIN'),
  (DEFAULT, 'ROLE_USER'),
  (DEFAULT, 'ROLE_CONSUMER'),
  (DEFAULT, 'ROLE_STOREKEEPER');

INSERT INTO user_roles (user_id, role_id)
  VALUES
  (1, 1),
  (2, 4),
  (3, 3),
  (4, 3);