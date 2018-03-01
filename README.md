# Recruiter

A simple web application to calculate the head hunter fees based on the successful recruitment they do.

1. See a list of registered head hunters

2. Click on a selected head hunter see the list of recruitment they have done for month period and calculate the fee

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
