--�Ի���� �ο���
SELECT TO_CHAR(hiredate, 'MM') AS hire_month,  count(*) AS cnt FROM emp group by TO_CHAR(hiredate, 'MM') ORDER BY TO_CHAR(hiredate, 'MM');

SELECT JOB, NVL(AVG(SAL), 0) FROM EMP GROUP BY JOB ORDER BY JOB;
--�μ��� ��� ����
SELECT JOB, NVL(AVG(SAL), 0) AS avgsal FROM EMP GROUP BY JOB ORDER BY JOB;

--���� ���� 5��
SELECT ext.* FROM (SELECT * FROM emp ORDER BY nvl(sal, 0) desc) ext WHERE rownum<=5;