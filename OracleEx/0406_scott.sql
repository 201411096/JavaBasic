---CREATE TABLE  table_name ( [column_name  data_type] );

/*
학점관리 : student_score

학번	id	char(10)
이름	name	varchar2(10)
국어	kor	number(3)	-999 ~ 999
영어	eng	number(3)	-999 ~ 999
총점	total	number(3)	-999 ~ 999
평균	avg	number(5, 2)	number(총자릿수, 소수점의 자릿수)
*/

CREATE TABLE student_score (
    id char(10),
    name varchar2(10),
    kor number(3),
    eng number(3),
    total number(3),
    avg number(5,2)
);
--구조확인
DESC student_score;

--테이블 삭제
--DROP TABLE student_score;

/*
테이블 구조 수정
ALTER   TABLE  table_name   ADD ( [ column_name  data_type ] );
                            MODIFY( [ column_name  data_type ] );
                            DROP( [ column_name ] );
*/
--수학점수 추가
ALTER TABLE student_score ADD(math number(3));

--총점 컬럼 삭제
ALTER TABLE student_score DROP(total);
--평균 컬럼을 소수점 1자리로 변경
ALTER TABLE student_score MODIFY(avg number(4,1));

DESC student_score;

-- #################### DML ####################
/*
-INSERT : 입력
INSERT INTO table_name( columns ) VALUES ( values );

           -UPDATE : 수정

                       UPDATE  table_name SETcolumn=value WHEREcondition;

           -DELETE : 삭제

                       DELETE            FROMtable_name WHEREcondition;

           -SELECT : 검색

                       SELECT columns FROM table_name WHEREcondition;
*/
INSERT INTO student_score(id, name, kor, eng, math) VALUES('1412345678', '홍길동', 80, 50, 40);

INSERT INTO student_score(id, name, kor, math) VALUES('2000', '홍길자', 50, 80);

COMMIT;

SELECT * FROM student_score;
DELETE FROM student_score;

ROLLBACK;

-- 홍길자 학생의 정보를 지우기

DELETE FROM student_score WHERE name='홍길자';

-- 1) 홍길동 학생의 학번을 1000으로 수정

UPDATE student_score SET id='1000' WHERE name='홍길동';
ROLLBACK;

-- 2) 1000번 학생의 수학점수를 77->44로 변경
UPDATE student_score SET math=44 WHERE id='1000';

-- 3) 2000번 학생의 수학점수를 88으로 입력
UPDATE student_score SET math=88 WHERE id='2000';

-- 4) 평균구하기
UPDATE student_score SET avg=(kor+eng+math)/3;
--nvl(math,0) : math가 null값일 경우 0으로 처리해줌

CREATE TABLE emp_copy AS SELECT empno, ename, sal, comm, hiredate, deptno FROM emp;

SELECT * FROM emp_copy;
desc emp_copy;
commit;

--연습문제
--1
UPDATE emp_copy SET deptno=10 WHERE empno=7782;
--2
UPDATE emp_copy SET ename='홍길숙', sal=3500 WHERE empno=7782;
--3
UPDATE emp_copy SET comm=nvl(comm, 0)+300;
--4
DELETE FROM emp_copy WHERE empno=7499;
--5
DELETE FROM emp_copy WHERE hiredate<TO_DATE(810601);

SELECT * FROM emp_copy WHERE hiredate<'1982-12-31';

--테이블명 : info_tab
/*
이름 name varchar2(10)
아이디 jumin char(14)
전화 tel varchar2(20)
성별 gender varchar2(4)
나이 age number
출신지 home varchar2(20)
*/
CREATE TABLE info_tab (name varchar2(10), jumin char(14), tel varchar2(20), gender varchar2(4), age number, home varchar2(20));
desc info_tab;
select * from info_tab;
INSERT INTO info_tab(name, jumin, tel, gender, age, home) VALUES('홍길동', '951230-1234567', '02-222-2222', '남자', 25, '서울');
INSERT INTO info_tab(name, jumin, tel, gender, age, home) VALUES('홍길동', '951230-1234567', '02-222-2223', '남자', 25, '서울');
--나이가 27 성별이 남자인 정보 입력
INSERT INTO info_tab(name, jumin, tel, gender, age, home) VALUES('가나다', '941230-1234567', '02-222-2222', '남자', 27, '서울');
INSERT INTO info_tab(name, tel, gender, age) VALUES('홍길자', '011-1111-2222', '남자', 27);

DROP TABLE info_tab;

CREATE TABLE info_tab (
name varchar2(10) not null, -- 이름은 필수 입력사항입니다.
jumin char(14), 
tel varchar2(20), 
gender varchar2(4) not null, 
age number not null, 
home varchar2(20),
CONSTRAINT pk_info_tel PRIMARY KEY(tel)
);
UPDATE info_tab SET jumin='987654-1234567' WHERE tel='02-222-2223';

ALTER TABLE info_tab ADD CONSTRAINT  un_info_jumin UNIQUE(jumin);

select constraint_name, constraint_type from user_constraints where table_name='INFO_TAB';

ALTER TABLE info_tab ADD CONSTRAINT ck_info_gender CHECK(gender IN('남성', '여성'));

--PK로 해당하는 성별을 '남성'으로 수정

UPDATE info_tab SET gender='남성' WHERE TEL='02-222-3222';
DELETE FROM info_tab WHERE name='홍길자';


ALTER  TABLE  info_tab MODIFY  gender varchar2(4)  DEFAULT '남성';

INSERT INTO info_tab(name, tel, gender, age) VALUES ('홍순이', '051-5555', '여성', 15);
INSERT INTO info_tab(name, tel, age) VALUES('홍갑이', '011-2345', 14);

select * from info_tab;

drop table info_tab; --롤백해도 적용이 안됨 (롤백은 DML에만 적용)

CREATE TABLE info_tab(
    name varchar2(10) NOT NULL,
    jumin char(14) UNIQUE,
    tel varchar2(20) PRIMARY KEY,
    gender varchar2(4) DEFAULT '남자' CHECK(gender in ('남자', '여자')),
    home varchar2(20)  
);

CREATE TABLE info_tab(
    name varchar2(10) NOT NULL,
    jumin char(14),
    tel varchar2(20),
    gender varchar2(4) default '남자',
    home varchar2(20),
    age number,
    CONSTRAINT unique_info_tab_jumin UNIQUE(jumin), -- 중복 허용 x
    CONSTRAINT primarykey_info_tab_tel PRIMARY KEY(tel),
    CONSTRAINT check_info_tab_gender CHECK(gender in ('남자', '여자'))
);

--테스트 데이터
INSERT INTO info_tab( name, tel, jumin )
VALUES('갑순이', '02-777-4444', '990909-1234567');

INSERT INTO info_tab( name, tel, jumin, gender, age, home )
VALUES('갑갑이','03-555-5555', '880808-1234567', '남자', 27,'경기');

INSERT INTO info_tab( name, tel, jumin, gender, age, home )
VALUES('홍길동','03-031-0310', '900909-1234567', '남성', 27,'경기'); -- gender 체크 제약조건에 걸림

INSERT INTO info_tab( name, jumin) VALUES('홍길자', '550505-1234567'); -- primary key 입력이 필요함

INSERT INTO info_tab( tel, jumin ) VALUES('031-777-7777', '700707-1234567'); -- 이름을 입력해줘야함(not null)
INSERT INTO info_tab( name, tel, jumin ) VALUES('갑동이', '031-000-0000', '700707-1234567');

DESC INFO_TAB;

select * from INFO_TAB;
--emp copy에 사번이 7654인 홍길동 사원의 정보를 입력하기
select * from emp_copy;
INSERT INTO emp_copy(empno, ename) VALUES(7654, '홍길동');
DELETE FROM emp_copy WHERE ename='홍길동';

ALTER TABLE emp_copy ADD CONSTRAINT pk_emp_copy_empno PRIMARY KEY(empno);

--emp에 사번이 7369인 사원의 정보를 입력하기
INSERT INTO emp(empno, ename) VALUES(7369, '홍길동');

--emp table에 사번 8888인 갑동이 사원의 정보를 입력하되 부서번호는 99를 입력
INSERT INTO emp(empno, ename, deptno) VALUES(8888, '갑동이', 99);
--emp_copy table에 사번 8888인 갑동이 사원의 정보를 입력하되 부서번호는 99를 입력
INSERT INTO emp_copy(empno, ename, deptno) VALUES(8888, '갑동이', 99);
DELETE FROM emp_copy WHERE empno=8888;

ALTER TABLE emp_copy ADD CONSTRAINT fk_emp_copy_deptno FOREIGN KEY(deptno) REFERENCES dept (deptno);


SELECT constraint_name, constraint_type FROM user_constraints WHERE table_name='EMP_COPY';

/* 과제 및 연습

1. 교재 chapter 8, 9, 10

2. 연습

*/
--1
CREATE TABLE sawon(
    sabun number(6),
    sawon_name varchar2(10),
    ubmu varchar2(20),
    weolgub number(10, 2),
    buseo number(3)
);
--2
ALTER TABLE sawon ADD CONSTRAINT pk_sawon_sabun PRIMARY KEY(sabun);
--3
ALTER TABLE sawon ADD jiyeok varchar(20) NOT NULL;
--4
ALTER TABLE sawon MODIFY weolgub number(7);
--5
ALTER TABLE sawon ADD CONSTRAINT check_sawon_ubmu CHECK (ubmu in('개발', '유지보수', '관리'));
--6
ALTER TABLE sawon MODIFY ubmu DEFAULT '개발';
--7
CREATE TABLE buseo(
    buseo_no number(3),
    buseo_name varchar2(20),
    CONSTRAINT pk_buseo_buseo_no PRIMARY KEY(buseo_no) 
);
--8
ALTER TABLE sawon ADD CONSTRAINT fk_sawon_buseo FOREIGN KEY(buseo) REFERENCES buseo(buseo_no);
--9
INSERT INTO buseo(buseo_no, buseo_name) VALUES(101, '소프트웨어유지보수부');
INSERT INTO buseo(buseo_no, buseo_name) VALUES(202, '관리부');
INSERT INTO buseo(buseo_no, buseo_name) VALUES(303, '인적자원부');
--10
INSERT INTO sawon(sabun, sawon_name, weolgub, buseo, jiyeok) VALUES (8001, '홍길동이군', 100000, 101, '부산');
INSERT INTO sawon(sabun, sawon_name, ubmu, weolgub, buseo, jiyeok) VALUES (8002, '홍길자', '사무', 200000, 202, '인천'); --업무의 check 제약조건
INSERT INTO sawon(sabun, sawon_name, ubmu, buseo, jiyeok) VALUES (8003, '홍길순', '개발', 111, '대전'); --부서 외래키 제약조건
INSERT INTO sawon(sabun, sawon_name, weolgub, jiyeok) VALUES (8004, '홍길석', 12345678, '서울'); --월급 자리수 초과
INSERT INTO sawon(sabun, sawon_name, ubmu, buseo, jiyeok) VALUES (8005, '홍길철', '유지보수', 303, '판교');
--11
ALTER TABLE sawon DROP COLUMN jiyeok;
--12
DELETE FROM buseo WHERE buseo_name='인적자원부'; -- 자식 레코드가 존재해서 삭제가 되지 않음
--13 데이터와 저장공간만 해제
-- delete는 데이터는 삭제되지만 테이블 용량은 줄어들지 않고, 롤백이 가능
-- truncate 테이블 용량이 줄어들고, 롤백이 불가능
TRUNCATE TABLE sawon;
--14
CREATE TABLE buseo(
    buseo_no number(3),
    buseo_name varchar2(20),
    CONSTRAINT pk_buseo_buseo_no PRIMARY KEY(buseo_no)
);
CREATE TABLE sawon(
    sabun number(6),
    sawon_name varchar2(10),
    ubmu varchar2(20) DEFAULT '개발',
    weolgub varchar2(14),
    buseo number(3),
    jiyeok varchar2(20) NOT NULL,
    CONSTRAINT pk_sawon_sabun PRIMARY KEY(sabun),
    CONSTRAINT ck_sawon_ubmu CHECK(ubmu in('개발', '유지보수', '관리')),
    CONSTRAINT fk_sawon_buseo FOREIGN KEY(buseo) REFERENCES buseo(buseo_no)
);






