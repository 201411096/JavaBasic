-- 사원번호, 이름, 업무, 급여, 현재 급여에 15% 증가된 급여(new salary), 증가액을 출력
SELECT empno, ename, job, sal, round(sal*1.15) as newsalary, round(sal*0.15) as increase
FROM emp;
-- 이름, 업무, 입사일, 입사한 요일
SELECT ename, job, TO_CHAR(hiredate, 'YYYY/MM/DD'), TO_CHAR(hiredate, 'day')
FROM emp;
--이름 입사일, 입사일로부터 현재까지의 년수, 급여, 입사일로부터 현재까지 받은 급여의 총액
SELECT ename, TO_CHAR(hiredate, 'YYYY/MM/DD'), (TO_CHAR(sysdate,'YYYY')-TO_CHAR(hiredate,'YYYY')) as years, sal, (TO_CHAR(sysdate,'YYYY')-TO_CHAR(hiredate,'YYYY'))*sal as saltotal
FROM emp;

SELECT ename, TO_CHAR(hiredate, 'YYYY/MM/DD') as hiredate, trunc(months_between(sysdate, hiredate)/12) as years, sal, round(trunc(months_between(sysdate, hiredate)/12)*(sal/12)) as saltotal
FROM emp;


/*
(5) 조건함수
           :if ~ else if ~ else 구문
DECODE ( expr, search 1, result1, search2, result2, ... , [default] )

CASE expr WHEN condition1 THEN result1
          WHEN condition2 THEN result2
          ...
          ELSE  default
END
-- 주민번호에서 성별 구하기
SELECT  decode( substr(jumin, 8, 1), ‘1’, '남자', ‘3’, '남자', '여자')AS  gender  FROM table_name;

SELECT  CASE  substr(jumin, 8, 1)
         WHEN '1' THEN '남자'
         WHEN'3' THEN '남자'
         ELSE '여자'
END    as gender
FROM   emp;
*/

--주민번호에서 성별구하기
SELECT jumin, decode( substr(jumin,8,1), '1', '남자', '3', '남자', '여자')
    AS gender
FROM info_tab;

SELECT jumin, CASE substr(jumin, 8, 1)
    WHEN '1' THEN '남자'
    WHEN '3' THEN '남자'
    ELSE '여자'
    END gender
FROM info_tab;

-- 부서번호가 10이면영업부, 20이면 관리부, 30이면 IT부 그 외는 기술부로 출력
SELECT deptno, decode(deptno, '10', '영업부', '20', '관리부', '30', 'IT부', '기술부')
    AS 부서
FROM emp;

SELECT deptno, CASE deptno
    WHEN 10 THEN '영업부'
    WHEN 20 THEN '관리부'
    WHEN 30 THEN 'IT부'
    ELSE '기술부'
    END 부서
FROM emp;
          
-- 업무(job)이 analyst이면 급여 증가가 10%이고 clerk이면 15%, manager이면 20%인 경우 사원번호, 사원명, 업무, 급여, 증가한 급여를 출력
SELECT empno, ename, job, decode(job, 'ANALYST', sal*1.1, 'CLERK', sal*1.15, 'MANAGER', sal*1.2, sal) as sal, decode(job, 'ANALYST', sal*0.1, 'CLERK', sal*0.15, 'MANAGER', sal*0.2, 0) as increased
FROM emp;

SELECT empno, ename, job ,sal, CASE job
                                WHEN 'ANALYST' THEN sal*1.1
                                WHEN 'CLERK' THEN sal*1.15
                                WHEN 'MANAGER' THEN sal*1.2
                                ELSE sal
                                END sal                        
FROM emp;
----------------------------------------------# HR 계정에서 ( employees 테이블 )
--1. 2003년에 입사한 사원들의 사번, 이름, 입사일을 출력
SELECT employee_id, first_name||' '||last_name, hire_date
FROM EMPLOYEES
WHERE TO_CHAR(hire_date, 'YYYY')='2003';
--2. 업무가 FI_ACCOUNT / FI_MGR / SA_MAN / SA_REP 인 사원들의 정보를 출력
SELECT *
FROM employees
WHERE job_id='FI_ACCOUNT' OR job_id='FI_MGR' OR job_id='SA_MAN' OR job_id='SA_REP';
--3. 커미션을 받는 사원들의 명단을 출력
SELECT *
FROM employees
WHERE commission_pct is not null;

--4.업무가 SA_MAN 또는 SA_REP이면 "판매부서"를 그 외는 "그 외 부서"라고 출력
SELECT CASE job_id
        WHEN 'SA_MAN' THEN '판매부서'
        WHEN 'SA_REP' THEN '판매부서'
        ELSE '그 외 부서'
        END 부서
FROM employees;

SELECT decode(job_id, 'SA_MEN', '판매부서', 'SA_REP', '판매부서', '그 외 부서') as 부서
FROM employees;
------------------------------------------------------------------------------------------------------------------

--업무가 'salesman'인 사람들의 월급의 평균, 총합, 최소값, 최대값을 구하기
SELECT count(*) as count, avg(nvl(comm,0)) as avg, sum(nvl(comm,0)) as sum, min(nvl(comm,0)) as min, max(nvl(comm,0)) as max
FROM emp
WHERE JOB='CLERK';
