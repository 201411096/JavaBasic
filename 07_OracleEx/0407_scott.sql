SELECT ename || '[' || job || ']' AS ename, sal, sal+nvl(comm, 0) AS total_sal FROM emp;

SELECT DISTINCT job FROM emp;

SELECT deptno FROM emp;
SELECT deptno, job FROM emp;
SELECT DISTINCT deptno, job FROM emp;

--1. 20번 부서에 사원번호, 이름, 부서번호을 출력
SELECT empno, ename, deptno
FROM emp
WHERE deptno=20;

--2. 입사일이 81/01/01에서 81/06/09인 사원의 사원번호, 이름, 입사일을 출력
SELECT empno, ename, hiredate
FROM emp
WHERE hiredate >= '81.01.01' and hiredate <= '81/06/09';

--2. 입사일이 81/01/01에서 81/06/09인 사원의 사원번호, 이름, 입사일을 출력 (BETWEEN AND 는 포함 값들을 포함하는 경우)
SELECT empno, ename, hiredate
FROM emp
WHERE hiredate BETWEEN '81/01/01' and '81/06/09';

--3. 담당업무가 salesman, clerk인 사원들의 이름과 업무를 출력
SELECT ename, job
FROM emp
WHERE job='SALESMAN' OR job='CLERK'; -- 대문자 사용해야 나옴

--4. 업무가 president이고 급여가 1500이상이거나 업무가 salesman인 사원의 정보를 출력
SELECT *
FROM emp
WHERE (job='PRESIDENT' AND SAL >=1500) OR (job ='SALESMAN');

--5. 업무가 president 또는 salesman이고 급여가 1500이상인 사원의 정보를 출력
SELECT *
FROM emp
WHERE (job='PRESIDENT' or job='SALESMAN') AND sal>=1500;

--6. 커미션(comm)이 없는 사원의 이름, 급여, 커미션을 출력
SELECT ename, sal, comm
FROM emp
WHERE comm IS NULL or comm=0;

--7. 사원명, 급여, 커미션, 총급여( 급여 + 커미션)을 출력
SELECT ename, sal, comm, sal+nvl(comm, 0) AS totalsal
FROM emp;

--8. 이름 A로 시작하는 사원명 출력
SELECT ename
FROM emp
WHERE ename LIKE 'A%';

--9. 이름이 두번째 문자가 L인 사원명 출력
SELECT ename
FROM emp
WHERE ename LIKE '_L%';

--10. 이름에 L이 두 번 이상 포함된 사원명
SELECT ename
FROM emp
WHERE ename LIKE '%L%L%';

--11. 커미션(COMM)이 NULL이 아닌 사원의 모든 정보를 출력
SELECT *
FROM emp
WHERE comm IS NOT NULL;

--12. 보너스가 급여보다 10%가 많은 모든 사원에 대해 이름, 급여, 보너스를 출력
SELECT ename, sal, comm
FROM emp
WHERE comm >= sal*1.1;

--13. 업무가 clerk이거나 analyst이고 급여가 1000, 3000, 5000이 아닌 모든 사원의 정보를 출력
SELECT *
FROM emp
WHERE (job='CLERK' or job='ANALYST') AND (SAL!=1000 AND SAL!=3000 AND SAL!=5000);

SELECT *
FROM emp
WHERE job IN ('CLERK', 'ANALYST') AND sal NOT IN(1000, 3000, 5000);
--14. 부서가 30이거나 또는 관리자가 7782인 사원의 모든 정보를 출력
SELECT *
FROM emp
WHERE deptno=30 OR mgr=7782;

INSERT INTO emp(empno, ename, job) VALUES(8000, '홍길동', '개발자');

SELECT *
FROM emp
ORDER BY deptno ASC, sal DESC;
-- 최근 입사한순으로 사원명, 급여, 입사일자를 출력
SELECT ename, sal, hiredate
FROM emp
ORDER BY hiredate DESC;

-- 커미션이 높은 순으로 정렬 (단, 커미션이 없으면 제일 나중에 출력되도록 )
SELECT *
FROM emp
ORDER BY comm DESC NULLS last;
 
SELECT *
FROM emp
ORDER BY nvl(comm, 0) DESC;

-- 부서번호로 정렬한 후 부서번호가 같을 경우는 급여가 많은순으로 정렬하여 사원번호, 이름, 업무, 부서번호, 급여를 출력

SELECT empno, ename, job, deptno, sal
FROM emp
ORDER BY deptno ASC, sal DESC;

--사원명, 급여, 월급(급여/12)를 출력하되 월급은 십단위에서 반올림하여 출력
SELECT ename, sal, ROUND((sal/12), -2) as "월급" -- 한글로 처리할때는 ""사용
FROM emp;

--사원명, 급여, 세금(급여의 3.3%)를 원단위 절삭하고 출력
SELECT ename, sal, TRUNC(sal*0.033, -1)as tax
FROM emp;

 -- smith의정보를 사원번호, 성명, 담당업무(소문자) 출력
SELECT empno, ename, lower(job) AS job
FROM emp
WHERE ename=upper('smith');

-- 사원번호, 사원명(첫글자만 대문자), 담당업무(첫글자만대문자)로 출력
SELECT empno, INITCAP(ename) AS ename, INITCAP(job) AS job
FROM emp;

-- 이름의 첫글자가 ‘K’보다크고 ‘Y’보다 작은 사원의 정보( 사원번호, 이름, 업무, 급여, 부서번호)를 출력하되 이름순으로 정렬
SELECT empno, ename, job, sal, deptno
FROM emp
WHERE SUBSTR(ename, 1, 1) > 'K' AND SUBSTR(ename, 1, 1) < 'Y'
ORDER BY ename;

--info_tab 테이블에서 주민번호 년도만 출력하기
SELECT SUBSTR(jumin, 1, 2) AS jumin
FROM info_tab;

--info_tab 테이블에서 성별만 출력하기
SELECT SUBSTR(jumin, 8, 1) AS jumin
FROM info_tab;

-- 이름이 5글자 이상인사원들을 출력
SELECT *
FROM emp
WHERE LENGTH(ename)>=5; 

-- 이름을 15자로 맞추고글자는 왼쪽에 오른쪽에는 ‘*’로 채운다
SELECT rpad(ename, 15, '*') as ename
FROM emp;

-- 급여는 10자로 맞추고숫자는 오른쪽에 왼쪽엔 ‘-‘로 채운다
SELECT lpad(sal, 10, '-') as sal
FROM emp;

-- 양쪽 공백을 제거

-- 급여를 숫자에서 ‘영일이삼사오육칠팔구’ 글자로 대체
SELECT translate(sal, '0123456789', '영일이삼사오육칠팔구') as sal
FROM emp;
-- 급여의 숫자에서 0을‘$’로 바꾸어 출력
SELECT replace(sal, 0, '$') sal
FROM emp;

SELECT sysdate
FROM DUAL;

SELECT 1+2
FROM DUAL;

SELECT ltrim(' 홍 길 동 ') AS name
FROM DUAL;

SELECT replace(' 홍 길 동 ', ' ', '') name
FROM DUAL;

-- 현재까지 근무일 수가 많은 사람 순으로 출력
SELECT *
FROM emp
ORDER BY sysdate - nvl(hiredate, sysdate) desc ;

-- 현재까지 근무일 수가 몇 주 몇 일인가를 출력
SELECT trunc(trunc(sysdate-hiredate)/7) as week, round(mod((sysdate-hiredate),7)) as days
FROM emp;

-- 10번 부서의 사원의 현재까지의 근무 월수를 계산 ..?
SELECT ename, TRUNC(MONTHS_BETWEEN(sysdate, hiredate), 0) as "근무월수"
FROM emp
WHERE deptno=10;

-- 현재 날짜에서 3개월후의 날짜 구하기 select  add_months( sysdate, 3 ) as mydate from dual;
select  add_months( sysdate, 3 ) as mydate from dual; -- 차이 비교 // 3달 후

select sysdate+3 as mydate from dual; -- 3일 후
-- 현재 날짜에서 돌아오는 ‘월’요일의 날짜 구하기
SELECT next_day(sysdate, '월') FROM DUAL; -- 지역에 따른 요일만 가능 , monday 불가능        

-- 현재 날짜에서 해당 월의 마지막 날짜 구하기
SELECT last_day(sysdate) FROM DUAL;

-- 입사일자에서 입사년도를 출력
SELECT ename, hiredate, to_char(hiredate,'YYYY') as hire_year -- 연도 뽑아오기
FROM emp;

SELECT ename, hiredate, to_char(hiredate,'MM') as hire_year -- 월 뽑아오기
FROM emp;
          

-- 입사일자를 ‘1999년 1월 1일’ 형식으로 출력
SELECT ename ,hiredate, to_char(hiredate, 'YYYY"년" MM"월" DD"일"') AS hireyear -- 날짜포맷(DATE_FORMAT) 부분 작성시 한글 부분을 큰따옴표로 한번씩 더 감싸줘야함
FROM EMP;

-- 급여 앞에 $를 삽입하고 3자리 마다 ,를 출력
SELECT ename, sal, to_char(sal, '$999,999,999') as dollar
FROM emp;

-- 1981년도에 입사한 사원 검색
SELECT *
FROM emp
WHERE to_char(hiredate,'YYYY')='1981';
 

-- 5월에 입사한 사원 검색
SELECT *
FROM emp
WHERE to_char(hiredate, 'MM')='05';
-- 81년 2월에 입사한 사원 검색
SELECT *
FROM emp
WHERE to_char(hiredate, 'YYYYMM')='198102';
-- 81년에 입사하지 않은 사원 검색
SELECT *
FROM emp
WHERE to_char(hiredate, 'YYYY')!='1981';
-- 81년 하반기에 입사한 사원 검색
SELECT *
FROM emp
WHERE to_char(hiredate, 'YYYYMM')>='198107' AND to_char(hiredate, 'YYYYMM')<='198112';

