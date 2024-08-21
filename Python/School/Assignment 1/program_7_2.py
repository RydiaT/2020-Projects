import math


def eval_loop():
    problem = ""
    answer = 0

    while True:
        problem = input("Enter a python compatable math problem.")

        if problem == "done":
            break

        answer = eval(problem)
        print(f"The answer is {answer}")

    print(f"The last result found is {answer}")
