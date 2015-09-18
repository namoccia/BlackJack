package com.example.nmoccia.blackjack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Deck deck;
    private Player dealer;
    private Player player;

    private boolean playerTurnOver = false;
    private boolean dealerTurnOver = false;
    private boolean dealerWins = false;
    private boolean playerWins = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newGame();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void newGame(){
        deck = new Deck();
        deck.shuffle();

        player = new Player();
        dealer = new Player();

        playGame();
    }

    public void playGame(){
        dealerWins = false;
        playerWins = false;
        initialDeal();
    }

    public void initialDeal(){
        dealer.hitMe(deck.deal());
        dealer.hitMe(deck.deal());
        player.hitMe(deck.deal());
        player.hitMe(deck.deal());
        displayHands();
    }


    public void hitMeButton(View v){
        player.hitMe(deck.deal());
        checkForWinner();
        displayHands();
    }

    public void stayButton(View v){
        dealerTurn();
    }

    public void newHandButton(View v){
        TextView dealerView = (TextView)findViewById(R.id.dealerCardsTextView);
        TextView playerView = (TextView)findViewById(R.id.playerCardsTextView);
        dealerWins = false;
        playerWins = false;
        dealerTurnOver = false;
        playerTurnOver = false;

        if (deck.getNextCard() >= 41)
            deck.shuffle();

        dealer.resetHand();
        dealer.hitMe(deck.deal());
        dealer.hitMe(deck.deal());

        player.resetHand();
        player.hitMe(deck.deal());
        player.hitMe(deck.deal());

        dealerView.setText("");
        playerView.setText("");
        displayHands();
    }

    public void checkForWinner(){
        if (player.getCardSum() > 21) {
            dealerWins = true;
        } else if (dealer.getCardSum() > 21) {
            playerWins = true;
        }
        else if (playerTurnOver && dealerTurnOver){
            if (player.getCardSum() > dealer.getCardSum()) {
                playerWins = true;
            } else {
                dealerWins = true;
            }
        }
    }

    public void dealerTurn(){
        playerTurnOver = true;
        hideHitAndStayButtons();

        while (!playerWins && !dealerWins) {
            if (dealer.getCardSum() < player.getCardSum()) {
                dealer.hitMe(deck.deal());
            }
            else {
                dealerTurnOver = true;
            }
            displayHands();
            checkForWinner();
        }

        displayHands();
    }

    public void hideHitAndStayButtons(){
        Button hitMeButton;
        Button stayButton;

        hitMeButton = (Button)findViewById(R.id.hit);
        stayButton = (Button)findViewById(R.id.stay);
        hitMeButton.setVisibility(View.GONE);
        stayButton.setVisibility(View.GONE);
    }

    public void showHitAndStayButtons(){
        Button hitMeButton;
        Button stayButton;

        hitMeButton = (Button)findViewById(R.id.hit);
        stayButton = (Button)findViewById(R.id.stay);
        hitMeButton.setVisibility(View.VISIBLE);
        stayButton.setVisibility(View.VISIBLE);
    }

    public void hideNewHandButton(){
        Button newHandButton;

        newHandButton = (Button)findViewById(R.id.newHand);
        newHandButton.setVisibility(View.GONE);
    }

    public void showNewHandButton(){
        Button newHandButton;

        newHandButton = (Button)findViewById(R.id.newHand);
        newHandButton.setVisibility(View.VISIBLE);
    }

    public void displayHands(){
        ArrayList<Card> dealerHand = dealer.getHand();
        ArrayList<Card> playerHand = player.getHand();

        setContentView(R.layout.activity_main);
        TextView dealerView = (TextView)findViewById(R.id.dealerCardsTextView);
        TextView playerView = (TextView)findViewById(R.id.playerCardsTextView);

        String dealerHandOutput = String.format("Card total: %d\n", dealer.getCardSum());
        for (int i = 0; i < dealerHand.size(); i++) {
            dealerHandOutput += String.format("%s of %s \n", dealerHand.get(i).getCard(), dealerHand.get(i).getSuit());
        }

        String playerHandOutput = String.format("Card total: %d\n", player.getCardSum());
        for (int i = 0; i < playerHand.size(); i++) {
            playerHandOutput += String.format("%s of %s \n", playerHand.get(i).getCard(), playerHand.get(i).getSuit());
        }

        if (playerWins) {
            dealerHandOutput += "LOSER";
            playerHandOutput += "WINNER";
            hideHitAndStayButtons();
            showNewHandButton();
        }else if (dealerWins) {
            dealerHandOutput += "WINNER";
            playerHandOutput += "LOSER";
            hideHitAndStayButtons();
            showNewHandButton();
        }
        else {
            showHitAndStayButtons();
            hideNewHandButton();
        }

        dealerView.setText(dealerHandOutput);
        playerView.setText(playerHandOutput);
    }
}
