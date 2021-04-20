use lab9;
create table Users
(
	id int primary key auto_increment,
    user_name nvarchar(20),
    login nvarchar(20),
    user_password bigint,
    user_role nvarchar(10),
    check(user_role in('user','admin'))
);

ALTER TABLE Users
MODIFY COLUMN user_password nvarchar(50);


select * from Users;
insert Users(user_name, login, user_password, user_role)
	values('Sergei','hateGnom', '1234', 'admin');
    
delete from Users;
