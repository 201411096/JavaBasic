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
WHERE sal > (SELECT min(sal) FROM emp WHERE deptno=30);

SELECT *
FROM emp, (SELECT min(sal) AS minsal FROM emp WHERE deptno=30) ext
WHERE sal > ext.minsal;
-- 5. 10�� �μ��� ������ �� 30�� �μ��� ����� ���� ������ �ð� �ִ� ����� �̸��� ������ ���
SELECT ename, job
FROM emp
WHERE deptno=10 AND job IN (SELECT job FROM emp WHERE deptno=30);
-- 6. Ford�� ������ ���޵� ���� ����� ��� ������ ���
SELECT *
FROM emp
WHERE sal = (SELECT sal FROM emp WHERE ename = 'FORD') AND job = (SELECT job FROM emp WHERE ename='FORD') AND ename!='FORD'; 
