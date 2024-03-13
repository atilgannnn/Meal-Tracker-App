package com.mealtracker.service;

import com.mealtracker.domain.Meal;
import com.mealtracker.domain.User;
import com.mealtracker.dto.MealDTO;
import com.mealtracker.exception.message.ErrorMessage;
import com.mealtracker.mapper.MealMapper;

import org.springframework.stereotype.Service;



import java.util.List;

@Service
public class MealService {

    private final MealMapper mealMapper;

    public MealService(MealMapper mealMapper) {
        this.mealMapper = mealMapper;
    }

    public void createMeal(MealDTO mealDTO,User user) {


        Meal meal = mealMapper.mapMealDTOToMeal(mealDTO);

        meal.setUser(user);

        mealMapper.createMeal(meal);

    }




    public Meal getMealById(Long mealId) {
        return mealMapper.getMealById(mealId);
    }

    public void updateMeal(Meal meal) {
        mealMapper.updateMeal(meal);
    }

    public void deleteMeal(Long mealId) {
        mealMapper.deleteMeal(mealId);
    }

    public List<MealDTO> getMealList(Long userId) {

        List<Meal> mealList = mealMapper.getAllMealListById(userId);

        return mealMapper.mapList(mealList);
    }
}
