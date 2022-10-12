-- customer 테이블 생성

create table customer(
	no number(5) unique, -- 번호
	id varchar2(30) primary key, -- 아이디
	name varchar2(30) not null, -- 이름
	age numbr(3), -- 나이
	phone varchar2(20) not null, -- 연락처
	addr varchar2(500) -- 주소
);


insert into customer values(1, 'hong', '홍길동', 27, '010-1111-1234', '서울');
insert into customer values(1, 'lee', '이순신', 27, '010-2222-6543', '부산');
insert into customer values(1, 'yoon', '윤봉길', 27, '010-3333-4567', '인천');