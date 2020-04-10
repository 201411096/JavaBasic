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

--2. �ֹ��� ��ǰ ������ �� ���� ���
SELECT g.gno AS GNO, g.gname AS GNAME, g.price AS GPRICE, m.id AS MID, m.name AS MNAME, m.tel AS MTEL, m.addr AS MADDR
FROM ex_order o, ex_good g, ex_member m
WHERE o.gno=g.gno AND o.id=m.id;

SELECT g.gno AS GNO, g.gname AS GNAME, g.price AS GPRICE, m.id AS MID, m.name AS MNAME, m.tel AS MTEL, m.addr AS MADDR
FROM ex_order o 
INNER JOIN ex_good g
ON o.gno=g.gno
INNER JOIN ex_member m
ON o.id=m.id;

--3. �ֹ���ȣ���� �� ����(���̵�)�� ������ ���
SELECT o.id AS ID, sum(o.count*g.price) AS SUM
FROM ex_order o, ex_good g
WHERE o.gno=g.gno
GROUP BY o.orderno, o.id; -- group by�� ���� �÷��� �׷��Լ��� select���� ��밡��
                          -- group by o.ordername, o.id, ____ �ְ� ���� �͵��� ������� �ִ� ���� subquery�� �ذ� ����
                          -- group by Į���� select���� ����� ���������� �׷��Լ��ȿ��� ��κ���? Į���� �ִ°� ������ ��
SELECT o.id AS ID, sum(o.count*g.price) AS SUM
FROM ex_order o 
INNER JOIN ex_good g
ON o.gno=g.gno
GROUP BY o.orderno, o.id;

--GROUP BY ���ÿ� select������ ����� �ȵǵ� groupby�Լ��� ����� �����ѵ�------------------------------
--4. 3���� �ֹ� ������ ù��° ��ǰ�� �� �� ���� ��� ex)  �Ӹ��� �� 2�� 
--����ϰ� ������� �ϴµ� �ֹ��� ������� ��µǴ°� �ƴѵ�
SELECT o.id AS ID, sum(o.count*g.price) AS SUM, min(g.gname)||' �� '||(sum(o.count)-1)||'��' AS �ֹ�����
FROM ex_order o, ex_good g
WHERE o.gno=g.gno
GROUP BY o.orderno, o.id;

SELECT o.id AS ID, sum(o.count*g.price) AS SUM, min(g.gname)||' �� '||(sum(o.count)-1)||'��' AS �ֹ�����
FROM ex_order o INNER JOIN ex_good g
ON o.gno=g.gno
GROUP BY o.orderno, o.id;
----------------------------------------------------------------------------------------------------
--SubQuery
/*----------------------------------------------------------------------------------------------------
- ���������� ( ) �� ���´� [����]
- ���������� �������� �����ʿ� [����]
- ���� ���� ��� �� ������ ���� ( >, >=, <, <=, =, !=(<>) ) ex) max, min, sum, avg => subquery�� ����� �ϳ��� �͵�..
- ���� ���� ��� IN, NOT IN, ANY, ALL, EXISTS
- ORDER BY���� ��� ���Ѵ�
*/----------------------------------------------------------------------------------------------------
-- ����
-- ��ü ����� �� ��ձ޿����� ���� �޿��� �޴� ����� ����� ��ȸ
-- 1) ��ձ޿�
SELECT round(avg(sal)) from emp ;
-- 2) �� ��ձ޿����� ���� �޿��� �޴� ����� ����� ��ȸ
SELECT ename, sal
FROM emp
WHERE sal< (SELECT round(avg(sal)) from emp) ; -- WHERE sal < (��ձ޿�);

--�������� ��� ( >, >=, <, <=, =, !=(<>) )

-- 1_1) �ְ� ���� ���ϱ�
SELECT MAX(sal)
FROM emp;
-- 1_2) ���� ���� ���� �޴� ��� ���ϱ�
SELECT *
FROM emp
WHERE sal=(SELECT MAX(sal)
            FROM emp);
            
--2) ��� �޿����� ���� �ִ� �޿����� ���� ������ �޴� ����� ��������ȸ
SELECT *
FROM emp
WHERE sal<(SELECT MAX(sal) FROM emp) AND sal>(SELECT AVG(sal) FROM emp);
--�������̺� ����--------------------------------------------------------------------------------
SELECT e.*
FROM emp e,
    (��հ� �ְ�) ext
WHERE e.sal>��� AND e.sal<�ְ�;
--�������̺� ����--------------------------------------------------------------------------------
SELECT e.*
FROM emp e,
    (SELECT avg(sal) avg, max(sal) max FROM emp) ext -- �������̺� ext
WHERE e.sal>ext.avg AND e.sal<ext.max;
-----------------------------------------------------------------------------------------------
--3) ���޼����� ���� 10���Ǹ���� ��� -- �������̺� ���
SELECT *
FROM emp
WHERE ROWNUM <=10
ORDER BY nvl(sal,0) desc nulls last; -- ����ƴ� // rownum ������ order by���� ���� ����Ǽ� ����� �� ������ ���� ����

SELECT * -- 3_1) ���� ������ ����
FROM emp
ORDER BY nvl(sal,0) desc nulls last;

SELECT ext.* --3_2) ���� ������ ������ ���̺��� �����ͼ� 10������ ���
FROM (SELECT *
      FROM emp
      ORDER BY nvl(sal,0) desc nulls last) ext
WHERE ROWNUM<=10;
-----------------------------------------------------------------------------------------------
/* ����<��������>
1. SCOTT�� �޿����� ���� ����� ������ �����ȣ, �̸�, ������, �޿��� ���
2. 30�� �μ��� �ּ� �޿����� ���μ��� �ּ� �޿��� ���� �μ��� ���
3. �������� ��� �޿� �߿��� ���� ���� �޿��� ���� ������ ���
4. �����ȣ�� 7521�� ������ ���� ��� 7934�� �������� �޿��� ���� �޴� ����� ������ ���
5. 'WARD'�� �μ��� ������ ���� ��� ��� ���
*/
-----------------------------------------------------------------------------------------------
select * from emp;
-- 1. SCOTT�� �޿����� ���� ����� ������ �����ȣ, �̸�, ������, �޿��� ���
SELECT empno, ename, job, sal
FROM emp
WHERE sal>( SELECT sal FROM emp WHERE ename='SCOTT' );
--2. 30�� �μ��� �ּ� �޿����� ���μ��� �ּ� �޿��� ���� �μ��� ��� ----------having ������ group�Լ� ��� ����
SELECT deptno, min(sal)
FROM emp
GROUP BY deptno
HAVING min(sal)>(SELECT min(sal) FROM emp GROUP BY deptno HAVING deptno=30);

--3. �������� ��� �޿� �߿��� ���� ���� �޿��� ���� ������ ��� 
SELECT e.job, avg(e.sal)
FROM emp e, (SELECT avg(sal) as avgsal  FROM emp GROUP BY job ORDER BY avg(sal)) ext
group by e.job
having avg(e.sal)=min(ext.avgsal);
--rownum �̿�
SELECT e.job
FROM (SELECT job, avg(sal) FROM emp GROUP BY job ORDER BY avg(sal)) e -- ������ ��ձ޿��� ���ؼ� ������ �ص�
WHERE rownum=1;                                                            -- ù�ٸ� �����ͼ� �޿��� ����

--������ ��� �޿�
SELECT avg(sal)
FROM emp
GROUP BY job
ORDER BY avg(sal);

--4. �����ȣ�� 7521�� ������ ���� ��� 7934�� �������� �޿��� ���� �޴� ����� ������ ���
SELECT *
FROM emp
WHERE job=(SELECT job FROM emp WHERE empno=7521) AND sal>(SELECT sal FROM emp WHERE empno=7934);

--5. 'WARD'�� �μ��� ������ ���� ��� ��� ��� -- as�� from���� ����� �� ���� <�������̺� �̸� ���� �� �򰥸� �� ���� ��)
SELECT e.empno, e.ename, e.job, e.deptno
FROM emp e, (SELECT job, deptno FROM emp WHERE ename='WARD') ext
WHERE e.job=ext.job AND e.deptno=ext.deptno;
