package com.example.nmoccia.blackjack;

/**
 * Created by nmoccia on 9/17/2015.
 */
public class Card {
    // Class fields
    public static final String[] suit = {"Spades", "Clubs", "Hearts", "Diamonds"};
    public static final String[] card = {"Ace","King","Queen","Jack","Ten","Nine"
                                        ,"Eight","Seven","Six","Five","Four"
                                        ,"Three","Two"};

    // Object fields
    private int suitIndex;
    private int cardIndex;

    public Card(int suit, int card)
    {
        suitIndex = suit;
        cardIndex = card;
    }

    public int getSuitIndex() { return suitIndex;}
    public int getCardIndex() { return cardIndex;}
    public String getSuit() { return suit[suitIndex];}
    public String getCard() { return card[cardIndex];}
}
