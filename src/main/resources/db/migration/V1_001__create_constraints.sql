
ALTER TABLE STUDENT_TRANSACTION ADD CONSTRAINT STUDENT_STRANSACTION FOREIGN KEY (STUDENT_ID) REFERENCES STUDENT (ID);
ALTER TABLE STUDENT_TRANSACTION ADD CONSTRAINT CARD_STRANSACTION FOREIGN KEY (TOKEN_CARD) REFERENCES CARD (TOKEN_CARD);