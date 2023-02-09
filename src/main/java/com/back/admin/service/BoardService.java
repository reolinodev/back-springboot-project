package com.back.admin.service;

import com.back.admin.domain.BoardAuth;
import com.back.admin.domain.BoardEntity;
import com.back.admin.repository.BoardAuthRepository;
import com.back.admin.repository.BoardRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardAuthRepository boardAuthRepository;


    public List<BoardEntity> getBoardList(BoardEntity boardEntity) {
        boardEntity.setStart_idx(boardEntity.getPage_per(), boardEntity.getCurrent_page());
        return boardRepository.findAll(boardEntity);
    }

    public int getBoardCount(BoardEntity boardEntity) {
        return boardRepository.countAll(boardEntity);
    }

    public int saveBoard(BoardEntity boardEntity) {
        int totResult = 1;

        String boardId = boardRepository.findLastBoardId();
        boardEntity.board_id = boardId;

        int result1 = boardRepository.save(boardEntity);
        if(result1 < 1){
            totResult = 0;
            return totResult;
        }

        String[] authIdArr = boardEntity.auth_id_arr;

        for (String authId : authIdArr) {
            BoardAuth boardAuth = new BoardAuth();
            boardAuth.auth_id = authId;
            boardAuth.board_id = boardId;
            boardAuth.created_id = boardEntity.created_id;

            int result2 = boardAuthRepository.save(boardAuth);
            if (result2 < 1) {
                totResult = 0;
            }
        }

        return totResult;
    }

    public BoardEntity getBoardData(String boardId) {
        return boardRepository.findByBoardId(boardId);
    }

    public int updateBoard(BoardEntity boardEntity) {
        int totResult = 1;

        int result1 = boardRepository.update(boardEntity);
        if(result1 < 1){
            totResult = 0;
            return totResult;
        }

        boardAuthRepository.deleteByBoardId(boardEntity.board_id);

        String[] authIdArr = boardEntity.auth_id_arr;

        for (String authId : authIdArr) {
            BoardAuth boardAuth = new BoardAuth();
            boardAuth.auth_id = authId;
            boardAuth.board_id = boardEntity.board_id;
            boardAuth.created_id = boardEntity.created_id;

            int result2 = boardAuthRepository.save(boardAuth);
            if (result2 < 1) {
                totResult = 0;
            }
        }

        return totResult;
    }

    public List<BoardEntity> getBoardAuthList(String boardId) {
        return boardAuthRepository.findByBoardId(boardId);
    }

}
