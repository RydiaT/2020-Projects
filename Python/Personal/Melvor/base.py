from random import randint


def get_data(runs, base, recycle, double):
    totals = []

    for i in range(runs):
        item = 0
        made = 0

        while item < base:
            chance = randint(0, 101)

            if chance <= recycle:
                item -= 1

            if chance <= double:
                made += 1

            item += 1
            made += 1

        totals.append(made)

    return [(sum(totals) / len(totals)), max(totals), min(totals)]