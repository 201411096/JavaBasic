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