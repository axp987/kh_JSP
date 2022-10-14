-- shop_products 테이블 생성

create table shop_products (
	pnum number(5) primary key, -- 상품 번호
	pname varchar2(100) not null, -- 상품명
	pcategory_fk varchar2(10) not null, -- 카테고리 코드
	pcompany varchar2(100), -- 상품 제조사
	pimage varchar2(100), -- 상품 이미지
	pqty number(5) default 0, -- 상품 수량
	price number(10) default 0, -- 상품 가격
	pspec varchar2(30), -- 상품 스펙
	pcontents varchar2(1000), -- 상품 설명
	point number(6) default 0, -- 상품 포인트(마일리지)
	pinputdate date -- 상품 입고일자
);