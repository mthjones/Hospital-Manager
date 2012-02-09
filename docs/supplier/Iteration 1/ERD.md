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

**Nurse**

* Name
* Phone Number

**Room**

* Number

**Nurse Type**

* Name

Relationships
=============
* Patient (0..1)-< Occupies >-(0..1) Bed
* Bed (n)-< Belongs To >-(1) Room
* Room (n)-< Belongs To >-(1) Ward
* Nurse (n)-< Assigned To >-(1) Ward
* Nurse (n)-< Has A >-(1) Nurse Type
* Ward (n)-< Has A >-(1) Nurse Type