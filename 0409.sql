------------------------------------------------추가 연습 DDL----------------------------------------------------------------
--1) student 테이블 만들기
CREATE TABLE student(
    no char(4),
    name varchar2(10) not null,
    gender varchar2(4),
    addr varchar2(50),
    jumin varchar2(50) not null,
    deptno number(2), 
    CONSTRAINT student_deptno_fk FOREIGN KEY(deptno) REFERENCES dept(deptno)
);
--2) 학번 컬럼에 기본키(primarykey) 제약조건 추가
ALTER TABLE student ADD CONSTRAINT student_no_pk PRIMARY KEY(no);
--3) 주민번호 컬럼에서 NOTNULL 제약조건 삭제
ALTER TABLE student MODIFY name NULL;
--4) 주소 컬럼에 디폴트 제약조건으로 “서울” 추가
ALTER TABLE student MODIFY addr DEFAULT '서울';
--5) 성별 컬럼에 체크 제약조건으로 “남성”, “여성” 추가
ALTER TABLE student ADD CONSTRAINT student_deptno_gender CHECK (gender in ('남성', '여성'));
-----------------------------------------------------------------------------------------------------------------------
INSERT INTO student(no, name, gender, addr,jumin ) VALUES('1111', '김태희','여성', '서울시 강남구','801212-1234567' );
INSERT INTO student(no, name, jumin ) VALUES('2222', '송혜교','881212-1234567');
INSERT INTO student(no, name, jumin ) VALUES('1111', '강동원','881212-1234567'); -- 학번 무결성 체크 제약조건 위배
INSERT INTO student(no, name, gender, jumin) VALUES('3333', '하정우','남자', '830303-1234567'); -- 성별 체크 제약조건 위배
INSERT INTO student(no, name,  addr, jumin ) VALUES('4444', '김새론','서울시 서초구', '990909-1234567');
INSERT INTO student(no, name,  addr, deptno ) VALUES('8888', '이병헌','서울시 한강', 50);  -- jumin null 제약조건 위배
-----------------------------------------------------------------------------------------------------------------------
--2) library 테이블 만들기
CREATE TABLE library(
    rent_id number(10),
    book_id varchar2(20) not null,
    hakbun char(4),
    CONSTRAINT library_rent_id_pk PRIMARY KEY(rent_id),
    CONSTRAINT library_hakbun_fk FOREIGN KEY(hakbun) REFERENCES student(no)
);
INSERT INTO library ( rent_id, book_id,hakbun ) VALUES( 1, 'bk001', '1111' );
INSERT INTO library ( rent_id, book_id,hakbun ) VALUES( 2, 'bk002', '2222' );
INSERT INTO library ( rent_id, book_id,hakbun ) VALUES( 3, 'bk003', '3333' ); -- hakbun 부모키 제약조건 위배
INSERT INTO library ( rent_id, book_id,hakbun ) VALUES( 4, 'bk004', '4444' );
INSERT INTO library ( rent_id, book_id,hakbun ) VALUES( 5, 'bk005', '5555' ); -- hakbun 부모키 제약조건 위배
--------------------------------------------------------------------------------------------------------------------------------
--사원정보 출력
SELECT ename, deptno FROM emp;

SELECT deptno, dname FROM emp;

SELECT emp.ename, emp.deptno, dept.dname -- 조인
FROM emp, dept
WHERE emp.deptno = dept.deptno;

--내부 조인 : null 값 연결이 안됨
SELECT e.ename as ename, e.deptno as deptno, d.dname as dname -- 테이블 별칭 사용
FROM emp e, dept d
WHERE e.deptno = d.deptno;

SELECT e.ename as ename, e.deptno as deptno, d.dname as dname  -- 조인을 먼저해줘야 속도가 빠르다..?
FROM emp e, dept d
WHERE e.deptno = d.deptno AND d.dname='ACCOUNTING';

SELECT e.ename as ename, e.deptno as deptno, d.dname as dname 
FROM emp e, dept d
WHERE d.dname='ACCOUNTING' AND e.deptno = d.deptno;

--외부 조인
SELECT e.ename as ename, e.deptno as deptno, d.dname as dname
FROM emp e, dept d
WHERE e.deptno = d.deptno(+); -- 부족한 부분에 +를 해주면???? null값을 갖고있는 행도 결과가 같이 나옴
                              -- 사원테이블에 있는데 부서쪽에는 없는 결과가 나옴

SELECT e.ename as ename, e.deptno as deptno, d.dname as dname
FROM emp e, dept d
WHERE e.deptno(+) = d.deptno; -- +를 반대로 붙이면..
                              -- 부서테이블에 있는데 사원쪽에는 없는 결과가 나옴

----------------------------------------ANSI JOIN (INNER JOIN, OUTER JOIN)---------------------------------------------
--내부 조인
SELECT e.ename as ename, e.deptno as deptno, d.dname as dname 
FROM emp e INNER JOIN dept d
ON e.deptno = d.deptno
WHERE d.dname='ACCOUNTING';
--외부조인
SELECT e.ename as ename, e.deptno as deptno, d.dname as dname
FROM emp e LEFT OUTER JOIN dept d -- +가 DEPTNO에 붙어있는거와 같음
ON e.deptno = d.deptno;

------------------------------------------------INNER JOIN 연습-------------------------------------------------------
--1. 사원번호,이름,업무,부서명,근무지 (예전 방식)
SELECT e.empno as empno, e.ename as ename, e.job as job, d.dname as dname, d.loc as loc
FROM emp e, dept d
WHERE e.deptno = d.deptno;
--1. 사원번호,이름,업무,부서명,근무지 (ANSI)
SELECT e.empno as empno, e.ename as ename, e.job as job, d.dname as dname, d.loc as loc
FROM emp e INNER JOIN dept d
ON e.deptno = d.deptno;
--2. 'SALESMAN'인 사원번호,이름,업무,부서명,근무지 (예전 방식)
SELECT e.empno as empno, e.ename as ename, e.job as job, d.dname as dname, d.loc as loc
FROM emp e, dept d
WHERE e.deptno = d.deptno AND e.job='SALESMAN';
--2. 'SALESMAN'인 사원번호,이름,업무,부서명,근무지 (ANSI)
SELECT e.empno as empno, e.ename as ename, e.job as job, d.dname as dname, d.loc as loc
FROM emp e INNER JOIN dept d
ON e.deptno = d.deptno
WHERE e.job='SALESMAN';
--3. 보너스(comm)를 받는 사원에 대해 이름, 부서명, 위치를 출력 (예전 방식)
SELECT e.ename as ename, d.dname as dname, d.loc as loc
FROM emp e, dept d
WHERE e.deptno=d.deptno AND e.comm is not null AND e.comm!=0;
--3. 보너스(comm)를 받는 사원에 대해 이름, 부서명, 위치를 출력 (ANSI)
SELECT e.ename as ename, d.dname as dname, d.loc as loc
FROM emp e INNER JOIN dept d
ON e.deptno=d.deptno
WHERE e.comm is not null AND e.comm!=0;
--4. 부서별 부서명과 급여 합계를 출력 (예전 방식)
SELECT d.dname as dname, sum(e.sal) as sal
FROM emp e, dept d
WHERE e.deptno=d.deptno
GROUP BY d.dname;   -- d.deptno으로 그룹을 묶을 경우 d.name이 출력이 안됨
                    -- d.name출력을 하고 싶을 경우 d.name으로 묶어줘야 출력이 가능
--4. 부서별 부서명과 급여 합계를 출력 (ANSI)
SELECT d.dname as dname, sum(e.sal) as sal
FROM emp e INNER JOIN dept d
ON e.deptno=d.deptno
GROUP BY d.dname;
-- 5. 업무가 ‘MANAGER’인 사원의 정보를 이름, 업무, 부서명, 근무지를 출력 (예전 방식)
SELECT e.ename as ename, e.job as job, d.dname as dname, d.loc as loc
FROM emp e, dept d
WHERE e.deptno=d.deptno AND job='MANAGER';
-- 5. 업무가 ‘MANAGER’인 사원의 정보를 이름, 업무, 부서명, 근무지를 출력 (ANSI)
SELECT e.ename as ename, e.job as job, d.dname as dname, d.loc as loc
FROM emp e INNER JOIN dept d
ON e.deptno=d.deptno
WHERE job='MANAGER';


