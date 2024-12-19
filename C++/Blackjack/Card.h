//
// Created by rydia on 10/5/2024.
//

#ifndef CARD_H
#define CARD_H
#include <string>


class Card {
private:
    int value;
    int suit;
    std::string dispSuit;
    void setDispSuit();


    public:
    Card(int value, int suit);
    std::string toString();
};



#endif //CARD_H
