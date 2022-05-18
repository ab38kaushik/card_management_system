package com.example.dao;

import com.example.model.Card;
import com.example.service.CardService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={com.example.service.CardService.class})
public class CardDaoTest {

    @Autowired
    private CardService cardService;

    @MockBean
    private CardRepository cardRepository;

    @Test
    public void getCardTest() {
        when(cardRepository.findAll()).thenReturn(Stream
                .of(new Card( "mechanics", "012850003580200", 200), new Card("test two", "012850003580200", 200)).collect(Collectors.toList()));
        assertEquals(2, cardRepository.findAll().size());
    }

    @Test
    public void saveCardTest() {
        Card course = new Card("mechanics", "012850003580200", 200);
        when(cardRepository.save(course)).thenReturn(course);
        assertEquals(course, cardService.addCard(course));
    }

    @Test
    public void deleteCardTest() {
        Card course = new Card("mechanics", "012850003580200", 200);
        cardService.deleteCard(course);
        verify(cardRepository, times(1)).delete(course);
    }
}
