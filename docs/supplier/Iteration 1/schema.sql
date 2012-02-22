DROP TABLE IF EXISTS patient;
CREATE TABLE patient (
	id				integer				primary key,
	gender			char				not null,
	treatment		text				not null,
	address			text,
	birthdate		date,
	medications		text,
	special_care	text,
	history			text,
	comments		text,
	
	FOREIGN KEY (contact) REFERENCES contact_info(id),
	FOREIGN KEY (emergency_contact) REFERENCES contact_info(id)
);

DROP TABLE IF EXISTS contact_info;
CREATE TABLE contact_info (
	id 				integer				primary key,
	name			text				not null,
	phone_number 	text				not null,
	email			text
);

-- DROP TABLE IF EXISTS bed;
-- CREATE TABLE bed (
-- 	id 				integer				primary key,
-- 	occupied		boolean				not null,
	
-- 	FOREIGN KEY (patient_id) REFERENCES patient(id),
-- 	FOREIGN KEY (room_number) REFERENCES room(number)
-- );

-- DROP TABLE IF EXISTS ward;
-- CREATE TABLE ward (
-- 	name			text				not null
-- );

-- DROP TABLE IF EXISTS nurse;
-- CREATE TABLE nurse (
-- 	name			text				not null,
-- 	phone_number	text				not null,
-- 	is_head			boolean				not null,
	
-- 	FOREIGN KEY (ward_id) REFERENCES nurse(id),
-- 	FOREIGN KEY (nurse_type) REFERENCES nurse_type(type)
-- );

-- DROP TABLE IF EXISTS room;
-- CREATE TABLE room (
-- 	number			integer				not null,
	
-- 	FOREIGN KEY (ward_id) REFERENCES ward(id),
-- 	FOREIGN KEY (nurse_type) REFERENCES nurse_type(type)
-- );

-- DROP TABLE IF EXISTS nurse_type;
-- CREATE TABLE nurse_type (
-- 	type			text				not null
-- );