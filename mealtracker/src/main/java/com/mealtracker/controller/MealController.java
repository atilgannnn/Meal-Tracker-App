package com.mealtracker.controller;

import com.mealtracker.domain.Meal;
import com.mealtracker.domain.User;
import com.mealtracker.dto.MealDTO;

import com.mealtracker.dto.response.MtResponse;
import com.mealtracker.dto.response.ResponseMessage;
import com.mealtracker.service.MealService;
import com.mealtracker.service.UserService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meals")
public class MealController {

    private final MealService mealService;

    private final UserService userService;

    public MealController(MealService mealService, UserService userService) {
        this.mealService = mealService;
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<MtResponse> createMeal(@Valid @RequestBody MealDTO mealDTO) {
        User user = userService.getCurrentUser();
        mealService.createMeal(mealDTO, user);

        MtResponse response = new MtResponse(ResponseMessage.MEAL_CREATED_RESPONSE, true);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}/all")
    public ResponseEntity<List<MealDTO>> getMealList(@PathVariable Long userId){
        List<MealDTO> mealDTOList = mealService.getMealList(userId);

        return ResponseEntity.ok(mealDTOList);
    }


    @GetMapping("/{mealId}")
    public Meal getMealById(@PathVariable Long mealId) {
        return mealService.getMealById(mealId);
    }

    @DeleteMapping("/{mealId}")
    public void deleteMeal(@PathVariable Long mealId) {
        mealService.deleteMeal(mealId);
    }
}


