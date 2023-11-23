package com.study;

import com.study.domain.post.PostMapper;
import com.study.domain.post.PostRequest;
import com.study.domain.post.PostResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PostMapperTest {
    @Autowired
    PostMapper postMapper;


    @Test
    void save(){
        //테스트의 3가지. 준비. 테스트. 이후의 행동
        //1. 준비
        PostRequest params = new PostRequest();
        params.setTitle("1번 게시글 제목");
        params.setContent("1번 게시글 내용");
        params.setWriter("테스터");
        params.setNoticeYn(false);

        //2.테스트
         postMapper.save(params);


        //3.이후의 행동
        List<PostResponse> posts = postMapper.findAll();
        System.out.println("전체 게시글 개수는 : " + posts.size() + "개입니다.");

    }

    @Test
    void findById(){
        //1. 준비
        //pk에 해당하는 글번호
        Long postId = 1l;
        PostResponse res = new PostResponse();

        //2. 테스트
        res = postMapper.findById(postId);

        //3. 이후 행동
        System.out.print(res);

        System.out.println("영빈");
    }


}
