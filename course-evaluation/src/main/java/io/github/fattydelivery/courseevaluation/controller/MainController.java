package io.github.fattydelivery.courseevaluation.controller;

import io.github.fattydelivery.courseevaluation.pojo.Comment;
import io.github.fattydelivery.courseevaluation.pojo.Course;
import io.github.fattydelivery.courseevaluation.properties.ScriptProperties;
import io.github.fattydelivery.courseevaluation.service.impl.CommentServiceImpl;
import io.github.fattydelivery.courseevaluation.service.impl.CourseServiceImpl;
import io.github.fattydelivery.courseevaluation.service.impl.LikeServiceImpl;
import io.github.fattydelivery.courseevaluation.service.impl.RatingServiceImpl;
import io.github.fattydelivery.courseevaluation.utils.CmdExcutor;
import io.github.fattydelivery.courseevaluation.utils.InitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * @program:course-evaluation
 * @description
 * @author: Jayce(Bingjie Yan)
 * @create: 2021-05-24-17:51
 **/
@Controller          // RestController=Controller+ResponseBody
@EnableAutoConfiguration
public class MainController {

    @Autowired
    CourseServiceImpl courseService;

    @Autowired
    CommentServiceImpl commentService;

    @Autowired
    LikeServiceImpl likeService;

    @Autowired
    RatingServiceImpl ratingService;

    @Autowired
    private ScriptProperties scriptProperties;

    @Autowired
    private CmdExcutor cmdExcutor;


    private HashMap<String, Integer> getCommentInfo(Collection<Course> list) {
        HashMap<String, Integer> commentMap = new HashMap<String, Integer>();
        for (Course course : list) {
            String courseId = course.getCourseId();
            commentMap.put(courseId, commentService.queryNumberOfCommentByCourseId(courseId));
            double ratingTemp = ratingService.queryAvgRatingByCourseId(courseId);
        }
        return commentMap;
    }

    private HashMap<String, Double> getRatingInfo(Collection<Course> list) {
        HashMap<String, Double> ratingMap = new HashMap<String, Double>();
        for (Course course : list) {
            String courseId = course.getCourseId();
            double ratingTemp = ratingService.queryAvgRatingByCourseId(courseId);
            if (Double.isNaN(ratingTemp)) {
                ratingTemp = 0;
            }
            ratingMap.put(courseId, ratingTemp);
        }
        return ratingMap;
    }

    private HashMap<String, Integer> getLikeInfo(Collection<Course> list) {
        HashMap<String, Integer> likeMap = new HashMap<String, Integer>();
        for (Course course : list) {
            String courseId = course.getCourseId();
            likeMap.put(courseId, likeService.queryNumberOfLikeByCourseId(courseId));
        }
        return likeMap;
    }


    //????????????
    @GetMapping("/tomain")
    public String tomain() {
        return "main";
    }


    // ???????????????????????????/home
    @GetMapping("/home")
    public String main(Model model) {
        Collection<Course> list = courseService.queryAllCourse();

        model.addAttribute("courselist", list);
        model.addAttribute("commentmap", getCommentInfo(list));
        model.addAttribute("ratingmap", getRatingInfo(list));
        model.addAttribute("likemap", getLikeInfo(list));
        return "home";
    }

    @GetMapping("/course/{id}")
    public String course(@PathVariable("id") String id, Model model) {
        System.out.println(id);
        List<Comment> comments = commentService.queryAllCommentByCourseId(id);
//        System.out.println(comments.size());
        model.addAttribute("commentlist", comments);
        model.addAttribute("courseId", id);
        return "course";
    }

    //   ??????id ????????????
    @GetMapping("/del-course/{id}")
    public String deleteById(@PathVariable("id") String id) { //PathVariable ??????????????????????????????????????????
        int i = courseService.delCourseById(id);
        if (i > 0)
            System.out.println("????????????");
        return "redirect:/home";
    }

    //    ??????id???????????????????????????????????????????????????????????????
    @GetMapping("/upd-course/{id}")
    public String update(@PathVariable("id") String id, Model model) {
        Course course = courseService.queryCourseById(id);
        model.addAttribute("course", course); //????????????????????????????????????????????????
        return "upd";   //????????????????????????????????????
    }

    //???????????????????????????
    @PostMapping("/updcourse")
    public String updatebook(Course course) {
        courseService.updateCourse(course);
        return "redirect:/home";
    }


    // ?????????????????????
    @GetMapping("/add-course")
    public String add() {
        return "add";   //????????????????????????????????????
    }


    // ???????????????????????????
    @PostMapping("/addcourse")
    public String addbook(Course course) {
        courseService.addCourse(course);
        return "redirect:/home";
    }


    // ???????????????????????????
    @PostMapping("/addrecord")
    public String addrecord(@RequestParam(value = "course_id", required = true) String courseId,
                            @RequestParam(value = "rating", required = false) Integer rating,
                            @RequestParam(value = "like", required = false) Integer like,
                            @RequestParam(value = "comment_content", required = false) String commentContent) {
        System.out.println(courseId);
        System.out.println(rating);
        System.out.println(like);
        System.out.println(commentContent);
        if (!commentContent.trim().equals("")) {
            System.out.println("add comment");
            commentService.addComment(courseId, commentContent);
        }
        if (!(rating == 0)) {
            System.out.println("add rating");
            ratingService.addRating(courseId, new Integer(rating));
        }
        if (!(like == 0)) {
            System.out.println("add like");
            likeService.addLike(courseId);
        }
        return "redirect:/course/" + courseId;
    }


    //  ????????????  ????????????
    @PostMapping("/search")
    public String search(String queryCourseName, Model model) {
        List<Course> list = courseService.queryCourseByName(queryCourseName);
//        System.out.println(books);

        if (list.size() == 0) {
            list = courseService.queryAllCourse();
            model.addAttribute("error", "?????????????????????");
        }

        model.addAttribute("courselist", list);
        model.addAttribute("commentmap", getCommentInfo(list));
        model.addAttribute("ratingmap", getRatingInfo(list));
        model.addAttribute("likemap", getLikeInfo(list));
        return "home";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        // InitRunner initRunner = new InitRunner();
        // initRunner.run();
        System.out.println(scriptProperties.getImportShell());
        // CmdExcutor cmdExcutor= new CmdExcutor();
        model.addAttribute("cmdExcutor", cmdExcutor);
        return "admin";
    }
}