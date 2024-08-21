from random import randint

def run():
    # We can manually set the number to check here
    num = int(input("Test Subject: ")) # I'm assuming this stays a string so that it won't overflow
    primmality = is_prime(num)

    print("Test does not allow for faliure points.")
    return primmality
# Hello?
def modpow(x, a, k, n):
    return pow(x, 2**a*k, n)

# n = 721 # possible prime
# e = 4; k = 45 # n-1 = 2^e * k --> 720 = 2^4 * 45

# witness = 13

# Criteria (only 1 must pass)
# x^45 = 1 or -1
# print(modpow(witness, 1, k, n)) # 442
# # x^(2*45) = -1
# print(modpow(witness, 2, k, n)) # 469
# # x^(4*45) = -1
# print(modpow(witness, 4, k, n)) # 64
# # x^(8*45) = -1
# print(modpow(witness, 8, k, n)) # 169
# If ALL fail, n is composite
# If at least one is met, test is inconclusive
# (Although if you run the trial t times, then the chance n is prime is > 1 - (1/4)^t 

# So because each condition came back false, we can be sure that 721 is not prime
# Because that makes 2 a Miller-Rabin witness
# 7 is another witness
# There may be some choice of witness where the test fails, but it doesn't matter because we just need 
# one M-R Witness
# Alright, so we know how to set up the test for the witnesses and n, but how to we get k algorithmically?

# Excellent question 
# I'd imagine by keeping a counter, checking it you can divide by 2 (if d%2==0) then dividing until you come across an odd number

def mr_pass(n, a): # Hehe, what a fancy name
    d = n - 1
    e = 0
    while d % 2 == 0:
        d //= 2
        e += 1

    # Actually, since the only real condition on a is that it has to be coprime to n,
    # We can use whatever number we want as a possible witness
    # As long as we choose one from 0 to n-1 then we'll never run into the problem of a being a multiple of n
    # So let's include it as a parameter

    x = pow(a,d,n)
    for i in range(e):
        y = (x*x)%n
        if y == 1 and x != 1 and x != n-1:
            return False
        x = y

    if y != 1: return False
    
    return True
    
print(mr_pass(721, 7))
# Use the run() function up top.
def is_prime(n):
    if n == 1: return False
    if n == 3: return True

    for i in range(1, 11):
        # you can't tell me what to do
        # Fine. Have fun. :p
        if not mr_pass(n, i): return False
    return True

# This detects
# It's kinda sad I can't see what it's divisable by, though.
        
# -----------------------------------------------------------------------------------------------------------------
# Alright, first things first, we need to get the test subject. Then we do the first check?

# We usually take prime numbers to be witnesses
# And for simplicity, let's just check if 101 is prime for testing

# Feck how do I set up scertian files to run when I hit run, not just the file i have focused

# execute()
# or maybe exec() ?         #I'm  pretty sure one of those

# No, like, when I hit the run button
# Eh, it's not too much of a bother. Just one extra click.

# What are you trying to run at the same time?

# Nothing, I'm trying to get main.py to run while I have this file focused. VSC just runs the one I have focused.
# OH I see I always thought that was weird

# Anyway, back to what we were doing, what's the first witness?
# So like are you looking to understand Miller-Rabin or just want the implementation

# Well, understanding it would be pretty cool. Whether or not that will happen without causing you substantial headaches is unclear, however.
# So I guess it's just whatever you wanna do today.

# Well you know which option I'll choose every time
# Let me just do some refreshing since I have such good notes and all

# Giving yourself headaches is just part of the programming experience <3
# Take your time, bby
# man this entire coffee does things too me. stupid caffine addiction

# I just realized Miller-Rabin goes in the opposite way that I told you
# Each pass checks for compositness

# :O How could you?

# I'm sowwy
# It might happen again

# I suppose I can forgive you. As long as we get to see some interesting number wizardry.

# Deal
# That's exactly what number theory is anyway # Wizardry?  #  Mhmm
# Okay I've read up a little and realized theat there's no way to get through this without Fermat's Little Theorem

# Well, at least it's a little theorum.

# Lol, choosing to work below the pile of comments

