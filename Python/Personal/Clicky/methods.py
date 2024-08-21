from pynput.mouse import Button, Controller
from time import sleep

resolution = (1920, 1080)

clicky = Controller()
delay = 1


def run(inputs):
    for thing in inputs:
        clicky.position = thing

        clicky.press(Button.left)
        clicky.release(Button.left)

        sleep(delay)


def load(file, mode=0):
    if mode == 0:
        raw = open(file + ".txt").read()
    else:
        raw = file

    instructions = raw.split()
    coords = []

    for instruction in instructions:
        pair = instruction.split(",")
        coords.append((int(pair[0]), int(pair[1])))

    return coords


def save():

    print("Coordinates are x,y")
    name = input("Macro Name: ")

    total = ""

    while True:
        coord = input("Enter coordinate: ")

        if coord.__contains__("end"):
            break

        total += coord + "\n"

    try:
        load(total, 1)
    except ValueError or IndexError:
        print("Failed to save.")
    else:
        file = open(name + ".txt", "w")
        file.write(total)
        file.close()
