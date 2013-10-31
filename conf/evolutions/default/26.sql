# T87 Booking entries overview

# --- !Ups

create table BOOKING_ENTRY (
  ID BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  OWNER_ID BIGINT NOT NULL,
  BOOKING_DATE DATE NOT NULL,
  BOOKING_NUMBER INTEGER NOT NULL,
  SUMMARY VARCHAR(254) NOT NULL,
  SOURCE_CURRENCY CHAR(3) NOT NULL,
  SOURCE_AMOUNT DECIMAL(13,3) NOT NULL,
  SOURCE_PERCENTAGE INTEGER NOT NULL,
  FROM_ID BIGINT NOT NULL,
  FROM_CURRENCY CHAR(3) NOT NULL,
  FROM_AMOUNT DECIMAL(13,3) NOT NULL,
  TO_ID BIGINT NOT NULL,
  TO_CURRENCY CHAR(3) NOT NULL,
  TO_AMOUNT DECIMAL(13,3) NOT NULL,
  REFERENCE VARCHAR(254),
  REFERENCE_DATE DATE,
  BRAND_ID BIGINT NOT NULL,
  DESCRIPTION VARCHAR(254),
  URL VARCHAR(254)
);

alter table BOOKING_ENTRY add constraint BOOKING_OWNER_FK foreign key(OWNER_ID) references PERSON(ID) on update NO ACTION on delete NO ACTION;
alter table BOOKING_ENTRY add constraint BOOKING_BRAND_FK foreign key(BRAND_ID) references BRAND(ID) on update NO ACTION on delete NO ACTION;
alter table BOOKING_ENTRY add constraint BOOKING_FROM_FK foreign key(FROM_ID) references ACCOUNT(ID) on update NO ACTION on delete NO ACTION;
alter table BOOKING_ENTRY add constraint BOOKING_TO_FK foreign key(TO_ID) references ACCOUNT(ID) on update NO ACTION on delete NO ACTION;


# --- !Downs

drop table BOOKING_ENTRY;