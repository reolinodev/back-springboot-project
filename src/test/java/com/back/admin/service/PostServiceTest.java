package com.back.admin.service;

import com.back.admin.domain.PostEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Test
    public void getPostList() {
        //given
        PostEntity postEntity = new PostEntity();
        postEntity.page_per = 10;
        postEntity.current_page = 1;
        postEntity.search_str = "test";
        postEntity.use_yn = "Y";

        //when
        var result =  postService.getPostList(postEntity);

        //then
        Assertions.assertEquals(0, result.size());
    }

    @Test
    public void getPostCount() {
        //given
        PostEntity postEntity = new PostEntity();
        postEntity.page_per = 10;
        postEntity.current_page = 1;
        postEntity.search_str = "test";
        postEntity.use_yn = "Y";

        //when
        var result =  postService.getPostCount(postEntity);

        //then
        Assertions.assertEquals(0, result);
    }

    @Test
    public void savePost() {
        //given
        PostEntity postEntity = new PostEntity();
        postEntity.board_id = "BD00000005";
        postEntity.post_title = "테스트";
        postEntity.main_text = "테스트본문";
        postEntity.created_id = "US00000002";

        //when
        var result =  postService.savePost(postEntity);

        //then
        Assertions.assertEquals(1, result);
    }


    @Test
    public void getBoardData() {
        //given
        String postId = "PT00000001";
        //when
        var result =  postService.getPostData(postId);
        //then
        Assertions.assertEquals("테스트", result.post_title);
    }

    @Test
    public void updatePost() {
        //given
        PostEntity postEntity = new PostEntity();
        postEntity.post_title = "테스트";
        postEntity.main_text = "테스트본문";
        postEntity.updated_id = "US00000002";
        postEntity.use_yn = "N";
        postEntity.view = "Y";
        postEntity.post_id = "PT00000001";

        //when
        var result =  postService.updatePost(postEntity);

        //then
        Assertions.assertEquals(1, result);
    }

}