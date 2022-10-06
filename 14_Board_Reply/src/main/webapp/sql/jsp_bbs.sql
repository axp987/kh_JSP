
-- jsp_bbs 테이블 생성
-- BBS(Bulletin Board Syste: 전자게시판)

-- jsp_bbs 게시판 테이블 구성
create table jsp_bbs(
	board_no number(5) primary key, -- 게시판 글번호
	board_writer varchar2(30) not null, -- 게시판 글 작성자
	board_title varchar2(100) not null, -- 게시판 제목
	board_cont varchar2(1000) not null, -- 게시판 내용
	board_pwd varchar2(30) not null, -- 게시판 패스워드
	board_hit number(5) default 0, -- 게시판 조회수
	board_date date, -- 작성일자
	board_update date, -- 수정일자
	board_group number(5), -- 게시판 그룹 
	board_step number(5), -- 게시판 글 답변 단계
	board_indent number(5) -- 게시판 답변글 들여쓰기
);


-- jsp_bbs 테이블 데이터 추가하기
insert into jsp_bbs values(1, '홍길동', '길동이 글', '도둑이얌!', 1111, default, sysdate, '', 1, 0, 0);
insert into jsp_bbs values(2, '이순신', '장군님 글', '꼬북선!', 2222, default, sysdate, '', 2, 0, 0);
insert into jsp_bbs values(3, '세종대왕', '왕님 글', '한끌!', 3333, default, sysdate, '', 3, 0, 0);
insert into jsp_bbs values(4, '이재용', '삼성 글', '갤럭티!', 4444, default, sysdate, '', 4, 0, 0);
insert into jsp_bbs values(5, '상만길', '무슨 글', '그래서 무ㅏ냐고!', 5555, default, sysdate, '', 5, 0, 0);

commit;