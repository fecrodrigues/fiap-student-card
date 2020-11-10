CREATE TABLE STUDENT_TRANSACTION (
    transactionID BIGINT NOT NULL AUTO_INCREMENT,
    studentID BIGINT NOT NULL,
    transactionCodeExternal VARCHAR(255) NOT NULL,
    cardLastDigits INT NULL,
    cardBrand VARCHAR(50) NULL,
    value DECIMAL NOT NULL,
    transactionDate DATETIME NOT NULL,

    PRIMARY KEY(transactionID)
)

