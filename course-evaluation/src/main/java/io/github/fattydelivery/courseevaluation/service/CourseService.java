package io.github.fattydelivery.courseevaluation.service;

import io.github.fattydelivery.courseevaluation.pojo.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program:course-evaluation
 * @description
 * @author: Jayce(Bingjie Yan)
 * @create: 2021-05-24-17:18
 **/
public interface CourseService {
    // 增加一个课程
    int addCourse(Course course);

    // 删除一个课程
    int delCourseById(String id);

    // 更新一个课程
    int updateCourse(Course course);

    // 查询一个课程
    Course queryCourseById(String id);

    List<Course> queryCourseByName(String name);

    // 查询所有的课程
    List<Course> queryAllCourse();
}
