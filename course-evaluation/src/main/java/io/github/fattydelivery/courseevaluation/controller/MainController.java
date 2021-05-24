package io.github.fattydelivery.courseevaluation.controller;

import io.github.fattydelivery.courseevaluation.pojo.Course;
import io.github.fattydelivery.courseevaluation.service.impl.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @program:course-evaluation
 * @description
 * @author: Jayce(Bingjie Yan)
 * @create: 2021-05-24-17:51
 **/
@Controller          //RestController=Controller+ResponseBody
public class MainController {

    @Autowired
    CourseServiceImpl courseService;


    //返回主页
    @GetMapping("/tomain")
    public String tomain() {
        return "main";
    }


    // 查询所有书籍展示在/home
    @GetMapping("/home")
    public String main(Model model) {
        Collection<Course> list = courseService.queryAllCourse();
        model.addAttribute("courselist", list);
        return "home";
    }

    //   根据id 删除书籍
    @GetMapping("/del/{id}")
    public String deleteById(@PathVariable("id") String id) { //PathVariable 表明它是前端传过来的一个参数
        int i = courseService.delCourseById(id);
        if (i > 0)
            System.out.println("删除成功");
        return "redirect:/home";
    }

    //    根据id查出要修改的书籍已有的信息去修改书籍的页面
    @GetMapping("/upd/{id}")
    public String update(@PathVariable("id") String id, Model model) {
        Course course = courseService.queryCourseById(id);
        model.addAttribute("course", course); //返回给前端要修改的书籍的所有信息
        return "upd";   //返回到修改书籍信息的页面
    }

    //修改书籍的请求处理
    @PostMapping("/updbook")
    public String updatebook(Course course) {
        courseService.updateCourse(course);
        return "redirect:/home";
    }


    //    添加书籍的页面
    @GetMapping("/add")
    public String add() {
        return "add";   //返回到修改书籍信息的页面
    }


    //添加书籍的请求处理
    @PostMapping("/addbook")
    public String addbook(Course course) {
        courseService.addCourse(course);
        return "redirect:/home";
    }


    //  根据书名  查询书籍
    @PostMapping("/search")
    public String search(String queryCourseName, Model model) {
        Course course = courseService.queryCourseByName(queryCourseName);
//        System.out.println(books);

        List<Course> list = new ArrayList<Course>();
        list.add(course);

        if (course == null) {
            list = courseService.queryAllCourse();
            model.addAttribute("error", "未查到相关书籍");
        }

        model.addAttribute("courselist", list);
        return "home";
    }
}