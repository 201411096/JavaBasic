-- ��� ���̺��� ���, �̸�, �μ���ȣ ���
SELECT employee_id, first_name||' '||last_name AS name, department_id
FROM employees;
-- ��� ���̺��� ���, �̸�, �μ���ȣ, �μ��̸� ���
SELECT e.employee_id as employee_id, e.first_name||' '||e.last_name AS ename, e.department_id AS department_id , d.department_name AS department_name
FROM employees e, departments d
WHERE e.department_id=d.department_id(+);
--ANSI
SELECT e.employee_id as employee_id, e.first_name||' '||e.last_name AS ename, e.department_id AS department_id , d.department_name AS department_name
FROM employees e LEFT OUTER JOIN departments d
ON e.department_id=d.department_id;
