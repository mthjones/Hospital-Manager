Entities
========
**Patient**

* Name
* Gender
* Treatment

**Bed**

* State
	
**Ward**

* Name
* Size
* Nursing Type

**Nurse**

* Name
* Type
* Phone Number

**Room**

* Number

Relationships
=============
* Patient (0..1)-< Occupies >-(0..1) Bed
* Bed (n)-< Belongs To >-(1) Room
* Room (n)-< Belongs To >-(1) Ward
* Nurse (n)-< Assigned To >-(1) Ward
