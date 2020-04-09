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
-- ��� ���̺��� ���, �̸�, �μ���ȣ, �μ��̸� ���, ���� ���
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
-- ��� ���̺��� ���, �̸�, �μ���ȣ, �μ��̸� ���, ����, ���� ��� - 106��
SELECT e.employee_id as employee_id, e.first_name||' '||e.last_name AS ename, e.department_id AS department_id, d.department_name AS department_name, j.job_title AS job_table, l.street_address AS street_address
FROM employees e, departments d, jobs j, locations l
WHERE e.department_id=d.department_id(+) AND e.job_id = j.job_id AND d.location_id=l.location_id;

-------------------------------------------------------------------------------------------------------------------------------------
/*  # HR ��������
���� ��� 2������ ��� �ۼ�
--1. �̸��� ���� 'King' ����� ����� �μ����� ��� ( employees, departments )
--2. �̸��� ���� 'King' ����� ����� �μ��� �׸��� �������� ��� ( employees, departments, jobs )
--3. 2007�� ��ݱ⿡ �Ի��� ������� ����� �̸�, �Ի��� �׸��� �μ����� ���
 (*) Grant�� ���� �μ��� �������� ���� ���������� Grant�� ��µǷ���
--4. 'Executive' �μ����� ����� �ش� ����� ������ �̸��� ���
 (*) �����ڰ� ���� ����� 'King'�� ��µǷ���
*/
--1. �̸��� ���� 'King' ����� ����� �μ����� ��� ( employees, departments )
SELECT e.employee_id AS employee_id, d.department_name AS department_name
FROM employees e, departments d
WHERE e.department_id=d.department_id AND e.last_name='King';
--ANSI
SELECT e.employee_id AS employee_id, d.department_name AS department_name
FROM employees e INNER JOIN departments d
ON e.department_id=d.department_id
WHERE e.last_name='King';
--2. �̸��� ���� 'King' ����� ����� �μ��� �׸��� �������� ��� ( employees, departments, jobs )
SELECT e.employee_id AS employee_id, d.department_name AS department_name, j.job_title AS job_title
FROM employees e, departments d, jobs j
WHERE e.department_id=d.department_id AND e.job_id=j.job_id AND e.last_name='King';
--ANSI
SELECT e.employee_id AS employee_id, d.department_name AS department_name, j.job_title AS job_title
FROM employees e 
INNER JOIN departments d ON e.department_id=d.department_id
INNER JOIN jobs j ON e.job_id=j.job_id
WHERE e.last_name='King';
--3. 2007�� ��ݱ⿡ �Ի��� ������� ����� �̸�, �Ի��� �׸��� �μ����� ��� (*) Grant�� ���� �μ��� �������� ���� ���������� Grant�� ��µǷ���
SELECT e.employee_id AS employee_id, e.first_name||' '||e.last_name AS employee_name, e.hire_date, d.department_name AS department_name
FROM employees e, departments d
WHERE e.department_id=d.department_id(+) AND TO_CHAR(e.hire_date,'YYYYMM')>='200701' AND TO_CHAR(e.hire_date,'YYYYMM')<='200706';
--ANSI
SELECT e.employee_id AS employee_id, e.first_name||' '||e.last_name AS employee_name, e.hire_date, d.department_name AS department_name
FROM employees e LEFT OUTER JOIN departments d
ON e.department_id=d.department_id
WHERE TO_CHAR(e.hire_date,'YYYYMM')>='200701' AND TO_CHAR(e.hire_date,'YYYYMM')<='200706';
--4. 'Executive' �μ����� ����� �ش� ����� ������ �̸��� ��� (*) �����ڰ� ���� ����� 'King'�� ��µǷ���
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
