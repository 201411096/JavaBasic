-- 사원번호, 이름, 업무, 급여, 현재 급여에 15% 증가된 급여(new salary), 증가액을 출력

SELECT empno, ename, job, sal, round(sal*1.15) as newsalary, round(sal*0.15) as increasedamount
FROM emp;