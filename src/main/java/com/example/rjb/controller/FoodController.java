package com.example.rjb.controller;

import com.example.rjb.model.Food;
import com.example.rjb.service.FoodService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FoodController {
    private FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
        //System.out.println("IN FoodController");
    }

    @GetMapping("/home")
    public String getHome(Model model,Food food){
        model.addAttribute("errorMessage","sorry");
        model.addAttribute("successMessage","Successful");
        model.addAttribute("url_obj","home");

        Food[] foods = foodService.getFoods();
        model.addAttribute("foods",foods);

        return "home";
    }
    @GetMapping("/home/edit/{id}")
    public String getEdit(Model model,Food food, @PathVariable Integer id){
        //System.out.println("ID : "+id);
        model.addAttribute("editFood","");
        model.addAttribute("url_obj","home/edit/"+id);
        Food food1=foodService.getFood(id);
        //System.out.println("ID2 : "+id);
        //Add food1 to model.addAttribute so that we can access data of food1 to html using Thymeleaf
        model.addAttribute("food",food1);
        Food[] foods = foodService.getFoods();
        model.addAttribute("foods",foods);

        return "home";
    }
    @PostMapping("/home/update/{id}")
    public String foodUpdate(Model model,Food food, @PathVariable Integer id){
        System.out.println("ID: "+id+" Name: "+food.getName()+" Price: "+food.getPrice());
        model.addAttribute("editFood","");
        model.addAttribute("url_obj","home/edit/"+id);
        //Food food1=foodService.getFood(id);
        int r=foodService.updateFood(id,food);
        System.out.println("ID2 : "+id);
        //Add food1 to model.addAttribute so that we can access data of food1 to html using Thymeleaf
       // model.addAttribute("food",food1);
        Food[] foods = foodService.getFoods();
        model.addAttribute("foods",foods);

        return "redirect:/home";
    }

    @PostMapping("/home")
    public String addFood(Model model,Food food){
        if(food.getPrice() >100){
            model.addAttribute("errorMessage",String.format("Could not - food item : %s bcz high price rate!",food.getName()));
        }
        else if(foodService.addFood(food) < 1){
            model.addAttribute("errorMessage",String.format("Could not - food item : %s",food.getName()));
        }
        else {
            model.addAttribute("successMessage", String.format("Successful - food item : %s", food.getName()));
        }
        Food[] foods = foodService.getFoods();
        model.addAttribute("foods",foods);
        //return "redirect:/home";
        return "home";
    }
}
