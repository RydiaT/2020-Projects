import mpmath


def calculate_pi():
    desired_digits = 100000000

    output_file = "PI_HundoMill_Digits.txt"

    mpmath.mp.dps = desired_digits

    pi_digits = mpmath.pi

    pi_str = str(pi_digits)

    with open(output_file, "w") as pi_file:
        pi_file.write(pi_str)

    return output_file
