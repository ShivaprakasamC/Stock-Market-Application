CREATE TABLE IF NOT EXISTS credential_details (
 user_id SERIAL PRIMARY KEY,
 login_name VARCHAR (50) UNIQUE NOT NULL,
 login_password VARCHAR (50) NOT NULL,
 created_by VARCHAR (50),
 updated_by VARCHAR (50),
 created_timestamp TIMESTAMP,
 updated_timestamp TIMESTAMP
)