package com.mealtracker.mapper;

import com.mealtracker.domain.Meal;

import com.mealtracker.dto.MealDTO;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface MealMapper {

    void createMeal(Meal meal);
    
    
    Meal getMealById(Long id);
    void updateMeal(Meal meal);
    void deleteMeal(Long id);

    default Meal mapMealDTOToMeal(MealDTO mealDTO) {
        if (mealDTO == null) {
            return null;
        }

        Meal meal = new Meal();
        meal.setId(mealDTO.getId());
        meal.setUser(mealDTO.getUser());
        meal.setMealType(mealDTO.getMealType());

        return meal;
    }



    default List<MealDTO> mapList(List<Meal> mealList) {
        return mealList.stream()
                .map(this::mapMealToMealDTO)
                .collect(Collectors.toList());
    }


    default MealDTO mapMealToMealDTO(Meal meal) {
        if (meal == null) {
            return null;
        }

        MealDTO mealDTO = new MealDTO();
        mealDTO.setId(meal.getId());
        mealDTO.setUser(meal.getUser());
        mealDTO.setMealType(meal.getMealType());


        return mealDTO;
    }

    List<Meal> getAllMealListById(Long userId);

}
