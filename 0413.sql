/*----------------------------------------------------------------------------------------------------
서브쿼리 복습
----------------------------------------------------------------------------------------------------*/
-- 1. Blake와 같은 부서에 있는 모든 사원의 이름과 입사일자를 출력
SELECT ename, hiredate, deptno
FROM emp
WHERE deptno = (SELECT deptno FROM emp WHERE ename='BLAKE');

SELECT e.ename AS ename, e.hiredate AS hiredate, e.deptno AS deptno
FROM emp e, (SELECT deptno AS deptno FROM emp WHERE ename='BLAKE') ext
WHERE e.deptno= ext.deptno;
-- 2. 평균 급여 이상을 받는 모든 사원에 대해 사번과 이름을 출력 단, 급여가 많은 순으로 정렬
SELECT *
FROM emp
WHERE sal >= (SELECT avg(sal) FROM emp)
ORDER BY sal desc;

SELECT e.empno AS empno, e.ename AS ename, e.sal AS sal
FROM emp e, (SELECT avg(sal) avgsal FROM emp) ext
WHERE e.sal >= ext.avgsal
ORDER BY sal desc;

-- 3. 부서위치가 Dallas인 모든 사원에 대해 이름, 업무, 급여를 출력
SELECT ename, job, sal
FROM emp
WHERE deptno = (SELECT deptno FROM dept WHERE loc='DALLAS');

SELECT e.ename AS ename, e.job AS job, e.sal AS sal
FROM emp e, (SELECT deptno FROM dept WHERE loc='DALLAS') ext
WHERE e.deptno=ext.deptno;
-- 4. 월급이 30번 부서의 최저 월급보다 많은 사원들을 출력
SELECT *
FROM emp
WHERE sal > (SELECT min(sal) FROM emp WHERE deptno=30);

SELECT *
FROM emp, (SELECT min(sal) AS minsal FROM emp WHERE deptno=30) ext
WHERE sal > ext.minsal;
-- 5. 10번 부서의 직원들 중 30번 부서의 사원과 같은 업무를 맡고 있는 사원의 이름과 업무를 출력
SELECT ename, job
FROM emp
WHERE deptno=10 AND job IN (SELECT job FROM emp WHERE deptno=30);
-- 6. Ford와 업무도 월급도 같은 사원의 모든 정보를 출력
SELECT *
FROM emp
WHERE sal = (SELECT sal FROM emp WHERE ename = 'FORD') AND job = (SELECT job FROM emp WHERE ename='FORD') AND ename!='FORD'; 
