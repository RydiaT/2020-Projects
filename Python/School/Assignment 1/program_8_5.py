def normalize_ord(x):
    return x - ord("a")


def rotate_word(word, shift):
    letters = list(word)
    new_word = ""

    for letter in letters:
        letter_i = normalize_ord(ord(letter))
        letter_i += shift
        letter_i = letter_i % 26

        new_word += chr(letter_i + ord("a"))

    return new_word
