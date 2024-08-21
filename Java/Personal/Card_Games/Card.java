public class Card {
    private final int value;
    private String suit;
    private String displayValue;

    /**
     * Fully Custom Card.
     *
     * @param value The card's internal value.
     * @param suit The display suit of the card.
     */
    public Card(int value, String suit) {
        this.value = value;
        this.suit = suit;

        setDisplayValue();
    }
    /**
     * Fully Custom Card.
     *
     * @param value The card's internal value.
     * @param suit The display suit of the card. 0: Diamonds, 1: Hearts, 2: Clubs, 3: Spades
     *
     */
    public Card(int value, int suit) {
        this.value = value;

        setDisplayValue();
        setSuit(suit);
    }

    /**
     * A mostly blank card.
     * Use auto_set() to randomly select a suit, and to set the display value.
     *
     * @param value The card's internal value.
     */
    public Card(int value) {
        this.value = value;

        auto_set();
    }

    /**
     * Set's a card display value based on its internal value.
     * Display value is decided based on value. For values between 2 and 10, it's the value. For 1 and 11 through 13, it's Ace, Jack, Queen, King, and Ace.
     */
    private void setDisplayValue() {

        if(this.value <= 10 && this.value > 1) {
            this.displayValue = String.valueOf(this.value);
        } else {
            switch (this.value) {
                case 11: {
                    this.displayValue = "Jack";
                    break;
                }
                case 12: {
                    this.displayValue = "Queen";
                    break;
                }
                case 13: {
                    this.displayValue = "King";
                    break;
                }
                case 14, 1: {
                    this.displayValue = "Ace";
                    break;
                }
                default: {
                    this.displayValue = "Blank";
                }
            }
        }
    }

    private void setSuit(int suitID) {
        switch (suitID) {
            case 0: {
                this.suit = "Diamonds";
                break;
            }
            case 1: {
                this.suit = "Hearts";
                break;
            }
            case 2: {
                this.suit = "Clubs";
                break;
            }
            case 3: {
                this.suit = "Spades";
                break;
            }
            default: {
                this.suit = "Blank";
            }
        }
    }

    public String toString() {
        return String.format("Card (Value: %d, Suit: %s, Display Value: %s)", value, suit, displayValue);
    }

    /**
     * Print out the card in a standard format (ex. Two of Spades).
     * Uses the card's Display Value and Suit.
     */
    public void show() {
        System.out.printf("%s of %s\n", displayValue, suit);
    }

    /**
     * Print out the card in a standard format (ex. Two of Spades).
     * Uses the card's Display Value and Suit.
     */
    public String showString() {
        return String.format("%s of %s", displayValue, suit);
    }

    /**
     * Automatically set's the card's suit and display value.
     * Suit is decided randomly.
     * Display value is decided based on value. For values between 2 and 10, it's the value. For 1 and 11 through 13, it's Ace, Jack, Queen, King, and Ace.
     */
    public void auto_set() {
        int suitID = (int) Math.round(Math.random() * 3);

        setDisplayValue();
        setSuit(suitID);
    }

    /**
     * @return The card's internal value.
     */
    public int getValue() {
        return value;
    }

    /**
     * Compares two card's values.
     * @param other The card to compare against.
     * @return 1 if this is greater, 0 if other is greater, and 2 if they are equal. If 3 is returned, there was a solar flare.
     */
    public int compare(Card other) {
        if (this.value > other.getValue()) {
            return 1;
        } else if (this.value < other.getValue()) {
            return 0;
        } else if (this.value == other.getValue()) {
            return 2;
        }

        System.out.println("Error Comparing Cards");
        return -1;
    }
}
