/* 사원 */
CREATE TABLE emp (
	empno CHAR(4) NOT NULL, /* 사원번호 */
	ename VARCHAR2(10), /* 사원명 */
	job VARCHAR2(20), /* 직책 */
	sal NUMBER, /* 급여 */
	deptno CHAR(2) /* 부서번호 */
);

COMMENT ON TABLE emp IS '사원';

COMMENT ON COLUMN emp.empno IS '사원번호';

COMMENT ON COLUMN emp.ename IS '사원명';

COMMENT ON COLUMN emp.job IS '직책';

COMMENT ON COLUMN emp.sal IS '급여';

COMMENT ON COLUMN emp.deptno IS '부서번호';

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

/* 부서 */
CREATE TABLE dept (
	deptno CHAR(2) NOT NULL, /* 부서번호 */
	dhame VARCHAR2(20) NOT NULL, /* 부서명 */
	loc VARCHAR2(20) /* 소속 */
);

COMMENT ON TABLE dept IS '부서';

COMMENT ON COLUMN dept.deptno IS '부서번호';

COMMENT ON COLUMN dept.dhame IS '부서명';

COMMENT ON COLUMN dept.loc IS '소속';

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