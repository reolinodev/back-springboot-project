package com.back.admin.service;

import com.back.admin.domain.AuthEntity;
import com.back.admin.domain.BoardEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    @Test
    public void getBoardList() {
        //given
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.page_per = 10;
        boardEntity.current_page = 1;
        boardEntity.search_str = "test";
        boardEntity.use_yn = "Y";
        boardEntity.setStart_idx(boardEntity.getPage_per(), boardEntity.getCurrent_page());

        //when
        var result =  boardService.getBoardList(boardEntity);

        System.out.println("result : " + result);

        //then
        Assertions.assertEquals(0, result.size());
    }

    @Test
    public void getBoardCount() {
        //given
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.page_per = 10;
        boardEntity.current_page = 1;
        boardEntity.search_str = "test";
        boardEntity.use_yn = "Y";
        boardEntity.setStart_idx(boardEntity.getPage_per(), boardEntity.getCurrent_page());

        //when
        var result =  boardService.getBoardCount(boardEntity);

        //then
        Assertions.assertEquals(0, result);
    }

    @Test
    public void saveBoard() {
        //given
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.board_title =  "공지사항";
        boardEntity.memo = "공지사항 게시판";
        boardEntity.created_id = "US00000002";
        boardEntity.auth_id_arr = new String[]{"AT00000002", "AT00000003"};

        //when
        var result =  boardService.saveBoard(boardEntity);

        //then
        Assertions.assertEquals(1, result);
    }


    @Test
    public void getBoardData() {
        //given
        String boardId = "BD00000003";
        //when
        var result =  boardService.getBoardData(boardId);
        //then
        Assertions.assertEquals("공지사항", result.board_title);
    }


    @Test
    public void updateBoard() {
        //given
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.board_id = "BD00000003";
        boardEntity.board_title =  "공지사항";
        boardEntity.memo = "공지사항 게시판";
        boardEntity.updated_id = "US00000002";
        boardEntity.use_yn = "Y";
        boardEntity.auth_id_arr = new String[]{"AT00000002", "AT00000003"};

        //when
        var result =  boardService.updateBoard(boardEntity);

        //then
        Assertions.assertEquals(1, result);
    }

    @Test
    public void getBoardAuthList() {
        //given
        String boardId = "BD00000003";

        //when
        var result =  boardService.getBoardAuthList(boardId);

        //then
        Assertions.assertEquals(2, result.size());
    }


}