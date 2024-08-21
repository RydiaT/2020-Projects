from random import randint, choice

a = int(input("First Number: "))
b = input("Operation (EX. +, *): ")
c = int(input("Second Number: "))
d = randint(1, 10)

suffixes = [", trust me bro.", ". Don't look that up.", ", you're just wrong.", ". I would know.", ""]
suffix = "."
prefixes = ["Um, actually, ", "As a matter of fact, ", "Fun fact: ", "The answer is: ", "Survey Says: ", ""]
prefix = ""
equal_list = [" is TOOOOOOTALLY ", " = ", " is definitely ", " is roughly ", " may or may not be ", " is approximately "]
equals = " = "

rolls = []

for i in range(3):
    rolls.append(randint(1, 100))

if rolls[0] >= 50:
    suffix = choice(suffixes)

if rolls[1] >= 50:
    prefix = choice(prefixes)

if rolls[2] >= 75:
    equals = choice(equal_list)

if b == "+":
    print(f"{prefix}{a} + {c}{equals}{a+c+d}{suffix}")
elif b == "-":
    print(f"{prefix}{a} - {c}{equals}{a-c+d}{suffix}")
elif b == "*":
    print(f"{prefix}{a} * {c}{equals}{a*c+d}{suffix}")
elif b == "/":
    print(f"{prefix}{a} / {c}{equals}{a/c+d}{suffix}")
