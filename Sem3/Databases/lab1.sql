create
database MusicStore;

go
use MusicStore;
go

-- 1
create table Department
(
    dep_id int         not null primary key,
    name   varchar(50) not null
)

-- 2
create table Employee
(
    emp_id     int         not null primary key,
    first_name varchar(50) not null,
    last_name  varchar(50) not null,
    dep_id     int foreign key references Department (dep_id)
)

-- 3
create table MusicInstrumentType
(
    mit_id int         not null primary key,
    name   varchar(50) not null
)

-- 4
create table MusicInstrument
(
    mi_id int         not null,
    maker varchar(50) not null,
    model varchar(50) not null,
    price float
        constraint default_price default 0.0,
    type  int foreign key references MusicInstrumentType (mit_id),
    constraint pk_music_instruments primary key (mi_id)
)

-- 5
create table AccessoryType
(
    act_id int         not null primary key,
    name   varchar(50) not null,
)

-- 6
create table Accessory
(
    acc_id int         not null primary key,
    maker  varchar(50) not null,
    price  float       not null,
    type   int foreign key references AccessoryType (act_id)
)

-- 7
create table Provider
(
    prov_id int          not null primary key,
    name    varchar(50)  not null,
    address varchar(200) not null,
    phone   varchar(20)  not null,
    email   varchar(50)  not null,
)

-- 8
create table ProviderOrder
(
    po_id int   not null primary key,
    total float not null,
    prov  int foreign key references Provider (prov_id)
)

-- 9
create table Customer
(
    cust_id    int          not null primary key,
    first_name varchar(50)  not null,
    last_name  varchar(50)  not null,
    address    varchar(200) not null,
    phone      varchar(20)  not null,
    email      varchar(50)  not null,
)

-- 10
create table CustomerOrder
(
    co_id      int   not null primary key,
    total      float not null,
    cust       int foreign key references Customer (cust_id),
    emp        int foreign key references Employee (emp_id),
    order_date date
)

create table MusicInstrumentProviderOrder
(
    mi_id int foreign key references MusicInstrument (mi_id),
    po_id int foreign key references ProviderOrder (po_id),
    constraint pk_MusicInstrumentProviderOrder primary key (mi_id, po_id)
)

create table AccessoryProviderOrder
(
    acc_id int foreign key references Accessory (acc_id),
    po_id  int foreign key references ProviderOrder (po_id),
    constraint pk_AccessoryProviderOrder primary key (acc_id, po_id)
)

create table MusicInstrumentCustomerOrder
(
    mi_id int foreign key references MusicInstrument (mi_id),
    co_id int foreign key references CustomerOrder (co_id),
    constraint pk_MusicInstrumentCustomerOrder primary key (mi_id, co_id)
)

create table AccessoryCustomerOrder
(
    acc_id int foreign key references Accessory (acc_id),
    co_id  int foreign key references CustomerOrder (co_id),
    constraint pk_AccessoryCustomerOrder primary key (acc_id, co_id)
)

create table DepartmentMusicInstrument
(
    dep_id int foreign key references Department (dep_id),
    mi_id  int foreign key references MusicInstrument (mi_id),
    constraint pk_DepartmentMusicInstrument primary key (dep_id, mi_id)
)

create table DepartmentAccessory
(
    dep_id int foreign key references Department (dep_id),
    acc_id int foreign key references Accessory (acc_id),
    constraint pk_DepartmentAccessory primary key (dep_id, acc_id)
)


