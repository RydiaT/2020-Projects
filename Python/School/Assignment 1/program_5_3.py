def is_triangle(a, b, c):
    a = int(a)
    b = int(b)
    c = int(c)

    return a + b > c and a + c > b and b + c > a


def check_triangle():
    a = input("Side 1: ")
    b = input("Side 2: ")
    c = input("Side 3: ")

    if is_triangle(a, b, c):
        print("\nThat is a triangle.")
    else:
        print("\nThat is not a triangle.")

