use
MusicStore;
go


-- a
create
proc ModifyTypeOfColumn
as
alter table MusicInstrument
alter
column price money
go

create
proc  RevertModifyTypeOfColumn
as
alter table MusicInstrument
alter
column price float
go

-- b
create
proc AddColumn
as
alter table Employee
    add phone varchar(20)
    go

create
proc RevertAddColumn
as
alter table Employee
drop
column phone
go

-- c
create
proc AddDefaultConstraint
as
alter table CustomerOrder
    add constraint order_date_df
        default getdate() for order_date
go

create
proc RevertAddDefaultConstraint
as
alter table CustomerOrder
drop
constraint order_date_df
go

-- d

create
proc AddPrimaryKey
as
alter table Rentals
    add constraint pk_Rentals primary key clustered (r_id)
    go

create
proc RevertAddPrimaryKey
as
alter table Rentals
drop
constraint pk_Rentals
go

-- e
create
proc AddCandidateKey
as
alter table Customer
    add constraint contact_data unique (phone, email)
    go

create
proc RevertAddCandidateKey
as
alter table Customer
drop
constraint contact_data
go

-- f
create
proc RemoveForeignKey
as
alter table CustomerOrder
drop
constraint fk_Customer
go

create
proc RevertRemoveForeignKey
as
alter table CustomerOrder
    add constraint fk_Customer foreign key (cust) references Customer (cust_id)
    go

-- e
create
proc DropTable
as
drop table Rentals
    go

create
proc RevertDropTable
as
create table Rentals
(
    r_id      int,
    client_id int foreign key references Customer (cust_id),
    due_date  date,
    constraint pk_Rentals primary key clustered (r_id)
)
    go



create
proc SetCurrentVersion(@new_version int)
as
update CurrentVersion
set version=@new_version
    go

create
proc saveExecutedProc(@name varchar(50), @reverse varchar(50))
as
insert into ExecutedProceduresStack (proc_name, reverse_proc_name)
values (@name, @reverse)
go

create
proc RevertDatabase(@version int)
as
declare
@current_version int
    set @current_version = (select version
                            from CurrentVersion)
    if (@current_version > @version)
        while(@current_version > @version)
begin
                declare
@reverse_proc varchar(50)
                set @reverse_proc =
                        (select reverse_proc_name from ExecutedProceduresStack where version = @current_version)
                exec @reverse_proc
                set @current_version = @current_version - 1
end
else if (@current_version < @version)
        while (@current_version < @version)
begin
                set
@current_version = @current_version + 1
                declare
@proc varchar(50)
                set @proc = (select proc_name from ExecutedProceduresStack where version = @current_version)
                exec @proc

end
exec SetCurrentVersion @current_version
go