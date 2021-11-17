Insert into STORE_DETAILS (ID, CREATED_AT, NAME, STATUS, UPDATED_AT)   VALUES (1001, TO_DATE('17/12/2015', 'DD/MM/YYYY'), 'Albert Heijn - store1', 'active', CURRENT_TIMESTAMP)
Insert into STORE_DETAILS (ID, CREATED_AT, NAME, STATUS, UPDATED_AT)   VALUES (1002, TO_DATE('17/12/2016', 'DD/MM/YYYY'), 'Albert Heijn - store2', 'active', CURRENT_TIMESTAMP)
Insert into STORE_DETAILS (ID, CREATED_AT, NAME, STATUS, UPDATED_AT)   VALUES (1003, TO_DATE('17/12/2017', 'DD/MM/YYYY'), 'Albert Heijn - store3', 'active', CURRENT_TIMESTAMP)
Insert into STORE_ADDRESS (STREET, HOUSE_NUMBER, HOUSE_NUMBER_SUFFIX, POSTAL_CODE, CITY, COUNTRY, ID) VALUES('kings street', 234, 'KS 001','84762','Koningsstraat', 'Netherlands', 2001)
Insert into STORE_ADDRESS (STREET, HOUSE_NUMBER, HOUSE_NUMBER_SUFFIX, POSTAL_CODE, CITY, COUNTRY, ID) VALUES('Mol street', 334, 'MS 001','94762','Molstraat', 'Netherlands', 2002)
Insert into STORE_ADDRESS (STREET, HOUSE_NUMBER, HOUSE_NUMBER_SUFFIX, POSTAL_CODE, CITY, COUNTRY, ID) VALUES('Palace street', 434, 'PS 001','95762','Paleisstraat', 'Netherlands', 2003)

INSERT INTO  ADDRESS_PERIOD (ID, DATE_VALID_FROM, DATE_VALID_UNTIL, STORE_ADDRESS_ID) VALUES(3001, TO_DATE('17/12/2015', 'DD/MM/YYYY'), TO_DATE('17/12/2025', 'DD/MM/YYYY'), 2001)
INSERT INTO  ADDRESS_PERIOD (ID, DATE_VALID_FROM, DATE_VALID_UNTIL, STORE_ADDRESS_ID) VALUES(3002, TO_DATE('17/12/2016', 'DD/MM/YYYY'), TO_DATE('17/12/2025', 'DD/MM/YYYY'), 2002)
INSERT INTO  ADDRESS_PERIOD (ID, DATE_VALID_FROM, DATE_VALID_UNTIL, STORE_ADDRESS_ID) VALUES(3003, TO_DATE('17/12/2017', 'DD/MM/YYYY'), TO_DATE('17/12/2025', 'DD/MM/YYYY'), 2003)

Insert into STORE_DETAILS_ADDRESS_PERIOD (STORE_DETAILS_ID, ADDRESS_PERIOD_ID) VALUES (1001, 3001)
Insert into STORE_DETAILS_ADDRESS_PERIOD (STORE_DETAILS_ID, ADDRESS_PERIOD_ID) VALUES (1002, 3002)
Insert into STORE_DETAILS_ADDRESS_PERIOD (STORE_DETAILS_ID, ADDRESS_PERIOD_ID) VALUES (1003, 3003)