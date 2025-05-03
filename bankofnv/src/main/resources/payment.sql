-- Step 1: Create a sequence
CREATE SEQUENCE user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

-- Step 2: Create the table using the sequence
CREATE TABLE users (
    id INTEGER PRIMARY KEY DEFAULT nextval('user_id_seq'),
    name VARCHAR(100),
    email VARCHAR(100),
    phone VARCHAR(15),
    password VARCHAR(255)
);

--------------------------------------------

CREATE SEQUENCE account_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
    
CREATE TABLE accounts (
    id INTEGER PRIMARY KEY DEFAULT nextval('account_id_seq'),
    user_id INTEGER,
    account_number VARCHAR(20),
    balance NUMERIC(10, 2),
    creation_date DATE,
    CONSTRAINT fk_user FOREIGN KEY(user_id) REFERENCES users(id)
);