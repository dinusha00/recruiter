# Recruiter

A large scale construction company want to recruit people for a project and they want masons
and carpenters.
They want you to create a simple web application to calculate the head hunter fees based on the
successful recruitment they do.

Head hunters can bring in people as individuals or as a group of 5 members (for sight allocation
reasons) with the same skill group (i.e. either masons or carpenters).

For each successfully recruited mason, the head hunter gets 200$ and for each successfully
recruited carpenter, the head hunter is payed 250$.

For each group recruited (i.e. A set of 5 people from the same skill group) an additional 10%
commission is payed for the head hunter. I.e. If a given head hunter recruited 5 masons (which is
considered a group) he’s payed (5*200 + (5*200)*0.1)

When calculating the fee for head hunters, recruited people should be grouped together
automatically and the commission should be calculated for each group and added to the fee
correctly (Assume price calculation is done always for 1 month period). This is to ensure head

## Technologies used
- Spring boot
- H2 database
- Logback
- VueJS

## Commands

To build the jar

```
mvn clean package
```

To start the server

```
java -jar <PATH>/recruiter-0.0.1.jar
```
 
## Startup parameters

Debug mode : --debug

## Application.properties configuration

N/A

## SQL schema

```
CREATE SCHEMA RECRUITER;
CREATE TABLE RECRUITER.JOB_TITLE(ID INT PRIMARY KEY, NAME VARCHAR(50));
INSERT INTO RECRUITER.JOB_TITLE VALUES(1, 'Mason');
INSERT INTO RECRUITER.JOB_TITLE VALUES(2, 'Carpenter');
```

## History

Version 0.0.1 : initial release of the component
