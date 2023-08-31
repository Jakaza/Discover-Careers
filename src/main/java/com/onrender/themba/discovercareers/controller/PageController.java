package com.onrender.themba.discovercareers.controller;


import com.onrender.themba.discovercareers.config.StorageProperty;
import com.onrender.themba.discovercareers.entity.CareerEntity;
import com.onrender.themba.discovercareers.entity.CategoryEntity;
import com.onrender.themba.discovercareers.repository.CareerRepository;
import com.onrender.themba.discovercareers.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.Normalizer;
import java.util.*;

@Controller
public class PageController {
    private final int PAGESIZE = 6;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CareerRepository careerRepository;
    private Path uploadsLocation;
    private Path subUploadsLocation;

    public PageController(StorageProperty storageProperty) {
        this.uploadsLocation = Paths.get(storageProperty.getUploadsLocation());
        this.subUploadsLocation = Paths.get(storageProperty.getSubUploadsLocation());
    }
    @GetMapping("/")
    private String index(Model model){
        return findPagination(1, model);
    }
    @GetMapping("/new-category")
    private String newCategory(Model model){
        List<CategoryEntity> categoryList = categoryRepository.findAll();
        System.out.println(categoryList);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("totalNo", 10);
        return "new_category";
    }
    @PostMapping("/add-new-category")
    private String addCategory(@RequestParam String title,
                               @RequestParam String description,
                               @RequestParam MultipartFile file,
                               RedirectAttributes redirectAttributes) throws IOException {
            System.out.println(title);
            System.out.println(description);
            System.out.println(file.getOriginalFilename());
        CategoryEntity category = new CategoryEntity();
        category.setTitle(title);
        category.setDescription(description);
        category.setImagePath(file.getOriginalFilename());
        CategoryEntity savedCategory = categoryRepository.save(category);
        Path specificImagePath = uploadsLocation.resolve("" + savedCategory.getId());

        if(!Files.exists(specificImagePath)){
            Files.createDirectories(specificImagePath);
        }
        Path destination = specificImagePath.resolve(Paths.get(file.getOriginalFilename())).normalize().toAbsolutePath();
        try(InputStream inputStream = file.getInputStream()){
            Files.copy(inputStream , destination , StandardCopyOption.REPLACE_EXISTING);
            redirectAttributes.addAttribute("message", "Category Successfully Added.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/";
    }
    @GetMapping("/category")
    private String category(@RequestParam("category-id") String categoryID, Model model){
        Optional<CategoryEntity> optionalCategory = categoryRepository.findById(Long.valueOf(categoryID));

        if (optionalCategory.isPresent()){
            CategoryEntity category = optionalCategory.get();
            model.addAttribute("category", category);
            List<CareerEntity> careers = new ArrayList<>();
            if(!category.getCareers().isEmpty()){
                careers = category.getCareers();
            }
            model.addAttribute("careers", careers);

            return "category";
        }
        return "redirect:/";
    }
    @GetMapping("/new-career")
    private String newCareer(Model model){
        List<CategoryEntity> careerCategories = categoryRepository.findAll();

        model.addAttribute("totalNo", 10);
        model.addAttribute("careerCategories", careerCategories);
        return "new_career";
    }

    @PostMapping("/add-new-career")
    private String addCareer(@RequestParam String title,
                             @RequestParam Long category,
                             @RequestParam String description,
                             @RequestParam MultipartFile file,
                             @RequestParam Double salary,
                             @RequestParam String skills
                             ) throws IOException {
        List<String> skillList = textorizeSkills(skills);
        String imageName = file.getOriginalFilename();
        Optional<CategoryEntity> optionalCategory = categoryRepository.findById(category);
        if(optionalCategory.isPresent()){
            CategoryEntity category1 = optionalCategory.get();
            CareerEntity careerEntity = new CareerEntity();
            careerEntity.setName(title);
            careerEntity.setSalary(salary);
            careerEntity.setSkills(skillList);
            careerEntity.setCategory(category1);
            careerEntity.setImagePath(imageName);
            CareerEntity saved = careerRepository.save(careerEntity);

            String savedID = String.valueOf(saved.getId());
            Path newPath = subUploadsLocation.resolve(savedID);
            if(!Files.exists(newPath)){
                Files.createDirectories(newPath);
            }
            Path destination = newPath.resolve(Paths.get(Objects.requireNonNull(imageName))).normalize().toAbsolutePath();

            try (InputStream inputStream = file.getInputStream()){
                Files.copy(inputStream, destination, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return "redirect:/";
    }

    private List<String> textorizeSkills(String skill){
        String[] skills = skill.split(",");
        List<String> normalisedSkills = new ArrayList<>();
        for (String text : skills) {
            String normalized = Normalizer.normalize(text, Normalizer.Form.NFD);
            normalisedSkills.add(normalized.trim());
        }
        return normalisedSkills;
    }


    @GetMapping("/testing")
    private String testing(){

        List<CategoryEntity> categories = categoryRepository.findAll();
        System.out.println();

        for (int i = 0; i < categories.size(); i++) {
            List<CareerEntity> careers = categories.get(i).getCareers();
            System.out.println(categories.get(i).getFileLocation());
            System.out.println("---------------------------------------");
            for (CareerEntity career: careers) {
                System.out.println("Name : " + career.getName());
                System.out.println("Salary : " + career.getSalary());
            }
        }

        return "testing";
    }

    @GetMapping("/page/{pageNumber}")
    private String findPagination(@PathVariable("pageNumber") int currentPage, Model model){
        Pageable pageable = PageRequest.of(currentPage - 1, PAGESIZE);
        Page<CategoryEntity> page = categoryRepository.findAll(pageable);

        int totalPage = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<CategoryEntity> categories = page.getContent();

        model.addAttribute("totalPage", totalPage);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("categories", categories);

        return "index";
    }


    @GetMapping("/*")
    private String pageNotFound(){
        return "404";
    }
}
