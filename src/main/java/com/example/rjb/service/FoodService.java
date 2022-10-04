package com.example.rjb.service;

import com.example.rjb.mapper.FoodMapper;
import com.example.rjb.model.Food;
import org.springframework.stereotype.Service;

@Service
public class FoodService {
    private FoodMapper foodMapper;

    public FoodService(FoodMapper foodMapper) {
        this.foodMapper = foodMapper;
        //System.out.println("IN FoodService");
    }

    public Food[] getFoods(){
        //Food[] foods = {new Food("Barger",12.50),new Food("Pizza",23.21),new Food("Biriani",23.4)};
        Food[] foods = foodMapper.selectFoods();

        return foods;
    }
    public Food getFood(Integer id){
        Food food=foodMapper.selectFood(id);
        return food;
    }
    public int addFood(Food food){
        return foodMapper.insertFood(food);
    }
    public int updateFood(Integer id,Food food){
        System.out.println("Service--ID: "+id+" Name: "+food.getName()+" Price: "+food.getPrice());
        return foodMapper.updateFood(id,food);
    }
}
