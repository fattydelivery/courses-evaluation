package io.github.fattydelivery.courseevaluation.mapper;

import io.github.fattydelivery.courseevaluation.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program:course-evaluation
 * @description
 * @author: Jayce(Bingjie Yan)
 * @create: 2021-05-25-12:13
 **/

@Repository
@Mapper
public interface CommentMapper {
    // 增加一个评论
    int addComment(String courseId, String commentContent);

    // 删除一个评论
    int delCommentById(@Param("commentId") String id);

    // 更新一个评论
    int updateComment(Comment comment);

    // 查询一个评论
    Comment queryCommentById(@Param("commentId") String id);

    // 根据课程Id查询所有评论
    List<Comment> queryAllCommentByCourseId(@Param("courseId") String id);

    // 查询所有评论
    List<Comment> queryAllComment();

    // 查询评论个数
    int queryNumberOfCommentByCourseId(@Param("courseId") String id);
}
