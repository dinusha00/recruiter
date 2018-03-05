# Recruiter

A simple web application to calculate the head hunter fees based on the successful recruitment they do.

1. See a list of registered head hunters

2. Click on a selected head hunter see the list of recruitment they have done for month period and calculate the fee

## Technologies used
- Spring boot
- H2 database
- JPA/Hibernate
- Logback
- VueJS
- Swagger

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

## SQL (default data)

```
-- job_title
insert into jobtitle(id, name) values(1, 'Mason');
insert into jobtitle(id, name) values(2, 'Carpenter');
insert into jobtitle(id, name) values(3, 'Driver');

-- fee
insert into fee(job_title_id, type, count, amount) values(1, 1, 1, 200); -- successfully recruited mason, the head hunter gets 200$
insert into fee(job_title_id, type, count, amount) values(1, 2, 5, 10); -- additional 10% commission is payed for the head hunter for set of 5 people

insert into fee(job_title_id, type, count, amount) values(2, 1, 1, 250); -- successfully recruited carpenter, the head hunter gets 250$.
insert into fee(job_title_id, type, count, amount) values(2, 1, 5, 10); -- additional 10% commission is payed for the head hunter for set of 5 people

-- headhunter
insert into headhunter(id, name) values(1, 'Wilson');
insert into headhunter(id, name) values(2, 'Simon');
insert into headhunter(id, name) values(3, 'Joan');
insert into headhunter(id, name) values(4, 'Vincent');

-- candidate
insert into candidate(id, name, headhunter_id, job_title_id, recruited) values(1, 'Arnold', 1, 1, true);
insert into candidate(id, name, headhunter_id, job_title_id, recruited) values(2, 'Thomson', 1, 1, false);
insert into candidate(id, name, headhunter_id, job_title_id, recruited) values(3, 'Jane', 1, 1, true);
insert into candidate(id, name, headhunter_id, job_title_id, recruited) values(4, 'Nash', 1, 1, true);
insert into candidate(id, name, headhunter_id, job_title_id, recruited) values(5, 'Alison', 1, 1, false);
insert into candidate(id, name, headhunter_id, job_title_id, recruited) values(6, 'Elizabeth', 1, 2, false);
insert into candidate(id, name, headhunter_id, job_title_id, recruited) values(7, 'Kevin', 1, 2, false);
insert into candidate(id, name, headhunter_id, job_title_id, recruited) values(8, 'John', 1, 2, true);
insert into candidate(id, name, headhunter_id, job_title_id, recruited) values(9, 'Murray', 1, 2, true);
insert into candidate(id, name, headhunter_id, job_title_id, recruited) values(10, 'Isaac', 1, 2, false);
insert into candidate(id, name, headhunter_id, job_title_id, recruited) values(11, 'Trevor', 1, 1, true);
insert into candidate(id, name, headhunter_id, job_title_id, recruited) values(12, 'William', 1, 1, true);
insert into candidate(id, name, headhunter_id, job_title_id, recruited) values(13, 'Alan', 1, 1, true);
insert into candidate(id, name, headhunter_id, job_title_id, recruited) values(14, 'Thomas', 1, 1, false);
insert into candidate(id, name, headhunter_id, job_title_id, recruited) values(15, 'Victoria', 1, 1, false);

insert into candidate(id, name, headhunter_id, job_title_id, recruited) values(16, 'Anthony', 2, 1, true);
insert into candidate(id, name, headhunter_id, job_title_id, recruited) values(17, 'Neil', 2, 1, true);
insert into candidate(id, name, headhunter_id, job_title_id, recruited) values(18, 'Ian', 2, 1, true);
insert into candidate(id, name, headhunter_id, job_title_id, recruited) values(19, 'Andrew', 2, 1, true);
insert into candidate(id, name, headhunter_id, job_title_id, recruited) values(20, 'Leonard', 2, 1, true);
insert into candidate(id, name, headhunter_id, job_title_id, recruited) values(21, 'Adrian', 2, 2, true);
insert into candidate(id, name, headhunter_id, job_title_id, recruited) values(22, 'Mackenzie', 2, 2, true);
insert into candidate(id, name, headhunter_id, job_title_id, recruited) values(23, 'Smith', 2, 2, true);
insert into candidate(id, name, headhunter_id, job_title_id, recruited) values(24, 'Jason', 2, 2, true);
insert into candidate(id, name, headhunter_id, job_title_id, recruited) values(25, 'Sam', 2, 2, true);

```

## URLs

- Application root page http://localhost:8080/recruiter
- H2 in-memory database http://localhost:8080/recruiter/h2 and give 'jdbc:h2:mem:recruiter' as the JDBC url
- Swagger API http://localhost:8080/recruiter/swagger-ui.html
	

## History

Version 0.0.1 : initial release of the component
