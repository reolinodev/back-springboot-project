package com.back.admin.repository;

import com.back.admin.domain.BoardEntity;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository {

    List<BoardEntity> findAll(BoardEntity boardEntity);

    int countAll(BoardEntity boardEntity);

    String findLastBoardId();

    int save(BoardEntity boardEntity);

    BoardEntity findByBoardId(String boardId);

    int update(BoardEntity boardEntity);






    List<BoardEntity> findByUseYn();


//    int saveBoardAuth(BoardEntity boardEntity);
//
//    List<BoardEntity> findBoardAuthByBoardId(String boardId);
//
//    void deleteBoardAuthByBoardId(BoardEntity boardEntity);

}
