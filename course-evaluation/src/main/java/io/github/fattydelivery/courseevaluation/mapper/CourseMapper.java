package io.github.fattydelivery.courseevaluation.mapper;

import io.github.fattydelivery.courseevaluation.pojo.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program:course-evaluation
 * @description
 * @author: Jayce(Bingjie Yan)
 * @create: 2021-05-24-17:01
 **/
@Repository
@Mapper
public interface CourseMapper {
    // 增加一个课程
    int addCourse(Course course);

    // 删除一个课程
    int delCourseById(@Param("courseId") String id);

    // 更新一个课程
    int updateCourse(Course course);

    // 查询一个课程
    Course queryCourseById(@Param("courseId") String id);

    List<Course> queryCourseByName(@Param("courseName") String name);

    // 查询所有课程
    List<Course> queryAllCourse();
}
