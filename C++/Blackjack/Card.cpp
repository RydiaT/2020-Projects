//
// Created by rydia on 10/5/2024.
//

#include "Card.h"

#include <charconv>
#include <ostream>

Card::Card(int value, int suit) {
    this->value = value;
    this->suit = suit;

    setDispSuit();
}

std::string Card::toString() {
    return std::to_string(this->value) + " of " + this->dispSuit + " (" + std::to_string(this->suit) + ")";
}

void Card::setDispSuit() {
    switch (this->suit) {
        case 0:
            this->dispSuit = "Hearts";
            break;
        case 1:
            this->dispSuit = "Diamonds";
            break;
        case 2:
            this->dispSuit = "Clubs";
            break;
        case 3:
            this->dispSuit = "Spades";
            break;
        default:
            this->dispSuit = "Nothin'";
    }
}



