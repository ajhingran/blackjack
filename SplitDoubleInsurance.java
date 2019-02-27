import java.util.Scanner;

/**
 * Handles game elements of splitting, doubling down, and the choice of an insurace bet
 */
class SplitDoubleInsurance{
    public Hand Split(Hand hands, Deck decks, PlayerBets player){
        Hand newHand = new Hand(hands.tempCard(decks));
        if(hands.getCard().getRank()==hands.getCard2().getRank()&&player.getCurrenthold()/2>player.getBet()){
            Scanner sc = new Scanner(System.in);
            System.out.println("Type 1 to Split, Type 2 to Keep");
            int value = sc.nextInt();
            while(value!=1 && value!=2){
                System.out.println("\nType 1 to Split or Type 2 to Keep");
                value = sc.nextInt();
            }
            if(value ==1&&player.getCurrenthold()/2>player.getBet()){
                newHand = new Hand(hands.removeCard(), decks.deal());
                hands.getNewCard(decks);
                hands.displayHand();
                newHand.displayHand();
            }
        }
        return newHand;
    }
    public boolean doubleDown(Hand hands, Deck decks, PlayerBets player){
        if(player.getCurrenthold()/2>player.getBet()&&hands.calculateValue()<=11) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Type 1 to Double Down, Type 2 to Keep");
            int value = sc.nextInt();
            while(value!=1 && value!=2){
                System.out.println("\nType 1 to Double Down, Type 2 to Keep");
                value = sc.nextInt();
            }
            if(value ==1){
                hands.getNewCard(decks);
                return true;
            }
        }
        return false;

    }
    public PlayerBets Insurance(House house, PlayerBets player, Hand hands){
        PlayerBets insurance = new PlayerBets();
        if(house.getfirstCard().getRank()==1&&hands.calculateValue()!=21){
            house.displayHidden();
            Scanner sc = new Scanner(System.in);
            System.out.println("Type 1 to play Insurance, Type 2 to Keep");
            int value = sc.nextInt();
            while(value!=1 && value!=2){
                System.out.println("\nType 1 to to play Insurance or Type 2 to Keep");
                value = sc.nextInt();
            }
            if (value==1){
                insurance.makeInsurance(player);
            }
        }
        return insurance;
    }
}
