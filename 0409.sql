--추가 연습 DDL

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
