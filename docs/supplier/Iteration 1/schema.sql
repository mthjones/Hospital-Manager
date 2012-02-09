DROP TABLE IF EXISTS patient;
CREATE TABLE patient (
	id				integer				primary key,
	name			text				not null,
	gender			char				not null,
	treatment		text				not null
);

DROP TABLE IF EXISTS bed;
CREATE TABLE bed (
	id 				integer				primary key,
	occupied		boolean				not null,
	
	FOREIGN KEY (patient_id) REFERENCES patient(id),
	FOREIGN KEY (room_number) REFERENCES room(number)
);

DROP TABLE IF EXISTS ward;
CREATE TABLE ward (
	name			text				not null
);

DROP TABLE IF EXISTS nurse;
CREATE TABLE nurse (
	name			text				not null,
	phone_number	text				not null,
	
	FOREIGN KEY (ward_id) REFERENCES nurse(id),
	FOREIGN KEY (nurse_type) REFERENCES nurse_type(type)
);

DROP TABLE IF EXISTS room;
CREATE TABLE room (
	number			integer				not null,
	
	FOREIGN KEY (ward_id) REFERENCES ward(id),
	FOREIGN KEY (nurse_type) REFERENCES nurse_type(type)
);

DROP TABLE IF EXISTS nurse_type;
CREATE TABLE nurse_type (
	type			text				not null
);