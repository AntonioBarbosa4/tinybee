CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE link (
  id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
  original_url VARCHAR(2048) NOT NULL,
  shortened_url VARCHAR(100) NOT NULL UNIQUE,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  is_active BOOLEAN NOT NULL DEFAULT true
);


CREATE INDEX idx_link_shortened_url ON link(shortened_url);


CREATE INDEX idx_link_is_active ON link(is_active);


CREATE INDEX idx_link_active_created ON link(is_active, created_at);