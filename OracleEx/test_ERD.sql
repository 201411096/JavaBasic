/* ��� */
CREATE TABLE emp (
	empno CHAR(4) NOT NULL, /* �����ȣ */
	ename VARCHAR2(10), /* ����� */
	job VARCHAR2(20), /* ��å */
	sal NUMBER, /* �޿� */
	deptno CHAR(2) /* �μ���ȣ */
);

COMMENT ON TABLE emp IS '���';

COMMENT ON COLUMN emp.empno IS '�����ȣ';

COMMENT ON COLUMN emp.ename IS '�����';

COMMENT ON COLUMN emp.job IS '��å';

COMMENT ON COLUMN emp.sal IS '�޿�';

COMMENT ON COLUMN emp.deptno IS '�μ���ȣ';

CREATE UNIQUE INDEX PK_emp
	ON emp (
		empno ASC
	);

ALTER TABLE emp
	ADD
		CONSTRAINT PK_emp
		PRIMARY KEY (
			empno
		);

/* �μ� */
CREATE TABLE dept (
	deptno CHAR(2) NOT NULL, /* �μ���ȣ */
	dhame VARCHAR2(20) NOT NULL, /* �μ��� */
	loc VARCHAR2(20) /* �Ҽ� */
);

COMMENT ON TABLE dept IS '�μ�';

COMMENT ON COLUMN dept.deptno IS '�μ���ȣ';

COMMENT ON COLUMN dept.dhame IS '�μ���';

COMMENT ON COLUMN dept.loc IS '�Ҽ�';

CREATE UNIQUE INDEX PK_dept
	ON dept (
		deptno ASC
	);

ALTER TABLE dept
	ADD
		CONSTRAINT PK_dept
		PRIMARY KEY (
			deptno
		);

ALTER TABLE emp
	ADD
		CONSTRAINT FK_dept_TO_emp
		FOREIGN KEY (
			deptno
		)
		REFERENCES dept (
			deptno
		);