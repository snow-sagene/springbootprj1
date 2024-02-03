package com.study;

import com.study.domain.post.PostMapper;
import com.study.domain.post.PostRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        //List<PostResponse> posts = postMapper.findAll();
        //System.out.println("전체 게시글 개수는 : " + posts.size() + "개입니다.");

    }
}

//    @Test
//    void findById(){
//        //1. 준비
//        //pk에 해당하는 글번호
//        Long postId = 1l;
//        PostResponse res = new PostResponse();
//
//        //2. 테스트
//        res = postMapper.findById(postId);
//
//        //3. 이후 행동
//        System.out.print(res);
//
//    }
//
//
//    @Test
//    void updatePost(){
//        //1.테스트 준비
//        PostRequest params= new PostRequest();
//        params.setId(1l);
//        params.setTitle("1번 게시글의 제목을 수정");
//        params.setContent("1번 게시글의 내용을 수정");
//        params.setWriter("홍길동");
//        params.setNoticeYn(false);
//        PostResponse res1 = postMapper.findById(params.getId());
//        System.out.println("바꾸기 전의 1번글: "+res1);
//
//
//        //2.테스트실행
//        postMapper.update(params);
//
//
//
//        //3.테스트 이후
//        //글 수정이 잘 되엇는지 체크해야하니까 해당글번호로 다시 글 조회해보기
//        PostResponse res2 = postMapper.findById(params.getId());
//        System.out.println("바꾼후의 1번글: "+res2);
//
//    }
//
//      @Test
//    void delete() {
//        System.out.println("삭제 이전의 전체 게시글 개수는 : " + postMapper.findAll().size() + "개입니다.");
//        postMapper.deleteById(1L);
//        System.out.println("삭제 이후의 전체 게시글 개수는 : " + postMapper.findAll().size() + "개입니다.");
//    }
//
//}
