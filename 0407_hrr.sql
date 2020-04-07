
CREATE TABLE gogek(
    id varchar(8),
    name varchar(10) not null,
    tel varchar(15),
    CONSTRAINT gogek_id_pk PRIMARY KEY(id)
);
CREATE TABLE sangpum(
    no varchar(6),
    title varchar(50) not null,
    detail varchar(1024),
    count number(32) not null,
    price number(32),
    CONSTRAINT sangpum_no_pk PRIMARY KEY(no)
);

CREATE TABLE jumun(
    no number(4),
    gogek varchar(8),
    sangpum varchar(6),
    count number(32),
    jumunil date,
    CONSTRAINT jumun_no_pk PRIMARY KEY(no),
    CONSTRAINT jumun_gogek_fk FOREIGN KEY(gogek) REFERENCES gogek(id),
    CONSTRAINT jumun_sangpum_fk FOREIGN KEY(sangpum) REFERENCES sangpum(no)
);
commit;

INSERT INTO gogek ( id, name, tel ) VALUES('babo1234','ȫ�浿', '031-333-3333' );
INSERT INTO gogek ( id, name, tel ) VALUES('babo1111','��浿', '031-111-1111' );
INSERT INTO gogek ( id, name, tel ) VALUES('babo2222','ȫ�浿', '031-222-2222' );
INSERT INTO gogek ( id, name, tel ) VALUES('babo9999','�ڱ浿', '031-333-3333' );
INSERT INTO gogek ( id, name, tel ) VALUES('babo1234','�ͱ浿', '031-999-9999' ); -- �⺻Ű �������� ����
INSERT INTO gogek ( id, name, tel ) VALUES('babo12345','�̱浿', '031-333-1234' ); -- ���̵� �޸� ���� �ʰ�
INSERT INTO gogek ( id, name, tel ) VALUES('123','�ֱ浿', '031-999-9999' );
INSERT INTO gogek ( id, name ) VALUES('babo','���浿' );
INSERT INTO gogek ( id, tel ) VALUES('babo1988','031-888-8888' ); -- not null �������� ����

INSERT INTO sangpum ( no, title, count,price, detail ) VALUES ('A00001','�̻۹���', 30, 1000, '�ΰ��̻��� �ݹ� �� �ȸ� �����Դϴ�' );
INSERT INTO sangpum ( no, title, count,price, detail ) VALUES ('A00002','�׳ɹ���', 20, 500, '�׳��̻��� ����� �����Դϴ�' );
INSERT INTO sangpum ( no, title, count )VALUES ('B00009','������', 20 );
INSERT INTO sangpum ( no, title, count,price ) VALUES ('Z0001','�����Ʈ', 3, 20000, '�̳� �����Ʈ�Դϴ�' ); -- ���� ���� �ʹ� ���� �Է���
INSERT INTO sangpum VALUES ('Z00001','�����Ʈ2', 5, 20000, '�̳� ��� ��Ʈ�Դϴ�' ); --��ǰ ����(number)�� ���ڰ� ��
INSERT INTO sangpum VALUES ('Z00001','�����Ʈ3','�̳� ��� ��Ʈ�Դϴ�', 5, 20000);

INSERT INTO jumun(no, gogek, sangpum,count, jumunil) VALUES ( 1000, 'babo1234', 'A00001', 1, '2016/09/09' );
INSERT INTO jumun(no, gogek, sangpum,count, jumunil) VALUES ( 1001, 'babo1233', 'Z00001', 1, '2016/09/09' ); -- ���� �������� ���� (babo1233�� �������� ����)
INSERT INTO jumun(no, gogek, sangpum,count, jumunil) VALUES ( 1002, 'Babo1234', 'A00002', 2, '2016/09/10' ); -- ���� �������� ���� (Babo1234�� �������� ����)
INSERT INTO jumun(no, gogek, sangpum,count, jumunil) VALUES ( 1003, 'babo1234', 'B00001', 1, '2016/09/11' ); -- ���� �������� ���� (B00001�� �������� ����)
INSERT INTO jumun(no, gogek, sangpum,count, jumunil) VALUES ( 1004, 'babo', 'Z00001', 1, '2016/10/11' );
INSERT INTO jumun(no, gogek, sangpum,count, jumunil) VALUES ( 1005, 'babo1234', 'B00009', 1, '2016/11/11' );
INSERT INTO jumun(no, gogek, sangpum,count, jumunil) VALUES ( 1006, 'babo1234', 'B00009', 1, '2016/12/12' );


--��ȭ��ȣ�� ���� ���� �������� ������ �˻��ϼ���
SELECT name
FROM gogek
WHERE tel IS NULL;
--ȫ�� ���� ���� ������ ������ �˻��ϼ���
SELECT *
FROM gogek
WHERE name LIKE 'ȫ%';
--babo2222 ������ �ڱ浿���� �����ϼ���
UPDATE gogek
SET name='�ڱ浿'
WHERE id='babo2222';
--���̵� 123 ���� ���̵� babo123 ���� �����ϼ���
UPDATE gogek
SET id='babo123'
WHERE id='123';
--���浿���� ��ȭ��ȣ 02-555-5555�� �߰��ϼ���
UPDATE gogek
SET tel='02-555-5555'
WHERE name='���浿';

--��ǰ��ȣ B00009 �� ���� 1200�� �׸��� ���̳� �ٽ��� ���Դϴ١���� ��ǰ������ �߰��ϼ���
UPDATE sangpum
SET detail='�̳� �ٽ��� ���Դϴ�', price=1200
WHERE no='B00009';
--�̻۹��� ��ǰ�� 10���� �ȷȽ��ϴ�.
UPDATE sangpum
SET count=count-10
WHERE title='�̻۹���';
--Z00001 ��ǰ�� 3���� �� �԰� �Ǿ����ϴ�.
UPDATE sangpum
SET count=count+3
WHERE no='Z00001';
--��ǰ ������ 1000���� �Ѵ� ��ǰ���� �˻��ϼ���
SELECT *
FROM sangpum
WHERE price>1000;

SELECT * FROM jumun;
--11�� ���Ŀ� �ֹ��� ��ǰ ��ȣ�� �˻��ϼ���
SELECT *
FROM jumun
WHERE TO_CHAR(jumunil, 'MM') > 11;
--babo ���� �ֹ��� ��ǰ�� A00002�� �����ϰ� ������ 2���� �����ϼ���
UPDATE jumun
SET sangpum='A00002', count=2
WHERE gogek='babo';

--babo1234 ���� 11���� �ֹ��� ��ǰ ��ȣ�� �˻��ϼ���
SELECT sangpum
FROM jumun
WHERE gogek='babo1234' AND TO_CHAR(jumunil, 'MM')='11';
--B00009 ��ǰ�� �ֹ��� �� ���̵� �˻��ϼ���
SELECT DISTINCT gogek
FROM jumun
WHERE sangpum='B00009';

------------------------------------------------ ���� 
/*
###### ����Ŭ ���Խ� ǥ��

# �Ʒ��� ���� ���̺��� �����ϰ� ���ڵ带 �Է��� ��

# REGEXP_LIKE  �ܿ� REGEXP_REPALCE,REGEXP_INSTR, REGEXP_SUBSTR, REGEXP_COUNT �Լ��� ����

 �׷��� �Ʒ��� ������ regexp_like �Լ��� �̿��Ͽ��� ������.
*/
CREATE TABLE reg_tab( text varchar2(20) );

INSERT INTO reg_tab VALUES('TIGER');
INSERT INTO reg_tab VALUES('TIGGER');
INSERT INTO reg_tab VALUES('elephant');
INSERT INTO reg_tab VALUES('tiger');
INSERT INTO reg_tab VALUES('tiger2');
INSERT INTO reg_tab VALUES('tiger3');
INSERT INTO reg_tab VALUES('doggy');
INSERT INTO reg_tab VALUES('5doggy');
INSERT INTO reg_tab VALUES('DOG');
INSERT INTO reg_tab VALUES('DOG2');
INSERT INTO reg_tab VALUES('��');
INSERT INTO reg_tab VALUES('cat');
INSERT INTO reg_tab VALUES('catty');
INSERT INTO reg_tab VALUES('9catty');
INSERT INTO reg_tab VALUES('catwoman');
INSERT INTO reg_tab VALUES('�����');
INSERT INTO reg_tab VALUES('BAT');
INSERT INTO reg_tab VALUES('BATMAN');
INSERT INTO reg_tab VALUES('BATGIRL');
INSERT INTO reg_tab VALUES('0BATGIRL');
INSERT INTO reg_tab VALUES('����');

commit;
----------------------------------------------------------------------------------------------
-- 1.  text �÷��� ���ڿ����� 't'�ν����ϴ� ������ �˻�
SELECT *
FROM reg_tab
WHERE text LIKE 't%';
-- 2. text �÷��� ���ڿ����� 't'�� ������ ������ �˻�
SELECT *
FROM reg_tab
WHERE text LIKE '%t';
-- 3. ù��° 't'�ν����Ͽ� 5��° 'r'�� �ִ� ������ �˻�
SELECT *
FROM reg_tab
WHERE text LIKE 't___r%';
-- 4. ���ڷ� ������ ������ �˻�
SELECT *
FROM reg_tab
WHERE regexp_like(text, '[0-9]$');
-- 5. ���ڷ� �����ϴ� ����Ÿ �˻�
SELECT *
FROM reg_tab
WHERE regexp_like(text, '^[0-9]');
-- 6. ���ڰ� �ƴ� ���ڷ� �����ϴ� ������ �˻�
SELECT *
FROM reg_tab
WHERE regexp_like(text, '^[^0-9]');
select * from reg_tab;
-- 7. �빮�ڷ� �����ϴ� ������ �˻�
SELECT *
FROM reg_tab
WHERE regexp_like(text, '^[A-Z]');
-- 8. �ҹ��� ���� ���ڷ� �����ϴ� ������ �˻�
SELECT *
FROM reg_tab
WHERE regexp_like(text, '^[^a-z]');
-- 9. �ѱ۷� �����ϴ� ������ �˻�
SELECT *
FROM reg_tab
WHERE regexp_like(text, '^[^a-zA-Z0-9]');
-- 10. ������ �� 'gg'�� 'GG'�� ����ִ� ������ �˻�
SELECT *
FROM reg_tab
WHERE regexp_like(text, '((gg)|(GG))');

---------------------------------------------------
/*
[ ���� �� ���� ]

1. ����ڸ� JAVA ��й�ȣ�� 1234�� ����ڸ� �����Ͽ���
sqlplus "/as sysdba"
create user JAVA identified by 1234;

2. 1������ ������ ����ڿ��� ����Ŭ�� ������ �� �ִ� ����,���̺� ������ �� �ִ�
����,�並 �����Ҽ� �ִ� ������ �ο��Ͽ���
grant connect, create table, create view to JAVA;

3. JAVA �� ��й�ȣ�� java1234�� �����Ͽ���
alter user JAVA identified by java1234;
4. JAVA �� ���� �� �並 ������ �� �ִ� ������ ȸ�� �Ͽ���?
revoke create view from JAVA;
*/