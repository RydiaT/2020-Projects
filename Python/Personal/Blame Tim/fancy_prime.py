def run():
    num = int(input("Test Subject: "))
    
    if num % 2 == 0:
        print("That's an even number, dumbass.")
        return False

    print("Test does not allow for faliure points.")
    
    # for i in range(1, 100, 2):
    #     if is_prime(i):
    #         print(i)
    #         print("---------------------")

    print(num)
    return is_prime(num)

# It's printing the X values and THEN the number it's checking.
# Also IMEDIATLY afer I put away my plate from dinner, I got handed the phone to place a McDonalds order. I just got a QP and a coffee
def mr_pass(n, a): # Hehe, what a fancy name
    d = n - 1
    e = 0  
    while d % 2 == 0:
        d //= 2
        e += 1

    x = pow(a,d,n) # < This X

    for i in range(e):
        y = (x*x) % n
        if y == 1 and x != 1 and x != n-1: return False
        x = y

    if y != 1: return False
    return True
    # print(x) # Man I'm teriffied of drinking my coffee. Scared it'll burn me lol.
    # Well I'm scared that this won't work
    # Let's put both of our fears to rest   (drumroll)
    # o7
    # Well, 5 is still False. So is 17 and 19.
    # But I think that there aren't any more composites that are flaged as prime
    # Since the numbers are so low, we can double check with our OTHER test. Or reveiw them manually. Either or.
import random

def is_prime(n):
    if n == 1: return False
    if n == 3: return True
    # hold on lemmie check something rq
    # It's saying 99 is prime. And 69. It's getting some primes right, but its getting all of the composites right I think
    for i in range(30):
        a = random.randint(2,n-2)
        if not mr_pass(n, a): return False

        # The console should be everything it thinks is prime
        # 45 is definitely not prime
        # Wait is it just printing out every odd nubmer?
        # Shouldn't be... There we go. Just a little formatting issue.
        # E voiala          By george I think we got IT!!!!!
        # Miller-Rabin test, done and done, ready for all of your prime checking needs 
        # Well, we still got one final question to anwer.
        # is 82395472347985243759328560901504810957840437854561757816804350345786130958746019347685104376517435895601085436758674032116327510634509134650813475601345961734507916345701924365018732451703486523 prime?
        # That's a superb question
        # I'm anticipating the answer

    return True

# Figures
# to be honest that was all keyboard smash
# Hold on, let me get you another one

# And then we could do advent or rust. Whatever wall you wanna bang your head against.
# 2^19 - 1. I want to know if its prime
# Cool
# Now do 2^107 - 1          I wanna see how big of a number it can handle
# I need it in int form lol
# ** User <<<< Average pow() user
# pow uses an optimized exponention algorithm that can handle modular powers and even take modular inverses
# There's no shot you can convince me that ** is better than pow()

# Cool. I just want one more
# Took it a second but it chugged through it.
# DAMNMNN that was fast
# You have to consider that this is 2^4423-1
# And we were able to check that it's prime in like 2 seconds
# My other test fails because sqrt can't handle a number that big.
# Sad.  I guess you'll just have to check every number up to half of it
# That's only 2^4422 things to check
# Nope, simple division cracks too.

# Isn't Miller-Rabin amazing?
# Ok fine, you've convinced me that it has applications outside of rapid onset insanity 🤓

# Awesome, now we can do a Diffie-Hellman key exchange

# I thought we were doing Advent or Rust?
# Why not advent in Rust?
# Because I don't hate myself THAT much. But I could be persuaded. :p

# Just think about how much better Rust developers we would be if we solved Advent of Code with it
# We'll be forced to learn ALL the niche concepts with obscure use cases
# ...alright but i REFUSE to use VSC for Rust. Out of princable.

# Okay let's see how well this cross-IDE stuff handles

# where the hell is my thumbdrive with all my projects
# my pc won't show it anymore

# Remove it and plug it back in
# Ayyy. Stupid cheap peice of crap. That acronym is rough to use lol.
# It could have been construed in the not nice way

# What year do you wanna do?
# Lets roll a number between the start and the end of AoC
# I think the first year was 2015

print(random.randint(15, 23))