package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class MainController {

    @Autowired
    StudentRepo studentRepo;

    @Autowired
    TestRepo testRepo;

    //create student
    @RequestMapping("/")
    public String homePage() {

        return "homepage";
    }

    @RequestMapping("/createstudent")
    public String createStudent(Model model) {

        model.addAttribute("student", new Student());


        return "studentform";
    }
//validated test form
    @PostMapping("/addtest")
    public String addTest(@Valid @ModelAttribute("test") Test test, BindingResult result) {

        if (result.hasErrors()) {
            return "testform";
        }
        testRepo.save(test);
        return "homepage";
    }
//creating test form

    @RequestMapping("/createtest")
    public String createTest(Model model) {
       model.addAttribute("teststudent", studentRepo.findAll());

       model.addAttribute("test", new Test());
        return "testform";
    }

    //validate student form
    @PostMapping("/addstudent")
    public String addStudent(@Valid @ModelAttribute("student") Student student, BindingResult result) {

        if (result.hasErrors()) {
            return "studentform";
        }

        studentRepo.save(student);


        return "homepage";
    }


}
