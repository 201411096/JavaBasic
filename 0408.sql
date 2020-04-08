-- �����ȣ, �̸�, ����, �޿�, ���� �޿��� 15% ������ �޿�(new salary), �������� ���
SELECT empno, ename, job, sal, round(sal*1.15) as newsalary, round(sal*0.15) as increase
FROM emp;
-- �̸�, ����, �Ի���, �Ի��� ����
SELECT ename, job, TO_CHAR(hiredate, 'YYYY/MM/DD'), TO_CHAR(hiredate, 'day')
FROM emp;
--�̸� �Ի���, �Ի��Ϸκ��� ��������� ���, �޿�, �Ի��Ϸκ��� ������� ���� �޿��� �Ѿ�
SELECT ename, TO_CHAR(hiredate, 'YYYY/MM/DD'), (TO_CHAR(sysdate,'YYYY')-TO_CHAR(hiredate,'YYYY')) as years, sal, (TO_CHAR(sysdate,'YYYY')-TO_CHAR(hiredate,'YYYY'))*sal as saltotal
FROM emp;

SELECT ename, TO_CHAR(hiredate, 'YYYY/MM/DD') as hiredate, trunc(months_between(sysdate, hiredate)/12) as years, sal, round(trunc(months_between(sysdate, hiredate)/12)*(sal/12)) as saltotal
FROM emp;


/*
(5) �����Լ�
           :if ~ else if ~ else ����
DECODE ( expr, search 1, result1, search2, result2, ... , [default] )

CASE expr WHEN condition1 THEN result1
          WHEN condition2 THEN result2
          ...
          ELSE  default
END
-- �ֹι�ȣ���� ���� ���ϱ�
SELECT  decode( substr(jumin, 8, 1), ��1��, '����', ��3��, '����', '����')AS  gender  FROM table_name;

SELECT  CASE  substr(jumin, 8, 1)
         WHEN '1' THEN '����'
         WHEN'3' THEN '����'
         ELSE '����'
END    as gender
FROM   emp;
*/

--�ֹι�ȣ���� �������ϱ�
SELECT jumin, decode( substr(jumin,8,1), '1', '����', '3', '����', '����')
    AS gender
FROM info_tab;

SELECT jumin, CASE substr(jumin, 8, 1)
    WHEN '1' THEN '����'
    WHEN '3' THEN '����'
    ELSE '����'
    END gender
FROM info_tab;

-- �μ���ȣ�� 10�̸鿵����, 20�̸� ������, 30�̸� IT�� �� �ܴ� ����η� ���
SELECT deptno, decode(deptno, '10', '������', '20', '������', '30', 'IT��', '�����')
    AS �μ�
FROM emp;

SELECT deptno, CASE deptno
    WHEN 10 THEN '������'
    WHEN 20 THEN '������'
    WHEN 30 THEN 'IT��'
    ELSE '�����'
    END �μ�
FROM emp;
          
-- ����(job)�� analyst�̸� �޿� ������ 10%�̰� clerk�̸� 15%, manager�̸� 20%�� ��� �����ȣ, �����, ����, �޿�, ������ �޿��� ���
SELECT empno, ename, job, decode(job, 'ANALYST', sal*1.1, 'CLERK', sal*1.15, 'MANAGER', sal*1.2, sal) as sal, decode(job, 'ANALYST', sal*0.1, 'CLERK', sal*0.15, 'MANAGER', sal*0.2, 0) as increased
FROM emp;

SELECT empno, ename, job ,sal, CASE job
                                WHEN 'ANALYST' THEN sal*1.1
                                WHEN 'CLERK' THEN sal*1.15
                                WHEN 'MANAGER' THEN sal*1.2
                                ELSE sal
                                END sal                        
FROM emp;
----------------------------------------------# HR �������� ( employees ���̺� )
--1. 2003�⿡ �Ի��� ������� ���, �̸�, �Ի����� ���
SELECT employee_id, first_name||' '||last_name, hire_date
FROM EMPLOYEES
WHERE TO_CHAR(hire_date, 'YYYY')='2003';
--2. ������ FI_ACCOUNT / FI_MGR / SA_MAN / SA_REP �� ������� ������ ���
SELECT *
FROM employees
WHERE job_id='FI_ACCOUNT' OR job_id='FI_MGR' OR job_id='SA_MAN' OR job_id='SA_REP';
--3. Ŀ�̼��� �޴� ������� ����� ���
SELECT *
FROM employees
WHERE commission_pct is not null;

--4.������ SA_MAN �Ǵ� SA_REP�̸� "�Ǹźμ�"�� �� �ܴ� "�� �� �μ�"��� ���
SELECT CASE job_id
        WHEN 'SA_MAN' THEN '�Ǹźμ�'
        WHEN 'SA_REP' THEN '�Ǹźμ�'
        ELSE '�� �� �μ�'
        END �μ�
FROM employees;

SELECT decode(job_id, 'SA_MEN', '�Ǹźμ�', 'SA_REP', '�Ǹźμ�', '�� �� �μ�') as �μ�
FROM employees;
------------------------------------------------------------------------------------------------------------------

--������ 'salesman'�� ������� ������ ���, ����, �ּҰ�, �ִ밪�� ���ϱ�
SELECT count(*) as count, avg(nvl(comm,0)) as avg, sum(nvl(comm,0)) as sum, min(nvl(comm,0)) as min, max(nvl(comm,0)) as max
FROM emp
WHERE JOB='CLERK';

SELECT count(*)
FROM emp
WHERE comm IS NOT NULL AND comm!=0;

-- �μ������ο���, ��ձ޿�, �����޿�, �ְ�޿�, �޿��� ���� ���ϱ�
SELECT deptno, count(*), avg(sal), min(sal), max(sal), sum(sal)
FROM emp
GROUP BY deptno;
-- �μ������ο���, ��ձ޿�, �����޿�, �ְ�޿�, �޿��� ���� ���ϱ� (�μ��� �޿��� ���� ���� ������
SELECT deptno, count(*), avg(sal), min(sal), max(sal), sum(sal) as sum_sal
FROM emp
GROUP BY deptno
ORDER BY sum_sal desc nulls last;
 
-- �μ��������� �׷��Ͽ� �μ���ȣ, ����, �ο���, �޿��� ���, �޿��� ���� ���ϱ�
SELECT deptno, job, count(*), avg(sal), sum(sal)
FROM emp
GROUP BY deptno, job;
 
-- �ִ� �޿��� 2900 �̻��κμ��� ���� �μ���ȣ, ��� �޿�, �޿��� ���� ���
SELECT deptno, count(*) , round(avg(sal)), sum(sal)
FROM emp
GROUP BY deptno
HAVING max(sal)>=2900;

-- ������ �޿��� ����� 3000�̻��� ������ ���� ������, ��� �޿�, �޿��� ���� ���
SELECT job, avg(sal), sum(sal)
FROM emp
GROUP BY job
HAVING avg(sal)>=3000;

-- ��ü �հ� �޿��� 5000���ʰ��ϴ� �� ������ ���ؼ� ������ �޿� �հ踦 ��� ��, SALESMAN�������ϰ� �޿� �հ谡 ���� ������ ����
SELECT job, sum(sal)
FROM emp
WHERE job!='SALESMAN'
GROUP BY job
HAVING sum(sal)>5000
ORDER BY sum(sal) desc;

--  �������ְ� �޿��� �ּ� �޿��� ���̸� ���϶�
SELECT (max(sal)-min(sal)) as diff
FROM emp
GROUP BY job;

-- �μ� �ο��� 4���� ���� �μ��� �μ���ȣ, �ο���, �޿��� ���� ���
SELECT deptno, count(*), sum(sal)
FROM emp
GROUP BY deptno
HAVING count(*)>4;

/*
[ GROUP BY ���� ����ϴ� �Լ� ]

- ROLLUP : ����� �׷캰 �հ� ������ �߰� -- ������ �κп�...

- CUBE : �׷��� �� �÷��� �հ� ������ �߰� -- �� �� �κп�...
*/
select job, sum( sal ) from emp group by job;
select job, sum( sal ) from emp group by rollup(job); -- �������� ���� �߰�
select job, sum( sal ) from emp group by cube(job); -- ������ ���� �߰�

------------------------------------------------------------------------------
/*
1. ���� chapter 4 / 5
2. grouping ���� Ǯ��
3. ����Ŭ IP ����
    - sql developer ���� : localhost -> ip
    - ex ERD ���� : localhost -> ip
*/
------------------------------------------------------------------------------
-- grouping ����
--1. ������, �μ��� �޿� �հ�� �ο����� ����ϵ�, 10�� �μ��� �����ϰ� ������ ��SALESMAN���� ��MANAGER���� ����Ѵ�.
SELECT job, deptno, count(*), sum(sal)
FROM emp
WHERE job='SALESMAN' OR job='MANAGER'
GROUP BY job, deptno
HAVING deptno!=10;

--2. �������� ��ձ޿��� �ִ�޿��� ����ϵ�, ��ձ޿��� 2000�̻��� �͸� ����ϰ� ��ձ޿��� ���� ������ ����
SELECT ROUND(avg(sal)), max(sal)
FROM emp
GROUP BY job
HAVING avg(sal)>=2000
ORDER BY avg(sal) desc;

--3. 5���� �޿��հ�� �ο����� ��� (rownum�̿�)
SELECT ceil(rownum/5), sum(sal), count(*)
FROM emp
GROUP BY ceil(rownum/5);


--4. ���� �Ի�⵵ ���� �ο����� ���
SELECT TO_CHAR(hiredate,'YYYY'), count(*)
FROM emp
GROUP BY TO_CHAR(hiredate,'YYYY');

--5. ������ ���� ���
--   CLERK     SALESMAN MANAGER       (������)
-----------------------------------------------------------------------
--     4           4       3           (�ο���)
SELECT COUNT(DECODE(job, 'CLERK', 1)) as CLERK, COUNT(DECODE(job, 'SALESMAN', 1)) as SALESMAN, COUNT(DECODE(job, 'MANAGER', 1)) as MANAGER
FROM emp;

-- count�� �� �Ӽ��� ���� ����, decode�Լ��� default�� �������� ������ default������ null�� ��
-- DECODE(job, 'CLERK', __) __�κ��� 1�� �ƴ� �ƹ� ���ڳ� ���� ī��Ʈ�� ���������� ���ư�
-- DECODE(job, 'CLERK', 1, __) __�κ��� default���� �������ִ� ��, ������� ������ �ڵ����� null�� �� ���������� �����

--6. ������ ���� ���
--������  10���μ� 20���μ� 30���μ� �޿��հ�
----------------------------------------------------------------------------------------
--CLERK  1300  1900     950  4150
--SALESMAN 0     0    5600   5600
--PRESIDENT 5000     0       0  5000
--MANAGER    2450  2975    2850  8275
--ANALYST     0  6000       0  6000
SELECT job as "������", sum(decode(deptno, 10, sal, 0)) as "10���μ�", sum(decode(deptno, 20, sal, 0))as "20���μ�", sum(decode(deptno, 30, sal, 0))as "30���μ�", sum(sal) as "�޿��հ�"
FROM emp
GROUP BY job;





