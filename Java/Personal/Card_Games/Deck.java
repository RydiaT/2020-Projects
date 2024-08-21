import java.lang.reflect.Array;
import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> deck;
    private ArrayList<Card> live_cards;
    private ArrayList<Card> dead_cards; // Or discards, if you will.

    /**
     * Standard 52 card deck.
     */
    public Deck() {
        deck = new ArrayList<>();
        live_cards = new ArrayList<>();
        dead_cards = new ArrayList<>();

        for(int i = 0; i < 4; i++) {
            for (int j = 2; j < 15; j++) {
                Card card = new Card(j, i);

                deck.add(card);
                live_cards.add(card);
            }
        }
    }

    /**
     * Custom Deck
     * @param deck ArrayList of cards to be used
     */
    public Deck (ArrayList<Card> deck) {
        this.deck = deck;
    }

    public void shuffle() {
        for(int i = 0; i < live_cards.size(); i++) {
            int targetIndex = (int)(Math.random()*live_cards.size());

            Card temp = live_cards.get(i);
            live_cards.set(i, live_cards.get(targetIndex));
            live_cards.set(targetIndex, temp);
        }
    }

    private void discard(Card card) {
        live_cards.remove(card);
        dead_cards.add(card);
    }

    private void revive(Card card) {
        dead_cards.remove(card);
        live_cards.add(card);
    }

    public ArrayList<Card> dealCards(int n) {
        ArrayList<Card> out = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            Card targetCard = live_cards.get(i);
            out.add(targetCard);
            discard(targetCard);
        }

        return out;
    }

    public boolean sanityCheck() {
        int check = live_cards.size() + dead_cards.size();

        return check == deck.size();
    }

    public String toString() {
        String out = "Deck (";

        for (Card liveCard : live_cards) {
            out += liveCard.toString() + ", ";
        }

        return out.substring(0, out.length() - 2) + ")";
    }

    public void show() {
        String out = "Current Cards:\n";

        for (Card liveCard : live_cards) {
            out += liveCard.showString() + "\n";
        }

        System.out.println(out);
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }
}
