CREATE TABLE buseo(
    buseo_no number(3),
    buseo_name varchar2(20),
    CONSTRAINT pk_buseo_buseo_no PRIMARY KEY(buseo_no)
);

CREATE TABLE sawon(
    sabun number(6),
    sawon_name varchar2(10),
    ubmu varchar2(20) DEFAULT '개발',
    weolgub varchar2(14),
    buseo number(3),
    jiyeok varchar2(20) NOT NULL,
    CONSTRAINT pk_sawon_sabun PRIMARY KEY(sabun),
    CONSTRAINT ck_sawon_ubmu CHECK(ubmu in('개발', '유지보수', '관리')),
    CONSTRAINT fk_sawon_buseo FOREIGN KEY(buseo) REFERENCES buseo(buseo_no)
);
desc buseo;
select * from buseo;
drop table buseo;
drop table sawon;
commit;