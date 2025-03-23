# Loop Forest II: The Hidden Loop Temple

# ***************************************************
# * When ninty-nine stars shine in the sky,         *
# *  and fifteen stones are placed upon the ground, *
# *  eight wooden pillars to the left,              *
# *  and another five to the right,                 *
# *  then the ancient Temple of Loop will appear.   *
# *                                                 *
# * Do not change where you begin,                  *
# *  nor how fast you move,                         *
# *  only change your destination,                  *
# *  to find yourself at the temple's door.         *
# *  (Only change the Conditions or the program     *
# *  might not work correctly. Don't change the     *
# *  Initializations or Updates.)                   *
# ***************************************************

def setup():
    # Setup
    size(900, 700)
    noStroke()
  
    # Background
    background(0, 0, 100)
    fill(0, 100, 0)
    rect(0, height - 100, width, 100)

    # ********************
    # Draws the stars
    starCount = 0  # <- I
    while starCount < 99:  # <- C
        drawStar()
        starCount += 1  # <- U
  
    # *********************
    # Draws the left trees
    leftTreeCount = 25  # <- I
    while leftTreeCount < 425:  # <- C
        drawTree(leftTreeCount)
        leftTreeCount += 50  # <- U  
  
    # *********************
    # Draws the right trees
    rightTreeCount = 650  # <- I
    while rightTreeCount < 900:  # <- C
        drawTree(rightTreeCount)
        rightTreeCount += 50  # <- U
  
    # *********************
    # Draws the stones
    stoneCount = 0  # <- I
    while stoneCount < 1800:  # <- C
        drawStone(stoneCount)
        stoneCount += 120  # <- U
  
    drawTemple(starCount, leftTreeCount, rightTreeCount, stoneCount)
    
#####################################
# IGNORE EVERYTHING BELOW THIS LINE #
#####################################

def drawStar():
    fill(200, 200, 100)
    circle(random(width), random(height - 150), 4)

def drawTree(x):
    fill(90, 70, 30)
    rect(x, height - 75, 20, -200)
    fill(0, 100, 0)
    ellipse(x + 10, height - 270, 45, 170)

def drawStone(x):
    y = height - 40
    if x > width:
        y += 30
    x += 40
    x %= width
    fill(200)
    beginShape()
    vertex(x - 18, y - 20)
    vertex(x + 20, y - 18)
    vertex(x + 30, y + 18)
    vertex(x - 30, y + 20)
    endShape(CLOSE)

def drawTemple(star, left, right, stone):
    if star == 99 and left == 425 and right == 900 and stone == 1800:
        stroke(70, 70, 30)
        fill(80, 80, 50)
        beginShape()
        vertex(320, 630)
        vertex(780, 630)
        vertex(725, 305)
        vertex(375, 305)
        endShape(CLOSE)

        beginShape()
        vertex(705, 305)
        vertex(390, 305)
        vertex(430, 105)
        vertex(655, 105)
        endShape(CLOSE)

        beginShape()
        vertex(442, 105)
        vertex(445, 85)
        vertex(472, 85)
        vertex(475, 105)
        endShape(CLOSE)

        beginShape()
        vertex(612, 105)
        vertex(615, 85)
        vertex(642, 85)
        vertex(645, 105)
        endShape(CLOSE)

        fill(0)
        beginShape()
        vertex(510, 305)
        vertex(520, 220)
        vertex(570, 220)
        vertex(580, 305)
        endShape(CLOSE)

        strokeWeight(2)
        stroke(70, 70, 30)
        for i in range(10):
            line(500 - 6 * i, 320 + 30 * i, 590 + 4 * i, 320 + 30 * i)
        
        noFill()
        arc(545, 170, 50, 50, PI * 0.1, PI * 1.9)
        line(570, 166, 573, 156)
        line(570, 166, 561, 163)
        noStroke()
