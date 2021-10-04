use
MusicStore;
go

insert into Department(dep_id, name)
values (1, 'Guitars and Picks'),
       (2, 'Keyboards, drums and sticks'),
       (3, 'Cables, strings and pedals')

	   
insert into Employee (emp_id, first_name, last_name, dep_id)
values (1, 'Andreea', 'Pop', 1),
       (2, 'Cristian', 'Avram', 1),
       (3, 'Andrei', 'Savu', 2),
       (4, 'Marius', 'Petre', 2),
       (5, 'Costin', 'Petrus', 3)


insert into MusicInstrumentType(mit_id, name)
values (1, 'Guitar'),
       (2, 'Keyboard'),
       (3, 'Drums')

insert into MusicInstrument(mi_id, maker, model, price, type)
values (1, 'Gibson', 'Les Paul', 2499.99, 1),
       (2, 'Fender', 'Telecaster', 1258.5, 1),
       (3, 'Gibson', 'SG', 1999.99, 1),
       (4, 'Korg', 'EK-50', 570.00, 2),
       (5, 'Korg', 'Nautilus', 320.99, 2),
       (6, 'Yamaha', 'PSR', 1399.99, 2),
       (7, 'Pearl', 'Classic', 3000.00, 3),
       (8, 'Tama', 'SoCal', 4586.00, 3)

insert into AccessoryType(act_id, name)
values (1, 'Strings'),
       (2, 'Plectrum'),
       (3, 'Cable'),
       (4, 'Sticks'),
       (5, 'Pedal')

insert into Accessory(acc_id, maker, price, type)
values (1, 'Dunlop', 15, 1),
       (2, 'Ernie ball', 16.5, 1),
       (3, 'Daddario', 13, 1),
       (4, 'Dunlop', 3, 2),
       (5, 'FireStone', 2.5, 2),
       (6, 'Boss', 59.99, 3),
       (7, 'Thomann', 35.99, 3),
       (8, 'Tama', 80, 4),
       (9, 'Thomann', 32, 4),
       (10, 'Boss', 350.99, 5),
       (11, 'Dunlop', 240, 5),
       (12, 'MXR', 650.85, 5)

insert into Provider(prov_id, name, address, phone, email)
values (1, 'Thomann', 'Munich', '555-555-555-555', 'thomann@thomann.de'),
       (2, 'Sweetwater', 'New York', '123-456-789-012', 'sweet@water.com'),
       (3, 'Andertons', 'London', '098-765-432-109', 'andertons@music.co.uk')

insert into ProviderOrder(po_id, total, prov)
values (1, 18549.75, 1),
       (2, 31245.99, 1),
       (3, 12355.54, 2),
       (4, 412512.65, 3)

insert into Customer(cust_id, first_name, last_name, address, phone, email)
values (1, 'Andrei', 'Popa', 'Baia Mare', '0751-055-999', 'andrei@gmail.com'),
       (2, 'Matei', 'Damb', 'Cluj-Napoca', '0777-908-756', 'matey@yahoo.com'),
       (3, 'Iustin', 'Marchis', 'Sibiu', '0775-000-123', 'marchisiusti@gmail.com'),
       (4, 'Costin', 'Petrus', 'Bucuresti', '0712-321-423', 'costin@gmail.com')

insert into CustomerOrder(co_id, total, cust, emp)
values (1, 200, 1, 1),
       (2, 24, 2, 1),
       (3, 524, 3, 2)

insert into MusicInstrumentProviderOrder(mi_id, po_id)
values (1, 1),
       (1, 2),
       (2, 1),
       (3, 1),
       (5, 2),
       (4, 2),
       (2, 2),
       (1, 3)
insert into AccessoryProviderOrder (acc_id, po_id)
values (1, 1),
       (1, 2),
       (2, 1),
       (3, 2)

insert into MusicInstrumentCustomerOrder(mi_id, co_id)
values (1, 1),
       (2, 1),
       (3, 2),
       (5, 3)

insert into AccessoryCustomerOrder(acc_id, co_id)
values (1, 1),
       (2, 1),
       (3, 3),
       (4, 2)

insert into DepartmentMusicInstrument(dep_id, mi_id)
values (1, 1),
       (1, 2),
       (2, 2),
       (1, 3),
       (3, 5),
       (4, 5)

update Employee
set last_name = 'Petrescu'
where [first_name] = 'Andreea'
  and [last_name] = 'Pop'

update Department
set name = 'Guitars'
where [name] = 'Guitars and strings'

update MusicInstrumentType
set name = 'Percussion'
where [name] = 'Drums'

delete
Employee
where [emp_id] > 2

insert into Employee (emp_id, first_name, last_name)
values (3, 'Andrei', 'Savu')

delete
MusicInstrumentType
where [name] = 'Keyboard'

insert into MusicInstrumentType (mit_id, name)
values (2, 'Keyboard')

-- a
select last_name
from Employee
where [dep_id] = 1
   or [dep_id] = 2
union
select name
from Department
where [dep_id] = 1
   or [dep_id] = 2

select price
from MusicInstrument
where [maker] = 'Gibson'
   or [maker] = 'Korg'
union
select price
from Accessory
where [maker] = 'Boss'
   or [maker] = 'Dunlop'

-- b
select maker
from Accessory
where maker in ('Thomann', 'Boss')
intersect
select name
from Provider

select last_name
from Employee
where emp_id in (2, 3, 4, 5, 6)
intersect
select last_name
from Customer

-- c
select last_name
from Employee
where last_name not in (select last_name from Customer)

select maker
from Accessory except (select name from Provider)

-- d
select *
from Employee
         inner join Department on Employee.dep_id = Department.dep_id

select *
from CustomerOrder
         full join Customer on CustomerOrder.cust = Customer.cust_id
         full join Employee on CustomerOrder.emp = Employee.emp_id

select *
from Accessory
         left join AccessoryProviderOrder on Accessory.acc_id = AccessoryProviderOrder.acc_id
         left join ProviderOrder on AccessoryProviderOrder.po_id = ProviderOrder.po_id

select *
from Customer
         right join CustomerOrder on Customer.cust_id = CustomerOrder.cust

-- e
select *
from Employee
where Employee.emp_id in (select emp_id from CustomerOrder where CustomerOrder.total > 50)

select *
from Customer
where Customer.cust_id in
      (select cust_id from CustomerOrder where CustomerOrder.emp in (select emp_id from Employee where emp_id < 2))

-- f
select name
from Department
where exists(select first_name from Employee where Employee.dep_id = Department.dep_id)

select *
from MusicInstrument
where exists(select * from MusicInstrumentType where MusicInstrumentType.mit_id = MusicInstrument.type)

-- g
select *
from (select * from MusicInstrument where MusicInstrument.type = 1) as MI
where MI.maker <> 'Fender'

select *
from (select * from Accessory where Accessory.price < 100) as Acc
where Acc.type = 2

-- h
select maker, sum(price) as total
from MusicInstrument
group by maker
having sum(price) > (select avg(price) from MusicInstrument)

select cust, avg(total) as average
from CustomerOrder
group by cust
having avg(total) < 1000

select po_id, count(prov) as provider
from ProviderOrder
group by po_id
having count(prov) < (select max(prov) from ProviderOrder)

select first_name, avg(dep_id) as dep
from Employee
group by first_name
having avg(dep_id) > (select min(dep_id) from Employee)

-- i
select maker, model
from MusicInstrument
where mi_id = any (select mi_id from MusicInstrument where price < 2000)

select maker
from Accessory
where acc_id = any (select acc_id from Accessory where price < 50)


select first_name, last_name
from Employee
where emp_id = all (select emp from CustomerOrder where total < 1500)

select name
from Provider
where prov_id = all (select prov from ProviderOrder where total > 1000)


select maker, model
from MusicInstrument
where mi_id in (select mi_id from MusicInstrument where price < 2000)

select maker
from Accessory
where acc_id not in (select acc_id from Accessory where price >= 50)


select count(first_name) as count_first_name
from Employee
where emp_id = all (select emp from CustomerOrder where total < 1500)

select count(name) as count_name
from Provider
where prov_id = all (select prov from ProviderOrder where total > 1000)


