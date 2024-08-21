from methods import run, load, save
from os import remove, path
from time import sleep

print("Welcome to Clicky! What do you want to do today?\n1. Run a Macro\n2. Save a Macro\n3. Delete a Macro")

answer = input("Choice: ").lower()

if answer.__contains__("run") or answer.__contains__("1"):
    answer = input("Macro name: ")
    runs = int(input("Loops: "))
    delay = int(input("Loop Delay: "))

    macro = load(answer)

    for i in range(runs):
        print("Run " + str(i + 1))
        run(macro)
        sleep(delay)
elif answer.__contains__("save") or answer.__contains__("2"):
    save()
elif answer.__contains__("delete") or answer.__contains__("3"):
    answer = input("Macro name: ")

    if path.exists(answer + ".txt"):
        remove(answer + ".txt")
    else:
        print("Macro does not exist.")


