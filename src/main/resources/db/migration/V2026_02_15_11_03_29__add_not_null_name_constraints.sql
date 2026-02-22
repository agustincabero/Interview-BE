-- Add NOT NULL constraint to name column in brand table
ALTER TABLE brand ALTER COLUMN name SET NOT NULL;

-- Add NOT NULL constraint to name column in country table
ALTER TABLE country ALTER COLUMN name SET NOT NULL;

-- Add NOT NULL constraint to name column in market_group table
ALTER TABLE market_group ALTER COLUMN name SET NOT NULL;

-- Add NOT NULL constraint to name column in market table
ALTER TABLE market ALTER COLUMN name SET NOT NULL;
