from english_words import get_english_words_set
from random import choice, randint
import os.path

word_pool_raw = get_english_words_set(['web2'], lower=True)

word_pool = []
punctuation = [".", "!", "?", ".", "!", "?", ".", "!", "?", ";"]

for word in word_pool_raw:
    word_pool.append(word)

time = input("How long do you have to write? (in decimal): ")

words_per_minute = 40

total_words = round(words_per_minute * float(time))

sentences = [""]
sentence_index = 0
freewrite = "     "

linelength = 0

recent_paragraph = True
recent_punctuation = True

for i in range(total_words):
    sentences[sentence_index] += choice(word_pool)

    if linelength % 10 == 0 and linelength != 0:
        sentences[sentence_index] += "\n"
        linelength = 0
        recent_paragraph = False
    elif not recent_paragraph and randint(0, 100) >= 95:
        sentences[sentence_index] += "*"
        linelength = 0
        recent_paragraph = True
        sentence_index += 1
        sentences.append("")
    elif not recent_punctuation and randint(0, 100) >= 75:
        sentences[sentence_index] += choice(punctuation)
        linelength += 1
        recent_punctuation = True
        sentence_index += 1
        sentences.append("")
    else:
        sentences[sentence_index] += " "
        linelength += 1
        recent_punctuation = False

print(sentences)

for sentence in sentences:
    if sentence[-1] == "*":
        sentence = sentence.replace("p", choice(punctuation) + "\n    ")
    freewrite += sentence + " "

print(sentences)

file = open("Freewrite.txt", "w")
file.write(freewrite + choice(punctuation))
file.close()
