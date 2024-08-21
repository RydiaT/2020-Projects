from random import choice, randint

TEST = {
    "cat": ["noun", 1],
    "apple": ["noun", 2],
    "approaching": ["noun, verb", 3]
}
PARTS = {
        "noun": ["Noun"],
        "adjective": ["Adjective"],
        "number": ["Number"],
        "pronoun": ["Pronoun"],
        "verb": ["Verb"],
        "adverb": ["Adverb"]
}
COM_WORDS = {
    "apple": ["Noun", 2],
    "banana": ["Noun", 3],
    "cat": ["Noun", 1],
    "dog": ["Noun", 1],
    "elephant": ["Noun", 3],
    "fruit": ["Noun", 1],
    "grape": ["Noun", 1],
    "happy": ["Adjective", 2],
    "igloo": ["Noun", 2],
    "jazz": ["Noun", 1],
    "kite": ["Noun", 1],
    "lemon": ["Noun", 2],
    "melon": ["Noun", 2],
    "nice": ["Adjective", 1],
    "orange": ["Noun", 2],
    "penguin": ["Noun", 2],
    "quiet": ["Adjective", 2],
    "rainbow": ["Noun", 2],
    "sunny": ["Adjective", 2],
    "tiger": ["Noun", 2],
    "umbrella": ["Noun", 3],
    "vivid": ["Adjective", 2],
    "xylophone": ["Noun", 3],
    "yellow": ["Adjective", 2],
    "zebra": ["Noun", 2],
    "the": ["Article", 1],
    "and": ["Conjunction", 1],
    "is": ["Verb", 1],
    "it": ["Pronoun", 1],
    "in": ["Preposition", 1],
    "you": ["Pronoun", 1],
    "of": ["Preposition", 1],
    "that": ["Conjunction", 1],
    "he": ["Pronoun", 1],
    "was": ["Verb", 1],
    "for": ["Preposition", 1],
    "on": ["Preposition", 1],
    "are": ["Verb", 1],
    "with": ["Preposition", 1],
    "as": ["Conjunction", 1],
    "his": ["Pronoun", 1],
    "they": ["Pronoun", 1],
    "at": ["Preposition", 1],
    "be": ["Verb", 1],
    "this": ["Pronoun", 1],
    "from": ["Preposition", 1],
    "have": ["Verb", 1],
    "or": ["Conjunction", 1],
    "one": ["Number", 1],
    "had": ["Verb", 1],
    "by": ["Preposition", 1],
    "word": ["Noun", 1],
    "but": ["Conjunction", 1],
    "not": ["Adverb", 1],
    "what": ["Pronoun", 1],
    "all": ["Determiner", 1],
    "were": ["Verb", 1],
    "we": ["Pronoun", 1],
    "when": ["Adverb", 1],
    "your": ["Determiner", 1],
    "can": ["Modal Verb", 1],
    "said": ["Verb", 1],
    "there": ["Adverb", 1],
    "use": ["Verb", 1],
    "each": ["Determiner", 1],
    "which": ["Pronoun", 1],
    "she": ["Pronoun", 1],
    "do": ["Verb", 1],
    "how": ["Adverb", 1],
    "their": ["Determiner", 1],
    "would": ["Modal Verb", 1],
    "make": ["Verb", 1],
    "like": ["Preposition", 1],
    "him": ["Pronoun", 1],
    "into": ["Preposition", 2],
    "time": ["Noun", 1],
    "has": ["Verb", 1],
    "look": ["Verb", 1],
    "two": ["Number", 1],
    "more": ["Determiner", 1],
    "write": ["Verb", 1],
    "go": ["Verb", 1],
    "see": ["Verb", 1],
    "number": ["Noun", 2],
    "way": ["Noun", 1],
    "could": ["Modal Verb", 1],
    "people": ["Noun", 2],
    "my": ["Determiner", 1],
    "than": ["Conjunction", 1],
    "first": ["Ordinal Number", 1],
    "water": ["Noun", 2],
    "been": ["Verb", 1],
    "call": ["Verb", 1],
    "who": ["Pronoun", 1],
    "oil": ["Noun", 1],
    "its": ["Determiner", 1],
    "now": ["Adverb", 1],
    "find": ["Verb", 1],
    "long": ["Adjective", 1],
    "down": ["Adverb", 1],
    "day": ["Noun", 1],
    "did": ["Verb", 1],
    "get": ["Verb", 1],
    "come": ["Verb", 1],
    "made": ["Verb", 1],
    "may": ["Modal Verb", 1],
    "a": ["Determiner", 1],
    "an": ["Determiner", 1],
    "ability": ["Noun", 4],
    "able": ["Adjective", 2],
    "about": ["Adverb, Preposition", 2],
    "above": ["Adjective, Adverb, Preposition", 2],
    "abroad": ["Adverb", 2],
    "adventure": ["Noun", 3],
    "always": ["Adverb", 2],
    "beautiful": ["Adjective", 3],
    "become": ["Verb", 2],
    "between": ["Preposition", 2],
    "blossom": ["Noun", 2],
    "brave": ["Adjective", 1],
    "breeze": ["Noun", 1],
    "cascade": ["Noun", 2],
    "celebrate": ["Verb", 3],
    "chase": ["Verb", 1],
    "create": ["Verb", 2],
    "dance": ["Verb", 1],
    "delight": ["Noun", 2],
    "dream": ["Noun", 1],
    "effort": ["Noun", 2],
    "enchanted": ["Adjective", 3],
    "explore": ["Verb", 2],
    "freedom": ["Noun", 2],
    "gentle": ["Adjective", 2],
    "gratitude": ["Noun", 3],
    "harmony": ["Noun", 3],
    "hope": ["Noun", 1],
    "illuminate": ["Verb", 4],
    "innovate": ["Verb", 3],
    "journey": ["Noun", 2],
    "joy": ["Noun", 1],
    "kindness": ["Noun", 2],
    "laughter": ["Noun", 2],
    "liberate": ["Verb", 3],
    "magic": ["Noun", 2],
    "noble": ["Adjective", 2],
    "ocean": ["Noun", 2],
    "passion": ["Noun", 2],
    "peace": ["Noun", 1],
    "quench": ["Verb", 1],
    "radiant": ["Adjective", 3],
    "serene": ["Adjective", 2],
    "tranquil": ["Adjective", 3],
    "uplift": ["Verb", 2],
    "vibrant": ["Adjective", 2],
    "wander": ["Verb", 2],
    "wisdom": ["Noun", 2],
    "zeal": ["Noun", 1],
    "adventurous": ["Adjective", 3],
    "blissful": ["Adjective", 2],
    "charming": ["Adjective", 2],
    "dazzling": ["Adjective", 3],
    "effervescent": ["Adjective", 4],
    "fearless": ["Adjective", 2],
    "gleaming": ["Adjective", 2],
    "harmonious": ["Adjective", 4],
    "innovative": ["Adjective", 4],
    "joyful": ["Adjective", 2],
    "kinetic": ["Adjective", 3],
    "luminous": ["Adjective", 3],
    "magnificent": ["Adjective", 4],
    "nurturing": ["Adjective", 3],
    "optimistic": ["Adjective", 4],
    "pensive": ["Adjective", 2],
    "quixotic": ["Adjective", 3],
    "resilient": ["Adjective", 3],
    "serendipitous": ["Adjective", 5],
    "timeless": ["Adjective", 2],
    "unwavering": ["Adjective", 4],
    "whimsical": ["Adjective", 3],
    "youthful": ["Adjective", 2],
    "zealous": ["Adjective", 2],
    "apathetic": ["Adjective", 4],
    "belligerent": ["Adjective", 4],
    "chaotic": ["Adjective", 3],
    "dismal": ["Adjective", 2],
    "erratic": ["Adjective", 3],
    "frustrating": ["Adjective", 3],
    "gloomy": ["Adjective", 2],
    "hostile": ["Adjective", 2],
    "incompetent": ["Adjective", 4],
    "jaded": ["Adjective", 2],
    "lackluster": ["Adjective", 3],
    "monotonous": ["Adjective", 4],
    "noxious": ["Adjective", 2],
    "obnoxious": ["Adjective", 3],
    "petty": ["Adjective", 2],
    "querulous": ["Adjective", 3],
    "repugnant": ["Adjective", 3],
    "shameful": ["Adjective", 2],
    "tedious": ["Adjective", 3],
    "unpleasant": ["Adjective", 3],
    "vexatious": ["Adjective", 4],
    "woeful": ["Adjective", 2],
    "xenophobic": ["Adjective", 4],
    "yielding": ["Adjective", 3],
    "oasis": ["Noun", 3],
    "peaceful": ["Adjective", 2],
    "quest": ["Noun", 1],
    "rapture": ["Noun", 2],
    "savor": ["Verb", 2],
    "serenity": ["Noun", 4],
    "thrive": ["Verb", 1],
    "utopia": ["Noun", 4],
    "vividly": ["Adverb", 3],
    "whisper": ["Verb", 2],
    "xanadu": ["Noun", 3],
    "yearning": ["Noun", 2],
    "zeppelin": ["Noun", 2],
    "aspire": ["Verb", 2],
    "benevolence": ["Noun", 4],
    "cynosure": ["Noun", 3],  # Center of attention
    "delineate": ["Verb", 4],
    "ephemeral": ["Adjective", 4],  # Short-lived
    "felicity": ["Noun", 4],  # Intense happiness
    "gossamer": ["Adjective", 3],  # Light, delicate, and translucent
    "halcyon": ["Adjective", 3],  # Calm, peaceful, tranquil
    "idyllic": ["Adjective", 3],  # Extremely happy, peaceful, picturesque
    "jubilation": ["Noun", 4],  # Joyful celebration
    "kaleidoscope": ["Noun", 4],  # Ever-changing pattern
    "labyrinthine": ["Adjective", 4],  # Complicated and confusing, like a labyrinth
    "mellifluous": ["Adjective", 4],  # Sweet-sounding
    "nebulous": ["Adjective", 3],  # Hazy, indistinct
    "obfuscate": ["Verb", 3],  # Render unclear or unintelligible
    "peregrinate": ["Verb", 4],  # Travel or wander around
    "quintessence": ["Noun", 3],  # Purest form or embodiment of something
    "resplendent": ["Adjective", 3],  # Shining brilliantly
    "seraphic": ["Adjective", 3],  # Angelic, heavenly
    "taciturn": ["Adjective", 3],  # Reserved or uncommunicative in speech
    "ubiquitous": ["Adjective", 4],  # Present everywhere
    "whimsy": ["Noun", 2],  # Playfully quaint or fanciful behavior
    "yonder": ["Adverb", 2],  # At some distance in the direction indicated
    "zenith": ["Noun", 2],  # Highest point, culmination
    "alacrity": ["Noun", 3],  # Brisk and cheerful readiness
    "beatitude": ["Noun", 4],  # Supreme blessedness or happiness
    "cacophony": ["Noun", 4],  # Harsh, discordant mixture of sounds
    "diaphanous": ["Adjective", 4],  # Light, delicate, and translucent
    "effulgent": ["Adjective", 3],  # Shining brightly
    "furtive": ["Adjective", 2],  # Attempting to avoid notice or attention
    "garrulous": ["Adjective", 3],  # Excessively talkative
    "hallowed": ["Adjective", 2],  # Regarded as holy or sacred
    "iconoclast": ["Noun", 4],  # Person who attacks or criticizes cherished beliefs or institutions
    "juxtapose": ["Verb", 3],  # Place or deal with close together for contrasting effect
    "kowtow": ["Verb", 2],  # Act in an excessively subservient manner
    "lucid": ["Adjective", 2],  # Clear, easy to understand
    "magnanimous": ["Adjective", 4],  # Generous and forgiving
    "nefarious": ["Adjective", 3],  # Wicked, villainous
    "oscillate": ["Verb", 3],  # Move or swing back and forth
    "panacea": ["Noun", 4],  # Solution or remedy for all difficulties or diseases
    "quizzical": ["Adjective", 3],  # Indicating mild or amused puzzlement
    "rhapsody": ["Noun", 3],  # Exuberant or ecstatic expression of feeling
    "sycophant": ["Noun", 3],  # Person who acts obsequiously towards someone important
    "trepidation": ["Noun", 4],  # Feeling of fear or agitation
    "ubiquity": ["Noun", 4],  # State of being everywhere at once
    "winsome": ["Adjective", 2],  # Charming, engaging
    "yearn": ["Verb", 1],  # Have an intense feeling of longing for something
    "zen": ["Noun", 1]  # Japanese school of Mahayana Buddhism emphasizing meditation
}
COM_ARRAY = [word for word in COM_WORDS.keys()]
PUNC = {
    ".": "ending",   # Period (.)
    ",": "sep",      # Comma (,)
    "!": "ending",   # Exclamation mark (!)
    "?": "ending",   # Question mark (?)
    ":": "sep",      # Colon (:)
    ";": "sep",      # Semicolon (;)
    "-": "sep",      # Hyphen (-)
    "(": "paren",    # Left parenthesis (()
    ")": "paren",    # Right parenthesis ())
    "[": "paren",    # Left square bracket ([)
    "]": "paren",    # Right square bracket (])
    "{": "paren",    # Left curly brace ({)
    "}": "paren",    # Right curly brace (})
    "'": "quote",    # Single quotation mark (')
    "\"": "quote",   # Double quotation mark (")
    "/": "sep",      # Forward slash (/)
    "\\": "sep",     # Backslash (\)
    "@": "special",  # At symbol (@)
    "#": "special",  # Hash/pound symbol (#)
    "$": "special",  # Dollar sign ($)
    "%": "special",  # Percent sign (%)
    "&": "special",  # Ampersand (&)
    "*": "special",  # Asterisk (*)
    "_": "special",  # Underscore (_)
    "+": "special",  # Plus sign (+)
    "=": "special",  # Equal sign (=)
    "<": "special",  # Less than sign (<)
    ">": "special"   # Greater than sign (>)
    # Add more punctuation marks as needed
}
PUNC_ARRAY = [thing for thing in PUNC.keys() if PUNC[thing] == "ending"]


def get_word(part):
    pool = [word for word in COM_WORDS.keys() if COM_WORDS[word][0] == part]

    return choice(pool)

def get_punc(part):
    pool = [punc for punc in PUNC.keys() if PUNC[punc] == part]

    return choice(pool)
def generate_line(target_syllables):
    line = ""
    syllables = 0

    while syllables < target_syllables:
        word = choice(COM_ARRAY)
        word_syllables = COM_WORDS[word][1]

        if syllables + word_syllables <= target_syllables:
            line += word + " "
            syllables += word_syllables

    return line.strip()


def generate_haiku():
    haiku = "\t" + generate_line(5).capitalize() + "\n" + generate_line(7).capitalize() + "\n\t" + generate_line(5).capitalize()
    return haiku

def generate_pure_essay(length):
    out = "\tWelcome to the best essay ever. "
    new_sentence = True

    for i in range(0, length):
        if new_sentence:
            out += choice(COM_ARRAY).capitalize()
            new_sentence = False
        else:
            out += choice(COM_ARRAY)

        if randint(0, 100) < 5:
            out += choice(PUNC_ARRAY) + " "
            new_sentence = True
        elif randint(0, 100) < 5:
            out += choice(PUNC_ARRAY) + "\n"
            new_sentence = True
        elif randint(0, 100) < 30:
            out += choice(PUNC_ARRAY) + "\n\t"
            new_sentence = True
        else:
            out += " "


def generate_basic_essay(length):
    e = "\t Welcome to the best essay ever. "
    l_b = 0
    w = 0
    s = 0

    while w < length:
        s_t = randint(0, 6)

        if s_t == 0:
            #   Subject - Verb Structure
            e += f"{get_word('Pronoun').capitalize() if randint(0, 1) == 1 else  get_word('Noun').capitalize()} {get_word('Verb')}{get_punc('ending')} "
            w += 2
        elif s_t == 1:
            # Subject - Verb - Object
            e += f"{get_word('Pronoun').capitalize() if randint(0, 1) == 1 else  get_word('Noun').capitalize()} {get_word('Verb')} {get_word('Preposition')} {get_word('Noun')}{get_punc('ending')} "
            w += 4
        elif s_t == 2:
            # Subject - Verb - Adjective
            e += f"{get_word('Pronoun').capitalize() if randint(0, 1) == 1 else  get_word('Noun').capitalize()} {get_word('Verb')} {get_word('Adjective')}{get_punc('ending')} "
            w += 3
        elif s_t == 3:
            # Subject - Verb - Adverb
            e += f"{get_word('Pronoun').capitalize() if randint(0, 1) == 1 else  get_word('Noun').capitalize()} {get_word('Verb')} {get_word('Adverb')}{get_punc('ending')} "
            w += 3
        elif s_t == 4:
            # Subject - Verb - Noun
            e += f"{get_word('Pronoun').capitalize() if randint(0, 1) == 1 else  get_word('Noun').capitalize()} {get_word('Verb')} {get_word('Noun')}{get_punc('ending')} "
            w += 3
        elif s_t == 5:
            # List
            e += f"{get_word('Pronoun').capitalize() if randint(0, 1) == 1 else  get_word('Noun').capitalize()} {get_word('Verb')} {get_word('Preposition')} {get_word('Noun')}, {get_word('Noun')}, and {get_word('Noun')}{get_punc('ending')} "
            w += 6
        elif s_t == 6:
            # Compound
            e += f"{get_word('Pronoun').capitalize() if randint(0, 1) == 1 else  get_word('Noun').capitalize()} {get_word('Verb')} {get_word('Preposition')} {get_word('Noun')}, {get_word('Conjunction')} {get_word('Preposition')} {get_word('Noun')}{get_punc('ending')} "
            w += 7

        if s % 5 == 0:
            e += "\n"
            l_b += 1
        if l_b % 5 == 0 and l_b > 1:
            e += "\t"
            l_b = 0

        s += 1

    return e

