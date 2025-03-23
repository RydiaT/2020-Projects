# Indian Flag
# Rydia Titus
def setup():
    size(900, 600)
    background(255)
    noStroke()
    
def draw():
    
    if dist(mouseX, mouseY, width / 2, height / 2) <= 20:
        fill(0, 0, 255)
        circle(mouseX, mouseY, 200)
        # print("1")
    elif mouseY <= height * (1.0 / 3.0):
        fill(250, 150, 0)
        circle(mouseX, mouseY, 50)
        # print("2")
    elif mouseY > (height * (2.0 /3.0)):
        fill(0, 255, 0)
        circle(mouseX, mouseY, 50)
        # print("3")
    else:
        fill(255, 255, 255)
        circle(mouseX, mouseY, 50)
        # print("4")
