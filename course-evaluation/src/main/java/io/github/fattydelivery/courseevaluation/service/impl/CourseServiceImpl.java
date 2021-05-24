package io.github.fattydelivery.courseevaluation.service.impl;

import io.github.fattydelivery.courseevaluation.mapper.CourseMapper;
import io.github.fattydelivery.courseevaluation.pojo.Course;
import io.github.fattydelivery.courseevaluation.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program:course-evaluation
 * @description
 * @author: Jayce(Bingjie Yan)
 * @create: 2021-05-24-17:21
 **/
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseMapper courseMapper;

    @Override
    public int addCourse(Course course) {
        return courseMapper.addCourse(course);
    }

    @Override
    public int delCourseById(String id) {
        return courseMapper.delCourseById(id);
    }

    @Override
    public int updateCourse(Course course) {
        return courseMapper.updateCourse(course);
    }

    @Override
    public Course queryCourseById(String id) {
        return courseMapper.queryCourseById(id);
    }

    @Override
    public Course queryCourseByName(String name) {
        return courseMapper.queryCourseByName(name);
    }

    @Override
    public List<Course> queryAllCourse() {
        return courseMapper.queryAllCourse();
    }
}
