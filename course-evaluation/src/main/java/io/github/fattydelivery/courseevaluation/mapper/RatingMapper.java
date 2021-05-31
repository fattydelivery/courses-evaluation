package io.github.fattydelivery.courseevaluation.mapper;

import io.github.fattydelivery.courseevaluation.pojo.Comment;
import io.github.fattydelivery.courseevaluation.pojo.Rating;
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
public interface RatingMapper {
    // 增加一个评分
    int addRating(String courseId, Integer ratingScore);

    // 删除一个评分
    int delRatingById(@Param("ratingId") String id);

    // 更新一个评分
    int updateRating(Rating rating);

    // 查询一个评分
    Rating queryRatingById(@Param("ratingId") String id);

    // 根据课程Id查询所有评分
    List<Rating> queryAllRatingByCourseId(@Param("courseId") String id);

    // 查询所有评分
    List<Rating> queryAllRating();

    // 查询评分个数
    int queryNumberOfRatingByCourseId(@Param("courseId") String id);

    // 查询平均评分
    double queryAvgRatingByCourseId(@Param("courseId") String id);
}
