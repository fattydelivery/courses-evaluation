package io.github.fattydelivery.courseevaluation.service.impl;

import io.github.fattydelivery.courseevaluation.mapper.LikeMapper;
import io.github.fattydelivery.courseevaluation.pojo.Like;
import io.github.fattydelivery.courseevaluation.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program:course-evaluation
 * @description
 * @author: Jayce(Bingjie Yan)
 * @create: 2021-05-31-08:41
 **/
@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    LikeMapper likeMapper;

    @Override
    public int addLike(String courseId) {
        return likeMapper.addLike(courseId);
    }

    @Override
    public int delLikeById(String id) {
        return likeMapper.delLikeById(id);
    }

    @Override
    public Like queryLikeById(String id) {
        return likeMapper.queryLikeById(id);
    }

    @Override
    public List<Like> queryAllLikeByCourseId(String id) {
        return likeMapper.queryAllLikeByCourseId(id);
    }

    @Override
    public List<Like> queryAllLike() {
        return likeMapper.queryAllLike();
    }

    @Override
    public int queryNumberOfLikeByCourseId(String id) {
        return likeMapper.queryNumberOfLikeByCourseId(id);
    }
}
