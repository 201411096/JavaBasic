
CREATE TABLE gogek(
    id varchar(8),
    name varchar(10) not null,
    tel varchar(15),
    CONSTRAINT gogek_id_pk PRIMARY KEY(id)
);
CREATE TABLE sangpum(
    no varchar(6),
    title varchar(50) not null,
    detail varchar(1024),
    count number(32) not null,
    price number(32),
    CONSTRAINT sangpum_no_pk PRIMARY KEY(no)
);

CREATE TABLE jumun(
    no number(4),
    gogek varchar(8),
    sangpum varchar(6),
    count number(32),
    jumunil date,
    CONSTRAINT jumun_no_pk PRIMARY KEY(no),
    CONSTRAINT jumun_gogek_fk FOREIGN KEY(gogek) REFERENCES gogek(id),
    CONSTRAINT jumun_sangpum_fk FOREIGN KEY(sangpum) REFERENCES sangpum(no)
);
commit;

INSERT INTO gogek ( id, name, tel ) VALUES('babo1234','홍길동', '031-333-3333' );
INSERT INTO gogek ( id, name, tel ) VALUES('babo1111','김길동', '031-111-1111' );
INSERT INTO gogek ( id, name, tel ) VALUES('babo2222','홍길동', '031-222-2222' );
INSERT INTO gogek ( id, name, tel ) VALUES('babo9999','박길동', '031-333-3333' );
INSERT INTO gogek ( id, name, tel ) VALUES('babo1234','맹길동', '031-999-9999' ); -- 기본키 제약조건 위배
INSERT INTO gogek ( id, name, tel ) VALUES('babo12345','이길동', '031-333-1234' ); -- 아이디 메모리 범위 초과
INSERT INTO gogek ( id, name, tel ) VALUES('123','최길동', '031-999-9999' );
INSERT INTO gogek ( id, name ) VALUES('babo','봉길동' );
INSERT INTO gogek ( id, tel ) VALUES('babo1988','031-888-8888' ); -- not null 제약조건 위배

INSERT INTO sangpum ( no, title, count,price, detail ) VALUES ('A00001','이쁜바지', 30, 1000, '싸고이뻐서 금방 다 팔릴 바지입니다' );
INSERT INTO sangpum ( no, title, count,price, detail ) VALUES ('A00002','그냥바지', 20, 500, '그냥이뻐고 편안한 바지입니다' );
INSERT INTO sangpum ( no, title, count )VALUES ('B00009','윗도리', 20 );
INSERT INTO sangpum ( no, title, count,price ) VALUES ('Z0001','비싼코트', 3, 20000, '겁나 비싼코트입니다' ); -- 값의 수를 너무 많이 입력함
INSERT INTO sangpum VALUES ('Z00001','비싼코트2', 5, 20000, '겁나 비싼 코트입니다' ); --상품 가격(number)에 문자가 들어감
INSERT INTO sangpum VALUES ('Z00001','비싼코트3','겁나 비싼 코트입니다', 5, 20000);

INSERT INTO jumun(no, gogek, sangpum,count, jumunil) VALUES ( 1000, 'babo1234', 'A00001', 1, '2016/09/09' );
INSERT INTO jumun(no, gogek, sangpum,count, jumunil) VALUES ( 1001, 'babo1233', 'Z00001', 1, '2016/09/09' ); -- 참조 제약조건 위배 (babo1233이 존재하지 않음)
INSERT INTO jumun(no, gogek, sangpum,count, jumunil) VALUES ( 1002, 'Babo1234', 'A00002', 2, '2016/09/10' ); -- 참조 제약조건 위배 (Babo1234가 존재하지 않음)
INSERT INTO jumun(no, gogek, sangpum,count, jumunil) VALUES ( 1003, 'babo1234', 'B00001', 1, '2016/09/11' ); -- 참조 제약조건 위배 (B00001이 존재하지 않음)
INSERT INTO jumun(no, gogek, sangpum,count, jumunil) VALUES ( 1004, 'babo', 'Z00001', 1, '2016/10/11' );
INSERT INTO jumun(no, gogek, sangpum,count, jumunil) VALUES ( 1005, 'babo1234', 'B00009', 1, '2016/11/11' );
INSERT INTO jumun(no, gogek, sangpum,count, jumunil) VALUES ( 1006, 'babo1234', 'B00009', 1, '2016/12/12' );


--전화번호가 없는 고객은 누구인지 고객명을 검색하세요
SELECT name
FROM gogek
WHERE tel IS NULL;
--홍씨 성을 가진 고객들의 정보를 검색하세요
SELECT *
FROM gogek
WHERE name LIKE '홍%';
--babo2222 고객명을 박길동으로 변경하세요
UPDATE gogek
SET name='박길동'
WHERE id='babo2222';
--아이디 123 고객의 아이디를 babo123 으로 변경하세요
UPDATE gogek
SET id='babo123'
WHERE id='123';
--봉길동씨의 전화번호 02-555-5555를 추가하세요
UPDATE gogek
SET tel='02-555-5555'
WHERE name='봉길동';

--상품번호 B00009 에 가격 1200원 그리고 “겁나 쌕시한 옷입니다”라는 상품설명을 추가하세요
UPDATE sangpum
SET detail='겁나 쌕시한 옷입니다', price=1200
WHERE no='B00009';
--이쁜바지 상품이 10개가 팔렸습니다.
UPDATE sangpum
SET count=count-10
WHERE title='이쁜바지';
--Z00001 상품이 3개가 더 입고 되었습니다.
UPDATE sangpum
SET count=count+3
WHERE no='Z00001';
--상품 가격이 1000원을 넘는 상품들을 검색하세요
SELECT *
FROM sangpum
WHERE price>1000;

SELECT * FROM jumun;
--11월 이후에 주문한 상품 번호를 검색하세요
SELECT *
FROM jumun
WHERE TO_CHAR(jumunil, 'MM') > 11;
--babo 고객이 주문한 상품을 A00002로 변경하고 수량을 2개로 수정하세요
UPDATE jumun
SET sangpum='A00002', count=2
WHERE gogek='babo';

--babo1234 고객이 11월에 주문한 상품 번호을 검색하세요
SELECT sangpum
FROM jumun
WHERE gogek='babo1234' AND TO_CHAR(jumunil, 'MM')='11';
--B00009 상품을 주문한 고객 아이디를 검색하세요
SELECT DISTINCT gogek
FROM jumun
WHERE sangpum='B00009';

------------------------------------------------ 과제 
/*
###### 오라클 정규식 표현

# 아래와 같이 테이블을 생성하고 레코드를 입력한 후

# REGEXP_LIKE  외에 REGEXP_REPALCE,REGEXP_INSTR, REGEXP_SUBSTR, REGEXP_COUNT 함수도 있음

 그러나 아래의 예문은 regexp_like 함수를 이용하여도 무방함.
*/
CREATE TABLE reg_tab( text varchar2(20) );

INSERT INTO reg_tab VALUES('TIGER');
INSERT INTO reg_tab VALUES('TIGGER');
INSERT INTO reg_tab VALUES('elephant');
INSERT INTO reg_tab VALUES('tiger');
INSERT INTO reg_tab VALUES('tiger2');
INSERT INTO reg_tab VALUES('tiger3');
INSERT INTO reg_tab VALUES('doggy');
INSERT INTO reg_tab VALUES('5doggy');
INSERT INTO reg_tab VALUES('DOG');
INSERT INTO reg_tab VALUES('DOG2');
INSERT INTO reg_tab VALUES('개');
INSERT INTO reg_tab VALUES('cat');
INSERT INTO reg_tab VALUES('catty');
INSERT INTO reg_tab VALUES('9catty');
INSERT INTO reg_tab VALUES('catwoman');
INSERT INTO reg_tab VALUES('고양이');
INSERT INTO reg_tab VALUES('BAT');
INSERT INTO reg_tab VALUES('BATMAN');
INSERT INTO reg_tab VALUES('BATGIRL');
INSERT INTO reg_tab VALUES('0BATGIRL');
INSERT INTO reg_tab VALUES('박쥐');

commit;
----------------------------------------------------------------------------------------------
-- 1.  text 컬럼의 문자열에서 't'로시작하는 데이터 검색
SELECT *
FROM reg_tab
WHERE text LIKE 't%';
-- 2. text 컬럼의 문자열에서 't'로 끝나는 데이터 검색
SELECT *
FROM reg_tab
WHERE text LIKE '%t';
-- 3. 첫번째 't'로시작하여 5번째 'r'이 있는 데이터 검색
SELECT *
FROM reg_tab
WHERE text LIKE 't___r%';
-- 4. 숫자로 끝나는 데이터 검색
SELECT *
FROM reg_tab
WHERE regexp_like(text, '[0-9]$');
-- 5. 숫자로 시작하는 데이타 검색
SELECT *
FROM reg_tab
WHERE regexp_like(text, '^[0-9]');
-- 6. 숫자가 아닌 문자로 시작하는 데이터 검색
SELECT *
FROM reg_tab
WHERE regexp_like(text, '^[^0-9]');
select * from reg_tab;
-- 7. 대문자로 시작하는 데이터 검색
SELECT *
FROM reg_tab
WHERE regexp_like(text, '^[A-Z]');
-- 8. 소문자 외의 문자로 시작하는 데이터 검색
SELECT *
FROM reg_tab
WHERE regexp_like(text, '^[^a-z]');
-- 9. 한글로 시작하는 데이터 검색
SELECT *
FROM reg_tab
WHERE regexp_like(text, '^[^a-zA-Z0-9]');
-- 10. 데이터 중 'gg'나 'GG'가 들어있는 데이터 검색
SELECT *
FROM reg_tab
WHERE regexp_like(text, '((gg)|(GG))');

---------------------------------------------------
/*
[ 계정 및 권한 ]

1. 사용자명 JAVA 비밀번호가 1234인 사용자를 생성하여라
sqlplus "/as sysdba"
create user JAVA identified by 1234;

2. 1번에서 생성한 사용자에게 오라클에 접속할 수 있는 역할,테이블 생성할 수 있는
역할,뷰를 생성할수 있는 권한을 부여하여라
grant connect, create table, create view to JAVA;

3. JAVA 의 비밀번호를 java1234로 변경하여라
alter user JAVA identified by java1234;
4. JAVA 의 권한 중 뷰를 생성할 수 있는 권한을 회수 하여라?
revoke create view from JAVA;
*/