from base import get_data


def get_base(r, a):
    numbers = []

    for i in range(len(r)):
        have = int(a[i])
        need = int(r[i])

        numbers.append(have / need)

    return min(numbers)


def run():
    raw_ratio = input("What's the ratio of materials? (2:1...) ")
    ratios = raw_ratio.split(":")

    raw_amounts = input("What do you have? (30:21:6...) ")
    amounts = raw_amounts.split(":")

    items = get_base(ratios, amounts)
    print("You can make up to " + str(items) + " items.")

    chance_one = int(input("What's your recycle chance? "))
    chance_two = int(input("What's your double chance? "))

    run_time = int(input("How many runs do you want to do? The more the better! "))

    output = get_data(run_time, items, chance_one, chance_two)

    print("Average: " + str(output[0]) + "\nMax: " + str(output[1]) + "\nMin: " + str(output[2]))
