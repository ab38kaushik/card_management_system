package com.example.service;

import com.example.dao.CardRepository;
import com.example.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public Card addCard(Card card) {
        return cardRepository.save(card);
    }


    public void deleteCard(Card card) {
        cardRepository.delete(card);
    }
}
