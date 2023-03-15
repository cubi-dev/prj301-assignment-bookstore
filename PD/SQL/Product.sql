CREATE TABLE Product(
	sku varchar(10) primary key, 
	name varchar(50), 
	description nvarchar(250), 
	quantity int, 
	price float, 
	status bit
)


insert into Product values ('se160223', 'duabo', N'Xuất xứ từ VN', 1, 5000, 1);
insert into Product values ('se160224', 'comrang', N'Xuất xứ từ VN', 1, 50000, 1);


CREATE TABLE Orders(
	id int identity(1,1) primary key,
	dateBuy datetime,
	total float,
)

CREATE TABLE OrderDetail(
	id int primary key identity (1,1), 
	sku varchar(10), 
	orderID int,
	quantity int, 
	price float, 
	total float,
	foreign key (orderID) references Orders
)