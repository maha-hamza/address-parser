# Address Parser

Usually it's Expected to receive unorganized address format provided by Users through Third party provider or even User Input.

So we it's a need to convert the address to a readable and usable format  that can be used further in processing

**v1:**  is to convert the address as String to a separated two fields street and house number ;

---
#### Processing Requirements
**Input:** string of address

**Output:** string of street and string of street-number as JSON object

---
#### Covered Cases in V1

###### Simple Cases

* "Winterallee 3" -> {"street": "Winterallee", "housenumber": "3"}
* "Musterstrasse 45" -> {"street": "Musterstrasse", "housenumber": "45"}
* "Blaufeldweg 123B" -> {"street": "Blaufeldweg", "housenumber": "123B"}
* "KüstrinerStraße 30B" -(special character)-> {"street": "KüstrinerStraße", "housenumber": "30B"}
* "Blaufeldweg 123ä" -(special character)-> {"street": "Blaufeldweg", "housenumber": "123ä"}
* "Blaufeldweg 123 ä" -(special character)-> {"street": "Blaufeldweg", "housenumber": "123 ä"}

###### complicated cases

* "Am Bächle 23" -> {"street": "Am Bächle", "housenumber": "23"}
* "Auf der Vogelwiese 23 b" -> {"street": "Auf der Vogelwiese", "housenumber": "23 b"}

###### Consider other countries

* "4, rue de la revolution" -> {"street": "rue de la revolution", "housenumber": "4"}
* "4. rue de la revolution" -> {"street": "rue de la revolution", "housenumber": "4"}
* "200 Broadway Av" -> {"street": "Broadway Av", "housenumber": "200"}
* "Calle Aduana, 29" -> {"street": "Calle Aduana", "housenumber": "29"}
* "Calle 39 No 1540" -> {"street": "Calle 39", "housenumber": "No 1540"}
* "Calle 39 Num 1540" -> {"street": "Calle 39", "housenumber": "No 1540"}
* "Calle 39 Number 1540" -> {"street": "Calle 39", "housenumber": "No 1540"}

--- 
#### Built With
* Gradle 
* SpringBoot
* JUnit 5
---
###### Run Tests 
./gradlew clean test

###### Run the application
./gradlew bootRun