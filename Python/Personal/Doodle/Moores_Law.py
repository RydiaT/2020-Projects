currentCapacity = 1
year = 0

while currentCapacity < 1000:
    year += 0.5

    if year % 1.5 == 0:
        currentCapacity *= 2


print(f"In {year} years, we will have {currentCapacity} Terabytes, or {currentCapacity / 1000} Petabytes.")



