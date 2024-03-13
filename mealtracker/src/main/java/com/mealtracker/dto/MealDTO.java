package com.mealtracker.dto;


import com.mealtracker.domain.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MealDTO {


    private Long id;

    private User user;

    private String mealType;


}
