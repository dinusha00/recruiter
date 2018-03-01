# Recruiter

Recruitement portal to manage the recruitments done via headhunters and calculate the commission for each headhunder. This uses springboot.

## To start the server

 java -jar <PATH>/recruiter-0.0.1.jar

# Startup parameters

Debug mode : --debug

# Application.properties configuration

N/A

# SQL schema

```
CREATE SCHEMA RECRUITER;
CREATE TABLE RECRUITER.JOB_TITLE(ID INT PRIMARY KEY, NAME VARCHAR(50));
INSERT INTO RECRUITER.JOB_TITLE VALUES(1, 'Mason');
INSERT INTO RECRUITER.JOB_TITLE VALUES(2, 'Carpenter');
```

# History

Version 0.0.1 : initial release of the component
