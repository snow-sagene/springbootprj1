package com.study.domain.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 게시글 작성-수정 페이지 dlehd
    @GetMapping("/post/write.do")
    public String openPostWrite(@RequestParam(value = "id", required = false) final Long id, Model model){
        //글 번홓가 있다면 해당 글번호의 정보를 담아서 글쓰기 화면 리턴j
        if (id != null) { //수정
            postService.findPostById(id); //write.do 함수 하나로 글쓰기와 글수정을 같이 하기 때문에
            PostResponse post = postService.findPostById(id);
            //해당 글 수정했을 때는 수정한 글 번호가 반환되고, 새글 작성시 null이 반환됨
            model.addAttribute("post", post);
        }
        // 글번호가 없다면 비어있는 글쓰기 화면 리턴(새글쓰기)
        return "post/write";

    }

    @PostMapping("/post/save.do")
    public String savePost(final PostRequest params) {
        postService.savePost(params);
        return "post/write";


    }

    //게시글 리스트 페이지
    @GetMapping("post/list.do")
    public String openPostList(Model model){
        //서비스에 들어가서 글의 리스트 받아오기
        List<PostResponse> posts = postService.findAllPost();

        //모델에 담아서 화면에 리턴
        model.addAttribute("posts", posts);
        return "post/list";
    }

        //게시글 상세 페이지
    @GetMapping("post/view.do")
    public String openPostView(@RequestParam Long id, Model model){
        //서비스에 들어가서 글의 리스트 받아오기
        PostResponse post = postService.findPostById(id);
        //모델에 담아서 화면에 리턴
        model.addAttribute("post", post);
        return "post/view";
    }


};

