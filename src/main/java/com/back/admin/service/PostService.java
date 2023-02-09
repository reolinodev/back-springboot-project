package com.back.admin.service;

import com.back.admin.domain.PostEntity;
import com.back.admin.repository.BoardAuthRepository;
import com.back.admin.repository.PostRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final BoardAuthRepository boardAuthRepository;


    public List<PostEntity> getPostList(PostEntity postEntity) {
        postEntity.setStart_idx(postEntity.getPage_per(), postEntity.getCurrent_page());
        return postRepository.findAll(postEntity);
    }

    public int getPostCount(PostEntity postEntity) {
        return postRepository.countAll(postEntity);
    }

    public PostEntity getPostData(String postId) {
        PostEntity postEntity = new PostEntity();
        postEntity.post_id = postId;
        postEntity.view="Y";
        postRepository.update(postEntity);
        return postRepository.findByPostId(postId);
    }

    public int savePost(PostEntity postEntity) {
        return postRepository.save(postEntity);
    }

    public int updatePost(PostEntity postEntity) {
        return postRepository.update(postEntity);
    }


    public int getPostAuthCnt(PostEntity postEntity) {
        return boardAuthRepository.countByAuthIdAndBoardId(postEntity);
    }
}
