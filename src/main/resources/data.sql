-- job_title
insert into job_title(id, name) values(1, 'Mason');
insert into job_title(id, name) values(2, 'Carpenter');

-- headhunter
insert into headhunter(id, name) values(1, 'James');
insert into headhunter(id, name) values(2, 'Mark');

-- candidate
insert into candidate(id, name, headhunterid, recruited) values(1, 'Virat', 1, false);
insert into candidate(id, name, headhunterid, recruited) values(2, 'Rohith', 1, false);
