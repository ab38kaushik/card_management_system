package com.example.controller;

import com.example.dao.CardRepository;
import com.example.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Arrays;

@RestController
public class CardsController {

    @Autowired
    private CardRepository cardRepository;

    @GetMapping("/")
    public List<Card> cards() {
        return cardRepository.findAll();
    }

    @PostMapping("/cards")
    public HashMap<String, String> card(@RequestBody Card card) {
        HashMap<String, String> hashMap = new HashMap<>();
        if (isValidCreditCardNumber(card.getCard_number())){
            card.setBalance(0.0f);
            cardRepository.save(card);
            hashMap.put("message", "Card created successfully.");

        }
        else {
            hashMap.put("error", "Invalid card number.");
        }
        return hashMap;
    }

    public static boolean isValidCreditCardNumber(String cardNumber) {
        // int array for processing the cardNumber
        int[] cardIntArray = new int[cardNumber.length()];

        for (int i = 0; i < cardNumber.length(); i++) {
            char c = cardNumber.charAt(i);
            cardIntArray[i] = Integer.parseInt("" + c);
        }
        for (int i = cardIntArray.length - 2; i >= 0; i = i - 2) {
            int num = cardIntArray[i];
            num = num * 2;  // step 1
            if (num > 9) {
                num = num % 10 + num / 10;  // step 2
            }
            cardIntArray[i] = num;
        }

        int sum = sumDigits(cardIntArray);  // step 3

        System.out.println(sum);

        if (sum % 10 == 0)  // step 4
        {
            return true;
        }

        return false;

    }

    public static int sumDigits(int[] arr)
    {
        return Arrays.stream(arr).sum();
    }
}
