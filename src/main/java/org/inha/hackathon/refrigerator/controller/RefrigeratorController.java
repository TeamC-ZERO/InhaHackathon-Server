package org.inha.hackathon.refrigerator.controller;

import lombok.RequiredArgsConstructor;
import lombok.val;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    @PostMapping("/ingredient")
    public ResponseEntity<ApiResponse<Void>> receiptScan(@RequestPart(value = "content") IngredientRequestDto ingredientRequest,
                                                         @RequestPart(value = "image") MultipartFile multipartFile) throws IOException {
        File file = multipartToFile(multipartFile);
        String extension = getExtensionByStringHandling(file.getName())
                .orElseThrow(() -> new IllegalArgumentException("파일명의 확장자가 존재하지 않습니다."));
        refrigeratorService.receiptScan(ingredientRequest, file, extension);
        return ResponseEntity.ok().body(ApiResponse.of(null));
    }

    private File multipartToFile(MultipartFile multipartFile) throws IOException {
        String fileName = Objects.requireNonNull(multipartFile.getOriginalFilename());
        File file = new File(System.getProperty("user.dir") + "/src/main/resources/" + fileName);
        multipartFile.transferTo(file);
        return file;
    }

    public static Optional<String> getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
                .filter(file -> file.contains("."))
                .map(file -> file.substring(filename.lastIndexOf(".") + 1));
    }
}
