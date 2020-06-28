--1. EMPLOYEES ���̺��� ��� �̸��� first_name�� last_name�� ���� full_name���� ���
SELECT first_name|| ' ' || last_name as full_name
FROM employees; 
--2. �μ���ȣ�� 30(���źμ�)�̰� �޿� 10000�̸��� ����� full_name�� ����,�μ���ȣ�� ���
SELECT first_name|| ' ' || last_name as full_name, salary, department_id
FROM employees
WHERE department_id=30 AND salary<10000;
--3. �μ���ȣ�� 30�̰� �޿��� 10000�޷� ���ϸ� �޴� 2006�⵵ ���� �Ի��� ����� full_name�� ���
SELECT first_name|| ' ' || last_name as full_name
FROM employees
WHERE department_id=30 AND salary<=10000 AND hire_date<'2006/01/01';
--4. �μ���ȣ�� 30(���źμ�)�̰� �޿��� 10000������ ����� �μ���ȣ�� 60(IT�μ�)���� �޿��� 5000�̻��� ����� ��ȸ
SELECT *
FROM employees
WHERE (department_id=30 AND salary<=10000) OR (department_id=60 AND salary>=5000);
--5. �����ȣ�� 110������ 120���� ��� �� �޿��� 5000���� 10000������ �������� ��ȸ
SELECT *
FROM employees
WHERE (employee_id>=110 AND employee_id<=120) AND (salary>=5000 and salary<=10000);
--6. �����ȣ�� 110������ 120���� �ƴ� ����� ��ȸ
SELECT *
FROM employees
WHERE (employee_id<110 OR employee_id>120);
--7. �μ���ȣ�� 30(���źμ�), 60(IT�μ�), 90(�濵�μ�)�� ���� ����� ��ȸ
SELECT *
FROM employees
WHERE department_id IN (30, 60, 90);
--8. �μ���ȣ�� 30(���źμ�), 60(IT�μ�), 90(�濵�μ�) ���� �μ��� ���� ����� ��ȸ
SELECT *
FROM employees
WHERE department_id NOT IN (30, 60, 90);
--9. ��ȭ��ȣ���� 590���� �����ϴ� ������ ��ȭ��ȣ�� ��ȸ
SELECT first_name|| ' ' || last_name as full_name, phone_number
FROM employees
WHERE phone_number LIKE ('590%');

