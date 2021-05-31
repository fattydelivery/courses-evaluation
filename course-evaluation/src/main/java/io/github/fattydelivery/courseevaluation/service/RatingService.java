package io.github.fattydelivery.courseevaluation.service;

import io.github.fattydelivery.courseevaluation.pojo.Rating;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program:course-evaluation
 * @description
 * @author: Jayce(Bingjie Yan)
 * @create: 2021-05-31-08:34
 **/

public interface RatingService {
    // 增加一个评分
    int addRating(String courseId, Integer ratingScore);

    // 删除一个评分
    int delRatingById(String id);

    // 更新一个评分
    int updateRating(Rating rating);

    // 查询一个评分
    Rating queryRatingById(String id);

    // 根据课程Id查询所有评分
    List<Rating> queryAllRatingByCourseId(String id);

    // 查询所有评分
    List<Rating> queryAllRating();

    // 查询评分个数
    int queryNumberOfRatingByCourseId(String id);

    // 查询平均评分
    double queryAvgRatingByCourseId(String id);
}
