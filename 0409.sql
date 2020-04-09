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
--4. �μ��� �μ���� �޿� �հ踦 ��� (ANSI)
SELECT d.dname as dname, sum(e.sal) as sal
FROM emp e INNER JOIN dept d
ON e.deptno=d.deptno
GROUP BY d.dname;
-- 5. ������ ��MANAGER���� ����� ������ �̸�, ����, �μ���, �ٹ����� ��� (���� ���)
SELECT e.ename as ename, e.job as job, d.dname as dname, d.loc as loc
FROM emp e, dept d
WHERE e.deptno=d.deptno AND job='MANAGER';
-- 5. ������ ��MANAGER���� ����� ������ �̸�, ����, �μ���, �ٹ����� ��� (ANSI)
SELECT e.ename as ename, e.job as job, d.dname as dname, d.loc as loc
FROM emp e INNER JOIN dept d
ON e.deptno=d.deptno
WHERE job='MANAGER';


