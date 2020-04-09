-- 사원 테이블에서 사번, 이름, 부서번호 출력
SELECT employee_id, first_name||' '||last_name AS name, department_id
FROM employees;
-- 사원 테이블에서 사번, 이름, 부서번호, 부서이름 출력
SELECT e.employee_id as employee_id, e.first_name||' '||e.last_name AS ename, e.department_id AS department_id , d.department_name AS department_name
FROM employees e, departments d
WHERE e.department_id=d.department_id(+);
--ANSI
SELECT e.employee_id as employee_id, e.first_name||' '||e.last_name AS ename, e.department_id AS department_id , d.department_name AS department_name
FROM employees e LEFT OUTER JOIN departments d
ON e.department_id=d.department_id;
-- 사원 테이블에서 사번, 이름, 부서번호, 부서이름 출력, 업무 출력
SELECT e.employee_id as employee_id, e.first_name||' '||e.last_name AS ename, e.department_id AS department_id, d.department_name AS department_name, j.job_title AS job_table
FROM employees e, departments d, jobs j
WHERE e.department_id=d.department_id(+) AND e.job_id = j.job_id;
--ANSI
SELECT e.employee_id as employee_id, e.first_name||' '||e.last_name AS ename, e.department_id AS department_id, d.department_name AS department_name, j.job_title AS job_table
FROM employees e 
LEFT OUTER JOIN departments d 
ON e.department_id=d.department_id
INNER JOIN jobs j 
ON e.job_id = j.job_id;
-- 사원 테이블에서 사번, 이름, 부서번호, 부서이름 출력, 업무, 지역 출력 - 106행
SELECT e.employee_id as employee_id, e.first_name||' '||e.last_name AS ename, e.department_id AS department_id, d.department_name AS department_name, j.job_title AS job_table, l.street_address AS street_address
FROM employees e, departments d, jobs j, locations l
WHERE e.department_id=d.department_id(+) AND e.job_id = j.job_id AND d.location_id=l.location_id;

-------------------------------------------------------------------------------------------------------------------------------------
/*  # HR 계정에서
조인 방식 2가지를 모두 작성
--1. 이름의 성이 'King' 사원의 사번과 부서명을 출력 ( employees, departments )
--2. 이름의 성이 'King' 사원의 사번과 부서명 그리고 업무명을 출력 ( employees, departments, jobs )
--3. 2007년 상반기에 입사한 사원들의 사번과 이름, 입사일 그리고 부서명을 출력
 (*) Grant는 아직 부서가 배정받지 못한 상태이지만 Grant도 출력되려면
--4. 'Executive' 부서에서 사원과 해당 사원의 관리자 이름을 출력
 (*) 관리자가 없는 사원인 'King'이 출력되려면
*/
--1. 이름의 성이 'King' 사원의 사번과 부서명을 출력 ( employees, departments )
SELECT e.employee_id AS employee_id, d.department_name AS department_name
FROM employees e, departments d
WHERE e.department_id=d.department_id AND e.last_name='King';
--ANSI
SELECT e.employee_id AS employee_id, d.department_name AS department_name
FROM employees e INNER JOIN departments d
ON e.department_id=d.department_id
WHERE e.last_name='King';
--2. 이름의 성이 'King' 사원의 사번과 부서명 그리고 업무명을 출력 ( employees, departments, jobs )
SELECT e.employee_id AS employee_id, d.department_name AS department_name, j.job_title AS job_title
FROM employees e, departments d, jobs j
WHERE e.department_id=d.department_id AND e.job_id=j.job_id AND e.last_name='King';
--ANSI
SELECT e.employee_id AS employee_id, d.department_name AS department_name, j.job_title AS job_title
FROM employees e 
INNER JOIN departments d ON e.department_id=d.department_id
INNER JOIN jobs j ON e.job_id=j.job_id
WHERE e.last_name='King';
--3. 2007년 상반기에 입사한 사원들의 사번과 이름, 입사일 그리고 부서명을 출력 (*) Grant는 아직 부서가 배정받지 못한 상태이지만 Grant도 출력되려면
SELECT e.employee_id AS employee_id, e.first_name||' '||e.last_name AS employee_name, e.hire_date, d.department_name AS department_name
FROM employees e, departments d
WHERE e.department_id=d.department_id(+) AND TO_CHAR(e.hire_date,'YYYYMM')>='200701' AND TO_CHAR(e.hire_date,'YYYYMM')<='200706';
--ANSI
SELECT e.employee_id AS employee_id, e.first_name||' '||e.last_name AS employee_name, e.hire_date, d.department_name AS department_name
FROM employees e LEFT OUTER JOIN departments d
ON e.department_id=d.department_id
WHERE TO_CHAR(e.hire_date,'YYYYMM')>='200701' AND TO_CHAR(e.hire_date,'YYYYMM')<='200706';
--4. 'Executive' 부서에서 사원과 해당 사원의 관리자 이름을 출력 (*) 관리자가 없는 사원인 'King'이 출력되려면
SELECT e1.first_name||' '||e1.last_name AS employee_name, e2.first_name||' '||e2.last_name AS manager_name
FROM employees e1, employees e2, departments d
WHERE e1.department_id=d.department_id AND e1.manager_id=e2.employee_id(+);
--ANSI
SELECT e1.first_name||' '||e1.last_name AS employee_name, e2.first_name||' '||e2.last_name AS manager_name
FROM employees e1 
INNER JOIN departments d
ON e1.department_id=d.department_id
LEFT OUTER JOIN employees e2
ON e1.manager_id=e2.employee_id;
