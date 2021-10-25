use MusicStore
go

create or alter view v1
as
	select Tb.bid, Tc.cid, Tb.b2 from Tb inner join Tc on Tb.bid = Tc.bid
	where Tb.b2 < 100
go