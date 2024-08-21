import basic_prime
import fancy_prime
print("Available Programs:\n1. Basic Primality Test\n2. Miller Rabin Primality Test")

test = input("What # do you want to run? ")
# possible_prime = 82395472347985243759328560901504810957840437854561757816804350345786130958746019347685104376517435895601085436758674032116327510634509134650813475601345961734507916345701924365018732451703486523
if "1" in test:
    print(basic_prime.run())
elif "2" in test:
    print(fancy_prime.run()) # I think you missunderstnad. Test is which file we're running.





# I changed the run() function to need an argume
# No you didn't, at least not on my end
# And why?      So you wouldn't have to write repetative code
# You always need to specify what number you're testing             but why?
# Becuase it makes more sense. Just look
# We're manually setting it where the user would be setting it. In the exact same way, minus some typecasting.
# I'm just wondering why you want to write the same code for every run()
# Because normally I don't have an "is_prime". That functionallity is handled by the run(). Because it runs the program that's intended. the other functions are to supplement run().
# I still don't get your reasoning
# Everything that is in your "is_prime" function would be in my run() function, along with the input from the user. Run() shouldn't need paramaters, because the only thing it needs is user input.
# Okay  
# I won't stop you from structuring how you see it in your creative vision

# I fixed it! (probably) *sad horn noises*

# Sorry about the run thing. I got far too hard-headed.
# No no it's fine. I think we both realized around the same time that it was getting more heated than it was worth
# I forget that there are other ways to organize your code than with the flow of data
# IKR? My code is organized in order of thought process lol. Which means when I get disrailed... things get messy.

# Y'know I kinda want to redo my science fair project but in rust
# I beleive in you. You can do it. Eventually.
# I just installed the rust analyzer today, so no time like the present
# I can already tell you that it will be so much better organized
# Have I shown you my original project?
# The one that takes 5 python files to run?
# I don't think you have

# Well during Christmas break, I had had enough with my eyes bleeding, so I rewrote it much more concisely

# That's good. Normally my projects get completly remade too.
# Well I couldn't very well let Tim from 2 years ago write my science fair project
# That guy is an idiot
# Doesn't even know how to make classes in python
# What a loser, I bet he even has a life!
# ...well... 
# Oh so that's just a constant.

# I would go as far calling it CONSTANT
# I'll just say that its derivative is 0

# I can't tell if that made me laugh because it was actually funny or out of sympathy-

# I hope its the former, but I'm glad you laughed either way

# Did you wanna host on VSC for your science fair project?
# Well we still haven't gotten miller_rabin up and running
# Even tho it should and I don't know what its talking about because it added the missing argument
# I think it's doing the thing again where it's editing it on your side but not mine.

# Look, I'm sorry if I'm getting snippy, this is just the way I've been doing it for years now. I normally use it when putting together console microgames, like RPS.
# And I've put together zero microgames (unless you count card games) so I suppose I should leave it to the professional amateur
# Lol, I'm just used to stitching together small useless projects into one collective "hub". I can't make big projects because I psyc myself out too fast.
# I can definatly see why you do it the way you do. Something called "is_prime" where you pass in the number to check makes a lot of sense as a peice of a larger whole.
# But normally, I just stop at the is_prime and don't use it in anything bigger. Unless I need to rip the code.
# "mr_pass() missing 1 required positional argument: 'a'"  Check line 72 in miller_rabin.py
# so do you choose the number to test in the file or in main?
# It's manually set right now, so we can get to Miller faster.
# Normally, it's an input()


# Do you just get thrown around when I change files?

# Yes I do

# Man who thought that that was a good idea

# Project Manager

# Wait how are we gonna use the white board?
# Apparently 46548694843243 is not prime            # Wolfram agrees

# I just checked and apparently the whiteboard is disabled because I'm in an 'unsafe environment'

# Well, get in a safer enviroment.

# Hold on, I got an idea for a change to the prime thing, one sec.
# Stupid Python, just assume I want that int cast to a string
# feck
# Lol it's divisable by 4
# Alright, I have an inkling that your number is a *little* too long, or big, as it were. One too many bits.

# Well ya for your puny baby primality algorithm
# That's why I suggested Miller-Rabin

# And how is that gonna help with the fact our number is MASSIVE

# Because it doesn't check factors at all, so the size of the number actually has little to do with how long it takes to run
# We have to choose a number to use the test with, called a Miller-Rabin witness, and it gives us confidence that a number is prime
# If we only find one miller-rabin witness, then we only have like a 75% certainty that the number is prime, but we can do it again and again with different witnesses to be virtually positive that our number is prime

# Alright, let's do this thing
# There's something oddly fitting about us communicating through comments in an IDE <3

# We're not like OTHER couples  

# We're not mentally sound

# <3

# If you haven't noticed by now, it's in miller_rabin.py


# from miller_rabin import *
# I'm confused
# whoopsie, there's an ussue with Mr. Pass
