create table product (id bigint generated by default as identity, current_price decimal(19,2) not null, last_updated timestamp, name varchar(255) not null, primary key (id));

insert into product(id, current_price, last_updated, name) values (1, 722001, '2020-03-04', 'Iphone11');
insert into product(id, current_price, last_updated, name) values (2, 10000, '2020-03-04', 'Iphone22');