package com.back.admin.repository;

import com.back.admin.domain.PostEntity;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository {

    List<PostEntity> findAll(PostEntity postEntity);

    int countAll(PostEntity postEntity);

    int save(PostEntity postEntity);

    int update(PostEntity postEntity);

    PostEntity findByPostId(String postId);
}
