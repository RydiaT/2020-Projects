public class Hand extends Deck{
    private final String name;
    private final Deck masterDeck;
    private Deck hand;

    public Hand(String name, Deck masterDeck){
        this.name = name;
        this.masterDeck = masterDeck;
    }

    public void generateHand() {
        hand = new Deck(masterDeck.dealCards(5));
    }

    public void show() {
        String out = "Current Cards:\n";

        for (Card liveCard : hand) {
            out += liveCard.showString() + "\n";
        }

        System.out.println(out);
    }
}
