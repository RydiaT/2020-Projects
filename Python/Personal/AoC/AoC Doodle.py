with open("calories.txt") as calorieRaw:
    calorieList = calorieRaw.readlines()
    temp, maximum, current_cals = 0, 0, 0

    for cal in calorieList:
        if cal == "\n":
            if current_cals > maximum:
                maximum = current_cals
            current_cals = 0
        else:
            temp = cal
            temp = int(temp)
            current_cals = current_cals + temp

    print(maximum)