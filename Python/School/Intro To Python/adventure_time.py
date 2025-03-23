# Create a Choose Your Own Adventure game. For each choice, you will need to receive input from the player.
# Then, you will need to check to see if what they typed is one of your allowed choices. If so, pick what prompt to give them next until the player gets to the end.
# You will need to provide at least 12 "pages", though some of these can be endings/deaths. If you ever need to end the program
# (because the player died or entered an unknown command), use the quit() function.

# Answer prompts. Think of these like pages to jump to. Answer 3 on prompt 0 will take you to prompt 3, ect.
prompts = [
    "You wake up in your house half a block from Jerry's Bait Shop. You know the place.\nYour mother feeds you your daily allotment of sauerkraut,\nand you are now faced with the challenge of what to do with the rest of your miserable existence.\n",
    "You go out to check the mail, feeling minimal shame about your limited edition smurf slippers and rainbow leopard robe.\nInside, there was nothing but a small spider who shakes its fist at you.\nYou close the mailbox, but it fails to latch and swings open hard.",
    "You're still not sure where or when you got this thing.\nIt reminds you of that thing you read about in a certain young adult book.\nSequence of Sad Happenings, or something like that.\nAnyway, it eats the one headed frozen mouse you toss at it.\nHis name is Bobby, by the way.",
    "No bidders yet. Bummer.\nSeems like you'll have to start doing some real advertising soon.\nAt least more than the podcast advertisements you've been paying for.\nAt this rate, you'll still have to grit your teeth through christmas with this neanderthal.",
    "They flock wildly around your front door, trying to shove themselves in, waving pamphlets in your face.\nGetting rid of them is a fairly simple process.\nAll you need to do is pretend to call their mother and tell them what they're up to..\nThey run off, and flock around an old bagel.",
    "You check again for mail, for some reason.\nAgainst all laws of time and physics, there's a piece of mail.\nReading it, you discover an advertisement for downloading more RAM directly into your brain.\nAll you have to do is give them your credit card and SSN.",
    "You find a dried out cricket on the sidewalk beside the mailbox and place it inside.\nThe spider looks carefully at it, examining it.\nBefore quickly and elegantly dragging it into the endless depths of your mailbox.\nYou wake up the next morning to find a small silk sweater on your table.",
    "You carefully reach your hand into Bobby's enclosure.\nOne hand shoots up to your hand, causing you to flinch.\nInstead of biting you, it rubs against you like a cat.\nThe other hand does bite you though.\nYou spend the rest of the week in the ICU.",
    "You carefully reach your hand into the freezer, and twords the bag of mice therein.\nSurprisingly, one of the mice spring to live and begins gnawing your hand.\nSuddenly, you hear a tiny 'VIVA LA REVOLUTION'\nas three dozen mice begen gnawing at, frankly, everywhere.",
    "You google frantically, trying to find any way to increase exposure.\nYou're willing to do ANYTHING to get rid of this small boulder, roughly the size of a medium boulder of a brother.\nSadly, all available options are out of budget, since your mom took away your allowance.\nBummer.",
    "You, metaphorically, dust off his phone number.\nYou then, figuratively, beg him to just stay the hell away.\nHe then, literally, calls your mother.\nYou are now, unequivocally, screwed.\nHe is, immeasurably, overjoyed.",
    "Despite some financial and legal troubles, you manage to get your last name changed.\nYour mother is rather mad at you for disparaging her families long legacy of names.\nYou however, do not care.\nUnfortunately, all the cool names were taken.\nEnjoy life as a new man, Pitstick.",
    "You leave them to their gnawing over by Jameson.\nInside, you beg that they mess up his car next.\nGoing back inside, you switch on the tv.\nMom refuses to pay for netflix, so you have to surf cable for it.\nAnd then you find it, literary genius.\nAbsolute Cinema.\nAnd then you promptly fall asleep."
    "Feeling bad for their slim pickings,\nYou leave them a week old bag of Wonder Bread you found under the sink.\nThey quickly turn their heads, and linebacker tackle the poor loaf.\nSlamming the door, you hear noises you never wanted too.\nThen, from under the door, slides in an ad for a Suck Master 9000."
]

# Responses mapped to their respective prompt.
responses = [
    ["1. Check the mail.", "2. Feed your pet 2 headed snake.", "3. Check your brother's Craigslist listing.", "4. Scare off this month's murder of vacuum salesmen."],
    ["1. Check it again.", "2. Leave a small bug for the spider."],
    ["1. Pet Bobby.", "2. Pet the mice."],
    ["1. Purchase more advertising.", "2. Call him, asking him not come down for christmas.", "3. Change your last name."],
    ["1. Go back inside and watch Desperate Housewives.", "2. Leave out a bag of stale bread."]
]
promptIndex = 0
steps = 0

# Kind of a stupid way to do this, I'm just checking how many times we've done this.
while steps < 3:
    print(prompts[promptIndex])

    # If we're at the last step, we don't need an answer.
    if not steps == 2:
        for response in responses[promptIndex]:
            print(response)

    # Again, if we're at the last step, we don't need an answer.
    if steps == 2:
        print("THE END.")
        quit()
    else:
        # With the way this is set up, all we need is the answer number to increment the prompt "pointer"
        answer = int(input("\nWhich number do you do?\n"))

        if answer <= len(responses[promptIndex]):
            promptIndex = promptIndex + answer
            steps = steps + 1
        else:
            quit("That's not a valid answer. Answer with a number (0 - 4)")


