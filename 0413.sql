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
WHERE sal > (SELECT min(sal) FROM emp WHERE deptno=30) AND deptno!=30;

SELECT *
FROM emp, (SELECT min(sal) AS minsal FROM emp WHERE deptno=30) ext
WHERE sal > ext.minsal  AND deptno!=30;
-- 5. 10번 부서의 직원들 중 30번 부서의 사원과 같은 업무를 맡고 있는 사원의 이름과 업무를 출력
SELECT ename, job
FROM emp
WHERE deptno=10 AND job IN (SELECT job FROM emp WHERE deptno=30);

SELECT *
FROM emp e, (SELECT job FROM emp WHERE deptno=30) ext
WHERE e.deptno=10 AND e.job IN ext.job;
-- 6. Ford와 업무도 월급도 같은 사원의 모든 정보를 출력
SELECT *
FROM emp
WHERE sal = (SELECT sal FROM emp WHERE ename = 'FORD') AND job = (SELECT job FROM emp WHERE ename='FORD') AND ename!='FORD'; 

/*----------------------------------------------------------------------------------------------------
-------------------------- INSERT / UPDATE / DELETE에서 사용하는 서브쿼리 -------------------------------
(1) 부서별 급여통계 테이블 생성
create table stat_salary (
           stat_date   date  not  null,        -- 날짜
           dept_no   number,                    --부서
           emp_count number,      --사원수
           tot_salary number,        --급여총액
           avg_salary number );     -- 급여평균
(2) 날짜와 부서번호 입력
(3) 사원수, 급여총액, 평균급여 입력 -> 수정
 ----------------------------------------------------------------------------------------------------*/
create table stat_salary (
           stat_date   date  not  null,        -- 날짜
           dept_no   number,                    --부서
           emp_count number,      --사원수
           tot_salary number,        --급여총액
           avg_salary number );     -- 급여평균
--1. INSERT문에서의 서브쿼리----------------------------------------------------------------------------------------------------
SELECT sysdate, deptno FROM dept; -- INSERT문에서 사용할 서브쿼리
INSERT INTO stat_salary(stat_date, dept_no) (SELECT sysdate, deptno FROM dept); -- VALUE를 사용하지 않고 여러행을 한꺼번에 삽입
--2. UPDATE문에서의 서브쿼리----------------------------------------------------------------------------------------------------
UPDATE stat_salary s
SET(s.emp_count, s.tot_salary, s.avg_salary) 
= ( SELECT count(*), sum(e.sal), round(avg(e.sal))
    FROM emp e
    WHERE e.deptno=s.dept_no                        --헷갈리는 부분(서브쿼리 안에서 외부값과 비교...)
    GROUP BY e.deptno
    );
    
UPDATE stat_salary s
SET(s.emp_count, s.tot_salary, s.avg_salary) 
= ( SELECT count(*), sum(e.sal), round(avg(e.sal))
    FROM emp e                                     -- 위에랑 다르게 조건이 없으면 서브쿼리에서 여러개의 행이 리턴되면서 오류 발생
    GROUP BY e.deptno
    );
--WHERE -- 모든 행에 다 삽입할 경우에는 생략
--3. DELETE문에서의 서브쿼리----------------------------------------------------------------------------------------------------
--emp copy 테이블에서 부서명이 sales인 사원의 정보를 삭제
DELETE
FROM emp_copy
WHERE deptno= (SELECT deptno FROM dept WHERE dept.dname='SALES');
rollback;
SELECT *
FROM emp_copy;