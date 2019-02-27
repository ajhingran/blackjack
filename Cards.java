/**
 * creates the card object and stores, rank, suit, and blackjack value
 */
class Cards{
    //instance variables
    private int rank;
    private String suit;
    private String rankasString;
    private int blackjackValue;

    /**
     * constructor for the cards
     * @param rank
     * @param suit
     */
    public Cards(int rank, String suit){
        this.rank = rank;
        this.suit = suit;
        if(rank>1 && rank<11){
            blackjackValue = rank;
        }
        else if(rank>=11){
            blackjackValue=10;
        }
        else if(rank ==1){
            blackjackValue=11;
        }
    }

    /**
     * returns value of card
     * @return returns card object as string with appropriate rank
     */
    public String toString(){
        if (rank == 11){
            rankasString = "Jack";
        }
        else if (rank == 12){
            rankasString = "Queen";
        }
        else if (rank == 13){
            rankasString = "King";
        }
        else if (rank == 1){
            rankasString = "Ace";
        }
        else rankasString = "" + rank;
        String card = rankasString + " of "+ suit;
        return card;
    }

    /**
     * @return blackjackValue
     */
    public int getBlackjackValue(){
        return blackjackValue;
    }

    /**
     * @return rank
     */
    public int getRank() {
        return rank;
    }
}
