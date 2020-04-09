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
