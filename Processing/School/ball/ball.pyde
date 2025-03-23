# Bouncing Ball

# Ball Data
ballR = 50
ballC = color(255, 255, 255)

ballX = 500
ballY = 250

movingUp = True
movingRight = False
ballS = 7
# How much slower up/down movement is. Larger number is faster.
vertFactor = 2
sillyness = 2

def setup():
    # size(1000, 1000)
    fullScreen()
    textSize(30)
    noStroke()

def draw():
    global ballR, ballX, ballY
    global movingUp, movingRight, ballS, vertFactor
    global ballC, sillyness
    
    # Clear Screen
    background(0, 0, 0)
    
    # Draw Ball
    fill(ballC)
    circle(ballX, ballY, ballR * 2)
    
    fill(255, 0, 0)
    text(":p " + str(sillyness), ballX - 10, ballY + 10)
    
    # If we've NOT clicked on the ball...
    if not (mousePressed and dist(mouseX, mouseY, ballX, ballY) <= ballR):
        # Move Position, and Clamp Position
        if movingRight:
            ballX += ballS
        else:
            ballX -= ballS
            
        if movingUp:
            ballY -= ballS * vertFactor
        else:
            ballY += ballS * vertFactor
    # Otherwise, drag it around.
    else:
        ballX = mouseX
        ballY = mouseY 
    
    # Check for Bounce
    if ballX >= width - ballR or ballX <= ballR:
        movingRight = not movingRight
        
        ballC = color(random(255), random(255), random(255))
        ballS += sillyness
        ballR += sillyness
    
    if ballY >= height - ballR or ballY <= ballR:
        movingUp = not movingUp
        
        ballC = color(random(255), random(255), random(255))
        ballS += sillyness
        ballR += sillyness
