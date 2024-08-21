from pynput import mouse


def on_click(x, y, button, pressed):
    if x < 0:
        # Stop listener
        return False

    print('{0} at {1}'.format(
        'Pressed' if pressed else 'Released',
        (x, y)))


with mouse.Listener(
        on_click=on_click,
        ) as listener:
    listener.join()
