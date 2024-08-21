from program_5_3 import check_triangle
from program_7_2 import eval_loop
from program_8_5 import rotate_word
from program_9_9 import find_age
from program_10_8 import birthday_probability

print("Welcome. Enter either y or yes when prompted to exit a program.")

print("Starting 5.3...")
interrupt = ""
while True:
    check_triangle()
    interrupt = input("Exit?")

    if interrupt == "y" or interrupt == "yes":
        break

print("Starting 7.2...\nEnter done when prompted to exit the eval loop.")
interrupt = ""
while True:
    eval_loop()
    interrupt = input("Exit?")

    if interrupt == "y" or interrupt == "yes":
        break

print("Starting 8.5...")
interrupt = ""
while True:
    word = input("Enter a word to shift: ")
    shift = int(input("Enter the shift amount: "))

    print(f"The result of the shift is: '{rotate_word(word, shift)}'")
    interrupt = input("Exit?")

    if interrupt == "y" or interrupt == "yes":
        break

print("Starting 9.9...")
interrupt = ""
while True:
    print(f"The child's age is: {find_age()}")
    interrupt = input("Exit?")

    if interrupt == "y" or interrupt == "yes":
        break

print("Starting 10.8...")
interrupt = ""
while True:
    kids = 23

    print(f"The chance of there being a shared birthday in a set of {kids} is: {birthday_probability(runs=1000, people=kids)}%")
    interrupt = input("Exit?")

    if interrupt == "y" or interrupt == "yes":
        break
