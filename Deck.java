import java.util.ArrayList;

class Deck{
    //instance array
    private ArrayList<Cards> deck;

    /**
     * constuctor, creats deck of type object Card
     */
    public Deck() {
        String suits = "";
        String ranks = "";
        deck = new ArrayList<Cards>();
        for (int rank = 1; rank <= 13; rank++) {
            for (int suit = 1; suit <= 4; suit++) {
                if (suit == 1){
                    suits = "Hearts";
                }
                else if (suit == 2){
                    suits = "Spades";
                }
                else if (suit == 3){
                    suits = "Clubs";
                }
                else if (suit == 4){
                    suits = "Diamonds";
                }
                Cards card = new Cards(rank, suits);
                deck.add(card);
            }
        }
    }

    /**
     * Shuffles all 52 cards with a random card in the deck
     */
    public void shuffle(){
        for(int i = 0; i<52; i++) {
            int rand = (int) (Math.random() * 52);
            Cards temp = deck.get(rand);
            deck.set(rand, deck.get(i));
            deck.set(i,temp);
        }
    }

    /**
     * removes the first card of the deck
     * @return Card at index 0 of deck
     */
    public Cards deal(){
        Cards dealtcard = deck.remove(0);
        return dealtcard;
    }
    public Cards dealSpecificCard(int x){
        return deck.remove(x);
    }
    public int deckSize(){
        return deck.size();
    }
    public Cards getCardFromDeck(int x){
        return deck.get(x);
    }

    /**
     * @return all cards in deck as Strings
     */
    public String toString() {
        String s = "";
        for (int i = 0; i < deck.size(); i++) {
            String card = deck.get(i).toString();
            s += card + "\n";
        }
        return s;
    }

}
