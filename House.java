import java.util.ArrayList;

/**
 * Dealer Class that determines the hand of the dealer and whether it should hit or stay
 */
class House{
    //instance ArrayList for Hand of house and its Value
    private int houseValue;
    private ArrayList<Cards> house = new ArrayList<Cards>();
    /**
     * Constructor that takes in a deck to create the initial hand.
     * @param deck
     */
    public House(Deck deck){
        house.add(deck.deal());
        house.add(deck.deal());
        System.out.print("\nHouse: ");
        System.out.println(house.get(0).toString() + ", Another Card");
        calculateHouseValue();
    }

    /**
     * determines the House Value based on the Cards in the Hand. Ace Logic
     * @return houseValue black jack value of the House Hand
     */
    public int calculateHouseValue(){
        houseValue = 0;
        for(int i = 0; i<house.size(); i++) {
            houseValue += house.get(i).getBlackjackValue();
        }
        int countofAce = 0;
        for(int i = 0; i<house.size(); i++){
            if(house.get(i).getRank()==1){
                countofAce ++;
            }
        }
        if(countofAce>1){
            houseValue = houseValue - (10*(countofAce-1));
        }
        if(countofAce ==1 && houseValue >21){
            houseValue = houseValue - 10;
        }

        return houseValue;
    }
    public void displayHidden(){
        System.out.print("\nHouse: ");
        System.out.println(house.get(0).toString() + ", Another Card");
    }

    /**
     * Displays House Hand
     */
    public void displayHouseHand(){
        System.out.print("\nHouse Hand is: ");
        System.out.println(house);
        System.out.print("Black Jack Value: ");
        System.out.println(calculateHouseValue());
    }

    /**
     * Logic for whether the house should hit or stay based on the known Player hand, and its on value of hand.
     * @param hands the hand of the player
     * @param decks the deck the player and house use.
     */
    public void houseTurn(Hand hands, Deck decks){
        int handcalc = hands.calculateValue();
        while (hands.calculateValue()<=21&& calculateHouseValue()<=hands.calculateValue()&&calculateHouseValue()<=16){
            house.add(decks.deal());
            try { Thread.sleep(500); } catch (Exception e) {}
            System.out.print("House hit and got a: ");
            System.out.println(house.get(house.size()-1).toString());
            try { Thread.sleep(500); } catch (Exception e) {}
            displayHouseHand();
        }
        if(hands.calculateValue()<=21&& calculateHouseValue()<=hands.calculateValue()&&calculateHouseValue()>16){
            int random = (int)(Math.random()*2);
            if(random>=1.8){
                house.add(decks.deal());
                try { Thread.sleep(500); } catch (Exception e) {}
                System.out.print("House hit and got a: ");
                System.out.println(house.get(house.size()-1).toString());
                try { Thread.sleep(500); } catch (Exception e) {}
                displayHouseHand();
            }
        }
        if(calculateHouseValue()<21) {
            System.out.println("The House Stayed:");
        }
    }
    public Cards getfirstCard(){
        return house.get(0);
    }

}
