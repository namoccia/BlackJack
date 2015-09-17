package com.example.nmoccia.blackjack;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by nmoccia on 9/17/2015.
 */
public class Deck {
    // Class fields

    // Object fields
    private Card[] cards;
    private int nextCard;

    public Deck() {
        cards = new Card[52];
        nextCard = 0;

        for (int i = 0; i < cards.length; i++) {
            cards[i] = new Card(i / 13, i % 13);
        }
    }

    public void shuffle() {
        Collections.shuffle(Arrays.asList(cards));
        nextCard = 0;
    }

    public Card deal() {
        if (nextCard < cards.length) {
            nextCard++;
            return cards[nextCard-1];
        }
        else {
            return null;
        }
    }
}
