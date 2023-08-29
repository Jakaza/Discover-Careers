package com.onrender.themba.discovercareers.controller;


import com.onrender.themba.discovercareers.config.StorageProperty;
import com.onrender.themba.discovercareers.entity.Category;
import com.onrender.themba.discovercareers.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
public class PageController {
    @Autowired
    private CategoryRepository categoryRepository;
    private Path uploadsLocation;
    public PageController(StorageProperty storageProperty) {
        this.uploadsLocation = Paths.get(storageProperty.getUploadsLocation());
    }
    @GetMapping("/")
    private String index(){
        return "index";
    }
    @GetMapping("/new-category")
    private String newCategory(){
        return "new_category";
    }
    @PostMapping("/add-new-category")
    private String addCategory(@RequestParam String title, @RequestParam String description, @RequestParam MultipartFile file) throws IOException {
            System.out.println(title);
            System.out.println(description);
            System.out.println(file.getOriginalFilename());
        Category category = Category.builder()
                .title(title)
                .description(description)
                .imagePath(file.getOriginalFilename())
                .build();
        Category savedCategory = categoryRepository.save(category);
        Path specificImagePath = uploadsLocation.resolve("" + savedCategory.getId());

        if(!Files.exists(specificImagePath)){
            Files.createDirectories(specificImagePath);
        }
        Path destination = specificImagePath.resolve(Paths.get(file.getOriginalFilename())).normalize().toAbsolutePath();
        try(InputStream inputStream = file.getInputStream()){
            Files.copy(inputStream , destination , StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
