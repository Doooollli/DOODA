
CREATE DATABASE DOODA_Database;

use DOODA_Database;

CREATE TABLE tbl_data
(
	epoch                CHAR(20) NOT NULL,
	sv_clock_drift_rate  VARCHAR(20) NULL,
	sv_clock_bias        VARCHAR(20) NULL,
	sv_clock_drift       VARCHAR(20) NULL,
	iode                 VARCHAR(20) NULL,
	crs                  VARCHAR(20) NULL,
	delta_n              VARCHAR(20) NULL,
	mo                   VARCHAR(20) NULL,
	cuc                  VARCHAR(20) NULL,
	eccentricity         VARCHAR(20) NULL,
	cus                  VARCHAR(20) NULL,
	sqrt_a               VARCHAR(20) NULL,
	toe                  VARCHAR(20) NULL,
	cic                  VARCHAR(20) NULL,
	big_omega            VARCHAR(20) NULL,
	cis                  VARCHAR(20) NULL,
	io                   VARCHAR(20) NULL,
	crc                  VARCHAR(20) NULL,
	little_omega         VARCHAR(20) NULL,
	omega_dot            VARCHAR(20) NULL,
	idot                 VARCHAR(20) NULL,
	12_code_channel      VARCHAR(20) NULL,
	gps_week             VARCHAR(20) NULL,
	12_p_data_flag       VARCHAR(20) NULL,
	sv_accuracy          VARCHAR(20) NULL,
	sv_health            VARCHAR(20) NULL,
	tgd                  VARCHAR(20) NULL,
	iodc                 VARCHAR(20) NULL,
	transmission_time    VARCHAR(20) NULL,
	interval1            VARCHAR(20) NULL,
	date                 TIME NOT NULL,
	satellite_prn_number INTEGER NOT NULL
);

ALTER TABLE tbl_data
ADD CONSTRAINT XPKtbl_data PRIMARY KEY (epoch,date,satellite_prn_number);

CREATE TABLE tbl_header
(
	date                 TIME NOT NULL,
	version              VARCHAR(20) NULL,
	ion_beta             DOUBLE PRECISION NULL,
	a0_polynomial_term   DOUBLE PRECISION NULL,
	ion_alpha            DOUBLE PRECISION NULL,
	a1_polynomial_term   DOUBLE PRECISION NULL,
	reference_time       INTEGER NULL,
	week_number          INTEGER NULL,
	leap_seconds         INTEGER NULL
);

ALTER TABLE tbl_header
ADD CONSTRAINT XPKtbl_header PRIMARY KEY (date);

ALTER TABLE tbl_data
ADD CONSTRAINT R_2 FOREIGN KEY (date) REFERENCES tbl_header (date);
