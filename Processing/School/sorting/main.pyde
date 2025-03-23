def b_sort(victim):
    for i in range(len(victim)):
        for j in range(len(victim)):
            if victim[i] < victim[j]:
                
                temp = victim[i]
                victim[i] = victim[j]
                victim[j] = temp
                
        print(victim)
        
def s_sort(victim):
    for j in range(len(victim)):
        biggest = 0
        
        for i in range(len(victim) - j):
            if victim[i] > victim[biggest]:
                biggest = i
        
            temp = victim[len(victim) - (1 + j)]
            victim[len(victim) - (1 + j)] = victim[biggest]
            victim[biggest] = temp
    
        print(victim)
        
def i_sort(victim):
    for i in range(1, len(victim)):
        target = victim[i]
        
        j = i - 1
        
        while j >= 0 and victim[j] > victim[j + 1]:
            temp = victim[j + 1]
            victim[j + 1] = victim[j]
            victim[j] = temp
            
            j -= 1
            
            print(victim)
    
test = []

for i in range(30):
    test.append(int(random(0, 40)))

print(test)
print()
i_sort(test)
