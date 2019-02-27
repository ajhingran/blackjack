import java.util.Scanner;

/**
 * Creates the betting system and has the current hold, the bet on hand, the bet on both
 * hands if there is a split, and the insurance bet
 */
class PlayerBets{
    //instance variables
    private long currenthold = 0;
    private long bet;
    private long bet2;
    private long insurance;
    private long max;

    /**
     * creates the deposit of an inital vaalue inputted by the user
     */
    public void deposit(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Make a deposit (No larger than 9,223,372,036,854,775,807");
        currenthold = sc.nextLong();
        while (currenthold<=0){
            System.out.println("You cannot deposit a non positive number: Deposit Again");
            currenthold = sc.nextLong();
        }
        max = currenthold;
        if(currenthold>1000000){
            highRoller();
        }
        System.out.println("You currently have: " + "$" + currenthold);
    }

    /**
     * player established a bet on the hand he is playing against the dealer
     */
    public void makeBet(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Make a bet");
        bet = sc.nextLong();
        while (bet<=0){
            System.out.println("You cannot bet a non positive number: Bet Again");
            bet = sc.nextLong();
        }
        if(bet>currenthold){
            bet = currenthold;
        }
        System.out.println("The bet is: " + "$" + bet);
    }

    /**
     * Calculates winnings
     * @param x value based on bet if doubledown or not
     */
    public void betWin(long x){
        currenthold = currenthold + x;
        System.out.println("\nYou currently have: " + "$"+ currenthold);
        if(currenthold>max){
            max = currenthold;
        }
    }

    /**
     * calculates losses
     * @param x
     */
    public void betLose(long x){
        currenthold = currenthold - x;
        System.out.println("\nYou currently have: " + "$" + currenthold);
    }

    /**
     * creates bet on equal hand
     * @return returns the equal bet
     */
    public long createBet2(){
        bet2=bet;
        return bet2;
    }

    /**
     * creates the insurance bet
     * @param player, decided by the bet on the hand
     */
    public void makeInsurance(PlayerBets player){
        Scanner sc = new Scanner(System.in);
        System.out.println("Make Insurance bet: Can be at Max 1/2 of your Original Bet");
        insurance = sc.nextInt();
        if(insurance>player.getBet()/2){
            insurance = player.getBet()/2;
        }
    }

    /**
     * methods to return various variables
     * @return insurace, current hold, max, bet
     */
    public long getInsurance(){
        return insurance;
    }
    public long getCurrenthold(){
        return currenthold;
    }
    public long getMax(){
        return max;
    }
    public long getBet(){
        return bet;
    }

    /**
     * displays ascii text for any better who is a high roller. deposits more than 1 million
     */
    public void highRoller(){
        System.out.println("\n$$\\      $$\\ $$$$$$$$\\        $$$$$$\\   $$$$$$\\ $$$$$$$$\\        $$$$$$\\        $$\\   $$\\ $$$$$$\\  $$$$$$\\  $$\\   $$\\       $$$$$$$\\   $$$$$$\\  $$\\       $$\\       $$$$$$$$\\ $$$$$$$\\  " +
               "\n$$ | $\\  $$ |$$  _____|      $$  __$$\\ $$  __$$\\\\__$$  __|      $$  __$$\\       $$ |  $$ |\\_$$  _|$$  __$$\\ $$ |  $$ |      $$  __$$\\ $$  __$$\\ $$ |      $$ |      $$  _____|$$  __$$\\ " +
                "\n$$ |$$$\\ $$ |$$ |            $$ /  \\__|$$ /  $$ |  $$ |         $$ /  $$ |      $$ |  $$ |  $$ |  $$ /  \\__|$$ |  $$ |      $$ |  $$ |$$ /  $$ |$$ |      $$ |      $$ |      $$ |  $$ |" +
                "\n$$ $$ $$\\$$ |$$$$$\\          $$ |$$$$\\ $$ |  $$ |  $$ |         $$$$$$$$ |      $$$$$$$$ |  $$ |  $$ |$$$$\\ $$$$$$$$ |      $$$$$$$  |$$ |  $$ |$$ |      $$ |      $$$$$\\    $$$$$$$  |" +
                "\n$$$$  _$$$$ |$$  __|         $$ |\\_$$ |$$ |  $$ |  $$ |         $$  __$$ |      $$  __$$ |  $$ |  $$ |\\_$$ |$$  __$$ |      $$  __$$< $$ |  $$ |$$ |      $$ |      $$  __|   $$  __$$< " +
                "\n$$$  / \\$$$ |$$ |            $$ |  $$ |$$ |  $$ |  $$ |         $$ |  $$ |      $$ |  $$ |  $$ |  $$ |  $$ |$$ |  $$ |      $$ |  $$ |$$ |  $$ |$$ |      $$ |      $$ |      $$ |  $$ |" +
                "\n$$  /   \\$$ |$$$$$$$$\\       \\$$$$$$  | $$$$$$  |  $$ |         $$ |  $$ |      $$ |  $$ |$$$$$$\\ \\$$$$$$  |$$ |  $$ |      $$ |  $$ | $$$$$$  |$$$$$$$$\\ $$$$$$$$\\ $$$$$$$$\\ $$ |  $$ |" +
                "\n\\__/     \\__|\\________|       \\______/  \\______/   \\__|         \\__|  \\__|      \\__|  \\__|\\______| \\______/ \\__|  \\__|      \\__|  \\__| \\______/ \\________|\\________|\\________|\\__|  \\__|" +
                "\n");
    }
}
