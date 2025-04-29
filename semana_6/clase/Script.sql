create table tb_familia
(
	tb_familia_id serial not null,
	tb_familia_nom character varying(50) not null,
	constraint pk_familia primary key(tb_familia_id),
	constraint unq_familia unique(tb_familia_nom),
	constraint chk_familia_id check(tb_familia_id > 0)
);

create table tb_producto
(
	tb_producto_cod character(4) not null,
	tb_producto_nom character varying(50) not null,
	tb_producto_preuni decimal(5,2) not null,
	tb_producto_sto integer not null,
	tb_familia_id integer not null,
	constraint pk_producto primary key(tb_producto_cod),
	constraint fk_familia_producto foreign key(tb_familia_id) references tb_familia(tb_familia_id),
	constraint unq_producto_nom unique(tb_producto_nom),
	constraint chk_producto_codpat check(tb_producto_cod similar to '[0-9][0-9][0-9][0-9]'),
	constraint chk_producto_coddom check(tb_producto_cod <> '0000'),
	constraint chk_producto_preuni check(tb_producto_preuni > 0),
	constraint chk_producto_sto check(tb_producto_sto >= 0)
);

select * from tb_familia
select * from tb_producto