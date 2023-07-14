package org.inha.hackathon.refrigerator.controller;

import lombok.RequiredArgsConstructor;
import org.inha.hackathon.common.ApiResponse;
import org.inha.hackathon.refrigerator.dto.request.IngredientRequestDto;
import org.inha.hackathon.refrigerator.dto.response.ExpirationIngredientResponseDto;
import org.inha.hackathon.refrigerator.dto.response.IngredientResponseDto;
import org.inha.hackathon.refrigerator.service.RefrigeratorService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("api/v1/refrigerator")
@Controller
public class RefrigeratorController {
    private final RefrigeratorService refrigeratorService;

    @PostMapping("/expiration")
    public ResponseEntity<ApiResponse<List<ExpirationIngredientResponseDto>>> expirationIngredients(@RequestBody IngredientRequestDto ingredientRequest) {
        return ResponseEntity.ok()
                .body(ApiResponse.of(refrigeratorService.expirationIngredients(ingredientRequest)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<List<IngredientResponseDto>>> ingredients(@RequestBody IngredientRequestDto ingredientRequest) {
        return ResponseEntity.ok()
                .body(ApiResponse.of(refrigeratorService.ingredients(ingredientRequest)));
    }
}
