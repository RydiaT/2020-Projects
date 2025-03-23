from pynput.keyboard import Listener, Key

with open("log.csv", "w") as f:
    pass
with open("letters.csv", "w") as f:
    pass

# String to store the pressed keys
word = ""
#

def on_press(key):
    global word
    try:
        # Add the character to the string

        if key == Key.space:
            with open("log.csv", "a") as f:
                f.write(word + ",")
                word = ""
        elif hasattr(key, "char") and key.char not in (None, ",", "'"):
            with open("letters.csv", "a") as f:
                f.write(key.char + ",")

            word += key.char

    except AttributeError:
        # Handle special keys
        word += f"[{key}]"


def on_release(key):
    # Stop the listener if the Escape key is pressed
    if key == Key.esc:
        return False


# Start the listener
with Listener(on_press=on_press, on_release=on_release) as listener:
    listener.join()
