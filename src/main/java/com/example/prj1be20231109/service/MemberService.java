package com.example.prj1be20231109.service;

import com.example.prj1be20231109.domain.Auth;
import com.example.prj1be20231109.domain.Member;
import com.example.prj1be20231109.mapper.BoardMapper;
import com.example.prj1be20231109.mapper.CommentMapper;
import com.example.prj1be20231109.mapper.LikeMapper;
import com.example.prj1be20231109.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.PriorityQueue;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)

public class MemberService {

    private final MemberMapper mapper;
    private final BoardMapper boardMapper;
    private final CommentMapper commentMapper;
    private final BoardService boardService;
    private final LikeMapper likeMapper;
//    private final BoardList boardIdList;

    public boolean add(Member member) {
        return mapper.insert(member) == 1;
    }

    public String getId(String id) {
        return mapper.selectId(id);
    }

    public String getEmail(String email) {
        return mapper.selectEmail(email);

    }

    public boolean validate(Member member) {
        if (member == null) {
            return false;
        }

        if (member.getEmail().isBlank()) {
            return false;
        }

        if (member.getPassword().isBlank()) {
            return false;
        }

        if (member.getId().isBlank()) {
            return false;
        }
        return true;
    }

    public List<Member> list() {
        return mapper.selectAll();
    }

    public Member getMember(String id) {
        return mapper.selectById(id);
    }


    public boolean deleteMember(String id) {
        //0. 이 멤버가 작성한 댓글 삭제 하는 기능
        commentMapper.deleteByMemberId(id);

        //좋아요 삭제
        likeMapper.deleteByMemberId(id);
        // 1. 이 멤버가 작성한 게시물 삭제
        boardMapper.deleteByWriter(id);
        // 이멤버가 작성한 게시물 번호들 조회
        List<Integer> boardIdList= boardMapper.selectIdListByMemberId(id);
        boardIdList.forEach((boardId)-> boardService.remove(boardId));
        // 2. 이 멤버 삭제

        return mapper.deleteById(id) == 1;
    }
    public boolean update(Member member) {
//        Member oldMember = mapper.selectById(member.getId());
//
//        if (member.getPassword().equals("")) {
//            member.setPassword(oldMember.getPassword());
//        }

        return mapper.update(member) == 1;

    }

    public String getNickName(String nickName) {
        return mapper.selectNickName(nickName);
    }

    public boolean login(Member member, WebRequest request) {
        Member dbMember = mapper.selectById(member.getId());


        if (dbMember != null) {
            if (dbMember.getPassword().equals(member.getPassword())) {

                List<Auth> auth = mapper.selectAuthById(member.getId());
                dbMember.setAuth(auth);

                dbMember.setPassword("");
                request.setAttribute("login", dbMember, RequestAttributes.SCOPE_SESSION);
                return true;
            }
        }

        return false;
    }


    public boolean hasAccess(String id, Member login) {
        if (isAdmin(login)) {
            return true;
        }

        return login.getId().equals(id);
    }

    public boolean isAdmin(Member login) {
        if (login.getAuth() != null) {
            return login.getAuth()
                    .stream()
                    .map(e -> e.getName())
                    .anyMatch(n -> n.equals("admin"));
        }

        return false;
    }
}