use
MusicStore
go

drop
proc if exists ModifyTypeOfColumn
drop
proc if exists RevertModifyTypeOfColumn
drop
proc if exists AddColumn
drop
proc if exists RevertAddColumn
drop
proc if exists AddDefaultConstraint
drop
proc if exists RevertAddDefaultConstraint
drop
proc if exists AddPrimaryKey
drop
proc if exists RevertAddPrimaryKey
drop
proc if exists AddCandidateKey
drop
proc if exists RevertAddCandidateKey
drop
proc if exists RemoveForeignKey
drop
proc if exists RevertRemoveForeignKey
drop
proc if exists DropTable
drop
proc if exists RevertDropTable
drop
proc if exists SetCurrentVersion
drop
proc if exists saveExecutedProc
drop
proc if exists RevertDatabase