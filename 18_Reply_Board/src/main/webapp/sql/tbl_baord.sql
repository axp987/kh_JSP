
-- tbl_board 게시판 테이블 생성

create table tbl_board(
	bno number(5) primary key, -- 게시판 글번호
	writer varchar2(30) not null, -- 게시판 글 작성자
	title varchar2(100) not null, -- 게시판 글 제목
	content varchar2(1000) not null, -- 게시판 글 내용
	pwd varchar2(30) not null, -- 게시판 글 비밀번호
	regdate date,  -- 게시판 글 작성일자
	regupdate date -- 게시판 글 수정일자
);

-- tbl_board 게시판 글 추가

insert into tbl_board values (1, '홍길동', '제목1', '내용1', '1111', sysdate, '');
insert into tbl_board values (2, '이순신', '장군글', '안녕', '2222', sysdate, '');
insert into tbl_board values (3, '유관순', '열사님글', '화이팅', '3333', sysdate, '');
insert into tbl_board values (4, '세종대왕', '한글', '가나다라', '4444', sysdate, '');
insert into tbl_board values (5, '김연아', '연아님글', '싁싁', '5555', sysdate, '');
commit;