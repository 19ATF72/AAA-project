CREATE TABLE APPOINTMENT_SLOTS ( 
	ASLID                integer NOT NULL  GENERATED ALWAYS AS IDENTITY,
	START_TIME           integer NOT NULL  ,
	END_TIME             integer NOT NULL  ,
	CONSTRAINT PK_APPOINTMENT_SLOTS_ASLID PRIMARY KEY ( ASLID )
 );

CREATE TABLE APPOINTMENT_STATUS ( 
	ASID                 integer NOT NULL  GENERATED ALWAYS AS IDENTITY,
	APPOINTMENT_STATUS   varchar(45)   ,
	CONSTRAINT PK_APPOINTMENT_STATUS PRIMARY KEY ( ASID )
 );

CREATE TABLE APPOINTMENT_TYPE ( 
	ATID                 integer NOT NULL  GENERATED ALWAYS AS IDENTITY,
	TYPE_NAME            varchar(45) NOT NULL  ,
	CONSTRAINT PRIMARY1 PRIMARY KEY ( ATID )
 );

CREATE TABLE ORGANISATION_TYPE ( 
	OTID                 integer NOT NULL  GENERATED ALWAYS AS IDENTITY,
	TYPE_NAME            varchar(45) NOT NULL  ,
	CONSTRAINT PRIMARY3 PRIMARY KEY ( OTID )
 );

CREATE TABLE PATIENT_TYPE ( 
	PTID                 integer NOT NULL  GENERATED ALWAYS AS IDENTITY,
	TYPE_NAME            varchar(45) NOT NULL  ,
	CONSTRAINT PRIMARY8 PRIMARY KEY ( PTID )
 );

CREATE TABLE USER_STATUS ( 
	USID                 integer NOT NULL  GENERATED ALWAYS AS IDENTITY,
	USER_STATUS          varchar(45) NOT NULL  ,
	CONSTRAINT PRIMARY5 PRIMARY KEY ( USID )
 );

CREATE TABLE ORGANISATION ( 
	OID                  integer NOT NULL  GENERATED ALWAYS AS IDENTITY,
	NAME                 varchar(45) NOT NULL  ,
	ORGANISATION_TYPE_OID integer NOT NULL  ,
	ADDRESS              varchar(45) NOT NULL  ,
	POSTCODE             varchar(45) NOT NULL  ,
	PHONE_NUMBER         varchar(45) NOT NULL  ,
	CONSTRAINT PRIMARY4 PRIMARY KEY ( OID )
 );

CREATE INDEX SQL201212165647341 ON ORGANISATION ( ORGANISATION_TYPE_OID );


CREATE TABLE USERS ( 
	UUID                 integer NOT NULL  GENERATED ALWAYS AS IDENTITY,
	USERPREFIX 	     	varchar(6) NOT NULL  ,
    USERFIRSTNAME        varchar(40) NOT NULL  ,
	USERSURNAME	     	varchar(40) NOT NULL  ,
	PASS                 varchar(40) NOT NULL  ,
	EMAIL                varchar(40) NOT NULL  ,
	DOB			         date NOT NULL  ,
	CREATED              date NOT NULL  ,
	LAST_ACCESS          date NOT NULL  ,
	LOGGED_IN            integer NOT NULL  ,
	USERTYPE 			 varchar(45) NOT NULL,
	PHONE_NUMBER 	     varchar(45),
	USER_STATUS_USID     integer NOT NULL  ,
	CONSTRAINT PRIMARY6 PRIMARY KEY ( UUID )
 );
 
 

CREATE INDEX SQL201212165647371 ON USERS ( USER_STATUS_USID );

CREATE TABLE WATCHDOG ( 
	WID                  integer NOT NULL  GENERATED ALWAYS AS IDENTITY,
	USERS_UID            integer NOT NULL  ,
	TYPE                 varchar(64) NOT NULL  ,
	MESSAGE              clob(2147483647) NOT NULL  ,
	TIMESTAMP            integer NOT NULL  ,
	CONSTRAINT PRIMARY13 PRIMARY KEY ( WID )
 );

CREATE INDEX SQL201212165647380 ON WATCHDOG ( USERS_UID );

CREATE TABLE EMPLOYEE ( 
	EID                  integer NOT NULL  GENERATED ALWAYS AS IDENTITY,
	SALARY               double NOT NULL  ,
	ADDRESS              varchar(40) NOT NULL  ,
	POSTCODE		     varchar(10) NOT NULL  ,
	ORGANISATION_OID     integer NOT NULL  ,
	USERS_UUID           integer NOT NULL  ,
	CONSTRAINT PRIMARY7 PRIMARY KEY ( EID )
 );

CREATE INDEX SQL201212165647290 ON EMPLOYEE ( EMPLOYEE_TYPE_TID );

CREATE INDEX SQL201212165647310 ON EMPLOYEE ( ORGANISATION_OID );

CREATE INDEX SQL201212165647320 ON EMPLOYEE ( USERS_UUID );

CREATE TABLE EMPLOYEE_HAS_APPOINTMENT_SLOTS ( 
	APPOINTMENT_SLOTS_ASLID integer NOT NULL  ,
	EMPLOYEE_EID         integer NOT NULL  ,
	DATE                 date NOT NULL DEFAULT CURRENT_DATE ,
	CONSTRAINT IDX_EMPLOYEE_HAS_APPOINTMENT_SLOTS_DATE UNIQUE ( DATE ) 
 );

CREATE INDEX IDX_EMPLOYEE_HAS_APPOINTMENT_SLOTS_EMPLOYEE1 ON EMPLOYEE_HAS_APPOINTMENT_SLOTS ( EMPLOYEE_EID );

CREATE INDEX IDX_EMPLOYEE_HAS_APPOINTMENT_SLOTS_APPOINTMENT_SLOTS ON EMPLOYEE_HAS_APPOINTMENT_SLOTS ( APPOINTMENT_SLOTS_ASLID );

CREATE TABLE PATIENT ( 
	PID                  integer NOT NULL  GENERATED ALWAYS AS IDENTITY,
	ADDRESS              varchar(45) NOT NULL  ,
	POSTCODE		     varchar(10) NOT NULL  ,
	PATIENT_TYPE_PTID    integer NOT NULL  ,
	USERS_UUID           integer NOT NULL  ,
	CONSTRAINT PRIMARY9 PRIMARY KEY ( PID )
 );

CREATE INDEX SQL201212165647360 ON PATIENT ( USERS_UUID );

CREATE TABLE PATIENT_PRESCRIPTIONS ( 
	PRID                 integer NOT NULL  GENERATED ALWAYS AS IDENTITY,
	PATIENT_PID          integer NOT NULL  ,
	MEDICINE             varchar(45) NOT NULL  ,
	REPEAT               boolean DEFAULT FALSE,
	CONSTRAINT PRIMARY10 PRIMARY KEY ( PRID )
 );

CREATE INDEX SQL201212165647370 ON PATIENT_PRESCRIPTIONS ( PATIENT_PID );

CREATE TABLE APPOINTMENT ( 
	AID                  integer NOT NULL  GENERATED ALWAYS AS IDENTITY,
	DURATION             integer NOT NULL  ,
	NOTES                varchar(400) DEFAULT ' ',
	CHARGE               double NOT NULL  ,
	DATE                 date NOT NULL DEFAULT CURRENT_DATE ,
	DATE_PAID            date DEFAULT '1970-01-02',
	START_TIME           integer   ,
	END_TIME             integer   ,
	PATIENT_PID          integer   ,
	EMPLOYEE_EID         integer NOT NULL  ,
	APPOINTMENT_TYPE_ATID integer NOT NULL  ,
	PATIENT_PRESCRIPTIONS_PRID integer ,
	APPOINTMENT_STATUS_ASID integer NOT NULL  ,
	CONSTRAINT PRIMARY11 PRIMARY KEY ( AID )
 );

CREATE INDEX SQL201212165647240 ON APPOINTMENT ( APPOINTMENT_STATUS_ASID );

CREATE INDEX SQL201212165647250 ON APPOINTMENT ( APPOINTMENT_TYPE_ATID );

CREATE INDEX SQL201212165647260 ON APPOINTMENT ( EMPLOYEE_EID );

CREATE INDEX SQL201212165647270 ON APPOINTMENT ( PATIENT_PID );

CREATE INDEX SQL201212165647280 ON APPOINTMENT ( PATIENT_PRESCRIPTIONS_PRID );

ALTER TABLE APPOINTMENT ADD CONSTRAINT FK_APPOINTMENT_APPOINTMENT_STATUS1 FOREIGN KEY ( APPOINTMENT_STATUS_ASID ) REFERENCES APPOINTMENT_STATUS( ASID ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE APPOINTMENT ADD CONSTRAINT FK_APPOINTMENT_APPOINTMENT_TYPE1 FOREIGN KEY ( APPOINTMENT_TYPE_ATID ) REFERENCES APPOINTMENT_TYPE( ATID ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE APPOINTMENT ADD CONSTRAINT FK_APPOINTMENT_EMPLOYEE1 FOREIGN KEY ( EMPLOYEE_EID ) REFERENCES EMPLOYEE( EID ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE APPOINTMENT ADD CONSTRAINT FK_APPOINTMENT_PATIENT1 FOREIGN KEY ( PATIENT_PID ) REFERENCES PATIENT( PID ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE APPOINTMENT ADD CONSTRAINT FK_APPOINTMENT_PATIENT_PRESCRIPTIONS1 FOREIGN KEY ( PATIENT_PRESCRIPTIONS_PRID ) REFERENCES PATIENT_PRESCRIPTIONS( PRID ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE EMPLOYEE ADD CONSTRAINT FK_EMPLOYEE_ORGANISATION1 FOREIGN KEY ( ORGANISATION_OID ) REFERENCES ORGANISATION( OID ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE EMPLOYEE ADD CONSTRAINT FK_EMPLOYEE_USERS1 FOREIGN KEY ( USERS_UUID ) REFERENCES USERS( UUID ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE EMPLOYEE_HAS_APPOINTMENT_SLOTS ADD CONSTRAINT FK_EMPLOYEE_HAS_APPOINTMENT_SLOTS_EMPLOYEE1 FOREIGN KEY ( EMPLOYEE_EID ) REFERENCES EMPLOYEE( EID ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE EMPLOYEE_HAS_APPOINTMENT_SLOTS ADD CONSTRAINT FK_EMPLOYEE_HAS_APPOINTMENT_SLOTS_APPOINTMENT_SLOTS1 FOREIGN KEY ( APPOINTMENT_SLOTS_ASLID ) REFERENCES APPOINTMENT_SLOTS( ASLID ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE ORGANISATION ADD CONSTRAINT FK_ORGANISATION_ORGANISATION_TYPE1 FOREIGN KEY ( ORGANISATION_TYPE_OID ) REFERENCES ORGANISATION_TYPE( OTID ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE PATIENT ADD CONSTRAINT FK_PATIENT_PATIENT_TYPE1 FOREIGN KEY ( PATIENT_TYPE_PTID ) REFERENCES PATIENT_TYPE( PTID ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE PATIENT ADD CONSTRAINT FK_PATIENT_USERS1 FOREIGN KEY ( USERS_UUID ) REFERENCES USERS( UUID ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE PATIENT_PRESCRIPTIONS ADD CONSTRAINT FK_PATIENT_RECORDS_PATIENT1 FOREIGN KEY ( PATIENT_PID ) REFERENCES PATIENT( PID ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE USERS ADD CONSTRAINT FK_USERS_USER_STATUS1 FOREIGN KEY ( USER_STATUS_USID ) REFERENCES USER_STATUS( USID ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE WATCHDOG ADD CONSTRAINT FK_WATCHDOG_USERS1 FOREIGN KEY ( USERS_UID ) REFERENCES USERS( UUID ) ON DELETE NO ACTION ON UPDATE NO ACTION;


