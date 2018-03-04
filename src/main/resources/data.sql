-- job_title
insert into job_title(id, name) values(1, 'Mason');
insert into job_title(id, name) values(2, 'Carpenter');
insert into job_title(id, name) values(3, 'Driver');

-- fee
insert into fee(jobtitleid, type, count, amount) values(1, 1, 1, 200); -- successfully recruited mason, the head hunter gets 200$
insert into fee(jobtitleid, type, count, amount) values(1, 2, 5, 10); -- additional 10% commission is payed for the head hunter for set of 5 people

insert into fee(jobtitleid, type, count, amount) values(2, 1, 1, 250); -- successfully recruited carpenter, the head hunter gets 250$.
insert into fee(jobtitleid, type, count, amount) values(2, 1, 5, 10); -- additional 10% commission is payed for the head hunter for set of 5 people

-- headhunter
insert into headhunter(id, name) values(1, 'Wilson');
insert into headhunter(id, name) values(2, 'Simon');
insert into headhunter(id, name) values(3, 'Joan');
insert into headhunter(id, name) values(4, 'David');
insert into headhunter(id, name) values(5, 'Vincent');

-- candidate
insert into candidate(id, name, headhunterid, jobtitleid, recruited) values(1, 'Arnold', 1, 1, true);
insert into candidate(id, name, headhunterid, jobtitleid, recruited) values(2, 'Thomson', 1, 1, false);
insert into candidate(id, name, headhunterid, jobtitleid, recruited) values(3, 'Jane', 1, 1, true);
insert into candidate(id, name, headhunterid, jobtitleid, recruited) values(4, 'Nash', 1, 1, true);
insert into candidate(id, name, headhunterid, jobtitleid, recruited) values(5, 'Alison', 1, 1, false);
insert into candidate(id, name, headhunterid, jobtitleid, recruited) values(6, 'Elizabeth', 1, 2, false);
insert into candidate(id, name, headhunterid, jobtitleid, recruited) values(7, 'Kevin', 1, 2, false);
insert into candidate(id, name, headhunterid, jobtitleid, recruited) values(8, 'John', 1, 2, true);
insert into candidate(id, name, headhunterid, jobtitleid, recruited) values(9, 'Murray', 1, 2, true);
insert into candidate(id, name, headhunterid, jobtitleid, recruited) values(10, 'Isaac', 1, 2, false);
insert into candidate(id, name, headhunterid, jobtitleid, recruited) values(11, 'Trevor', 1, 1, true);
insert into candidate(id, name, headhunterid, jobtitleid, recruited) values(12, 'William', 1, 1, true);
insert into candidate(id, name, headhunterid, jobtitleid, recruited) values(13, 'Alan', 1, 1, true);
insert into candidate(id, name, headhunterid, jobtitleid, recruited) values(14, 'Thomas', 1, 1, false);
insert into candidate(id, name, headhunterid, jobtitleid, recruited) values(15, 'Victoria', 1, 1, false);

insert into candidate(id, name, headhunterid, jobtitleid, recruited) values(16, 'Anthony', 2, 2, true);
insert into candidate(id, name, headhunterid, jobtitleid, recruited) values(17, 'Neil', 2, 2, true);
insert into candidate(id, name, headhunterid, jobtitleid, recruited) values(18, 'Ian', 2, 2, true);
insert into candidate(id, name, headhunterid, jobtitleid, recruited) values(19, 'Andrew', 2, 2, false);
insert into candidate(id, name, headhunterid, jobtitleid, recruited) values(20, 'Leonard', 2, 2, false);
insert into candidate(id, name, headhunterid, jobtitleid, recruited) values(21, 'Adrian', 2, 1, true);
insert into candidate(id, name, headhunterid, jobtitleid, recruited) values(22, 'Mackenzie', 2, 1, true);
insert into candidate(id, name, headhunterid, jobtitleid, recruited) values(23, 'Smith', 2, 1, true);
insert into candidate(id, name, headhunterid, jobtitleid, recruited) values(24, 'Jason', 2, 1, false);
insert into candidate(id, name, headhunterid, jobtitleid, recruited) values(25, 'Sam', 2, 1, true);


insert into candidate(id, name, headhunterid, jobtitleid, recruited) values(26, 'Churchill', 3, 1, false);
insert into candidate(id, name, headhunterid, jobtitleid, recruited) values(27, 'Adam', 3, 1, false);
insert into candidate(id, name, headhunterid, jobtitleid, recruited) values(28, 'Claire', 3, 1, true);
