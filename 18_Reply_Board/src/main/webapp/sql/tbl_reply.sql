-- tbl_reply 테이블 생성

create table tbl_reply(
	rno number(5) primary key, -- 답변글 번호
	bno number(5) not null, -- 원글 번호
	rewriter varchar2(30) not null, -- 답변글 작성자
	recont varchar2(500) not null, -- 답변글 내용
	redate date, -- 작성일자
	reupdate date -- 수정일자
);

alter table tbl_reply add constraint fk_reply_board foreign key(bno) references tbl_board(bno);
commit;