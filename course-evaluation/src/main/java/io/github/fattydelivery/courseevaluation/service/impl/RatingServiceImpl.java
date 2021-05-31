package io.github.fattydelivery.courseevaluation.service.impl;

import io.github.fattydelivery.courseevaluation.mapper.RatingMapper;
import io.github.fattydelivery.courseevaluation.pojo.Rating;
import io.github.fattydelivery.courseevaluation.service.RatingService;
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
public class RatingServiceImpl implements RatingService {
    @Autowired
    RatingMapper ratingMapper;

    @Override
    public int addRating(String courseId, Integer ratingScore) {
        // System.out.println(courseId);
        // System.out.println(ratingScore);
        return ratingMapper.addRating(courseId, ratingScore);
    }

    @Override
    public int delRatingById(String id) {
        return ratingMapper.delRatingById(id);
    }

    @Override
    public int updateRating(Rating rating) {
        return ratingMapper.updateRating(rating);
    }

    @Override
    public Rating queryRatingById(String id) {
        return ratingMapper.queryRatingById(id);
    }

    @Override
    public List<Rating> queryAllRatingByCourseId(String id) {
        return ratingMapper.queryAllRatingByCourseId(id);
    }

    @Override
    public List<Rating> queryAllRating() {
        return ratingMapper.queryAllRating();
    }

    @Override
    public int queryNumberOfRatingByCourseId(String id) {
        return ratingMapper.queryNumberOfRatingByCourseId(id);
    }

    @Override
    public double queryAvgRatingByCourseId(String id) {
        return ratingMapper.queryAvgRatingByCourseId(id);
    }
}
