from helpers import hits
from random import randint

total_dupe_runs = 0
people = 23
runs = 1000

for i in range(0, runs):
    birthdays = []

    for x in range(0, people):
        birthdays.append(randint(1, 365))

    dupes = 0

    for birthday in birthdays:
        if hits(birthdays, birthday) > 1:
            dupes += 1

    if dupes > 1:
        total_dupe_runs += 1

print((total_dupe_runs / runs) * 100)