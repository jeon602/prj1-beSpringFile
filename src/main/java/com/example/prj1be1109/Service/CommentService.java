package com.example.prj1be1109.Service;

import com.example.prj1be1109.domain.Comment;
import com.example.prj1be1109.domain.Member;
import com.example.prj1be1109.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {


    private final CommentMapper mapper;

    public boolean add(Comment comment, Member login) {
        comment.setMemeberId(login.getId());
        return mapper.insert(comment) == 1;
    }

    public boolean validate(Comment comment) {
        if (comment == null) {
            return false;
        }

        if (comment.getBoardId() == null || comment.getBoardId() < 1) {
            return false;
        }

        if (comment.getComment() == null || comment.getComment().isBlank()) {
            return false;
        }

        return true;
    }

    public List<Comment> list(Integer boardId) {
        return mapper.selectByBoardId(boardId);
    }

    public boolean remove(Integer id) {

        return mapper.deleteById(id) == 1;
    }

    public boolean hasAccess(Integer id, Member login) {
        Comment comment = mapper.selectById(id);

        return comment.getMemeberId().equals(login.getId());
    }
}