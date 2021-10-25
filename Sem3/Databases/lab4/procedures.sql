use MusicStore
go

-- single column primary key and no foreign key: Department
create or alter proc InsertDepartment
	@rows int
as
declare @current_row int


set @current_row = 0

while @current_row < @rows 
	begin
	insert into Department
		(dep_id, name)
	values
		(
			@current_row,
			'Department no.' + convert(varchar(10), @current_row)
				)
	set @current_row = @current_row + 1
end
go

create or alter proc DeleteDepartment
	@rows int
as


select *
into #TEMP
from Department
order by dep_id desc

delete top (@rows) 
	from #TEMP

delete from Department

insert Department
select *
from #TEMP
order by dep_id
go

-- single column primary key and 1 foreign key: Accessory
create or alter proc InsertAccessory
	@rows int
as
declare @current_row int

set @current_row = 0

insert into AccessoryType
	(act_id, name)
values
	(1, 'Everything')

while @current_row < @rows
	begin
	insert into Accessory
		(acc_id, maker, price, type)
	values
		(
			@current_row,
			'Maker' + convert(varchar(10), @current_row),
			floor(rand() * 100),
			1
		)
	set @current_row = @current_row + 1
end
go

create or alter proc DeleteAccessory
	@rows int
as


select *
into #TEMP
from Accessory
order by acc_id desc

delete top (@rows) 
	from #TEMP

delete from Employee

insert Accessory
select *
from #TEMP
order by acc_id
go

-- multicolumn primary key: DepartmentAccessory
create or alter proc InsertDepartmentAccessory
	@rows int
as
declare @current_row int
set @current_row = 1

while @current_row < @rows
	begin
	insert into DepartmentAccessory
	values
		(@current_row, @current_row)
	set @current_row = @current_row + 1
end
go

create or alter proc DeleteDepartmentAccessory
	@rows int
as
delete from DepartmentAccessory
delete from Accessory
delete from AccessoryType
delete from Department
go

create or alter proc ExecuteTest
	@name varchar(50)
as
begin
	declare @test_id int
	set @test_id = (select TestID
	from Tests t
	where t.Name = @name)
	insert into TestRuns
		(Description)
	values
		('Run for ' + @name)
	declare @start datetime
	declare @end datetime
	declare @local_start datetime
	declare @local_end datetime

	declare @id int
	set @id = convert(int, (select LAST_VALUE
	from sys.identity_columns
	where name = 'TestRunID'))

	set @start = getdate()

	declare crs cursor scroll for 
	select t.TableID, Name, NoOfRows
	from TestTables t inner join Tables on t.TableID = Tables.TableID
	where TestID = @test_id
	order by Position

	declare @table_name varchar(50)
	declare @table_rows int
	declare @table_id int

	declare @executed_proc varchar(100)

	open crs
	fetch first from crs into @table_id, @table_name, @table_rows

	while @@FETCH_STATUS = 0
	begin
		set @executed_proc = 'Delete' + @table_name + ' ' + convert(varchar(50), @table_rows)
		set @local_start = getdate()
		exec (@executed_proc)
		insert into TestRunTables
		values
			(@id, @table_id, @local_start, 0)
		fetch next from crs into @table_id, @table_name, @table_rows
	end

	close crs
	open crs
	fetch last from crs into @table_id, @table_name, @table_rows
	while @@FETCH_STATUS = 0
	begin
		set @executed_proc = 'Insert' + @table_name + ' ' + convert(varchar(50), @table_rows)
		exec (@executed_proc)
		set @local_end = getdate()
		update TestRunTables set EndAt = @local_end where TestRunID = @id and TableID = @table_id
		fetch prior from crs into @table_id, @table_name, @table_rows
	end

	close crs
	deallocate crs

	declare crs cursor scroll for 
	select TestViews.ViewID, Name
	from TestViews inner join Views on TestViews.ViewID = Views.ViewID
	where @test_id = TestViews.TestID

	declare @view_name varchar(50)
	declare @view_id int

	open crs
	fetch first from crs into @view_id, @view_name

	while @@FETCH_STATUS = 0
	begin
		-- set @executed_proc = 'Delete' + @table_name + ' ' + convert(varchar(50), @table_rows)

		set @executed_proc = 'select * from ' + @view_name
		set @local_start = getdate()
		exec (@executed_proc)
		set @local_end = getdate()
		insert into TestRunViews
		values
			(@id, @view_id, @local_start, @local_end)
		fetch next from crs into @view_id, @view_name
	end

	close crs
	deallocate crs

	set @end = getdate()

	update TestRuns set StartAt = @start, EndAt = @end where @id = TestRunID
end
go

create or
alter procedure newTest
	@name varchar(50)
as
begin
	if @name in (select Name
	from Tests)
        begin
		print 'There is a test with the name' + @name
		return
	end
	insert into Tests
		(Name)
	values
		(@name)
end
go

create or
alter procedure addTable
	@name varchar(50)
as
begin
	if @name not in (select Name
	from Tables)
        begin
		insert into Tables
			(Name)
		values
			(@name)
	end
    else
        begin
		print 'Table' + @name + 'is already added'
		return
	end
end
go

create or
alter procedure addView
	@name varchar(50)
as
begin
	if @name not in (select Name
	from Views)
        begin
		insert into Views
			(Name)
		values
			(@name)
	end
    else
        begin
		print 'View' + @name + 'is already added'
		return
	end
end
go

create or
alter procedure addTestTables
	@testName varchar(50),
	@tableName varchar(50),
	@noOfRows int,
	@position int
as
begin

	declare @testID int
	declare @tableID int

	set @testID = (select TestID
	from Tests
	where Name = @testName)
	if @testID is null
        begin
		print 'Test' + @testName + 'does not exist'
		return
	end

	set @tableID = (select TableID
	from Tables
	where Name = @tableName)
	if @tableID is null
        begin
		print 'Table ' + @tableName + 'does not exist'
		return
	end

	if (select count(*)
	from TestTables
	where TestID = @testID and TableID = @tableID) > 0
        begin
		print 'There is already a record with ids TestID:' + @testID + 'TableID:' + @tableID
		return
	end

	if @noOfRows <= 0
        begin
		print 'Number of rows needs to be greater than 0'
		return
	end

	if @position <= 0
        begin
		print 'Position needs to be greater than 0'
		return
	end

	insert into TestTables
		(TestID, TableID, NoOfRows, Position)
	values
		(@testID, @tableID, @noOfRows, @position)

end
go

create or
alter procedure addViewTests
	@testName varchar(50),
	@viewName varchar(50)
as
begin

	declare @testID int
	declare @viewID int

	set @testID = (select TestID
	from Tests
	where Name = @testName)
	if @testID is null
        begin
		print 'Test' + @testName + 'does not exist'
		return
	end

	set @viewID = (select ViewID
	from Views
	where Name = @viewName)
	if @viewID is null
        begin
		print 'View' + @viewName + 'does not exist'
		return
	end

	if (select count(*)
	from TestViews
	where TestID = @testID and ViewID = @viewID) > 0
        begin
		print 'There is already a record with ids TestID:' + @testID + 'ViewID:' + @viewID
		return
	end

	insert into TestViews
		(TestID, ViewID)
	values
		(@testID, @viewID)

end
go

