CREATE
    EXTENSION IF NOT EXISTS "uuid-ossp";

DROP TABLE IF EXISTS test_table;

CREATE TABLE test_table
(
    id       uuid DEFAULT uuid_generate_v4() NOT NULL,
    name                varchar(50)                     NOT NULL,
    CONSTRAINT "PK_1" PRIMARY KEY (id)
)
;

