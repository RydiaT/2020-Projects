def check_mirror(thing1, thing2):
    list1 = list(str(thing1).zfill(2))
    list2 = list(str(thing2).zfill(2))

    return list1[0] == list2[1] and list1[1] == list2[0]


def find_age():
    mirrors = 0  # Filled with the kid's age
    acceptable_answer = 0

    mom_age = 0
    diff = 0
    kid_age = 0

    while diff < 90:

        while mom_age < 100:
            if check_mirror(mom_age, kid_age):
                mirrors += 1

            mom_age += 1
            kid_age += 1

        if mirrors == 8:
            acceptable_answer = diff

        diff += 1
        mom_age = diff
        kid_age = 0
        mirrors = 0

    mom_age = acceptable_answer
    kid_age = 0
    matches = 0

    while mom_age < 100:

        if check_mirror(mom_age, kid_age):
            matches += 1

        if matches == 6:
            break

        kid_age += 1
        mom_age += 1

    return kid_age
