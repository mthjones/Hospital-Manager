Entities
========
**Patient**

* Name
* Gender
* Treatment

**Bed**

* 
	
**Ward**

* Name
* Size

**Nurse**

* Name
* Phone Number
* Head Nurse?

**Room**

* Number

**Nurse Type**

* Name

Relationships
=============
	Patient (1)-<    Occupies	>-(1) Bed
	Bed 	(n)-<   Belongs To 	>-(1) Room
	Room 	(n)-<   Belongs To 	>-(1) Ward
	Nurse 	(n)-<  Assigned To 	>-(1) Ward
	Nurse 	(n)-< 	  Has A		>-(1) Nurse Type
	Ward 	(n)-< 	  Has A		>-(1) Nurse Type