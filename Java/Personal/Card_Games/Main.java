import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
//        Card[] cards = new Card[14];
//
//        for (int i = 0; i < 14; i++) {
//            cards[i] = new Card(i + 1);
//            cards[i].auto_set();
//            cards[i].show();
//        }
//
//        System.out.println(cards.length);
//        System.out.println(Arrays.toString(cards));
//        System.out.println(cards[2].compare(cards[0]));
//        System.out.println(cards[2].compare(cards[3]));
//        System.out.println(cards[2].compare(cards[2]));
//        System.out.println(cards[0].compare(cards[13]));

        Deck deck = new Deck();
        deck.show();
        deck.shuffle();
        System.out.println("---------------------");
        deck.show();

        Hand playerHand = new Hand("Player", deck);
        System.out.println("---------------------");
        playerHand.show();
        playerHand.generateHand();
        System.out.println("---------------------");
        playerHand.show();
        System.out.println("---------------------");
        deck.show();
    }
}
