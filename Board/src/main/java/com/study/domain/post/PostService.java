package com.study.domain.post;

import com.study.common.dto.SearchDto;
import com.study.common.paging.Pagination;
import com.study.common.paging.PagingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

//아래의 서비스는 매퍼 호출 역할
@Service
@RequiredArgsConstructor
//final로 선언된 모든 멤버에 대한 생성자를 만들어준다.
public class PostService {
    private final PostMapper postMapper;



    /**
     * 글저장 C
     * @param param
     * @return 글번호
     */
    @Transactional
    public Long savePost(PostRequest param){
        //화면에서부터 받은 데이터를 자바단에서 조작한 후
        postMapper.save(param);
        //디비에 저장한 후 일어날 일
        return param.getId(); //글의 식별자를 반환
    }


    /**
     * 글읽기 R
     * @param id
     * @return 게시글 상세정보
     */

    public PostResponse findPostById(Long id){
        PostResponse r = postMapper.findById(id);
        return r;
    }

    /**
     * 글수정 U
     * 수정이 성공했는지 안했는지 boolean, 수정한 이후의 글 response, 수정한 글 id 이중 암거나 리턴타입으로 만들면 됨. 짜기 나름
     * @param param
     * @return
     */
    @Transactional
    public Long updatePost(PostRequest param){
        postMapper.update(param);
        return param.getId();
    }



    /**
     * 글 삭제 D
     * @param id
     * @return
     */
    @Transactional
    public Long deletePost(Long id){
        postMapper.deleteById(id);
        return id;
    }

    /**
     * 게시글 리스트 조회
     * @param params - search conditions
     * @return list & pagination information
     */
    public PagingResponse<PostResponse> findAllPost(final SearchDto params) {

        // 조건에 해당하는 데이터가 없는 경우, 응답 데이터에 비어있는 리스트와 null을 담아 반환
        int count = postMapper.count(params);
        if (count < 1) {
            return new PagingResponse<>(Collections.emptyList(), null);
        }

        // Pagination 객체를 생성해서 페이지 정보 계산 후 SearchDto 타입의 객체인 params에 계산된 페이지 정보 저장
        Pagination pagination = new Pagination(count, params);
        params.setPagination(pagination);

        // 계산된 페이지 정보의 일부(limitStart, recordSize)를 기준으로 리스트 데이터 조회 후 응답 데이터 반환
        List<PostResponse> list = postMapper.findAll(params);
        return new PagingResponse<>(list, pagination);
    }


}


