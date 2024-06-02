CREATE TABLE if not exists public.deal (
    id bigserial NOT NULL ,
    uuid VARCHAR(255) NOT NULL ,
    from_currency_iso_code VARCHAR(255) NOT NULL,
    to_currency_iso_code VARCHAR(255) NOT NULL,
    deal_time_stamp TIMESTAMP(6) NOT NULL,
    amount DECIMAL(19, 4) NOT NULL,
    version int8 NOT NULL,
    creation_date TIMESTAMP(6) NOT NULL,
    update_date TIMESTAMP(6),
    CONSTRAINT deal_pk PRIMARY KEY (id)
);
CREATE UNIQUE INDEX IF NOT exists deal_uuid_idx ON public.deal (uuid);

CREATE TABLE lkp_currency(
  id              bigserial  NOT NULL ,
  country         VARCHAR(255) NOT NULL,
  currency        VARCHAR(255) NOT NULL,
  alphabetic_code VARCHAR(3),
  numeric_code    int8,
  CONSTRAINT lkp_currency_pk PRIMARY KEY (id)
);
CREATE INDEX IF NOT exists lkp_currency_idx ON public.lkp_currency (id);
