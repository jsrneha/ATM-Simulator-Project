create table user( userid varchar(15), name varchar(30), address varchar(100), birthdate date , phone varchar(15));

alter table user add primary key(userid) ;

describe user ; 

create table account (accountno int ,atmissuestatus int ,bankname varchar(50),type varchar(50), primary key(accountno),balance int );

describe account ;

alter table account modify atmissuestatus int not null ;

alter table account modify type varchar(50) not null ;

alter table account modify bankname varchar(50) not null ;

alter table account modify balance int not null ;

describe account  ; 

describe user ;

alter table user modify name varchar(30) not null ;

alter table user modify address varchar(100) not null ;

alter table user modify birthdate date not null ;

alter table user modify phone varchar(15) not null ;

describe user;

describe account; 

create table admin( adminid varchar(15), name varchar(50) not null , address varchar(100) not null, phone varchar (15) not null, password varchar(30) not null );

alter table admin add primary key(adminid);

create table atmcard( cardno varchar(16), pin varchar(10) not null , cvv int not null , expirydate date not null, blockstatus int not null);

describe atmcard;

create table transaction ( transactionid varchar(10), transactiondate date not null, withdrawal int , deposit int , depositedto int , primary key (transactionid));

alter table transaction add accountno int  not null ;

alter table transaction add foreign key(accountno) references account(accountno)  ;

alter table atmcard add accountno int  not null ;

alter table atmcard add foreign key(accountno) references account(accountno) ;

alter table user add accountno int  not null ;

alter table user add foreign key(accountno) references account(accountno)  ;

alter table account add cardno varchar(16);

alter table account add foreign key(cardno) references atmcard(cardno)  ;







