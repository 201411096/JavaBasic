-- �����ȣ, �̸�, ����, �޿�, ���� �޿��� 15% ������ �޿�(new salary), �������� ���

SELECT empno, ename, job, sal, round(sal*1.15) as newsalary, round(sal*0.15) as increasedamount
FROM emp;