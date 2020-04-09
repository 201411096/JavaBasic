--�߰� ���� DDL

--1) student ���̺� �����
CREATE TABLE student(
    no char(4),
    name varchar2(10) not null,
    gender varchar2(4),
    addr varchar2(50),
    jumin varchar2(50) not null,
    deptno number(2), 
    CONSTRAINT student_deptno_fk FOREIGN KEY(deptno) REFERENCES dept(deptno)
);
--2) �й� �÷��� �⺻Ű(primarykey) �������� �߰�
ALTER TABLE student ADD CONSTRAINT student_no_pk PRIMARY KEY(no);
--3) �ֹι�ȣ �÷����� NOTNULL �������� ����
ALTER TABLE student MODIFY name NULL;

--4) �ּ� �÷��� ����Ʈ ������������ ����� �߰�
ALTER TABLE student MODIFY addr DEFAULT '����';
--5) ���� �÷��� üũ ������������ ��������, �������� �߰�
ALTER TABLE student ADD CONSTRAINT student_deptno_gender CHECK (gender in ('����', '����'));
-----------------------------------------------------------------------------------------------------------------------
INSERT INTO student(no, name, gender, addr,jumin ) VALUES('1111', '������','����', '����� ������','801212-1234567' );
INSERT INTO student(no, name, jumin ) VALUES('2222', '������','881212-1234567');
INSERT INTO student(no, name, jumin ) VALUES('1111', '������','881212-1234567'); -- �й� ���Ἲ üũ �������� ����
INSERT INTO student(no, name, gender, jumin) VALUES('3333', '������','����', '830303-1234567'); -- ���� üũ �������� ����
INSERT INTO student(no, name,  addr, jumin ) VALUES('4444', '�����','����� ���ʱ�', '990909-1234567');
INSERT INTO student(no, name,  addr, deptno ) VALUES('8888', '�̺���','����� �Ѱ�', 50);  -- jumin null �������� ����
-----------------------------------------------------------------------------------------------------------------------

--2) library ���̺� �����
CREATE TABLE library(
    rent_id number(10),
    book_id varchar2(20) not null,
    hakbun char(4),
    CONSTRAINT library_rent_id_pk PRIMARY KEY(rent_id),
    CONSTRAINT library_hakbun_fk FOREIGN KEY(hakbun) REFERENCES student(no)
);

INSERT INTO library ( rent_id, book_id,hakbun ) VALUES( 1, 'bk001', '1111' );
INSERT INTO library ( rent_id, book_id,hakbun ) VALUES( 2, 'bk002', '2222' );
INSERT INTO library ( rent_id, book_id,hakbun ) VALUES( 3, 'bk003', '3333' ); -- hakbun �θ�Ű �������� ����
INSERT INTO library ( rent_id, book_id,hakbun ) VALUES( 4, 'bk004', '4444' );
INSERT INTO library ( rent_id, book_id,hakbun ) VALUES( 5, 'bk005', '5555' ); -- hakbun �θ�Ű �������� ����
