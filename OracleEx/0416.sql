--입사월별 인원수
SELECT TO_CHAR(hiredate, 'MM') AS hire_month,  count(*) AS cnt FROM emp group by TO_CHAR(hiredate, 'MM') ORDER BY TO_CHAR(hiredate, 'MM');

SELECT JOB, NVL(AVG(SAL), 0) FROM EMP GROUP BY JOB ORDER BY JOB;
--부서별 평균 월급
SELECT JOB, NVL(AVG(SAL), 0) AS avgsal FROM EMP GROUP BY JOB ORDER BY JOB;

--상위 월급 5명
SELECT ext.* FROM (SELECT * FROM emp ORDER BY nvl(sal, 0) desc) ext WHERE rownum<=5;