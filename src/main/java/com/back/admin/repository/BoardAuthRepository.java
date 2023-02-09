package com.back.admin.repository;

import com.back.admin.domain.BoardAuth;
import com.back.admin.domain.BoardEntity;
import com.back.admin.domain.PostEntity;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardAuthRepository {

    int save(BoardAuth boardAuth);

    void deleteByBoardId(String boardId);

    List<BoardEntity> findByBoardId(String boardId);

    int countByAuthIdAndBoardId(PostEntity postEntity);

}
