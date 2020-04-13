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
--위 서브퀴리에서 WHERE절이 없을 경우
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
----------------------------------------------------------------------------------------------------
--뷰
CREATE VIEW v_emp AS
SELECT empno, ename, deptno from emp; -- 권한 불충분(ORA_01031)

DESC v_emp;
SELECT * from v_emp;
-- view는 읽기 전용이 아님 (입력, 수정, 삭제가 가능하지만 일반적으로 읽기만 사용)
INSERT INTO v_emp VALUES(1000, '홍길택', 20);
SELECT * FROM emp;
SELECT * FROM v_emp; 
-- view에서 수정한 결과는 원본 테이블에 바로 적용이 됨 -> view를 가상테이블이라고 부르는 이유
INSERT INTO v_emp VALUES(1000, '홍길댁', 30); -- 원본 테이블의 기본키 제약조건에 따라서 값이 들어가지 않음
-- 원본의 제약조건을 그대로 따라감
INSERT INTO v_emp VALUES(1001, '홍길댁', 50); -- 원본 테이블의 참조 제약조건에 따라서 값이 들어가지 않음

DELETE FROM v_emp WHERE empno=1000; -- view에서의 삭제가 원본 테이블에 영향을 줌

CREATE OR REPLACE VIEW v_emp AS
SELECT empno, ename, deptno from emp
with read only;                                       -- view를 읽기 전용으로 생성
INSERT INTO v_emp VALUES(1000, '홍길택', 20);          -- 읽기 전용 view로 생성을 해서 값의 입력, 수정, 삭제가 되지 않음

/* updateable view----------------------------------------------------------------------------------------------------
    ㄴ수정이 가능한 뷰
[연습 ] EMP 테이블에서 30번 부서만
EMPNO를 EMP_NO로 ENAME을 NAME로 SAL를 SALARY로 바꿔서
EMP_30뷰를 생성하시오
*/--------------------------------------------------------------------------------------------------------------------
--1 view에서 이름을 바꿔주는 방법
CREATE OR REPLACE VIEW v_emp(emp_no, name, salary) AS
select empno, ename, sal from emp where deptno=30;
--2 서브쿼리에서 별칭을 주는 방법
CREATE OR REPLACE VIEW v_emp 
AS SELECT empno AS EMP_NO, ename AS NAME, sal AS SALARY FROM emp WHERE deptno=30;
--확인
select * from v_emp;
/*----------------------------------------------------------------------------------------------------
복합뷰(complex view) : 두 개 이상의 기본 테이블로 생성한 뷰 <-> 단순 뷰(simple view) : 하나의 기본 테이블로 생성한 뷰
----------------------------------------------------------------------------------------------------*/
Create or replace view v_emp_info as
select e.empno empno, e.ename ename, d.dname dname
from emp e, dept d
where e.deptno=d.deptno;

select * from v_emp_info; -- 확인

--복합뷰에서의 값 입력 확인
INSERT INTO v_emp_info VALUES(9999, '삼순이', 'SALES'); -- 두개의 테이블의 값들이 포함되있으면 복합뷰에 삽입 불가능
INSERT INTO v_emp_info(empno, ename) VALUES(9999, '삼순이'); -- 하나의 테이블의 값들만 삽입하는 건 가능( 뷰에는 입력되지 않고, 원본테이블에만 들어갔음)
DELETE FROM v__emp_info WHERE empno=9999; -- 뷰에는 값이 들어가있지 않아서 삭제가 되지 않음(한쪽 원본테이블에만 값이 들어가 있음)
--> 일반적으로 읽기 전용으로 만들기 때문에 크게 신경쓰지 않아도 되는 듯
