package io.github.fattydelivery.courseevaluation.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program:course-evaluation
 * @description
 * @author: Jayce(Bingjie Yan)
 * @create: 2021-05-24-16:57
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
    private String ratingId;
    private String courseId;
    private String ratingTime;
    private double ratingScore;
}
