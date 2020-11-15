INSERT INTO USER (LOGIN, PASSWORD) VALUES ('fulano', 'Pp123456@');

-- INSERT USERS AND RESPECTIVE CARDS
-- USER JOÃO
INSERT INTO STUDENT (ADDRESS_ID, CARD_ID, CLASS_NAME, CPF, NAME) VALUES ('', '', '36SCJ', '12345678945', 'João Da Silva');

INSERT INTO CARD (TOKEN_CARD, BRAND, CVV, NUMBER, VALIDATE) VALUES ('0313e412-be96-4d7d-9dfa-2aeb311310ab', 'MASTERCARD', '123', '4564', '08/22');
INSERT INTO CARD (TOKEN_CARD, BRAND, CVV, NUMBER, VALIDATE) VALUES ('0313e412-be96-4d7d-9dfa-2aeb311310b1', 'VISA', '456', '1475', '05/28');

-- USER MARIA
INSERT INTO STUDENT (ADDRESS_ID, CARD_ID, CLASS_NAME, CPF, NAME) VALUES ('', '', '16SCJ', '98765432112', 'Maria da Costa');

INSERT INTO CARD (TOKEN_CARD, BRAND, CVV, NUMBER, VALIDATE) VALUES ('0313e412-be96-4d7d-9dfa-2aeb311310B4', 'ELO', '985', '0325', '02/21');
INSERT INTO CARD (TOKEN_CARD, BRAND, CVV, NUMBER, VALIDATE) VALUES ('0313e412-be96-4d7d-9dfa-2aeb311310B5', 'MASTERCARD', '089', '9678', '03/22');


-- USER CHICO
INSERT INTO STUDENT (ADDRESS_ID, CARD_ID, CLASS_NAME, CPF, NAME) VALUES ('', '', '14SCJ', '45678912332', 'Chico Bento');

INSERT INTO CARD (TOKEN_CARD, BRAND, CVV, NUMBER, VALIDATE) VALUES ('0313e412-be96-4d7d-9dfa-2aeb311310B9', 'ELO', '528', '3467', '01/21');


-- TRANSACTIONS
INSERT INTO STUDENT_TRANSACTION (CARD_ID, STATUS, STUDENT_ID, TRANSACTION_CODE_EXTERNAL, TRANSACTION_DATE, VALUE) VALUES (1, 'IN_ANALYSIS', 1, '49467b91-4352-4b42-a85d-ce3516487bdd', '2020-11-15 16:29:18.550', 15.00);
INSERT INTO STUDENT_TRANSACTION (CARD_ID, STATUS, STUDENT_ID, TRANSACTION_CODE_EXTERNAL, TRANSACTION_DATE, VALUE) VALUES (1, 'IN_ANALYSIS', 1, '49467b91-4352-4b42-a85d-ce3516487bd1', '2019-04-02 13:18:00.550', 17.00);
INSERT INTO STUDENT_TRANSACTION (CARD_ID, STATUS, STUDENT_ID, TRANSACTION_CODE_EXTERNAL, TRANSACTION_DATE, VALUE) VALUES (2, 'APPROVED', 1, '49467b91-4352-4b42-a85d-ce3516487bd2', '2019-08-12 17:12:00.550', 115.18);

INSERT INTO STUDENT_TRANSACTION (CARD_ID, STATUS, STUDENT_ID, TRANSACTION_CODE_EXTERNAL, TRANSACTION_DATE, VALUE) VALUES (3, 'IN_ANALYSIS', 2, '49467b91-4352-4b42-a85d-ce3516487bE4', '2020-02-07 14:18:00.550', 23.00);
INSERT INTO STUDENT_TRANSACTION (CARD_ID, STATUS, STUDENT_ID, TRANSACTION_CODE_EXTERNAL, TRANSACTION_DATE, VALUE) VALUES (4, 'APPROVED', 2, '49467b91-4352-4b42-a85d-ce3516487bdE5', '2020-05-07 16:00:00.550', 456.00);
INSERT INTO STUDENT_TRANSACTION (CARD_ID, STATUS, STUDENT_ID, TRANSACTION_CODE_EXTERNAL, TRANSACTION_DATE, VALUE) VALUES (4, 'APPROVED', 2, '49467b91-4352-4b42-a85d-ce3516487bdE6', '2020-04-04 10:12:00.550', 174.45);

INSERT INTO STUDENT_TRANSACTION (CARD_ID, STATUS, STUDENT_ID, TRANSACTION_CODE_EXTERNAL, TRANSACTION_DATE, VALUE) VALUES (5, 'APPROVED', 3, '49467b91-4352-4b42-a85d-ce3516487bE4', '2020-11-15 17:01:00.550', 45.70);


