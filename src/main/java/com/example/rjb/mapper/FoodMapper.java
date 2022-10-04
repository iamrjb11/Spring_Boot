package com.example.rjb.mapper;

import com.example.rjb.model.Food;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface FoodMapper {
    @Select("SELECT * FROM FOOD")
    Food[] selectFoods();
    @Insert("INSERT INTO FOOD (name,price) VALUES( #{name},#{price} ) ")
    int insertFood(Food food);
    @Update("UPDATE FOOD SET name=#{name},price=#{price} WHERE id=#{id} ")
    //int updateFood(Food food,Integer id);
    int updateFood(Integer id,Food food);

    @Select("SELECT * FROM FOOD WHERE id=#{id}")
    Food selectFood(Integer id);

}
