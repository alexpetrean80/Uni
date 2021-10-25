use
MusicStore
go

create
proc InitVersioningSystem as
	exec saveExecutedProc 'ModifyTypeOfColumn', 'RevertModifyTypeOfColumn'
	exec saveExecutedProc 'AddColumn', 'RevertAddColumn'
	exec saveExecutedProc 'AddDefaultConstraint', 'RevertAddDefaultConstraint'
	exec saveExecutedProc 'AddPrimaryKey', 'RevertAddPrimaryKey'
	exec saveExecutedProc 'AddCandidateKey', 'RevertAddCandidateKey'
	exec saveExecutedProc 'RemoveForeignKey', 'RevertRemoveForeignKey'
	exec saveExecutedProc 'DropTable', 'RevertDropTable'
	
	insert CurrentVersion (version)
		values (0)
go

select *
from ExecutedProceduresStack

select * from Rentals

select *
from CurrentVersion execute RevertDatabase 0

execute RevertDatabase 1

execute RevertDatabase 2

execute RevertDatabase 3

execute RevertDatabase 4

execute RevertDatabase 5

execute RevertDatabase 6

execute RevertDatabase 7

