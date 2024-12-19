#include <iostream>

#include "Card.h"

int main()
{
    std::cout << "Hello, World!" << std::endl;
    auto card = Card(3, 1);
    std::cout << card.toString();
    return 0;
}
