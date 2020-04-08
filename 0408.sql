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

SELECT count(*)
FROM emp
WHERE comm IS NOT NULL AND comm!=0;

-- 부서별로인원수, 평균급여, 최저급여, 최고급여, 급여의 합을 구하기
SELECT deptno, count(*), avg(sal), min(sal), max(sal), sum(sal)
FROM emp
GROUP BY deptno;
-- 부서별로인원수, 평균급여, 최저급여, 최고급여, 급여의 합을 구하기 (부서별 급여의 합이 높은 순으로
SELECT deptno, count(*), avg(sal), min(sal), max(sal), sum(sal) as sum_sal
FROM emp
GROUP BY deptno
ORDER BY sum_sal desc nulls last;
 
-- 부서별업무별 그룹하여 부서번호, 업무, 인원수, 급여의 평균, 급여의 합을 구하기
SELECT deptno, job, count(*), avg(sal), sum(sal)
FROM emp
GROUP BY deptno, job;
 
-- 최대 급여가 2900 이상인부서에 대해 부서번호, 평균 급여, 급여의 합을 출력
SELECT deptno, count(*) , round(avg(sal)), sum(sal)
FROM emp
GROUP BY deptno
HAVING max(sal)>=2900;

-- 업무별 급여의 평균이 3000이상인 업무에 대해 업무명, 평균 급여, 급여의 합을 출력
SELECT job, avg(sal), sum(sal)
FROM emp
GROUP BY job
HAVING avg(sal)>=3000;

-- 전체 합계 급여가 5000를초과하는 각 업무에 대해서 업무와 급여 합계를 출력 단, SALESMAN은제외하고 급여 합계가 높은 순으로 정렬
SELECT job, sum(sal)
FROM emp
WHERE job!='SALESMAN'
GROUP BY job
HAVING sum(sal)>5000
ORDER BY sum(sal) desc;

--  업무별최고 급여와 최소 급여의 차이를 구하라
SELECT (max(sal)-min(sal)) as diff
FROM emp
GROUP BY job;

-- 부서 인원이 4명보다 많은 부서의 부서번호, 인원수, 급여의 합을 출력
SELECT deptno, count(*), sum(sal)
FROM emp
GROUP BY deptno
HAVING count(*)>4;

/*
[ GROUP BY 절에 사용하는 함수 ]

- ROLLUP : 결과에 그룹별 합계 정보를 추가 -- 마지막 부분에...

- CUBE : 그룹핑 된 컬럼의 합계 정보를 추가 -- 맨 위 부분에...
*/
select job, sum( sal ) from emp group by job;
select job, sum( sal ) from emp group by rollup(job); -- 마지막에 총합 추가
select job, sum( sal ) from emp group by cube(job); -- 맨위에 총합 추가

------------------------------------------------------------------------------
/*
1. 교재 chapter 4 / 5
2. grouping 문제 풀이
3. 오라클 IP 접속
    - sql developer 변경 : localhost -> ip
    - ex ERD 변경 : localhost -> ip
*/
------------------------------------------------------------------------------
-- grouping 문제
--1. 업무별, 부서별 급여 합계와 인원수를 출력하되, 10번 부서를 제외하고 업무가 ‘SALESMAN’과 ‘MANAGER’만 출력한다.
SELECT job, deptno, count(*), sum(sal)
FROM emp
WHERE job='SALESMAN' OR job='MANAGER'
GROUP BY job, deptno
HAVING deptno!=10;

--2. 업무별로 평균급여와 최대급여를 출력하되, 평균급여가 2000이상인 것만 출력하고 평균급여가 높은 순으로 정렬
SELECT ROUND(avg(sal)), max(sal)
FROM emp
GROUP BY job
HAVING avg(sal)>=2000
ORDER BY avg(sal) desc;

--3. 5개씩 급여합계와 인원수를 출력 (rownum이용)
SELECT ceil(rownum/5), sum(sal), count(*)
FROM emp
GROUP BY ceil(rownum/5);


--4. 같은 입사년도 별로 인원수를 출력
SELECT TO_CHAR(hiredate,'YYYY'), count(*)
FROM emp
GROUP BY TO_CHAR(hiredate,'YYYY');

--5. 다음과 같이 출력
--   CLERK     SALESMAN MANAGER       (업무명)
-----------------------------------------------------------------------
--     4           4       3           (인원수)
SELECT COUNT(DECODE(job, 'CLERK', 1)) as CLERK, COUNT(DECODE(job, 'SALESMAN', 1)) as SALESMAN, COUNT(DECODE(job, 'MANAGER', 1)) as MANAGER
FROM emp;

-- count는 널 속성을 세지 않음, decode함수에 default를 지정하지 않으면 default값으로 null을 줌
-- DECODE(job, 'CLERK', __) __부분이 1이 아닌 아무 숫자나 들어가면 카운트가 정상적으로 돌아감
-- DECODE(job, 'CLERK', 1, __) __부분이 default값을 지정해주는 곳, 적어두지 않으면 자동으로 null이 들어가 정상적으로 실행됨

--6. 다음과 같이 출력
--업무명  10번부서 20번부서 30번부서 급여합계
----------------------------------------------------------------------------------------
--CLERK  1300  1900     950  4150
--SALESMAN 0     0    5600   5600
--PRESIDENT 5000     0       0  5000
--MANAGER    2450  2975    2850  8275
--ANALYST     0  6000       0  6000
SELECT job as "업무명", sum(decode(deptno, 10, sal, 0)) as "10번부서", sum(decode(deptno, 20, sal, 0))as "20번부서", sum(decode(deptno, 30, sal, 0))as "30번부서", sum(sal) as "급여합계"
FROM emp
GROUP BY job;





