package io.github.fattydelivery.courseevaluation.mapper;

import io.github.fattydelivery.courseevaluation.pojo.Like;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program:course-evaluation
 * @description
 * @author: Jayce(Bingjie Yan)
 * @create: 2021-05-31-08:21
 **/

@Repository
@Mapper
public interface LikeMapper {
    // 增加一个喜欢
    int addLike(String courseId);

    // 删除一个喜欢
    int delLikeById(@Param("likeId") String id);

    // 查询一个喜欢
    Like queryLikeById(@Param("likeId") String id);

    // 根据课程Id查询所有喜欢
    List<Like> queryAllLikeByCourseId(@Param("courseId") String id);

    // 查询所有喜欢
    List<Like> queryAllLike();

    // 查询喜欢个数
    int queryNumberOfLikeByCourseId(@Param("courseId") String id);
}
