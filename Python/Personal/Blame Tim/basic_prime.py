from numpy import sqrt


def run():
    num = int(input("Test Subject: ")) # Do ya like my input methods, lol? Good little number test subjects...

    if num % 2 == 0:
        return False

    for i in range(1, num / 2):
        if num & i == 0:
            print("Failed on " + str(i))
            return False

    return True

# I live
# Nooo my walls
# Don't worry I came in through the forth one
# What are you, deadpool?
# Lamo I wish I was Ryan Renolds
# WHY DO YOU REFUSE TO BE IN MILLER

# Iwant to be there!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
#this is just progressivly getting more and more strange