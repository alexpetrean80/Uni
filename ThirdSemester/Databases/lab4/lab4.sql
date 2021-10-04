use MusicStore
go

exec newTest 'first'
exec addTable 'Department'
exec addTestTables 'first', 'Department', 1000, 3
exec addTable 'Accessory'
exec addTestTables 'first', 'Accessory', 1000, 2
exec addTable 'DepartmentAccessory'
exec addTestTables 'first', 'DepartmentAccessory', 800, 1
exec addView 'SelectOnOneTable'
exec addViewTests 'first', 'SelectOnOneTable'
exec addView 'SelectOnTwoTables'
exec addViewTests 'first', 'SelectOnTwoTables'
exec addView 'SelectGroupBy'
exec addViewTests 'first', 'SelectGroupBy'

exec ExecuteTest 'first'

select *
from TestRunTables
select *
from TestRunViews
select *
from TestRuns