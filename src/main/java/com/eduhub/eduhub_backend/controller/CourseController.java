package com.eduhub.eduhub_backend.controller;


import com.eduhub.eduhub_backend.component.Course;
import com.eduhub.eduhub_backend.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//import static com.sun.tools.classfile.Attribute.Code;

@RestController
public class CourseController {

    static List<Course> courseList=new ArrayList<>();
    static{
        courseList.add(new Course("U23CS401","DSA",4));
        courseList.add(new Course("U23CS402","CC",3));
        courseList.add(new Course("U23CS403","SE",2));
        courseList.add(new Course("U23CS404","OOPs",4));
        courseList.add(new Course("U23CS405","PSC",3));
    }
    @GetMapping("course")
    public ResponseEntity<Course> getCourse()
    {
        Course course = new Course("U23CS409","DSA",4);
        return new ResponseEntity(course, HttpStatus.OK);
    }

    @GetMapping("courses")
    public ResponseEntity<List<Course>> getCourses()
    {
        List<Course> coursesList = new ArrayList<Course>();
        coursesList.add(new Course("U23CS401","DSA",4));
        coursesList.add(new Course("U23CS402","CC",3));
        coursesList.add(new Course("U23CS403","SE",2));
        coursesList.add(new Course("U23CS404","OOPs",4));
        coursesList.add(new Course("U23CS405","PSC",3));
        return new ResponseEntity<>(coursesList,HttpStatus.OK);
    }

    //https:localhost:8080/courses/U23CS404/OOPs/4
    @GetMapping("courses/{courseCode}/{CourseName}/{credits}")
    public ResponseEntity<Course> getCourse(@PathVariable String courseCode,@PathVariable String courseName,@PathVariable int credits)
    {
        Course course = new Course(courseCode,courseName,credits);
        return new ResponseEntity(course,HttpStatus.OK);
    }

    @PostMapping("courses/create")
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {

        System.out.println(course.getCourseCode());
        System.out.println(course.getCourseName());
        System.out.println(course.getCredits());

        courseList.add(course);
        return ResponseEntity.ok(course);
    }

    @PutMapping("courses/update/{courseCode}")
    public ResponseEntity<Course> updateCourse(
            @PathVariable String courseCode,
            @RequestBody Course updateCourse) {
        Course course=courseList.stream().filter(c->c.getCourseCode().equalsIgnoreCase(courseCode))
                .findFirst()
                .orElseThrow(()->new ResourceNotFoundException("Course","CourseCode",courseCode));
        course.setCourseCode(updateCourse.getCourseCode());
        course.setCourseName(updateCourse.getCourseName());
        course.setCredits(updateCourse.getCredits());

        return ResponseEntity.ok(course);
    }

    @DeleteMapping("/delete/{courseCode}")
    public ResponseEntity<String> deleteCourse(@PathVariable String courseCode) {
        Course course = courseList.stream().filter(c->c.getCourseCode().equalsIgnoreCase(courseCode))
                .findFirst()
                .orElseThrow(()->new ResourceNotFoundException("Course","CourseCode",courseCode));
        courseList.remove(course);
        return ResponseEntity.ok("Course Deleted!");
    }

    @PutMapping("query/{courseCode}")
    public String queryCourse(@PathVariable String courseCode)
    {
        if(courseCode.startsWith("*"))
        {
            throw new IllegalArgumentException("It is having a special character");
        }
//        else if(courseCode.startsWith("0"))
//        {
//            throw new IllegalArgumentException("It is 0");
//        }

        return courseCode;
    }


    @GetMapping("course/getcourses/{courseCode}")
        public ResponseEntity<Course> getCourse(@PathVariable String courseCode)
        {
            return courseList.stream().filter(c->c.getCourseCode().equalsIgnoreCase(courseCode)).
                    findFirst().map(ResponseEntity::ok).orElseThrow(()-> new ResourceNotFoundException("Course","CourseCode",courseCode));
        }
//    need to declare as static to use this
//    stream can be used instead of for loop.





}
