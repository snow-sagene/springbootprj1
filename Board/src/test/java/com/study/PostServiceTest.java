package com.study;

import com.study.common.dto.SearchDto;
import com.study.domain.post.PostRequest;
import com.study.domain.post.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PostServiceTest {
    @Autowired
    PostService postService;

    @Test
    void save(){
        //1.준비
        PostRequest params = new PostRequest();
        params.setTitle("서비스 테스트로 넣은 제목");
        params.setContent("서비스 테스트로 넣은 내용");
        params.setWriter("테스터");
        params.setNoticeYn(false);
        //2.테스트
        Long id = postService.savePost(params);
       //3.테스트후의 행동
        System.out.println("생성된 게시글 ID : " + id);




    }
        @Test
        void saveByForeach() {
            for (int i = 13; i <= 1000; i++) {
                PostRequest params = new PostRequest();
                params.setTitle(i + "번 게시글 제목");
                params.setContent("junit으로 넣은" +i + "번 게시글 내용");
                params.setWriter("테스터" + i);
                params.setNoticeYn(false);
                postService.savePost(params);
            }
    }

        @Test
        void SelectAll(){
            SearchDto params = new SearchDto();
            postService.findAllPost(params);
        }




}
