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

--2. 주문한 상품 내역과 고객 정보 출력
SELECT g.gno AS GNO, g.gname AS GNAME, g.price AS GPRICE, m.id AS MID, m.name AS MNAME, m.tel AS MTEL, m.addr AS MADDR
FROM ex_order o, ex_good g, ex_member m
WHERE o.gno=g.gno AND o.id=m.id;

SELECT g.gno AS GNO, g.gname AS GNAME, g.price AS GPRICE, m.id AS MID, m.name AS MNAME, m.tel AS MTEL, m.addr AS MADDR
FROM ex_order o 
INNER JOIN ex_good g
ON o.gno=g.gno
INNER JOIN ex_member m
ON o.id=m.id;

--3. 주문번호별로 고객 정보(아이디)와 총합을 출력
SELECT o.id AS ID, sum(o.count*g.price) AS SUM
FROM ex_order o, ex_good g
WHERE o.gno=g.gno
GROUP BY o.orderno, o.id; -- group by에 들어가는 컬럼과 그룹함수만 select문에 사용가능
                          -- group by o.ordername, o.id, ____ 넣고 싶은 것들을 어거지로 넣는 것을 subquery로 해결 가능
                          -- group by 칼럼만 select문에 사용이 가능하지만 그룹함수안에는 대부분의? 칼럼을 넣는게 가능한 듯
SELECT o.id AS ID, sum(o.count*g.price) AS SUM
FROM ex_order o 
INNER JOIN ex_good g
ON o.gno=g.gno
GROUP BY o.orderno, o.id;

--GROUP BY 사용시에 select문으로 출력은 안되도 groupby함수에 사용은 가능한듯------------------------------
--4. 3번에 주문 내역을 첫번째 상품명 외 몇 개로 출력 ex)  머리끈 외 2개 
--비슷하게 나오기는 하는데 주문한 순서대로 출력되는건 아닌듯
SELECT o.id AS ID, sum(o.count*g.price) AS SUM, min(g.gname)||' 외 '||(sum(o.count)-1)||'개' AS 주문내역
FROM ex_order o, ex_good g
WHERE o.gno=g.gno
GROUP BY o.orderno, o.id;

SELECT o.id AS ID, sum(o.count*g.price) AS SUM, min(g.gname)||' 외 '||(sum(o.count)-1)||'개' AS 주문내역
FROM ex_order o INNER JOIN ex_good g
ON o.gno=g.gno
GROUP BY o.orderno, o.id;
----------------------------------------------------------------------------------------------------
--SubQuery
/*----------------------------------------------------------------------------------------------------
- 서브쿼리는 ( ) 로 묶는다 [권장]
- 서브쿼리는 연산자의 오른쪽에 [권장]
- 단일 행인 경우 비교 연산자 가능 ( >, >=, <, <=, =, !=(<>) ) ex) max, min, sum, avg => subquery의 결과가 하나인 것들..
- 복수 행인 경우 IN, NOT IN, ANY, ALL, EXISTS
- ORDER BY에선 사용 못한다
*/----------------------------------------------------------------------------------------------------
-- 문제
-- 전체 사원들 중 평균급여보다 낮은 급여를 받는 사원의 명단을 조회
-- 1) 평균급여
SELECT round(avg(sal)) from emp ;
-- 2) 그 평균급여보다 낮은 급여를 받는 사원의 명단을 조회
SELECT ename, sal
FROM emp
WHERE sal< (SELECT round(avg(sal)) from emp) ; -- WHERE sal < (평균급여);

--단일행인 경우 ( >, >=, <, <=, =, !=(<>) )

-- 1_1) 최고 월급 구하기
SELECT MAX(sal)
FROM emp;
-- 1_2) 월급 제일 많이 받는 사원 구하기
SELECT *
FROM emp
WHERE sal=(SELECT MAX(sal)
            FROM emp);
            
--2) 평균 급여보다 높고 최대 급여보다 낮은 월급을 받는 사원의 정보를조회
SELECT *
FROM emp
WHERE sal<(SELECT MAX(sal) FROM emp) AND sal>(SELECT AVG(sal) FROM emp);
--가상테이블 형식--------------------------------------------------------------------------------
SELECT e.*
FROM emp e,
    (평균과 최고값) ext
WHERE e.sal>평균 AND e.sal<최고값;
--가상테이블 예시--------------------------------------------------------------------------------
SELECT e.*
FROM emp e,
    (SELECT avg(sal) avg, max(sal) max FROM emp) ext -- 가상테이블 ext
WHERE e.sal>ext.avg AND e.sal<ext.max;
-----------------------------------------------------------------------------------------------
--3) 월급순으로 상위 10명의명단을 출력 -- 가상테이블 사용
SELECT *
FROM emp
WHERE ROWNUM <=10
ORDER BY nvl(sal,0) desc nulls last; -- 정답아님 // rownum 조건이 order by보다 먼저 적용되서 제대로 된 정렬이 되지 않음

SELECT * -- 3_1) 월급 순으로 정리
FROM emp
ORDER BY nvl(sal,0) desc nulls last;

SELECT ext.* --3_2) 월급 순으로 정리된 테이블을 가져와서 10개까지 출력
FROM (SELECT *
      FROM emp
      ORDER BY nvl(sal,0) desc nulls last) ext
WHERE ROWNUM<=10;
-----------------------------------------------------------------------------------------------
/* 연습<서브쿼리>
1. SCOTT의 급여보다 많은 사원의 정보를 사원번호, 이름, 담당업무, 급여를 출력
2. 30번 부서의 최소 급여보다 각부서의 최소 급여가 높은 부서를 출력
3. 업무별로 평균 급여 중에서 가장 적은 급여를 가진 직업을 출력
4. 사원번호가 7521의 업무와 같고 사번 7934인 직원보다 급여를 많이 받는 사원의 정보를 출력
5. 'WARD'와 부서와 업무가 같은 사원 명단 출력
*/
-----------------------------------------------------------------------------------------------
select * from emp;
-- 1. SCOTT의 급여보다 많은 사원의 정보를 사원번호, 이름, 담당업무, 급여를 출력
SELECT empno, ename, job, sal
FROM emp
WHERE sal>( SELECT sal FROM emp WHERE ename='SCOTT' );
--2. 30번 부서의 최소 급여보다 각부서의 최소 급여가 높은 부서를 출력 ----------having 절에도 group함수 사용 가능
SELECT deptno, min(sal)
FROM emp
GROUP BY deptno
HAVING min(sal)>(SELECT min(sal) FROM emp GROUP BY deptno HAVING deptno=30);

--3. 업무별로 평균 급여 중에서 가장 적은 급여를 가진 직업을 출력 
SELECT e.job, avg(e.sal)
FROM emp e, (SELECT avg(sal) as avgsal  FROM emp GROUP BY job ORDER BY avg(sal)) ext
group by e.job
having avg(e.sal)=min(ext.avgsal);
--rownum 이용
SELECT e.job
FROM (SELECT job, avg(sal) FROM emp GROUP BY job ORDER BY avg(sal)) e -- 업무별 평균급여를 구해서 정렬을 해둠
WHERE rownum=1;                                                            -- 첫줄만 가져와서 급여를 가짐

--업무별 평균 급여
SELECT avg(sal)
FROM emp
GROUP BY job
ORDER BY avg(sal);

--4. 사원번호가 7521의 업무와 같고 사번 7934인 직원보다 급여를 많이 받는 사원의 정보를 출력
SELECT *
FROM emp
WHERE job=(SELECT job FROM emp WHERE empno=7521) AND sal>(SELECT sal FROM emp WHERE empno=7934);

--5. 'WARD'와 부서와 업무가 같은 사원 명단 출력 -- as는 from절에 사용할 수 없음 <가상테이블 이름 붙일 때 헷갈릴 수 있을 듯)
SELECT e.empno, e.ename, e.job, e.deptno
FROM emp e, (SELECT job, deptno FROM emp WHERE ename='WARD') ext
WHERE e.job=ext.job AND e.deptno=ext.deptno;
