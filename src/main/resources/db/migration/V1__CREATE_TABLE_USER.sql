CREATE TABLE tb_users
(
    id        UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    username  VARCHAR(255) NOT NULL,
    name      VARCHAR(255) NOT NULL,
    password  VARCHAR(255) NOT NULL,
    email     VARCHAR(255) NOT NULL,
    phone     VARCHAR(255) NOT NULL,
    enabled   BOOLEAN      NOT NULL,
    createdAt DATE         NOT NULL,
    updatedAt DATE         NOT NULL,
    deletedAt DATE
);