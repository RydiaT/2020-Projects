# from pi_calculator import calculate_pi

pi_file = "PI_Milllion_Digits.txt"

pi_digits = []

with open(pi_file, "r") as unclean_pi_digits:
    pi_lines = unclean_pi_digits.readlines()

    num_lines = len(pi_lines)

    current_line = 0

    # Temp code because current file has a header
    num_lines -= 5

    pi_start = False

    for line in pi_lines:
        if "141592" in line:
            pi_start = True

        if pi_start:
            current_line += 1

            if current_line != num_lines:
                line = line[:-1]

            pi_digits.append(line)

    unclean_pi_digits.close()

# Look through each line for a funny number.
funny_numbers = ["69", "420", "42", "21", "314", "37", "95", "8008", "8008135"]
funny_counts = []

for number in funny_numbers:
    funny_counts.append(0)


for test_num in range(len(funny_numbers)):
    test = funny_numbers[test_num]

    for line in pi_digits:
        if test in line:
            funny_counts[test_num] += line.count(test)

for test_num in range(len(funny_numbers)):
    print(f"%s: %s\n" % (funny_numbers[test_num], funny_counts[test_num]))






