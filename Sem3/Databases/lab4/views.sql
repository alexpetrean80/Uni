use MusicStore
go

create or alter view SelectOnOneTable
as
	select *
	from Department
go

create or alter view SelectOnTwoTables
as
	select *
	from Department, Accessory
go

create or alter view SelectGroupBy
as
	select d.name, sum(a.price) as total
	from Department d, Accessory a
	group by name
	having sum(a.price) > 1
go