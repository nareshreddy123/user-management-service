drop table if exists user_details;
drop table if exists address_details;


create table address_details(
  address_id int auto_increment primary key,
  street varchar not null,
  city varchar not null,
  state varchar not null,
  postcode Long not null
);


create table user_details(
user_id Long primary key,
title varchar not null,
first_name varchar not null,
last_name varchar not null,
email_id varchar not null,
gender varchar not null,
address_id int not null,
created_date date,
modified_date date,
foreign key (address_id) references address_details(address_id)
);


insert into address_details(street,city,state,postcode) values ('123 holling Rd','Sydney','NSW',2000);
insert into address_details(street,city,state,postcode) values ('123 Rowling Rd','Sydney','NSW',2000);
insert into address_details(street,city,state,postcode) values ('145 Rowling Rd','Sydney','NSW',2000);
insert into address_details(street,city,state,postcode) values ('160 Rowling Rd','Sydney','NSW',2000);
insert into address_details(street,city,state,postcode) values ('161 Rowling Rd','Sydney','NSW',2000);
insert into address_details(street,city,state,postcode) values ('162 Rowling Rd','Sydney','NSW',2000);


insert into user_details(user_id,title,first_name,last_name,email_id,gender,address_id,created_date,modified_date) values(345626,'Mr','Peter','Parker','Peter.Parker@org.com.au','Male',select address_id from address_details where street='123 holling Rd' and city='Sydney' and state='NSW' and postcode='2000',sysdate,sysdate);
insert into user_details(user_id,title,first_name,last_name,email_id,gender,address_id,created_date,modified_date) values(345627,'Mrs','Mary','Parker','Mary.Parker@org.com.au','Female',select address_id from address_details where street='123 Rowling Rd' and city='Sydney' and state='NSW' and postcode='2000',sysdate,sysdate);
insert into user_details(user_id,title,first_name,last_name,email_id,gender,address_id,created_date,modified_date) values(345628,'Mx','XYZ','ABC','XYZ.ABC@@org.com.au','Transgender',select address_id from address_details where street='145 Rowling Rd' and city='Sydney' and state='NSW' and postcode='2000',sysdate,sysdate);
insert into user_details(user_id,title,first_name,last_name,email_id,gender,address_id,created_date,modified_date) values(345629,'Dr','Abraham','Louis','Abraham.Louis@@org.com.au','Male',select address_id from address_details where street='160 Rowling Rd' and city='Sydney' and state='NSW' and postcode='2000',sysdate,sysdate);
insert into user_details(user_id,title,first_name,last_name,email_id,gender,address_id,created_date,modified_date) values(345630,'Mr','Simon','Baker','Simon.Baker@org.com.au','Male',select address_id from address_details where street='161 Rowling Rd' and city='Sydney' and state='NSW' and postcode='2000',sysdate,sysdate);
insert into user_details(user_id,title,first_name,last_name,email_id,gender,address_id,created_date,modified_date) values(345631,'Mr','Peter','Byron','Peter.Byron@org.com.au','Male',select address_id from address_details where street='162 Rowling Rd' and city='Sydney' and state='NSW' and postcode='2000',sysdate,sysdate);

