CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

INSERT INTO brand (public_id, name) VALUES
(uuid_generate_v4(), 'BALMOHK'),
(uuid_generate_v4(), 'ONLY'),
(uuid_generate_v4(), 'NAME IT'),
(uuid_generate_v4(), 'EXIT');

INSERT INTO country (public_id, name) VALUES
(uuid_generate_v4(), 'Denmark'),
(uuid_generate_v4(), 'Sweden'),
(uuid_generate_v4(), 'Norway'),
(uuid_generate_v4(), 'Finland'),
(uuid_generate_v4(), 'Germany'),
(uuid_generate_v4(), 'Netherlands'),
(uuid_generate_v4(), 'United Kingdom'),
(uuid_generate_v4(), 'France'),
(uuid_generate_v4(), 'Italy'),
(uuid_generate_v4(), 'Spain'),
(uuid_generate_v4(), 'United States'),
(uuid_generate_v4(), 'Canada'),
(uuid_generate_v4(), 'Australia'),
(uuid_generate_v4(), 'Portugal'),
(uuid_generate_v4(), 'Belgium'),
(uuid_generate_v4(), 'Switzerland'),
(uuid_generate_v4(), 'Austria'),
(uuid_generate_v4(), 'Ireland'),
(uuid_generate_v4(), 'Uruguay'),
(uuid_generate_v4(), 'Australia');