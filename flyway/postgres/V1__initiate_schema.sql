CREATE TYPE role AS ENUM ('USER', 'ADMIN');
CREATE TYPE status AS ENUM ('ACTIVE', 'INACTIVE');


CREATE TABLE mulo.user
(
    user_id       uuid   NOT NULL DEFAULT uuid_generate_v4(),
    first_name    text,
    last_name     text,
    email         text,
    password      text,
    phone_number  text,
    date_of_birth text,
    role          role,
    status        status NOT NULL DEFAULT 'ACTIVE',
    created_by    text   NOT NULL,
    updated_by    text,
    created_date  timestamp WITHOUT TIME ZONE DEFAULT NOW(),
    updated_date  timestamp WITHOUT TIME ZONE DEFAULT NOW()
);
