create table if not exists customer (
	id serial primary key,
	name varchar(50) not null,
	email varchar(50) not null,
	cpf bigint not null unique
);

create table if not exists product (
	id serial primary key,
	name varchar(50) not null,
	brand varchar(50) not null,
	value real not null
);

create table if not exists sale (
	id serial primary key,
	customer_fk int not null references customer(id),
	product_fk int not null references product(id),
	date timestamp not null
);
