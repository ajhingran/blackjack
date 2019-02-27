import java.util.ArrayList;
import java.util.Scanner;

/**
 * hand class for player hand
 * determines blackjack value
 * asks player to hit or stay
 */
class Hand{
    //instance variables
    private int handValue;
    private ArrayList<Cards> hand = new ArrayList<Cards>();

    /**
     * constructor that deals the initial first 2 cards of hand
     * @param decks
     */
    public Hand(Deck decks){
        hand.add(decks.deal());
        hand.add(decks.deal());
        calculateValue();
    }
    public Hand(Cards card, Cards card1){
        for(int i = 0; i<hand.size(); i++){
            hand.remove(i);
        }
        hand.add(card);
        hand.add(card1);
        calculateValue();
    }
    public Hand(Cards card){
        hand.add(card);
    }
    public Cards getCard(){
        return hand.get(0);
    }
    public Cards getCard2(){
        return hand.get(1);
    }
    public void getNewCard(Deck decks){
        hand.add(decks.deal());
    }
    public Cards tempCard(Deck decks){
        return decks.deal();
    }
    public int getSize(){
        return hand.size();
    }
    public Cards removeCard(){
        return hand.remove(0);
    }


    /**
     * takes input to determinte whether player hits and adds a card to his hand
     or stays and plays his current hand. Goes till user stays, or hand is over 21
     * @param decks
     */
    public void getUserChoice(Deck decks){
        int value = 0;
        Scanner scanner = new Scanner(System.in);
        while(handValue<21){
            System.out.println("\nType 1 to Hit or Type 2 to Stay");
            value = scanner.nextInt();
            while(value!=1 && value!=2){
                System.out.println("\nType 1 to Hit or Type 2 to Stay");
                value = scanner.nextInt();
            }
            if(value == 1){
                try { Thread.sleep(500); } catch (Exception e) {}
                hand.add(decks.deal());
                System.out.print("You hit and got a: ");
                System.out.println(hand.get(hand.size()-1).toString());
                calculateValue();
                try { Thread.sleep(500); } catch (Exception e) {}
                displayHand();
            }
            if(value ==2){
                calculateValue();
                try { Thread.sleep(500); } catch (Exception e) {}
                displayHand();
                break;
            }
        }

    }

    /**
     * displays hand, blackjackvalue, and if user went over 21
     */
    public void displayHand(){
        System.out.print("\nYour Hand is: ");
        System.out.println(hand);
        System.out.print("Black Jack Value: ");
        System.out.println(calculateValue());
    }


    /**
     * calculate hanvalue and ace value of either 11 or 1 depending on hand value
     * @return handValue
     */
    public int calculateValue(){
        handValue = 0;
        for(int i = 0; i<hand.size(); i++) {
            handValue += hand.get(i).getBlackjackValue();
        }
        int countofAce = 0;
        for(int i = 0; i<hand.size(); i++){
            if(hand.get(i).getRank()==1){
                countofAce ++;
            }
        }
        if(countofAce>1){
            handValue = handValue - (10*(countofAce-1));
        }
        if(countofAce ==1 && handValue >21){
            handValue = handValue - 10;
        }

        return handValue;
    }

}
