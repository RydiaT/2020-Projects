from base import get_data


def get_best(need, recycle, double):
    test = need
    threshold = need / 20

    i = 1

    while True:
        average, most, least = get_data(1000, test, recycle, double)

        if average > need + threshold:
            test *= 0.5
        elif average < need - threshold:
            test *= 1.5
        else:
            print("Iter " + str(i) + ": " + str(average) + ", " + str(most) + ", " + str(least))
            return round(test) + 1

        i += 1


def run():
    goal = int(input("How much do you need? "))

    chance_one = int(input("What's your recycle chance? "))
    chance_two = int(input("What's your double chance? "))

    result = get_best(goal, chance_one, chance_two)

    print("You'll need about " + str(result) + " items worth of materials.")
