create table tb_persona
(
	tb_persona_id serial not null,
	tb_persona_tipdocide character(1) not null, -- Puede ser: 1(DNI), 6(RUC)
	tb_persona_numdocide character varying(11) not null,
	tb_persona_apenomdenrazsoc character varying(50) not null,
	constraint pk_persona primary key(tb_persona_id),
	constraint chk_persona_id check(tb_persona_id > 0),
	constraint chk_persona_tipdocide check(tb_persona_tipdocide in ('1','6')),
	constraint chk_persona_numdocide check(tb_persona_numdocide similar to '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]|[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]')																
)
										   
select *
from tb_persona

insert into tb_persona(tb_persona_tipdocide,tb_persona_numdocide,tb_persona_apenomdenrazsoc)
values('1','16221133','DIAZ PEREZ ANA')