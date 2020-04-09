------------------------------------------------�߰� ���� DDL----------------------------------------------------------------
--1) student ���̺� �����
CREATE TABLE student(
    no char(4),
    name varchar2(10) not null,
    gender varchar2(4),
    addr varchar2(50),
    jumin varchar2(50) not null,
    deptno number(2), 
    CONSTRAINT student_deptno_fk FOREIGN KEY(deptno) REFERENCES dept(deptno)
);
--2) �й� �÷��� �⺻Ű(primarykey) �������� �߰�
ALTER TABLE student ADD CONSTRAINT student_no_pk PRIMARY KEY(no);
--3) �ֹι�ȣ �÷����� NOTNULL �������� ����
ALTER TABLE student MODIFY name NULL;
--4) �ּ� �÷��� ����Ʈ ������������ ����� �߰�
ALTER TABLE student MODIFY addr DEFAULT '����';
--5) ���� �÷��� üũ ������������ ��������, �������� �߰�
ALTER TABLE student ADD CONSTRAINT student_deptno_gender CHECK (gender in ('����', '����'));
-----------------------------------------------------------------------------------------------------------------------
INSERT INTO student(no, name, gender, addr,jumin ) VALUES('1111', '������','����', '����� ������','801212-1234567' );
INSERT INTO student(no, name, jumin ) VALUES('2222', '������','881212-1234567');
INSERT INTO student(no, name, jumin ) VALUES('1111', '������','881212-1234567'); -- �й� ���Ἲ üũ �������� ����
INSERT INTO student(no, name, gender, jumin) VALUES('3333', '������','����', '830303-1234567'); -- ���� üũ �������� ����
INSERT INTO student(no, name,  addr, jumin ) VALUES('4444', '�����','����� ���ʱ�', '990909-1234567');
INSERT INTO student(no, name,  addr, deptno ) VALUES('8888', '�̺���','����� �Ѱ�', 50);  -- jumin null �������� ����
-----------------------------------------------------------------------------------------------------------------------
--2) library ���̺� �����
CREATE TABLE library(
    rent_id number(10),
    book_id varchar2(20) not null,
    hakbun char(4),
    CONSTRAINT library_rent_id_pk PRIMARY KEY(rent_id),
    CONSTRAINT library_hakbun_fk FOREIGN KEY(hakbun) REFERENCES student(no)
);
INSERT INTO library ( rent_id, book_id,hakbun ) VALUES( 1, 'bk001', '1111' );
INSERT INTO library ( rent_id, book_id,hakbun ) VALUES( 2, 'bk002', '2222' );
INSERT INTO library ( rent_id, book_id,hakbun ) VALUES( 3, 'bk003', '3333' ); -- hakbun �θ�Ű �������� ����
INSERT INTO library ( rent_id, book_id,hakbun ) VALUES( 4, 'bk004', '4444' );
INSERT INTO library ( rent_id, book_id,hakbun ) VALUES( 5, 'bk005', '5555' ); -- hakbun �θ�Ű �������� ����
--------------------------------------------------------------------------------------------------------------------------------
--������� ���
SELECT ename, deptno FROM emp;

SELECT deptno, dname FROM emp;

SELECT emp.ename, emp.deptno, dept.dname -- ����
FROM emp, dept
WHERE emp.deptno = dept.deptno;

--���� ���� : null �� ������ �ȵ�
SELECT e.ename as ename, e.deptno as deptno, d.dname as dname -- ���̺� ��Ī ���
FROM emp e, dept d
WHERE e.deptno = d.deptno;

SELECT e.ename as ename, e.deptno as deptno, d.dname as dname  -- ������ ��������� �ӵ��� ������..?
FROM emp e, dept d
WHERE e.deptno = d.deptno AND d.dname='ACCOUNTING';

SELECT e.ename as ename, e.deptno as deptno, d.dname as dname 
FROM emp e, dept d
WHERE d.dname='ACCOUNTING' AND e.deptno = d.deptno;

--�ܺ� ����
SELECT e.ename as ename, e.deptno as deptno, d.dname as dname
FROM emp e, dept d
WHERE e.deptno = d.deptno(+); -- ������ �κп� +�� ���ָ�???? null���� �����ִ� �൵ ����� ���� ����
                              -- ������̺� �ִµ� �μ��ʿ��� ���� ����� ����

SELECT e.ename as ename, e.deptno as deptno, d.dname as dname
FROM emp e, dept d
WHERE e.deptno(+) = d.deptno; -- +�� �ݴ�� ���̸�..
                              -- �μ����̺� �ִµ� ����ʿ��� ���� ����� ����

----------------------------------------ANSI JOIN (INNER JOIN, OUTER JOIN)---------------------------------------------
--���� ����
SELECT e.ename as ename, e.deptno as deptno, d.dname as dname 
FROM emp e INNER JOIN dept d
ON e.deptno = d.deptno
WHERE d.dname='ACCOUNTING';
--�ܺ�����
SELECT e.ename as ename, e.deptno as deptno, d.dname as dname
FROM emp e LEFT OUTER JOIN dept d -- +�� DEPTNO�� �پ��ִ°ſ� ����
ON e.deptno = d.deptno;

------------------------------------------------INNER JOIN ����-------------------------------------------------------
--1. �����ȣ,�̸�,����,�μ���,�ٹ��� (���� ���)
SELECT e.empno as empno, e.ename as ename, e.job as job, d.dname as dname, d.loc as loc
FROM emp e, dept d
WHERE e.deptno = d.deptno;
--1. �����ȣ,�̸�,����,�μ���,�ٹ��� (ANSI)
SELECT e.empno as empno, e.ename as ename, e.job as job, d.dname as dname, d.loc as loc
FROM emp e INNER JOIN dept d
ON e.deptno = d.deptno;
--2. 'SALESMAN'�� �����ȣ,�̸�,����,�μ���,�ٹ��� (���� ���)
SELECT e.empno as empno, e.ename as ename, e.job as job, d.dname as dname, d.loc as loc
FROM emp e, dept d
WHERE e.deptno = d.deptno AND e.job='SALESMAN';
--2. 'SALESMAN'�� �����ȣ,�̸�,����,�μ���,�ٹ��� (ANSI)
SELECT e.empno as empno, e.ename as ename, e.job as job, d.dname as dname, d.loc as loc
FROM emp e INNER JOIN dept d
ON e.deptno = d.deptno
WHERE e.job='SALESMAN';
--3. ���ʽ�(comm)�� �޴� ����� ���� �̸�, �μ���, ��ġ�� ��� (���� ���)
SELECT e.ename as ename, d.dname as dname, d.loc as loc
FROM emp e, dept d
WHERE e.deptno=d.deptno AND e.comm is not null AND e.comm!=0;
--3. ���ʽ�(comm)�� �޴� ����� ���� �̸�, �μ���, ��ġ�� ��� (ANSI)
SELECT e.ename as ename, d.dname as dname, d.loc as loc
FROM emp e INNER JOIN dept d
ON e.deptno=d.deptno
WHERE e.comm is not null AND e.comm!=0;
--4. �μ��� �μ���� �޿� �հ踦 ��� (���� ���)
SELECT d.dname as dname, sum(e.sal) as sal
FROM emp e, dept d
WHERE e.deptno=d.deptno
GROUP BY d.dname;   -- d.deptno���� �׷��� ���� ��� d.name�� ����� �ȵ�
                    -- d.name����� �ϰ� ���� ��� d.name���� ������� ����� ����
--*********************** group by�� ���� ��� select���� ����� �� �ִ� ���� group by�� ����� �÷���� group�Լ���
--4. �μ��� �μ���� �޿� �հ踦 ��� (ANSI)
SELECT d.dname as dname, sum(e.sal) as sal
FROM emp e INNER JOIN dept d
ON e.deptno=d.deptno
GROUP BY d.dname;
-- 5. ������ ��MANAGER���� ����� ������ �̸�, ����, �μ���, �ٹ����� ��� (���� ���)
SELECT e.ename as ename, e.job as job, d.dname as dname, d.loc as loc
FROM emp e, dept d
WHERE e.deptno=d.deptno AND e.job='MANAGER';
-- 5. ������ ��MANAGER���� ����� ������ �̸�, ����, �μ���, �ٹ����� ��� (ANSI)
SELECT e.ename as ename, e.job as job, d.dname as dname, d.loc as loc
FROM emp e INNER JOIN dept d
ON e.deptno=d.deptno
WHERE job='MANAGER';
----------------------------------------------------------------------------------------------------------------------------
-------------------------�ܺ� ����------------------------------------------------------
--����1 <���� ���>
SELECT e.ename AS ename, e.deptno AS deptno, d.dname AS dname
FROM emp e, dept d
WHERE e.deptno = d.deptno(+);
--����1 <ANSI>
SELECT e.ename AS ename, e.deptno AS deptno, d.dname AS dname
FROM emp e LEFT OUTER JOIN dept d -- LEFT, RIGHT�� (+)�� ���̴� ���� �ݴ������� �ٿ��ߵ�
ON e.deptno = d.deptno;

--����2 <���� ���>
SELECT e.ename AS ename, e.deptno AS deptno, d.dname AS dname
FROM emp e, dept d
WHERE e.deptno(+) = d.deptno;
--����2 <ANSI>
SELECT e.ename AS ename, e.deptno AS deptno, d.dname AS dname
FROM emp e RIGHT OUTER JOIN dept d -- LEFT, RIGHT�� (+)�� ���̴� ���� �ݴ������� �ٿ��ߵ�
ON e.deptno = d.deptno;

--����3 <���� ��� - ������ ������ �κ�>
SELECT e.ename AS ename, e.deptno AS deptno, d.dname AS dname
FROM emp e, dept d
WHERE e.deptno(+) = d.deptno(+);
--����3 <ANSI ���� ������ �κ�>
SELECT e.ename AS ename, e.deptno AS deptno, d.dname AS dname
FROM emp e FULL OUTER JOIN dept d -- LEFT, RIGHT�� (+)�� ���̴� ���� �ݴ������� �ٿ��ߵ�
ON e.deptno = d.deptno;

----------------------------------------------------------------------------------------------------------
--���� ����
--�����ȣ,�����, �Ŵ�����ȣ, �Ŵ����̸�
SELECT e1.empno AS empno, e1.ename AS ename, e1.mgr AS mgr, e2.ename AS mgrname
FROM emp e1, emp e2 -- e1 : ����� ã�� ���̺�, e2 : �Ŵ����� ã�� ���̺�
WHERE e1.mgr=e2.empno; -- ���� ������ ���� president�� ��簡 ���� ȫ�浿 �����Ͱ� ������ ����

SELECT e1.empno AS empno, e1.ename AS ename, e1.mgr AS mgr, e2.ename AS mgrname
FROM emp e1, emp e2 -- e1 : ����� ã�� ���̺�, e2 : �Ŵ����� ã�� ���̺�
WHERE e1.mgr=e2.empno(+); -- ���� ������ ���� president�� ��簡 ���� ȫ�浿 �����Ͱ� ������ �ϱ� ���ؼ� OUTER JOIN ���

SELECT e1.empno AS empno, e1.ename AS ename, e1.mgr AS mgr, e2.ename AS mgrname
FROM emp e1 LEFT OUTER JOIN emp e2 -- e1 : ����� ã�� ���̺�, e2 : �Ŵ����� ã�� ���̺�
ON e1.mgr=e2.empno; -- ���� ������ ���� president�� ��簡 ���� ȫ�浿 �����Ͱ� ������ �ϱ� ���ؼ� OUTER JOIN ���
----------------------------------------------------------------------------------------------------------
--1. �����ں��� ���� �Ի��� ����� ���� �̸�, �Ի���, ������ �̸�, ������ �Ի����� ���
SELECT e1.ename AS ename, e1.hiredate AS hiredate, e2.ename AS mgrename, e2.hiredate AS mgrhiredate
FROM emp e1, emp e2
WHERE e1.mgr = e2.empno AND e1.hiredate<e2.hiredate;
--(ANSI)
SELECT e1.ename AS ename, e1.hiredate AS hiredate, e2.ename AS mgrename, e2.hiredate AS mgrhiredate
FROM emp e1 INNER JOIN emp e2
ON e1.mgr = e2.empno
WHERE e1.hiredate<e2.hiredate;
--2. ����� ��L���ڰ� �ִ� ����� ���� �̸�, ����, �μ���, ��ġ�� ���
SELECT e.ename AS ename, e.job AS job, d.dname, d.loc
FROM emp e, dept d
WHERE e.deptno = d.deptno AND regexp_like(e.ename, 'L'); -- LIKE '%L%';
-- LIKE ���
SELECT e.ename AS ename, e.job AS job, d.dname, d.loc
FROM emp e, dept d
WHERE e.deptno = d.deptno AND e.ename LIKE '%L%';
--(ANSI)
SELECT e.ename AS ename, e.job AS job, d.dname, d.loc
FROM emp e INNER JOIN dept d
ON e.deptno = d.deptno
WHERE regexp_like(e.ename, 'L');
--3. �Ʒ��� ����� ��� ( �����ڰ� ���� KING�� �����Ͽ� ��� ����� ��� )
SELECT e1.empno AS empno, e1.ename AS ename, e2.empno AS mgrno, e2.ename AS mgrname
FROM emp e1, emp e2
WHERE e1.mgr=e2.empno(+);
--(ANSI)
SELECT e1.empno AS empno, e1.ename AS ename, e2.empno AS mgrno, e2.ename AS mgrname
FROM emp e1 LEFT OUTER JOIN emp e2
ON e1.mgr=e2.empno;
