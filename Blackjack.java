import java.util.Scanner;

/**
 * Creates blackjack game, with hand, player, deck, and house.
 */
class Blackjack{
    //instance variable for if the game ended in a push and to restart the game with a new hand. I.e: Tie game
    private boolean push = true;
    private long winnings;
    private long max;
    private long deposit;
    public Blackjack(){
        displayHeader();
        PlayerBets player = new PlayerBets();
        player.deposit();
        deposit = player.getCurrenthold();
        /**
         * runs the actual game; win cases, creates deck, hand, bets, and house, and runs until player
         * runs out of money.
         */
        //while push denotes whether the game ends in a tie and to restart and as the general new game boolean
        while(push) {
            //creates deck and house as well as the game mechanics
            max = player.getMax();
            Deck decks = new Deck();
            decks.shuffle();
            Hand myHand = new Hand(decks);
            SplitDoubleInsurance Game = new SplitDoubleInsurance();
            player.makeBet();
            try { Thread.sleep(500); } catch (Exception e) {}
            House theHouse = new House(decks);
            myHand.displayHand();
            //at this point the  game mechanics determines whether an insurance bet is possible.
            PlayerBets insurance = Game.Insurance(theHouse, player, myHand);
            if (myHand.calculateValue() == 21) {
                try { Thread.sleep(500); } catch (Exception e) {}
                System.out.println("You got BlackJack: You win");
                player.betWin(player.getBet());
                push = false;
            }
            //this runs if the player does put an insurance bet
            else if(insurance.getInsurance()>0) {
                if (theHouse.calculateHouseValue() == 21) {
                    try {
                        Thread.sleep(500);
                    } catch (Exception e) {
                    }
                    //if the house had blackjack, you lose the bet on your hand but win the insurance bet
                    theHouse.displayHouseHand();
                    System.out.println("House got BlackJack: House wins");
                    player.betLose(player.getBet());
                    System.out.println("You won the insurance bet");
                    player.betWin(insurance.getInsurance()*2);
                    push = false;
                }
                else {
                    // you lose the insurance bet but still have the chance to win on your main hand.
                    theHouse.displayHidden();
                    System.out.println("The House did not have a Blackjack");
                    player.betLose(insurance.getInsurance());
                    System.out.println("You lost the insurance bet");
                    //determines whether a double down is applicable, if the player chooses to double down this scenario runs
                    //with double the betting value
                    if (Game.doubleDown(myHand, decks, player)) {
                        myHand.displayHand();
                        try {
                            Thread.sleep(500);
                        } catch (Exception e) {
                        }
                        theHouse.houseTurn(myHand, decks);
                        try {
                            Thread.sleep(500);
                        } catch (Exception e) {
                        }
                        theHouse.displayHouseHand();
                        if (myHand.calculateValue() > 21) {
                            try {
                                Thread.sleep(500);
                            } catch (Exception e) {
                            }
                            System.out.println("You Busted: House Wins");
                            player.betLose(player.getBet()*2);
                            push = false;
                        } else if (theHouse.calculateHouseValue() > 21) {
                            try {
                                Thread.sleep(500);
                            } catch (Exception e) {
                            }
                            System.out.println("House Busted: You Win");
                            player.betWin(player.getBet()*2);
                            push = false;
                        } else if ((21 - theHouse.calculateHouseValue()) < (21 - myHand.calculateValue())) {
                            try {
                                Thread.sleep(500);
                            } catch (Exception e) {
                            }
                            System.out.println("House Wins");
                            player.betLose(player.getBet()*2);
                            push = false;
                        } else if ((21 - theHouse.calculateHouseValue()) > (21 - myHand.calculateValue())) {
                            try {
                                Thread.sleep(500);
                            } catch (Exception e) {
                            }
                            System.out.println("You Win");
                            player.betWin(player.getBet()*2);
                            push = false;
                        } else if ((21 - theHouse.calculateHouseValue()) == (21 - myHand.calculateValue())) {
                            try {
                                Thread.sleep(500);
                            } catch (Exception e) {
                            }
                            System.out.println("\nPush");
                            try {
                                Thread.sleep(1000);
                            } catch (Exception e) {
                            }
                        }
                    } else {
                        Hand newHand = Game.Split(myHand, decks, player);
                        if (newHand.getSize() <= 1) {
                            myHand.getUserChoice(decks);
                            try {
                                Thread.sleep(500);
                            } catch (Exception e) {
                            }
                            theHouse.houseTurn(myHand, decks);
                            try {
                                Thread.sleep(500);
                            } catch (Exception e) {
                            }
                            theHouse.displayHouseHand();
                            if (myHand.calculateValue() > 21) {
                                try {
                                    Thread.sleep(500);
                                } catch (Exception e) {
                                }
                                System.out.println("You Busted: House Wins");
                                player.betLose(player.getBet());
                                push = false;
                            } else if (theHouse.calculateHouseValue() > 21) {
                                try {
                                    Thread.sleep(500);
                                } catch (Exception e) {
                                }
                                System.out.println("House Busted: You Win");
                                player.betWin(player.getBet());
                                push = false;
                            } else if ((21 - theHouse.calculateHouseValue()) < (21 - myHand.calculateValue())) {
                                try {
                                    Thread.sleep(500);
                                } catch (Exception e) {
                                }
                                System.out.println("House Wins");
                                player.betLose(player.getBet());
                                push = false;
                            } else if ((21 - theHouse.calculateHouseValue()) > (21 - myHand.calculateValue())) {
                                try {
                                    Thread.sleep(500);
                                } catch (Exception e) {
                                }
                                System.out.println("You Win");
                                player.betWin(player.getBet());
                                push = false;
                            } else if ((21 - theHouse.calculateHouseValue()) == (21 - myHand.calculateValue())) {
                                try {
                                    Thread.sleep(500);
                                } catch (Exception e) {
                                }
                                System.out.println("\nPush");
                                try {
                                    Thread.sleep(1000);
                                } catch (Exception e) {
                                }
                            }
                        } else if (newHand.getSize() > 1) {
                            player.createBet2();
                            theHouse.displayHidden();
                            myHand.getUserChoice(decks);
                            try {
                                Thread.sleep(500);
                            } catch (Exception e) {
                            }
                            theHouse.displayHidden();
                            newHand.displayHand();
                            newHand.getUserChoice(decks);
                            try {
                                Thread.sleep(500);
                            } catch (Exception e) {
                            }
                            theHouse.houseTurn(newHand, decks);
                            try {
                                Thread.sleep(500);
                            } catch (Exception e) {
                            }
                            theHouse.displayHouseHand();
                            if (newHand.calculateValue() > 21) {
                                System.out.println("Your Second Hand Busted: House Wins");
                                player.betLose(player.createBet2());
                                push = false;
                            } else if (theHouse.calculateHouseValue() > 21) {
                                try {
                                    Thread.sleep(500);
                                } catch (Exception e) {
                                }
                                System.out.println("House Busted: Your Second Hand Wins");
                                player.betWin(player.createBet2());
                                push = false;
                            } else if ((21 - theHouse.calculateHouseValue()) < (21 - newHand.calculateValue())) {
                                try {
                                    Thread.sleep(500);
                                } catch (Exception e) {
                                }
                                System.out.println("House Wins over your second hand");
                                player.betLose(player.createBet2());
                                push = false;
                            } else if ((21 - theHouse.calculateHouseValue()) > (21 - newHand.calculateValue())) {
                                try {
                                    Thread.sleep(500);
                                } catch (Exception e) {
                                }
                                System.out.println("Your Second Hand Wins over the House");
                                player.betWin(player.createBet2());
                                push = false;
                            } else if ((21 - theHouse.calculateHouseValue()) == (21 - newHand.calculateValue())) {
                                try {
                                    Thread.sleep(500);
                                } catch (Exception e) {
                                }
                                System.out.println("\nPush");
                                try {
                                    Thread.sleep(1000);
                                } catch (Exception e) {
                                }
                            }
                            if (myHand.calculateValue() > 21) {
                                System.out.println("Your first Hand Busted: House Wins");
                                player.betLose(player.getBet());
                                push = false;
                            } else if (theHouse.calculateHouseValue() > 21) {
                                try {
                                    Thread.sleep(500);
                                } catch (Exception e) {
                                }
                                System.out.println("House Busted: Your First Hand Win");
                                player.betWin(player.getBet());
                                push = false;
                            } else if ((21 - theHouse.calculateHouseValue()) < (21 - myHand.calculateValue())) {
                                try {
                                    Thread.sleep(500);
                                } catch (Exception e) {
                                }
                                System.out.println("House Wins over your First Hand");
                                player.betLose(player.getBet());
                                push = false;
                            } else if ((21 - theHouse.calculateHouseValue()) > (21 - myHand.calculateValue())) {
                                try {
                                    Thread.sleep(500);
                                } catch (Exception e) {
                                }
                                System.out.println("Your first Hand Wins over the House");
                                player.betWin(player.getBet());
                                push = false;
                            } else if ((21 - theHouse.calculateHouseValue()) == (21 - myHand.calculateValue())) {
                                try {
                                    Thread.sleep(500);
                                } catch (Exception e) {
                                }
                                System.out.println("\nPush");
                                try {
                                    Thread.sleep(1000);
                                } catch (Exception e) {
                                }
                            }

                        }
                    }
                }
            }
            //now if there was no insurance applicable or the player chose to not play insurance this game scenario runs
            else if (theHouse.calculateHouseValue() == 21 &&insurance.getInsurance()==0) {
                try { Thread.sleep(500); } catch (Exception e) {}
                theHouse.displayHouseHand();
                System.out.println("House got BlackJack: House wins");
                player.betLose(player.getBet());
                push = false;
            }
            else {
                if (Game.doubleDown(myHand, decks, player)) {
                    myHand.displayHand();
                    try {
                        Thread.sleep(500);
                    } catch (Exception e) {
                    }
                    theHouse.houseTurn(myHand, decks);
                    try {
                        Thread.sleep(500);
                    } catch (Exception e) {
                    }
                    theHouse.displayHouseHand();
                    if (myHand.calculateValue() > 21) {
                        try {
                            Thread.sleep(500);
                        } catch (Exception e) {
                        }
                        System.out.println("You Busted: House Wins");
                        player.betLose(player.getBet()*2);
                        push = false;
                    } else if (theHouse.calculateHouseValue() > 21) {
                        try {
                            Thread.sleep(500);
                        } catch (Exception e) {
                        }
                        System.out.println("House Busted: You Win");
                        player.betWin(player.getBet()*2);
                        push = false;
                    } else if ((21 - theHouse.calculateHouseValue()) < (21 - myHand.calculateValue())) {
                        try {
                            Thread.sleep(500);
                        } catch (Exception e) {
                        }
                        System.out.println("House Wins");
                        player.betLose(player.getBet()*2);
                        push = false;
                    } else if ((21 - theHouse.calculateHouseValue()) > (21 - myHand.calculateValue())) {
                        try {
                            Thread.sleep(500);
                        } catch (Exception e) {
                        }
                        System.out.println("You Win");
                        player.betWin(player.getBet()*2);
                        push = false;
                    } else if ((21 - theHouse.calculateHouseValue()) == (21 - myHand.calculateValue())) {
                        try {
                            Thread.sleep(500);
                        } catch (Exception e) {
                        }
                        System.out.println("\nPush");
                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {
                        }
                    }
                } else {
                    Hand newHand = Game.Split(myHand, decks, player);
                    if (newHand.getSize() <= 1) {
                        myHand.getUserChoice(decks);
                        try {
                            Thread.sleep(500);
                        } catch (Exception e) {
                        }
                        theHouse.houseTurn(myHand, decks);
                        try {
                            Thread.sleep(500);
                        } catch (Exception e) {
                        }
                        theHouse.displayHouseHand();
                        if (myHand.calculateValue() > 21) {
                            try {
                                Thread.sleep(500);
                            } catch (Exception e) {
                            }
                            System.out.println("You Busted: House Wins");
                            player.betLose(player.getBet());
                            push = false;
                        } else if (theHouse.calculateHouseValue() > 21) {
                            try {
                                Thread.sleep(500);
                            } catch (Exception e) {
                            }
                            System.out.println("House Busted: You Win");
                            player.betWin(player.getBet());
                            push = false;
                        } else if ((21 - theHouse.calculateHouseValue()) < (21 - myHand.calculateValue())) {
                            try {
                                Thread.sleep(500);
                            } catch (Exception e) {
                            }
                            System.out.println("House Wins");
                            player.betLose(player.getBet());
                            push = false;
                        } else if ((21 - theHouse.calculateHouseValue()) > (21 - myHand.calculateValue())) {
                            try {
                                Thread.sleep(500);
                            } catch (Exception e) {
                            }
                            System.out.println("You Win");
                            player.betWin(player.getBet());
                            push = false;
                        } else if ((21 - theHouse.calculateHouseValue()) == (21 - myHand.calculateValue())) {
                            try {
                                Thread.sleep(500);
                            } catch (Exception e) {
                            }
                            System.out.println("\nPush");
                            try {
                                Thread.sleep(1000);
                            } catch (Exception e) {
                            }
                        }
                    } else if (newHand.getSize() > 1) {
                        player.createBet2();
                        theHouse.displayHidden();
                        myHand.getUserChoice(decks);
                        try {
                            Thread.sleep(500);
                        } catch (Exception e) {
                        }
                        theHouse.displayHidden();
                        newHand.displayHand();
                        newHand.getUserChoice(decks);
                        try {
                            Thread.sleep(500);
                        } catch (Exception e) {
                        }
                        theHouse.houseTurn(newHand, decks);
                        try {
                            Thread.sleep(500);
                        } catch (Exception e) {
                        }
                        theHouse.displayHouseHand();
                        if (newHand.calculateValue() > 21) {
                            System.out.println("Your Second Hand Busted: House Wins");
                            player.betLose(player.createBet2());
                            push = false;
                        } else if (theHouse.calculateHouseValue() > 21) {
                            try {
                                Thread.sleep(500);
                            } catch (Exception e) {
                            }
                            System.out.println("House Busted: Your Second Hand Wins");
                            player.betWin(player.createBet2());
                            push = false;
                        } else if ((21 - theHouse.calculateHouseValue()) < (21 - newHand.calculateValue())) {
                            try {
                                Thread.sleep(500);
                            } catch (Exception e) {
                            }
                            System.out.println("House Wins over your second hand");
                            player.betLose(player.createBet2());
                            push = false;
                        } else if ((21 - theHouse.calculateHouseValue()) > (21 - newHand.calculateValue())) {
                            try {
                                Thread.sleep(500);
                            } catch (Exception e) {
                            }
                            System.out.println("Your Second Hand Wins over the House");
                            player.betWin(player.createBet2());
                            push = false;
                        } else if ((21 - theHouse.calculateHouseValue()) == (21 - newHand.calculateValue())) {
                            try {
                                Thread.sleep(500);
                            } catch (Exception e) {
                            }
                            System.out.println("\nPush");
                            try {
                                Thread.sleep(1000);
                            } catch (Exception e) {
                            }
                        }
                        if (myHand.calculateValue() > 21) {
                            System.out.println("Your first Hand Busted: House Wins");
                            player.betLose(player.getBet());
                            push = false;
                        } else if (theHouse.calculateHouseValue() > 21) {
                            try {
                                Thread.sleep(500);
                            } catch (Exception e) {
                            }
                            System.out.println("House Busted: Your First Hand Win");
                            player.betWin(player.getBet());
                            push = false;
                        } else if ((21 - theHouse.calculateHouseValue()) < (21 - myHand.calculateValue())) {
                            try {
                                Thread.sleep(500);
                            } catch (Exception e) {
                            }
                            System.out.println("House Wins over your First Hand");
                            player.betLose(player.getBet());
                            push = false;
                        } else if ((21 - theHouse.calculateHouseValue()) > (21 - myHand.calculateValue())) {
                            try {
                                Thread.sleep(500);
                            } catch (Exception e) {
                            }
                            System.out.println("Your first Hand Wins over the House");
                            player.betWin(player.getBet());
                            push = false;
                        } else if ((21 - theHouse.calculateHouseValue()) == (21 - myHand.calculateValue())) {
                            try {
                                Thread.sleep(500);
                            } catch (Exception e) {
                            }
                            System.out.println("\nPush");
                            try {
                                Thread.sleep(1000);
                            } catch (Exception e) {
                            }
                        }

                    }
                }
            }
            if(player.getCurrenthold()<=0){
                push = false;
            }
            else {
                Scanner scanner = new Scanner(System.in);
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                }
                System.out.println("\nType 1 to keep playing or Type 2 to finish");
                int value = scanner.nextInt();
                while(value!=1 && value!=2){
                    System.out.println("\nType 1 to keep playing or Type 2 to finish");
                    value = scanner.nextInt();
                }
                if (value == 1) {
                    push = true;
                } else push = false;
                winnings = player.getCurrenthold();
            }
            winnings = player.getCurrenthold();
            max = player.getMax();

        }
        System.out.println("You finished with: " + "$" + winnings);
        long profit = winnings - deposit;
        if(profit>0) {
            System.out.println("You made: " + "$" + profit);
        }

        else{
            profit = 0-profit;
            System.out.println("You lost: " + "$" + profit);
        }
        System.out.println("Your max hold was: " + "$" + max);
    }

    /**
     * displays blackjack header
     */
    public static void displayHeader(){
        System.out.println("\n$$$$$$$\\  $$\\                     $$\\          $$$$$\\                     $$\\       " +
                "\n$$  __$$\\ $$ |                    $$ |         \\__$$ |                    $$ |      "
        + "\n$$ |  $$ |$$ | $$$$$$\\   $$$$$$$\\ $$ |  $$\\       $$ | $$$$$$\\   $$$$$$$\\ $$ |  $$\\ " +
                "\n$$$$$$$\\ |$$ | \\____$$\\ $$  _____|$$ | $$  |      $$ | \\____$$\\ $$  _____|$$ | $$  |"+
                "\n$$  __$$\\ $$ | $$$$$$$ |$$ /      $$$$$$  / $$\\   $$ | $$$$$$$ |$$ /      $$$$$$  / "+
                "\n$$ |  $$ |$$ |$$  __$$ |$$ |      $$  _$$<  $$ |  $$ |$$  __$$ |$$ |      $$  _$$<  "+
                "\n $$$$$$$  |$$ |\\$$$$$$$ |\\$$$$$$$\\ $$ | \\$$\\ \\$$$$$$  |\\$$$$$$$ |\\$$$$$$$\\ $$ | \\$$\\ "
        +"\n\\_______/ \\__| \\_______| \\_______|\\__|  \\__| \\______/  \\_______| \\_______|\\__|  \\__|\n\n");
    }
}
