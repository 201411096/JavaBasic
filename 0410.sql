/*----------------------------------------------------------------------------------------------------
SQL ����
1.DDL
        CREATE / DROP / ALTER
        
2.DML -> CRUD(CREATE - READ - UPDATE - DELETE)
        ` INSERT INTO ���̺��(�÷����) VALUES(����);
        ` UPDATE ���̺�� SET ������� WHERE ���ǹ�;
        ` DELETE FROM ���̺�� WHERE ���ǹ�;
        ` SELECT �÷��� FROM ���̺�� WHERE ���ǹ�;
        * Transaction : all or none
        ex) A�۾� : A���¿��� 100���� ���� ��
            B�۾� : B���¿��� 100���� �Ա�
3.DCL
        ` GRANT ex) GRANT create table TO hr;
        ` REVOKE ex) REVOKE create table FROM hr;

**
SELECT
FROM
WHERE
GROUP BY
HAVING
ORDER BY

* ���� : 1�� �̻��� ���̺�κ��� ������ �˻��� ��(1���� ��� - �������� ex)employee ���̺��� �Ŵ����� �̸��� ��� ���� ���� ������)


*/----------------------------------------------------------------------------------------------------
/*----------------------------------------------------------------------------------------------------
����(SET)
UNION : ������
UNION ALL : �����տ� �ߺ��Ǵ� ������ �� �� ���
INTERSECT : ������
MINUS : ������
*/----------------------------------------------------------------------------------------------------
SELECT empno, ename, job, deptno FROM emp WHERE job='CLERK'
--SMITH / ADAMS / JAMES / MILLER
----------------------------------------------------------------------------------------------------
UNION
-- UNION -- SMITH ADAMS JAMES CLARK KING MILLER(�ߺ�)
-- UNION ALL -- SMITH ADAMS JAMES CLARK KING MILLER MILLER(�ι� ���)
-- INTERSECT -- MILLER
-- MINUS -- SMITH ADAMS JAMES
----------------------------------------------------------------------------------------------------
SELECT empno, ename, job, deptno FROM emp WHERE deptno=10;
--CLARK / KING / MILLER

----------------------------------------------------------------------------------------------------
-- ���� ����
CREATE TABLE ex_member(
id varchar2(10), -- ���̵�
pass varchar2(10), -- �н�����
name varchar2(20), -- �̸�
tel varchar2(20) UNIQUE, -- ��ȭ��ȣ
addr varchar2(100), -- �ּ�
CONSTRAINT ex_member_id_pk PRIMARY KEY(id)
);

CREATE TABLE ex_good(
gno varchar2(10), -- ��ǰ��ȣ
gname varchar(30), -- ��ǰ��
gdetail varchar2(300), -- �󼼼���
price number,
CONSTRAINT ex_good_gno_pk PRIMARY KEY(gno)
);  -- ����

CREATE TABLE ex_order (
ono number,  -- ��ȣ
orderno varchar2(20), -- �ֹ���ȣ
gno varchar2(10), -- ��ǰ��ȣ
id varchar2(10), -- ȸ�� ���̵�
count number,  -- ����
status varchar2(10), -- ��ۻ���
CONSTRAINT ex_order_ono_pk PRIMARY KEY(ono),
CONSTRAINT ex_order_gno_fk FOREIGN KEY(gno) REFERENCES ex_good(gno),
CONSTRAINT ex_order_id_fk FOREIGN KEY(id) REFERENCES ex_member(id)
);

insert into ex_member(id, pass, name, tel, addr )
values('kim', '1111', '��浿', '02-222-2222','���� ������ �̻۵�');
insert into ex_member(id, pass, name, tel, addr )
values('park', '1111', '�ڱ浿', '03-333-3333','��õ ������ �̻۵�');
insert into ex_member(id, pass, name, tel, addr )
values('meng', '1111', '�ͱ浿', '04-444-4444','��� ������ �̻۵�');

insert into ex_good( gno, gname, gdetail, price )
values('1001', '�Ӹ���', '���� ��� �Ӹ���', 200 );
insert into ex_good( gno, gname, gdetail, price )
values('2002', '������-A', '������� ������ �޸��� ������', 10000 );
insert into ex_good( gno, gname, gdetail, price )
values('2010', '������-B', '���� ������� ������ �����ϰ� �޸��ٴ� ������', 20000 );
insert into ex_good( gno, gname, gdetail, price )
values('3333', '�ڵ������̽�', '�ΰ� �����ϴ� �ڵ��� ���̽�', 1500 );

insert into ex_order( ono, orderno, gno, id, count, status )
values( 1, '20161212', '1001', 'park', 1, '��ۿϷ�');
insert into ex_order( ono, orderno, gno, id, count, status )
values( 2, '20161212', '2010', 'park', 1, '�����');
insert into ex_order( ono, orderno, gno, id, count, status )
values( 3, '20161111', '1001', 'kim', 2, '�ֹ�');
insert into ex_order( ono, orderno, gno, id, count, status )
values( 4, '20161111', '3333', 'kim', 3, '�ֹ�');
insert into ex_order( ono, orderno, gno, id, count, status )
values( 5, '20163333', '1001', 'park', 3, '�ֹ�');
insert into ex_order( ono, orderno, gno, id, count, status )
values( 6, '20163333', '2010', 'park', 1, '�����');
insert into ex_order( ono, orderno, gno, id, count, status )
values( 7, '20163333', '2002', 'park', 2, '�ֹ�');
insert into ex_order( ono, orderno, gno, id, count, status )
values( 8, '20165050', '1001', 'meng', 1, '�����');
commit;
----------------------------------------------------------------------------------------------------
--1.  ������� �ֹ� ������ ��ǰ ������ ����Ͻÿ�.
SELECT g.gno AS GNO, g.gname AS GNAME, g.gdetail AS GDETAIL, g.price AS PRICE, o.ono AS ONO, o.orderno AS ORDERNO, o.id AS ID, o.count AS COUNT, o.status AS STATUS
FROM ex_good g, ex_order o
WHERE g.gno=o.gno AND o.status='�����';

SELECT *
FROM ex_good g INNER JOIN ex_order o
ON g.gno=o.gno
WHERE o.status='�����';











----------------------------------------------------------------------------------------------------
CREATE TABLE SAMPLE(
sample_num number,
sample_name varchar2(10)
CONSTRAINT sample_sample_num
);
DROP TABLE SAMPLE