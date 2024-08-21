def get_list(filename):
    file = open(filename, "r")

    raw = file.read()
    out = []

    current_number = ""

    for thing in raw:
        if thing != " ":
            current_number += thing
        else:
            out.append(int(current_number))
            current_number = ""

    file.close()
    return out


def is_sorted(nums):

    for i in range(len(nums) - 1):
        if nums[i] > nums[i + 1]:
            return False

    return True
