package org.inha.hackathon.refrigerator.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.inha.hackathon.infra.naver.ocr.NaverOcrApi;
import org.inha.hackathon.refrigerator.dto.request.IngredientRequestDto;
import org.inha.hackathon.refrigerator.dto.response.ExpirationIngredientResponseDto;
import org.inha.hackathon.refrigerator.dto.response.IngredientResponseDto;
import org.inha.hackathon.refrigerator.entity.Ingredient;
import org.inha.hackathon.refrigerator.entity.IngredientMeta;
import org.inha.hackathon.refrigerator.entity.Refrigerator;
import org.inha.hackathon.refrigerator.repository.IngredientMetaRepository;
import org.inha.hackathon.refrigerator.repository.IngredientRepository;
import org.inha.hackathon.refrigerator.repository.RefrigeratorRepository;
import org.inha.hackathon.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class RefrigeratorService {
    private final UserRepository userRepository;
    private final RefrigeratorRepository refrigeratorRepository;
    private final IngredientMetaRepository ingredientMetaRepository;
    private final IngredientRepository ingredientRepository;

    @Value("${naver.secret}")
    private String secretKey;

    public List<ExpirationIngredientResponseDto> expirationIngredients(IngredientRequestDto ingredientRequest) {
        String deviceToken = ingredientRequest.getDeviceToken();
        Long userId = findUserIdByDeviceToken(deviceToken);
        Refrigerator refrigerator = findRefrigerator(userId);

        Long refrigeratorId = refrigerator.getId();
        List<Ingredient> result = refrigeratorRepository.findIngredients(refrigeratorId);

        return result.stream()
                .sorted((o1, o2) ->
                    getLatestExpiration(o1.getPurchaseDate()) - getLatestExpiration(o2.getPurchaseDate()))
                .map(ExpirationIngredientResponseDto::of)
                .collect(Collectors.toList());
    }

    public List<IngredientResponseDto> ingredients(IngredientRequestDto ingredientRequest) {
        String deviceToken = ingredientRequest.getDeviceToken();
        Long userId = findUserIdByDeviceToken(deviceToken);
        Refrigerator refrigerator = findRefrigerator(userId);

        Long refrigeratorId = refrigerator.getId();
        return refrigeratorRepository.findIngredients(refrigeratorId)
                .stream()
                .map(IngredientResponseDto::of)
                .collect(Collectors.toList());
    }

    public void receiptScan(IngredientRequestDto ingredientRequest, File file, String extension) {
        String deviceToken = ingredientRequest.getDeviceToken();
        Long userId = findUserIdByDeviceToken(deviceToken);
        Refrigerator refrigerator = refrigeratorRepository.findRefrigerator(userId)
                .orElseThrow(() -> new IllegalArgumentException("id: " + userId + " 회원은 존재하지 않습니다."));
        List<String> processedReceipt = NaverOcrApi.callApi("POST", file, secretKey, extension);
        for (String word : processedReceipt) {
            if (word.startsWith("p") & word.length() > 1) {
                String processedWord = word.substring(1);
                IngredientMeta ingredientMeta = ingredientMetaRepository.findByName(processedWord)
                        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 식재료입니다."));
                Ingredient ingredient = Ingredient.builder()
                        .refrigerator(refrigerator)
                        .ingredientMeta(ingredientMeta)
                        .purchaseDate(LocalDateTime.now())
                        .endDate(LocalDateTime.now().plusDays(ingredientMeta.getPreservationDate()))
                        .build();
                ingredientRepository.save(ingredient);
            }
        }
    }

    private Long findUserIdByDeviceToken(String deviceToken) {
        return userRepository.findByDeviceToken(deviceToken)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 디바이스 토큰 값입니다."))
                .getId();
    }

    private Refrigerator findRefrigerator(Long userId) {
        return refrigeratorRepository.findRefrigerator(userId)
                .orElseThrow(() -> new IllegalArgumentException("id: " + userId + " 회원은 존재하지 않습니다."));
    }

    private Integer getLatestExpiration(LocalDateTime foodLimitTime) {
        Duration duration = Duration.between(LocalDateTime.now(), foodLimitTime);
        long limitedDay = duration.toDays();
        return (int) limitedDay;
    }
}
