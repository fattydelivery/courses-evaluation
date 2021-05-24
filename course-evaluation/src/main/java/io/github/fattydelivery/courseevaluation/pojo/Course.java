package io.github.fattydelivery.courseevaluation.pojo;

import lombok.*;

/**
 * @program:course-evaluation
 * @description
 * @author: Jayce(Bingjie Yan)
 * @create: 2021-05-24-16:54
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    private String courseId;
    private String courseName;
    private int courseCredit;
    private int courseCreditHour;
    private String teacherName;
    private String typeName;
}
