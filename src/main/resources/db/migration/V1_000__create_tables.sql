
/* USER TABLE */
CREATE TABLE USER(
    ID BIGINT AUTO_INCREMENT NOT NULL,
    LOGIN VARCHAR(200) NOT NULL,
    PASSWORD VARCHAR(200) NOT NULL,
    PRIMARY KEY (ID)
);


/* STUDENT TABLE */
CREATE TABLE STUDENT (
     ID BIGINT NOT NULL AUTO_INCREMENT,
     ADDRESS_ID VARCHAR(255),
     CARD_ID VARCHAR(255),
     CLASS_NAME VARCHAR(255),
     CPF VARCHAR(255),
     NAME VARCHAR(255), PRIMARY KEY (ID)
);

/* ADDRESS TABLE */
CREATE TABLE ADDRESS (
    ID BIGINT NOT NULL AUTO_INCREMENT,
    CEP VARCHAR(255),
    CITY VARCHAR(255),
    COMPLEMENT VARCHAR(255),
    NEIGHBORHOOD VARCHAR(255),
    NUMBER VARCHAR(255),
    STREET VARCHAR(255),
    UF VARCHAR(255), PRIMARY KEY (ID)
);

/* CARD TABLE */
CREATE TABLE CARD (
    ID BIGINT NOT NULL AUTO_INCREMENT,
    TOKEN_CARD VARCHAR(255) UNIQUE NOT NULL,
    BRAND VARCHAR(255),
    CVV VARCHAR(255),
    NUMBER VARCHAR(255),
    VALIDATE VARCHAR(255),
    PRIMARY KEY (ID)
);

/* STUDENT TRANSACTION  */
CREATE TABLE STUDENT_TRANSACTION (
    TRANSACTION_ID BIGINT NOT NULL AUTO_INCREMENT,
    STUDENT_ID BIGINT NOT NULL,
    CARD_ID BIGINT NOT NULL,
    TRANSACTION_CODE_EXTERNAL VARCHAR(255),
    TRANSACTION_DATE DATETIME(6),
    VALUE DECIMAL(19,2),
    STATUS VARCHAR(20) NOT NULL,

    PRIMARY KEY (TRANSACTION_ID)
);
