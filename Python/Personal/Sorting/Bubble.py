from Helpers import get_list, is_sorted


things = get_list("Numbers500.txt")


print(things)


while not is_sorted(things):

    for i in range(len(things) - 1):
        if things[i] > things[i + 1]:
            temp = things[i]
            things[i] = things[i + 1]
            things[i + 1] = temp


print(things)
