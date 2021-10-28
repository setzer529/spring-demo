DROP SEQUENCE IF EXISTS HIBERNATE_SEQUENCE;
DROP TABLE IF EXISTS real_estate;
DROP TABLE IF EXISTS customers;

CREATE SEQUENCE HIBERNATE_SEQUENCE START WITH 1 INCREMENT BY 1;
CREATE TABLE real_estate (
    id     INT AUTO_INCREMENT PRIMARY KEY,
    fname  VARCHAR(250) NOT NULL,
    lname  VARCHAR(250) NOT NULL,
    yrblt  VARCHAR(250) NOT NULL,
    isnew  BOOLEAN      NOT NULL,
    sqft   INT          NOT NULL,
    baths  INT          NOT NULL,
    beds   INT          NOT NULL,
    street VARCHAR(250) NOT NULL,
    city   VARCHAR(250) NOT NULL,
    state  VARCHAR(250) NOT NULL,
    zip    INT          NOT NULL


);

CREATE TABLE customers
(
    id     INT AUTO_INCREMENT PRIMARY KEY,
    fname  VARCHAR(250) NOT NULL,
    lname  VARCHAR(250) NOT NULL,
    email  VARCHAR(250) NOT NULL,
    street VARCHAR(250) NOT NULL,
    city   VARCHAR(250) NOT NULL,
    state  VARCHAR(250) NOT NULL,
    zip    INT          NOT NULL,
    phone  VARCHAR(250) NOT NULL
);



