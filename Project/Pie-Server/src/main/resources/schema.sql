DROP TABLE IF EXISTS Member;
CREATE TABLE Member
(
    user_id  VARCHAR(255) NOT NULL,
    user_name   VARCHAR(255),
    pass_word   VARCHAR(255),
    PRIMARY KEY (user_id)
);

DROP TABLE IF EXISTS KOSPI_STOCK;
CREATE TABLE KOSPI_STOCK
(
    KOSPI_STOCK_id  VARCHAR(255) NOT NULL,
    no  VARCHAR(255) NOT NULL,
    stock_name  VARCHAR(255) NOT NULL,
    price  VARCHAR(255),
    diff_amount  VARCHAR(255),
    day_range  VARCHAR(255),
    par_value  VARCHAR(255),
    market_cap  VARCHAR(255),
    number_of_listed_shares   VARCHAR(255),
    foreign_own_rate   VARCHAR(255),
    per   VARCHAR(255),
    roe   VARCHAR(255),
    discussion_room_url   VARCHAR(255),
    PRIMARY KEY (KOSPI_STOCK_id)
);