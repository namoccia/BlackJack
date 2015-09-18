package com.example.nmoccia.blackjack;

import java.util.ArrayList;

/**
 * Created by nmoccia on 9/18/2015.
 */
public class Player {
    // Class fields

    // Object fields
    private ArrayList<Card> hand;
    private boolean handHasAce;

    public Player(){
        hand = new ArrayList<>();
        handHasAce = false;
    }

    public void resetHand(){
        hand.clear();
        handHasAce = false;
    }

    public int getCardSum(){
        int sum = 0;

        for (int i = 0; i < hand.size(); i++) {
            switch (hand.get(i).getCardIndex())
            {
                case 0:
                    handHasAce = true;
                    sum += 11;
                    break;
                case 1:
                case 2:
                case 3:
                case 4:
                    sum += 10;
                    break;
                case 5:
                    sum += 9;
                    break;
                case 6:
                    sum += 8;
                    break;
                case 7:
                    sum += 7;
                    break;
                case 8:
                    sum += 6;
                    break;
                case 9:
                    sum += 5;
                    break;
                case 10:
                    sum += 4;
                    break;
                case 11:
                    sum += 3;
                    break;
                case 12:
                    sum += 2;
                    break;
            }
        }

        if (handHasAce && (sum > 21))
            sum -= 10;

        return sum;
    }

    public void hitMe(Card newCard){
        hand.add(newCard);
    }

    public ArrayList<Card> getHand() {
        return hand;
    }
}
