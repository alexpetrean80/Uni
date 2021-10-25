use MusicStore
go

create or alter proc insertTa
	@rows int
as
begin
	declare @current_row int
	set @current_row = 0
	while @current_row < @rows
	begin
		insert into Ta
			(aid, a2, a3)
		values
			(@current_row, @current_row * 100, rand())
		set @current_row = @current_row + 1
	end
end
	go

create or alter proc insertTb
	@rows int
as
begin
	declare @current_row int
	set @current_row = 0
	while @current_row < @rows
	begin
		insert into Tb
			(bid, b2)
		values
			(@current_row, rand() * 60 + 1)
		set @current_row = @current_row + 1
	end
end
	go

create or alter proc insertTc
	@rows int
as
declare @current_row int
set @current_row = 0
while @current_row < @rows
		BEGIN
	DECLARE @aid INT
	DECLARE @bid INT
	set @aid = (SELECT top(1)
		aid
	FROM Ta
	ORDER BY newid())
	set @bid = (SELECT top(1) bid from Tb ORDER BY newid())
	
	insert into Tc(aid, bid, cid) values (@aid, @bid, @current_row)
	set @current_row = @current_row + 1
	
END
go