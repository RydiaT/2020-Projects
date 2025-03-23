# # Inital States
# hasRed = False;
# hasGreen = False;
# hasBlue = False;

# # Get User Input
# redAnswer = input("Does your color contain Red?")

# if redAnswer.lower() == "yes":
#     hasRed = True
    
# greenAnswer = input("Does your color contain Green?")

# if greenAnswer.lower() == "yes":
#     hasGreen = True
    
# blueAnswer = input("Does your color contain Blue?")

# if blueAnswer.lower() == "yes":
#     hasBlue = True

hasRed = input("Does your color contain Red?").lower() == "yes"
hasGreen = input("Does your color contain Green?").lower() == "yes"
hasBlue = input("Does your color contain Blue?").lower() == "yes"

# Check Color

# Check Red
if hasRed:
    if hasGreen and hasBlue:
        print("Your color is Black!")
    elif hasGreen:
        print("Your color is Yellow!")
    elif hasBlue:
        print("Your color is Purple!")
    else:
        print("Your color is Red!")
# Check Green
elif hasGreen:
    if hasBlue:
        print("Your color is Teal!")
    else:
        print("Your color is Green!")
# Check Blue  
elif hasBlue:
    print("Your color is Blue!")
else:
    print("Your color is White!")
