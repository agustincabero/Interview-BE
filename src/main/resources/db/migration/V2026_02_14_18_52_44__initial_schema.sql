-- Create brand table
CREATE TABLE brand (
                       id BIGSERIAL PRIMARY KEY,
                       public_id UUID NOT NULL UNIQUE,
                       created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       updated TIMESTAMP,
                       deleted BOOLEAN NOT NULL DEFAULT FALSE,
                       name VARCHAR(255),
                       version BIGINT NOT NULL DEFAULT 0
);

CREATE INDEX idx_brand_public_id ON brand(public_id);
CREATE INDEX idx_brand_deleted ON brand(deleted);
CREATE INDEX idx_brand_name ON brand(name);

-- Create country table
CREATE TABLE country (
                         id BIGSERIAL PRIMARY KEY,
                         public_id UUID NOT NULL UNIQUE,
                         created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         updated TIMESTAMP,
                         deleted BOOLEAN NOT NULL DEFAULT FALSE,
                         name VARCHAR(255),
                         version BIGINT NOT NULL DEFAULT 0
);

CREATE INDEX idx_country_public_id ON country(public_id);
CREATE INDEX idx_country_deleted ON country(deleted);
CREATE INDEX idx_country_name ON country(name);

-- Create market_group table
CREATE TABLE market_group (
                              id BIGSERIAL PRIMARY KEY,
                              public_id UUID NOT NULL UNIQUE,
                              created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              updated TIMESTAMP,
                              deleted BOOLEAN NOT NULL DEFAULT FALSE,
                              name VARCHAR(255),
                              version BIGINT NOT NULL DEFAULT 0,
                              brand_public_id UUID
);

CREATE INDEX idx_market_group_public_id ON market_group(public_id);
CREATE INDEX idx_market_group_deleted ON market_group(deleted);
CREATE INDEX idx_market_group_name ON market_group(name);
CREATE INDEX idx_market_group_brand_public_id ON market_group(brand_public_id);

-- Create market table
CREATE TABLE market (
                        id BIGSERIAL PRIMARY KEY,
                        public_id UUID NOT NULL UNIQUE,
                        created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        updated TIMESTAMP,
                        deleted BOOLEAN NOT NULL DEFAULT FALSE,
                        name VARCHAR(255),
                        version BIGINT NOT NULL DEFAULT 0,
                        brand_public_id UUID,
                        market_group_public_id UUID,
                        country_public_id UUID
);

CREATE INDEX idx_market_public_id ON market(public_id);
CREATE INDEX idx_market_deleted ON market(deleted);
CREATE INDEX idx_market_name ON market(name);
CREATE INDEX idx_market_brand_public_id ON market(brand_public_id);
CREATE INDEX idx_market_market_group_id ON market(market_group_public_id);
