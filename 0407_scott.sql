SELECT ename || '[' || job || ']' AS ename, sal, sal+nvl(comm, 0) AS total_sal FROM emp;

SELECT DISTINCT job FROM emp;

SELECT deptno FROM emp;
SELECT deptno, job FROM emp;
SELECT DISTINCT deptno, job FROM emp;

--1. 20�� �μ��� �����ȣ, �̸�, �μ���ȣ�� ���
SELECT empno, ename, deptno
FROM emp
WHERE deptno=20;

--2. �Ի����� 81/01/01���� 81/06/09�� ����� �����ȣ, �̸�, �Ի����� ���
SELECT empno, ename, hiredate
FROM emp
WHERE hiredate >= '81.01.01' and hiredate <= '81/06/09';

--2. �Ի����� 81/01/01���� 81/06/09�� ����� �����ȣ, �̸�, �Ի����� ��� (BETWEEN AND �� ���� ������ �����ϴ� ���)
SELECT empno, ename, hiredate
FROM emp
WHERE hiredate BETWEEN '81/01/01' and '81/06/09';

--3. �������� salesman, clerk�� ������� �̸��� ������ ���
SELECT ename, job
FROM emp
WHERE job='SALESMAN' OR job='CLERK'; -- �빮�� ����ؾ� ����

--4. ������ president�̰� �޿��� 1500�̻��̰ų� ������ salesman�� ����� ������ ���
SELECT *
FROM emp
WHERE (job='PRESIDENT' AND SAL >=1500) OR (job ='SALESMAN');

--5. ������ president �Ǵ� salesman�̰� �޿��� 1500�̻��� ����� ������ ���
SELECT *
FROM emp
WHERE (job='PRESIDENT' or job='SALESMAN') AND sal>=1500;

--6. Ŀ�̼�(comm)�� ���� ����� �̸�, �޿�, Ŀ�̼��� ���
SELECT ename, sal, comm
FROM emp
WHERE comm IS NULL or comm=0;

--7. �����, �޿�, Ŀ�̼�, �ѱ޿�( �޿� + Ŀ�̼�)�� ���
SELECT ename, sal, comm, sal+nvl(comm, 0) AS totalsal
FROM emp;

--8. �̸� A�� �����ϴ� ����� ���
SELECT ename
FROM emp
WHERE ename LIKE 'A%';

--9. �̸��� �ι�° ���ڰ� L�� ����� ���
SELECT ename
FROM emp
WHERE ename LIKE '_L%';

--10. �̸��� L�� �� �� �̻� ���Ե� �����
SELECT ename
FROM emp
WHERE ename LIKE '%L%L%';

--11. Ŀ�̼�(COMM)�� NULL�� �ƴ� ����� ��� ������ ���
SELECT *
FROM emp
WHERE comm IS NOT NULL;

--12. ���ʽ��� �޿����� 10%�� ���� ��� ����� ���� �̸�, �޿�, ���ʽ��� ���
SELECT ename, sal, comm
FROM emp
WHERE comm >= sal*1.1;

--13. ������ clerk�̰ų� analyst�̰� �޿��� 1000, 3000, 5000�� �ƴ� ��� ����� ������ ���
SELECT *
FROM emp
WHERE (job='CLERK' or job='ANALYST') AND (SAL!=1000 AND SAL!=3000 AND SAL!=5000);

SELECT *
FROM emp
WHERE job IN ('CLERK', 'ANALYST') AND sal NOT IN(1000, 3000, 5000);
--14. �μ��� 30�̰ų� �Ǵ� �����ڰ� 7782�� ����� ��� ������ ���
SELECT *
FROM emp
WHERE deptno=30 OR mgr=7782;

INSERT INTO emp(empno, ename, job) VALUES(8000, 'ȫ�浿', '������');

SELECT *
FROM emp
ORDER BY deptno ASC, sal DESC;
-- �ֱ� �Ի��Ѽ����� �����, �޿�, �Ի����ڸ� ���
SELECT ename, sal, hiredate
FROM emp
ORDER BY hiredate DESC;

-- Ŀ�̼��� ���� ������ ���� (��, Ŀ�̼��� ������ ���� ���߿� ��µǵ��� )
SELECT *
FROM emp
ORDER BY comm DESC NULLS last;
 
SELECT *
FROM emp
ORDER BY nvl(comm, 0) DESC;

-- �μ���ȣ�� ������ �� �μ���ȣ�� ���� ���� �޿��� ���������� �����Ͽ� �����ȣ, �̸�, ����, �μ���ȣ, �޿��� ���

SELECT empno, ename, job, deptno, sal
FROM emp
ORDER BY deptno ASC, sal DESC;

--�����, �޿�, ����(�޿�/12)�� ����ϵ� ������ �ʴ������� �ݿø��Ͽ� ���
SELECT ename, sal, ROUND((sal/12), -2) as "����" -- �ѱ۷� ó���Ҷ��� ""���
FROM emp;

--�����, �޿�, ����(�޿��� 3.3%)�� ������ �����ϰ� ���
SELECT ename, sal, TRUNC(sal*0.033, -1)as tax
FROM emp;

 -- smith�������� �����ȣ, ����, ������(�ҹ���) ���
SELECT empno, ename, lower(job) AS job
FROM emp
WHERE ename=upper('smith');

-- �����ȣ, �����(ù���ڸ� �빮��), ������(ù���ڸ��빮��)�� ���
SELECT empno, INITCAP(ename) AS ename, INITCAP(job) AS job
FROM emp;

-- �̸��� ù���ڰ� ��K������ũ�� ��Y������ ���� ����� ����( �����ȣ, �̸�, ����, �޿�, �μ���ȣ)�� ����ϵ� �̸������� ����
SELECT empno, ename, job, sal, deptno
FROM emp
WHERE SUBSTR(ename, 1, 1) > 'K' AND SUBSTR(ename, 1, 1) < 'Y'
ORDER BY ename;

--info_tab ���̺��� �ֹι�ȣ �⵵�� ����ϱ�
SELECT SUBSTR(jumin, 1, 2) AS jumin
FROM info_tab;

--info_tab ���̺��� ������ ����ϱ�
SELECT SUBSTR(jumin, 8, 1) AS jumin
FROM info_tab;

-- �̸��� 5���� �̻��λ������ ���
SELECT *
FROM emp
WHERE LENGTH(ename)>=5; 

-- �̸��� 15�ڷ� ���߰���ڴ� ���ʿ� �����ʿ��� ��*���� ä���
SELECT rpad(ename, 15, '*') as ename
FROM emp;

-- �޿��� 10�ڷ� ���߰���ڴ� �����ʿ� ���ʿ� ��-���� ä���
SELECT lpad(sal, 10, '-') as sal
FROM emp;

-- ���� ������ ����

-- �޿��� ���ڿ��� �������̻�����ĥ�ȱ��� ���ڷ� ��ü
SELECT translate(sal, '0123456789', '�����̻�����ĥ�ȱ�') as sal
FROM emp;
-- �޿��� ���ڿ��� 0����$���� �ٲپ� ���
SELECT replace(sal, 0, '$') sal
FROM emp;

SELECT sysdate
FROM DUAL;

SELECT 1+2
FROM DUAL;

SELECT ltrim(' ȫ �� �� ') AS name
FROM DUAL;

SELECT replace(' ȫ �� �� ', ' ', '') name
FROM DUAL;

-- ������� �ٹ��� ���� ���� ��� ������ ���
SELECT *
FROM emp
ORDER BY sysdate - nvl(hiredate, sysdate) desc ;

-- ������� �ٹ��� ���� �� �� �� ���ΰ��� ���
SELECT trunc(trunc(sysdate-hiredate)/7) as week, round(mod((sysdate-hiredate),7)) as days
FROM emp;

-- 10�� �μ��� ����� ��������� �ٹ� ������ ��� ..?
SELECT ename, TRUNC(MONTHS_BETWEEN(sysdate, hiredate), 0) as "�ٹ�����"
FROM emp
WHERE deptno=10;

-- ���� ��¥���� 3�������� ��¥ ���ϱ� select  add_months( sysdate, 3 ) as mydate from dual;
select  add_months( sysdate, 3 ) as mydate from dual; -- ���� �� // 3�� ��

select sysdate+3 as mydate from dual; -- 3�� ��
-- ���� ��¥���� ���ƿ��� ������������ ��¥ ���ϱ�
SELECT next_day(sysdate, '��') FROM DUAL; -- ������ ���� ���ϸ� ���� , monday �Ұ���        

-- ���� ��¥���� �ش� ���� ������ ��¥ ���ϱ�
SELECT last_day(sysdate) FROM DUAL;

-- �Ի����ڿ��� �Ի�⵵�� ���
SELECT ename, hiredate, to_char(hiredate,'YYYY') as hire_year -- ���� �̾ƿ���
FROM emp;

SELECT ename, hiredate, to_char(hiredate,'MM') as hire_year -- �� �̾ƿ���
FROM emp;
          

-- �Ի����ڸ� ��1999�� 1�� 1�ϡ� �������� ���
SELECT ename ,hiredate, to_char(hiredate, 'YYYY"��" MM"��" DD"��"') AS hireyear -- ��¥����(DATE_FORMAT) �κ� �ۼ��� �ѱ� �κ��� ū����ǥ�� �ѹ��� �� ���������
FROM EMP;

-- �޿� �տ� $�� �����ϰ� 3�ڸ� ���� ,�� ���
SELECT ename, sal, to_char(sal, '$999,999,999') as dollar
FROM emp;

-- 1981�⵵�� �Ի��� ��� �˻�
SELECT *
FROM emp
WHERE to_char(hiredate,'YYYY')='1981';
 

-- 5���� �Ի��� ��� �˻�
SELECT *
FROM emp
WHERE to_char(hiredate, 'MM')='05';
-- 81�� 2���� �Ի��� ��� �˻�
SELECT *
FROM emp
WHERE to_char(hiredate, 'YYYYMM')='198102';
-- 81�⿡ �Ի����� ���� ��� �˻�
SELECT *
FROM emp
WHERE to_char(hiredate, 'YYYY')!='1981';
-- 81�� �Ϲݱ⿡ �Ի��� ��� �˻�
SELECT *
FROM emp
WHERE to_char(hiredate, 'YYYYMM')>='198107' AND to_char(hiredate, 'YYYYMM')<='198112';

