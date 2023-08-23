package com.onrender.themba.discovercareers.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class PageController {

    @GetMapping("/")
    private String index(){
        return "index";
    }
    @GetMapping("/new-category")
    private String newCategory(){
        return "new_category";
    }
    @PostMapping("/add-new-category")
    private String addCategory(@RequestParam String title, @RequestParam String description, @RequestParam MultipartFile file){
        System.out.println(title);
        System.out.println(description);
        System.out.println(file.getOriginalFilename());
        return "redirect:/";
    }

    @GetMapping("/new-career")
    private String newCareer(){
        return "new_career";
    }
    @GetMapping("/testing")
    private String testing(){
        return "testing";
    }
}
