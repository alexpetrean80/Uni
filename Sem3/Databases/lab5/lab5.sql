use
MusicStore

delete from Tc
delete from Tb 
delete from Ta

create table Ta
(
	aid int not null primary key,
	a2 int unique,
	a3 int
)

create table Tb
(
	bid int not null primary key,
	b2 int
)

create table Tc
(
	cid int not null primary key,
	aid int foreign key references Ta(aid),
	bid int foreign key references Tb(bid),
)

EXEC insertTa 1000
EXEC insertTb 1000000
EXEC insertTc 1000

select * from Ta
select * from Tb
select * from Tc

-- a
select * from Ta
order by aid

select * from Ta
where aid > 20

select * from Ta 
order by a2

select a2 from Ta
where a2 between 15 and 200

select a2, a3 from Ta
where a2 = 200

-- b
select b2 from Tb where b2 < 100

if exists (select name from sys.indexes where name='N_idx_Tb_b2')
	drop index N_idx_Tb_b2 on Tb
	create nonclustered index N_idx_Tb_b2 on Tb(b2)


-- clustered: 2.137 seconds on 1 million elements
-- nonclustered: 1.140 seconds on 1 million elements

select * from v1

create or alter view v1
as
	select Tb.bid, Tc.cid, Tb.b2 from Tb inner join Tc on Tb.bid = Tc.bid
	where Tb.b2 < 100
go
-- clustered: 1 sec
-- nonclustered: 1 sec