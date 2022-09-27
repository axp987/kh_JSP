-- board 테이블 만들기

create table board(
	
	board_no number(5) primary key, 	-- 게시판 글 번호
	board_writer varchar2(30) not null, -- 게시판 글 작성자
	board_title varchar2(200) not null; -- 게시판 글제목
	board_cont varchar2(1000),			-- 게시판 글내용
	board_pwd varchar2(30) not null, 	-- 게시판 글 비밀번호
	board_hit number(5) default 0, 		-- 게시판 글 조회수
	board_date date, 					-- 게시판 글 작성일
	board_update date					-- 게시판 글 수정일
);

-- board 테이블에 게시글을 추가해 보자.
insert into board values (1, 'lee', '오늘 하루', '재미있는 하루였다 하하하', '1234', default, sysdate, '');

insert into board values (2, '홍길동', '내일도', '신나고 재미있게 허허허', '1111', default, sysdate, '');

insert into board values (2, '네시다', '2시간', '남았네 호호호 자습때 뭐부터 해야하나', '2222', default, sysdate, '');


