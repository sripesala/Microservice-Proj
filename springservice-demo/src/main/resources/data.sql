DROP TABLE transaction IF EXISTS;
create table transaction(
	transaction_id INT,
	customer VARCHAR(20),
	amount DECIMAL(10,2),
	timestamp TIMESTAMP
);

insert into transaction values(112121,'aa',155.60,CURRENT_TIMESTAMP);
insert into transaction values(213131,'bb',2324.60,CURRENT_TIMESTAMP);
insert into transaction values(313131,'cc',3241.60,CURRENT_TIMESTAMP);