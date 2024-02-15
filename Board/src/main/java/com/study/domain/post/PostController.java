package com.study.domain.post;

import com.study.common.dto.MessageDto;
import com.study.common.dto.SearchDto;
import com.study.common.paging.PagingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String savePost(final PostRequest params, Model model) {
        postService.savePost(params);
        MessageDto message = new MessageDto("게시글 생성이 완료되었습니다.", "/post/list.do", RequestMethod.GET, null);
        //return "redirect:/post/list.do";
        return showMessageAndRedirect(message, model);


    }

    //게시글 리스트 페이지
    @GetMapping("post/list.do")
    public String openPostList(SearchDto params, Model model){
        //서비스에 들어가서 글의 리스트 받아오기
        PagingResponse<PostResponse> response = postService.findAllPost(params);

        //모델에 담아서 화면에 리턴
        model.addAttribute("responses", response);
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

    //글 수정 처리
    @PostMapping("/post/update.do")
    public String updatePost(final PostRequest params, Model model) {
        postService.updatePost(params);
        MessageDto message = new MessageDto("게시글 수정이 완료되었습니다.", "/post/list.do", RequestMethod.GET, null);
        return showMessageAndRedirect(message, model);
        //return "redirect:/post/list.do";


    }

    //글삭제처리
    @PostMapping("/post/delete.do")
    public String deletePost(final PostRequest params, Model model) {
        System.out.println("글삭제처리"+params);
        postService.deletePost(params.getId());
        MessageDto message = new MessageDto("게시글 삭제가 완료되었습니다.", "/post/list.do", RequestMethod.GET, null);

        //void면 요청 스트링 그대로 return 해주는 것과 같ㄷ.ㅏ return "/post/delete"
        //글의 리스트를 화면에 보여달라는 요청 ("/post/list.do")을 보내야함
        //return "redirect:/post/list.do";
        return showMessageAndRedirect(message, model);

    }
        // 사용자에게 메시지를 전달하고, 페이지를 리다이렉트 한다.
    private String showMessageAndRedirect(final MessageDto params, Model model) {
        model.addAttribute("params", params);
        return "common/messageRedirect";
    }
};

