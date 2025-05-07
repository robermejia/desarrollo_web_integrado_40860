-- DDL: Data Definition Language
create table tb_cliente
(
	tb_cliente_cod character(4) not null,
	tb_cliente_nom character varying(50) not null,
	constraint pk_cliente primary key(tb_cliente_cod),
	constraint chk_cliente_codpat check(tb_cliente_cod similar to '[0-9][0-9][0-9][0-9]'),
	constraint chk_cliente_coddom check(tb_cliente_cod <> '0000')
);

create table tb_producto
(
	tb_producto_cod character(4) not null,
	tb_producto_nom character varying(50) not null,
	tb_producto_preuni decimal not null,
	tb_producto_sto	decimal not null,
	constraint pk_producto primary key(tb_producto_cod),
	constraint chk_producto_codpat check(tb_producto_cod similar to '[0-9][0-9][0-9][0-9]'),
	constraint chk_producto_coddom check(tb_producto_cod <> '0000'),
	constraint chk_producto_preuni check(tb_producto_preuni > 0),
	constraint chk_producto_sto check(tb_producto_sto >= 0)
);

create table tb_venta
(
	tb_venta_id serial not null,
	tb_venta_fec date not null,
	tb_venta_imptot decimal not null,
	tb_cliente_cod character(4) not null,
	constraint pk_venta primary key(tb_venta_id),
	constraint fk_cliente_venta foreign key(tb_cliente_cod) references tb_cliente(tb_cliente_cod),
	constraint chk_venta_id check(tb_venta_id > 0),
	constraint chk_venta_fec check(tb_venta_fec <= now()::date),
	constraint chk_venta_imptot check(tb_venta_imptot > 0)
);

create table tb_detalleventa
(
	tb_detalleventa_id serial not null,
	tb_detalleventa_can decimal not null,
	tb_detalleventa_preuni decimal not null,
	tb_detalleventa_imptot decimal not null,
	tb_venta_id integer not null,
	tb_producto_cod character(4) not null,
	constraint pk_detalleventa primary key(tb_detalleventa_id),
	constraint fk_venta_detalleventa foreign key(tb_venta_id) references tb_venta(tb_venta_id),
	constraint fk_producto_detalleventa foreign key(tb_producto_cod) references tb_producto(tb_producto_cod),
	constraint chk_detalleventa_id check(tb_detalleventa_id > 0),
	constraint chk_detalleventa_can check(tb_detalleventa_can > 0),
	constraint chk_detalleventa_preuni check(tb_detalleventa_preuni > 0),
	constraint chk_detalleventa_imptot check(tb_detalleventa_imptot > 0)
);

-- DML: Data Manipulation Language
insert into tb_cliente(tb_cliente_cod,tb_cliente_nom) values('0001','JORGE');
insert into tb_cliente(tb_cliente_cod,tb_cliente_nom) values('0002','ANA');
insert into tb_cliente(tb_cliente_cod,tb_cliente_nom) values('0003','PAULO');

insert into tb_producto(tb_producto_cod,tb_producto_nom,tb_producto_preuni,tb_producto_sto) values('0001','INKA KOLA',3,100);
insert into tb_producto(tb_producto_cod,tb_producto_nom,tb_producto_preuni,tb_producto_sto) values('0002','COCA COLA',4,80);
insert into tb_producto(tb_producto_cod,tb_producto_nom,tb_producto_preuni,tb_producto_sto) values('0003','PERÚ KOLA',2,300);

-- SQL: Structured Query Language
select * from tb_cliente
select * from tb_producto

select *
from tb_venta ve
inner join tb_cliente cl on ve.tb_cliente_cod = cl.tb_cliente_cod
inner join tb_detalleventa dv on ve.tb_venta_id = dv.tb_venta_id
inner join tb_producto pr on dv.tb_producto_cod = pr.tb_producto_cod
