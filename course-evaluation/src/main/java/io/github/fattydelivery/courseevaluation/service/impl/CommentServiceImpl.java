package io.github.fattydelivery.courseevaluation.service.impl;

import io.github.fattydelivery.courseevaluation.mapper.CommentMapper;
import io.github.fattydelivery.courseevaluation.pojo.Comment;
import io.github.fattydelivery.courseevaluation.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program:course-evaluation
 * @description
 * @author: Jayce(Bingjie Yan)
 * @create: 2021-05-25-12:19
 **/

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;

    @Override
    public int addComment(String courseId, String commentContent) {
        return commentMapper.addComment(courseId, commentContent);
    }

    @Override
    public int delCommentById(String id) {
        return delCommentById(id);
    }

    @Override
    public int updateComment(Comment comment) {
        return commentMapper.updateComment(comment);
    }

    @Override
    public Comment queryCommentById(String id) {
        return commentMapper.queryCommentById(id);
    }

    @Override
    public List<Comment> queryAllCommentByCourseId(String id) {
        return commentMapper.queryAllCommentByCourseId(id);
    }

    @Override
    public List<Comment> queryAllComment() {
        return commentMapper.queryAllComment();
    }

    @Override
    public int queryNumberOfCommentByCourseId(String id) {
        return commentMapper.queryNumberOfCommentByCourseId(id);
    }
}
