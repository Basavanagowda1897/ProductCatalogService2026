CREATE TABLE category
(
    id                  BIGINT AUTO_INCREMENT NOT NULL,
    created_at          BIGINT NULL,
    last_updated_at     BIGINT NULL,
    state               SMALLINT NULL,
    name                VARCHAR(255) NULL,
    `description`       VARCHAR(255) NULL,
    category_details_id BIGINT NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE category_details
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    created_at      BIGINT NULL,
    last_updated_at BIGINT NULL,
    state           SMALLINT NULL,
    name            VARCHAR(255) NULL,
    `description`   VARCHAR(255) NULL,
    CONSTRAINT pk_categorydetails PRIMARY KEY (id)
);

CREATE TABLE product
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    created_at      BIGINT NULL,
    last_updated_at BIGINT NULL,
    state           SMALLINT NULL,
    name            VARCHAR(255) NULL,
    `description`   VARCHAR(255) NULL,
    price DOUBLE NULL,
    image_url       VARCHAR(255) NULL,
    category_id     BIGINT NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

ALTER TABLE category
    ADD CONSTRAINT FK_CATEGORY_ON_CATEGORYDETAILS FOREIGN KEY (category_details_id) REFERENCES category_details (id);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id);