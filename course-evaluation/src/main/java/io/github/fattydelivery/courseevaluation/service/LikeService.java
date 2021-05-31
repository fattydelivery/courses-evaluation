package io.github.fattydelivery.courseevaluation.service;

import io.github.fattydelivery.courseevaluation.pojo.Like;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program:course-evaluation
 * @description
 * @author: Jayce(Bingjie Yan)
 * @create: 2021-05-31-08:34
 **/

public interface LikeService {
    // 增加一个喜欢
    int addLike(String courseId);

    // 删除一个喜欢
    int delLikeById(String id);

    // 查询一个喜欢
    Like queryLikeById(String id);

    // 根据课程Id查询所有喜欢
    List<Like> queryAllLikeByCourseId(String id);

    // 查询所有喜欢
    List<Like> queryAllLike();

    // 查询喜欢个数
    int queryNumberOfLikeByCourseId(String id);
}
