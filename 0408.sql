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
