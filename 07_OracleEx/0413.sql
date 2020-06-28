/*----------------------------------------------------------------------------------------------------
�������� ����
----------------------------------------------------------------------------------------------------*/
-- 1. Blake�� ���� �μ��� �ִ� ��� ����� �̸��� �Ի����ڸ� ���
SELECT ename, hiredate, deptno
FROM emp
WHERE deptno = (SELECT deptno FROM emp WHERE ename='BLAKE');

SELECT e.ename AS ename, e.hiredate AS hiredate, e.deptno AS deptno
FROM emp e, (SELECT deptno AS deptno FROM emp WHERE ename='BLAKE') ext
WHERE e.deptno= ext.deptno;
-- 2. ��� �޿� �̻��� �޴� ��� ����� ���� ����� �̸��� ��� ��, �޿��� ���� ������ ����
SELECT *
FROM emp
WHERE sal >= (SELECT avg(sal) FROM emp)
ORDER BY sal desc;

SELECT e.empno AS empno, e.ename AS ename, e.sal AS sal
FROM emp e, (SELECT avg(sal) avgsal FROM emp) ext
WHERE e.sal >= ext.avgsal
ORDER BY sal desc;

-- 3. �μ���ġ�� Dallas�� ��� ����� ���� �̸�, ����, �޿��� ���
SELECT ename, job, sal
FROM emp
WHERE deptno = (SELECT deptno FROM dept WHERE loc='DALLAS');

SELECT e.ename AS ename, e.job AS job, e.sal AS sal
FROM emp e, (SELECT deptno FROM dept WHERE loc='DALLAS') ext
WHERE e.deptno=ext.deptno;
-- 4. ������ 30�� �μ��� ���� ���޺��� ���� ������� ���
SELECT *
FROM emp
WHERE sal > (SELECT min(sal) FROM emp WHERE deptno=30) AND deptno!=30;

SELECT *
FROM emp, (SELECT min(sal) AS minsal FROM emp WHERE deptno=30) ext
WHERE sal > ext.minsal  AND deptno!=30;
-- 5. 10�� �μ��� ������ �� 30�� �μ��� ����� ���� ������ �ð� �ִ� ����� �̸��� ������ ���
SELECT ename, job
FROM emp
WHERE deptno=10 AND job IN (SELECT job FROM emp WHERE deptno=30);

SELECT *
FROM emp e, (SELECT job FROM emp WHERE deptno=30) ext
WHERE e.deptno=10 AND e.job IN ext.job;
-- 6. Ford�� ������ ���޵� ���� ����� ��� ������ ���
SELECT *
FROM emp
WHERE sal = (SELECT sal FROM emp WHERE ename = 'FORD') AND job = (SELECT job FROM emp WHERE ename='FORD') AND ename!='FORD'; 

/*----------------------------------------------------------------------------------------------------
-------------------------- INSERT / UPDATE / DELETE���� ����ϴ� �������� -------------------------------
(1) �μ��� �޿���� ���̺� ����
create table stat_salary (
           stat_date   date  not  null,        -- ��¥
           dept_no   number,                    --�μ�
           emp_count number,      --�����
           tot_salary number,        --�޿��Ѿ�
           avg_salary number );     -- �޿����
(2) ��¥�� �μ���ȣ �Է�
(3) �����, �޿��Ѿ�, ��ձ޿� �Է� -> ����
 ----------------------------------------------------------------------------------------------------*/
create table stat_salary (
           stat_date   date  not  null,        -- ��¥
           dept_no   number,                    --�μ�
           emp_count number,      --�����
           tot_salary number,        --�޿��Ѿ�
           avg_salary number );     -- �޿����
--1. INSERT�������� ��������----------------------------------------------------------------------------------------------------
SELECT sysdate, deptno FROM dept; -- INSERT������ ����� ��������
INSERT INTO stat_salary(stat_date, dept_no) (SELECT sysdate, deptno FROM dept); -- VALUE�� ������� �ʰ� �������� �Ѳ����� ����
--2. UPDATE�������� ��������----------------------------------------------------------------------------------------------------
UPDATE stat_salary s
SET(s.emp_count, s.tot_salary, s.avg_salary) 
= ( SELECT count(*), sum(e.sal), round(avg(e.sal))
    FROM emp e
    WHERE e.deptno=s.dept_no                        --�򰥸��� �κ�(�������� �ȿ��� �ܺΰ��� ��...)
    GROUP BY e.deptno
    );
--�� ������������ WHERE���� ���� ���
UPDATE stat_salary s
SET(s.emp_count, s.tot_salary, s.avg_salary) 
= ( SELECT count(*), sum(e.sal), round(avg(e.sal))
    FROM emp e                                     -- ������ �ٸ��� ������ ������ ������������ �������� ���� ���ϵǸ鼭 ���� �߻�
    GROUP BY e.deptno
    );
--WHERE -- ��� �࿡ �� ������ ��쿡�� ����
--3. DELETE�������� ��������----------------------------------------------------------------------------------------------------
--emp copy ���̺��� �μ����� sales�� ����� ������ ����
DELETE
FROM emp_copy
WHERE deptno= (SELECT deptno FROM dept WHERE dept.dname='SALES');
rollback;
SELECT *
FROM emp_copy;
----------------------------------------------------------------------------------------------------
--��
CREATE VIEW v_emp AS
SELECT empno, ename, deptno from emp; -- ���� �����(ORA_01031)

DESC v_emp;
SELECT * from v_emp;
-- view�� �б� ������ �ƴ� (�Է�, ����, ������ ���������� �Ϲ������� �б⸸ ���)
INSERT INTO v_emp VALUES(1000, 'ȫ����', 20);
SELECT * FROM emp;
SELECT * FROM v_emp; 
-- view���� ������ ����� ���� ���̺� �ٷ� ������ �� -> view�� �������̺��̶�� �θ��� ����
INSERT INTO v_emp VALUES(1000, 'ȫ���', 30); -- ���� ���̺��� �⺻Ű �������ǿ� ���� ���� ���� ����
-- ������ ���������� �״�� ����
INSERT INTO v_emp VALUES(1001, 'ȫ���', 50); -- ���� ���̺��� ���� �������ǿ� ���� ���� ���� ����

DELETE FROM v_emp WHERE empno=1000; -- view������ ������ ���� ���̺� ������ ��

CREATE OR REPLACE VIEW v_emp AS
SELECT empno, ename, deptno from emp
with read only;                                       -- view�� �б� �������� ����
INSERT INTO v_emp VALUES(1000, 'ȫ����', 20);          -- �б� ���� view�� ������ �ؼ� ���� �Է�, ����, ������ ���� ����

/* updateable view----------------------------------------------------------------------------------------------------
    �������� ������ ��
[���� ] EMP ���̺��� 30�� �μ���
EMPNO�� EMP_NO�� ENAME�� NAME�� SAL�� SALARY�� �ٲ㼭
EMP_30�並 �����Ͻÿ�
*/--------------------------------------------------------------------------------------------------------------------
--1 view���� �̸��� �ٲ��ִ� ���
CREATE OR REPLACE VIEW v_emp(emp_no, name, salary) AS
select empno, ename, sal from emp where deptno=30;
--2 ������������ ��Ī�� �ִ� ���
CREATE OR REPLACE VIEW v_emp 
AS SELECT empno AS EMP_NO, ename AS NAME, sal AS SALARY FROM emp WHERE deptno=30;
--Ȯ��
select * from v_emp;
/*----------------------------------------------------------------------------------------------------
���պ�(complex view) : �� �� �̻��� �⺻ ���̺�� ������ �� <-> �ܼ� ��(simple view) : �ϳ��� �⺻ ���̺�� ������ ��
----------------------------------------------------------------------------------------------------*/
Create or replace view v_emp_info as
select e.empno empno, e.ename ename, d.dname dname
from emp e, dept d
where e.deptno=d.deptno;

select * from v_emp_info; -- Ȯ��

--���պ信���� �� �Է� Ȯ��
INSERT INTO v_emp_info VALUES(9999, '�����', 'SALES'); -- �ΰ��� ���̺��� ������ ���Ե������� ���պ信 ���� �Ұ���
INSERT INTO v_emp_info(empno, ename) VALUES(9999, '�����'); -- �ϳ��� ���̺��� ���鸸 �����ϴ� �� ����( �信�� �Էµ��� �ʰ�, �������̺��� ����)
DELETE FROM v__emp_info WHERE empno=9999; -- �信�� ���� ������ �ʾƼ� ������ ���� ����(���� �������̺��� ���� �� ����)
--> �Ϲ������� �б� �������� ����� ������ ũ�� �Ű澲�� �ʾƵ� �Ǵ� ��
--[ ���� ] �μ����� �μ���, �ּұ޿�, �ִ�޿�, �μ��� ��� �޿��� �����ϴ� DEPT_SUM �並�����Ͽ���.------------------------------------------------
CREATE VIEW v_deptgroup(dept_name, minsal, maxsal, avgsal)
AS SELECT d.dname, min(e.sal), max(e.sal), round(avg(e.sal))
    FROM emp e, dept d
    WHERE e.deptno=d.deptno
    GROUP BY d.dname
WITH read only;            -- view�� �б� �������� ����
--�信 ����� ��������
SELECT d.dname, min(e.sal), max(e.sal), round(avg(e.sal))
FROM emp e, dept d
WHERE e.deptno=d.deptno
GROUP BY d.dname;
--�������̺� ���� �Է��� �� �信�� Ȯ��------------------------------------------------------------------------------------------------------
INSERT INTO emp(empno, ename, sal, deptno) VALUES(8899, '������', 2000, 20); -- �������̺� �� �Է�
select * from v_deptgroup; -- �信�� ����� �������̺��� ������ ����
/*-------------------------------------------------------------------------------------------------------------------------------------------
������(SEQUENCE)
-------------------------------------------------------------------------------------------------------------------------------------------*/
/*------------------------------
[���� ]  ���̺� ������ ���� TEMP
no        number         pk
name    varchar2(20)
indate   date
*/-----------------------------
CREATE TABLE TEMP(
    no number,
    name varchar2(20),
    indate date,
    CONSTRAINT temp_no_pk PRIMARY KEY(no)
);
--PK�� 10000������ 100�� �����ϴ� ��ȣ(SEQUENCE ����) -- ������ ������ �ɼ� ���̿��� ,�� ���� ����
CREATE SEQUENCE seq_temp_no
    START WITH 10000
    INCREMENT BY 100;
INSERT INTO temp VALUES(seq_temp_no.nextval, 'ȫ���', sysdate); --10000���Ͱ��ƴ϶� 10100���� ��
--sequence�� �⺻�ɼ����� ����
CREATE SEQUENCE seq_emp_empno;
INSERT INTO emp_copy(empno, ename, sal)
VALUES(seq_emp_empno.nextval, 'ȫȫ��', 3000);

/*------------------------------------------------
dual ���̺�
    - ����Ŭ ��ü���� �����Ǵ� ���̺�
    - ������ �Լ��� �̿��� ������� Ȯ���� �� ���
    - dummy ���̺�, ���� ���̺�
*/-------------------------------------------------
SELECT seq_emp_empno.currval from dual; -- dual �������̺��� sequence�� ���簪(CURRVAL)�� Ȯ�� ����

/*-------------------------------------------------------------------------------------------------------------------------------------------
�ε���(INDEX) -- �˻� �ӵ��� ����Ŵ
 �� pk�� �⺻������ �޸𸮿� �ö�
 �� �ε��� ������ ������ �ε����� �޸𸮿� ���� �ö�
-------------------------------------------------------------------------------------------------------------------------------------------*/
--scott�������� Ȯ��-----------------------------
select empno, ename, rowid from emp;
--hr ������ �ε��� Ȯ��---------------------------
SELECT index_name, index_type, uniqueness
FROM user_indexes;
------------------------------------------------
------------------------------------------------
--Oracle �ڵ�����(AutoTrace) ���� �ο�
grant SELECT_CATALOG_ROLE to HR;
grant SELECT ANY DICTIONARY to HR;

SELECT * FROM employees; --f6 ���� �ڵ�����
-- 1. pk�� �ð� COST------------------------------------------------------
SELECT * FROM employees
WHERE employee_id=130; -- f6�� COST=1 (�ð� COST)
-- 2. �ε����� �ð� COST---------------------------------------------------
SELECT * FROM employees
WHERE email='MROGERS'; -- pk�� �ƴ����� �ε������� PK�� COST�� ����
--3. pk�� �ε����� �ƴ� ���� COST-------------------------------------------
SELECT * FROM employees
WHERE salary=3000; -- �ð� COST=3 + OPTIONS=FULL(full - scans)
-- ������ ���󵵰� ���ٸ� -> �ε����� ����ؼ� ���޵� �޸𸮿� ���� �ø�
CREATE INDEX idx_employees_sal ON employees(salary); -- employees�� ���̺��� salary�� �ε����� ����
SELECT * FROM employees WHERE salary=3000; -- �ε����� ���� �� �ڵ����� --> COST=2 + OPTIONS : BY INDEX
/*------------------------------------------------------------------------------------------------
�ε����� ���� ���̵����
    �� ���� ��ȸ�Ǵ� �÷��� �ε��� �÷����� ����(where���� �����̳� ���ν� ���� ���Ǵ� �÷�)
    �� ������ ���� ���� ���̺��� 15%������ �����͸� ��ȸ�� ���(�� ������ �ƴ� ��� �ε����� ����ϴ� ���� �� �ʾ��� ���� ����)
    �� ���̺��� ���� ���ŵǰų� ���� ��� �������� �ʴ� ���� ����
------------------------------------------------------------------------------------------------*/

