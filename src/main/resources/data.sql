-- job_title
insert into job_title(id, name) values(1, 'Mason');
insert into job_title(id, name) values(2, 'Carpenter');

-- headhunter
insert into headhunter(id, name) values(1, 'Royal Challengers Bangalore');
insert into headhunter(id, name) values(2, 'Mumbai Indians');

-- candidate
insert into candidate(id, name, headhunterid, recruited) values(1, 'Virat Kohli', 1, false);
insert into candidate(id, name, headhunterid, recruited) values(2, 'AB de Villiers', 1, false);
insert into candidate(id, name, headhunterid, recruited) values(3, 'Chris Gayle', 1, false);

insert into candidate(id, name, headhunterid, recruited) values(4, 'Rohit Sharma', 2, false);
insert into candidate(id, name, headhunterid, recruited) values(5, 'Lasith Malinga', 2, false);
