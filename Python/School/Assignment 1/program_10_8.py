from random import randint


def check_dupes(array, check):
    count = 0

    for item in array:
        if item == check:
            count += 1

    return count


def birthday_probability(people=23, runs=1000):
    total_dupe_runs = 0

    for i in range(0, runs):
        birthdays = []

        for x in range(0, people):
            birthdays.append(randint(1, 365))

        duped = False

        for birthday in birthdays:
            if check_dupes(birthdays, birthday) > 1:
                duped = True

        if duped:
            total_dupe_runs += 1

    return (total_dupe_runs / runs) * 100
