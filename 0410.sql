/*----------------------------------------------------------------------------------------------------
SQL 정리
1.DDL
        CREATE / DROP / ALTER
        
2.DML -> CRUD(CREATE - READ - UPDATE - DELETE)
        ` INSERT INTO 테이블명(컬럼명들) VALUES(값들);
        ` UPDATE 테이블명 SET 변경사항 WHERE 조건문;
        ` DELETE FROM 테이블명 WHERE 조건문;
        ` SELECT 컬럼들 FROM 테이블명 WHERE 조건문;
        * Transaction : all or none
        ex) A작업 : A계좌에서 100원을 빼는 것
            B작업 : B계좌에서 100원을 입금
3.DCL
        ` GRANT ex) GRANT create table TO hr;
        ` REVOKE ex) REVOKE create table FROM hr;

**
SELECT
FROM
WHERE
GROUP BY
HAVING
ORDER BY

* 조인 : 1개 이상의 테이블로부터 정보를 검색할 때(1개인 경우 - 셀프조인 ex)employee 테이블에서 매니저의 이름도 묶어서 같이 보고 싶을때)


*/----------------------------------------------------------------------------------------------------
/*----------------------------------------------------------------------------------------------------
집합(SET)
UNION : 합집합
UNION ALL : 합집합에 중복되는 교집합 두 번 출력
INTERSECT : 교집합
MINUS : 차집합
*/----------------------------------------------------------------------------------------------------
SELECT empno, ename, job, deptno FROM emp WHERE job='CLERK'
--SMITH / ADAMS / JAMES / MILLER
----------------------------------------------------------------------------------------------------
UNION
-- UNION -- SMITH ADAMS JAMES CLARK KING MILLER(중복)
-- UNION ALL -- SMITH ADAMS JAMES CLARK KING MILLER MILLER(두번 출력)
-- INTERSECT -- MILLER
-- MINUS -- SMITH ADAMS JAMES
----------------------------------------------------------------------------------------------------
SELECT empno, ename, job, deptno FROM emp WHERE deptno=10;
--CLARK / KING / MILLER

----------------------------------------------------------------------------------------------------
-- 복습 조인
CREATE TABLE ex_member(
id varchar2(10), -- 아이디
pass varchar2(10), -- 패스워드
name varchar2(20), -- 이름
tel varchar2(20) UNIQUE, -- 전화번호
addr varchar2(100), -- 주소
CONSTRAINT ex_member_id_pk PRIMARY KEY(id)
);

CREATE TABLE ex_good(
gno varchar2(10), -- 상품번호
gname varchar(30), -- 상품명
gdetail varchar2(300), -- 상세설명
price number,
CONSTRAINT ex_good_gno_pk PRIMARY KEY(gno)
);  -- 가격

CREATE TABLE ex_order (
ono number,  -- 번호
orderno varchar2(20), -- 주문번호
gno varchar2(10), -- 상품번호
id varchar2(10), -- 회원 아이디
count number,  -- 갯수
status varchar2(10), -- 배송상태
CONSTRAINT ex_order_ono_pk PRIMARY KEY(ono),
CONSTRAINT ex_order_gno_fk FOREIGN KEY(gno) REFERENCES ex_good(gno),
CONSTRAINT ex_order_id_fk FOREIGN KEY(id) REFERENCES ex_member(id)
);

insert into ex_member(id, pass, name, tel, addr )
values('kim', '1111', '김길동', '02-222-2222','서울 멋지구 이쁜동');
insert into ex_member(id, pass, name, tel, addr )
values('park', '1111', '박길동', '03-333-3333','인천 멋지구 이쁜동');
insert into ex_member(id, pass, name, tel, addr )
values('meng', '1111', '맹길동', '04-444-4444','경기 멋지구 이쁜동');

insert into ex_good( gno, gname, gdetail, price )
values('1001', '머리끈', '아주 비싼 머리끈', 200 );
insert into ex_good( gno, gname, gdetail, price )
values('2002', '자전거-A', '비싸지만 빠르게 달리는 자전거', 10000 );
insert into ex_good( gno, gname, gdetail, price )
values('2010', '자전거-B', '아주 비싸지만 느리고 안전하게 달린다는 자전거', 20000 );
insert into ex_good( gno, gname, gdetail, price )
values('3333', '핸드폰케이스', '싸고 유행하는 핸드폰 케이스', 1500 );

insert into ex_order( ono, orderno, gno, id, count, status )
values( 1, '20161212', '1001', 'park', 1, '배송완료');
insert into ex_order( ono, orderno, gno, id, count, status )
values( 2, '20161212', '2010', 'park', 1, '배송중');
insert into ex_order( ono, orderno, gno, id, count, status )
values( 3, '20161111', '1001', 'kim', 2, '주문');
insert into ex_order( ono, orderno, gno, id, count, status )
values( 4, '20161111', '3333', 'kim', 3, '주문');
insert into ex_order( ono, orderno, gno, id, count, status )
values( 5, '20163333', '1001', 'park', 3, '주문');
insert into ex_order( ono, orderno, gno, id, count, status )
values( 6, '20163333', '2010', 'park', 1, '배송중');
insert into ex_order( ono, orderno, gno, id, count, status )
values( 7, '20163333', '2002', 'park', 2, '주문');
insert into ex_order( ono, orderno, gno, id, count, status )
values( 8, '20165050', '1001', 'meng', 1, '배송중');
commit;
----------------------------------------------------------------------------------------------------
--1.  배송중인 주문 내역과 상품 내용을 출력하시오.
SELECT g.gno AS GNO, g.gname AS GNAME, g.gdetail AS GDETAIL, g.price AS PRICE, o.ono AS ONO, o.orderno AS ORDERNO, o.id AS ID, o.count AS COUNT, o.status AS STATUS
FROM ex_good g, ex_order o
WHERE g.gno=o.gno AND o.status='배송중';

SELECT *
FROM ex_good g INNER JOIN ex_order o
ON g.gno=o.gno
WHERE o.status='배송중';











----------------------------------------------------------------------------------------------------
CREATE TABLE SAMPLE(
sample_num number,
sample_name varchar2(10)
CONSTRAINT sample_sample_num
);
DROP TABLE SAMPLE