CREATE TABLE power_stats
(
    id           UUID PRIMARY KEY NOT NULL,
    strength     SMALLINT         NOT NULL,
    agility      SMALLINT         NOT NULL,
    dexterity    SMALLINT         NOT NULL,
    intelligence SMALLINT         NOT NULL,
    created_at   TIMESTAMPTZ      NOT NULL DEFAULT now(),
    updated_at   TIMESTAMPTZ      NOT NULL DEFAULT now()
);
