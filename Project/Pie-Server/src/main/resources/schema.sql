DROP TABLE IF EXISTS MEMBER;
CREATE TABLE MEMBER
(
    USER_ID  VARCHAR(255) NOT NULL,
    USER_NAME   VARCHAR(255),
    PASS_WORD   VARCHAR(255),
    PRIMARY KEY (USER_ID)
);

DROP TABLE IF EXISTS KOSPI_STOCK;
CREATE TABLE KOSPI_STOCK
(
    KOSPI_STOCK_ID  VARCHAR(255) NOT NULL,
    NO  VARCHAR(255) NOT NULL,
    STOCK_NAME  VARCHAR(255) NOT NULL,
    PRICE  VARCHAR(255),
    DIFF_AMOUNT  VARCHAR(255),
    DAY_RANGE  VARCHAR(255),
    PAR_VALUE  VARCHAR(255),
    MARKET_CAP  VARCHAR(255),
    NUMBER_OF_LISTED_SHARES   VARCHAR(255),
    FOREIGN_OWN_RATE   VARCHAR(255),
    PER   VARCHAR(255),
    ROE   VARCHAR(255),
    DISCUSSION_ROOM_URL   VARCHAR(255),
    PRIMARY KEY (KOSPI_STOCK_ID)
);

DROP TABLE IF EXISTS ROOM;
CREATE TABLE ROOM (
                      ROOM_ID VARCHAR(255) PRIMARY KEY,
                      NO VARCHAR(255),
                      STOCK_NAME VARCHAR(255),
                      PRICE VARCHAR(255),
                      PRICE_PERCENT INT,
                      PERSON_CNT INT,
                      TOTAL_PERCENT INT
);

DROP TABLE IF EXISTS ROOM_MEMBER;
CREATE TABLE Room_Member (
                             room_member_id VARCHAR(255) PRIMARY KEY,
                             room_id VARCHAR(255),
                             user_id VARCHAR(255),
                             price_percent INT,
                             trading_cnt DOUBLE,
                             person_percent INT
);
