from random import choice,randint

input_word = input("Give me a word. ")
frankenstein = ""

for i in range(randint(1, 10)):

    for i in range(randint(1, len(input_word))):
        chosen_one = choice(input_word)

        frankenstein += chosen_one

    frankenstein += " "


print(frankenstein)

