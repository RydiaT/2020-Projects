# Points of a Triangle
# No one made this. A ghost wrote it.

# Triangle Coords
x1, y1, x2 = 0, 0, 50
y2, x3, y3 = 50, 100, 0

step = 1

def setup():
    fullScreen()
    noStroke()
    
    
def draw():
    # background(0, 0, 0)
    
    # beginShape()
    # vertex(x1, y1)
    # vertex(x2, y2)
    # vertex(x3, y3)
    # endShape()
    
    fill(0, 5)
    rect(0, 0, width, height)
    fill("#FCE90A")
    
    # Draw Triangle
    fill("#FCE90A")
    triangle(x1, y1, x2, y2, x3, y3)
    
    
    # Highlight which point is being moved next
    if step == 1:
        fill(255, 0, 0)
    else:
        fill(255, 255, 255)
    circle(x1, y1, 5)
    
    if step == 2:
        fill(255, 0, 0)
    else:
        fill(255, 255, 255)
    circle(x2, y2, 5)
    
    if step == 3:
        fill(255, 0, 0)
    else:
        fill(255, 255, 255)
    circle(x3, y3, 5)
    
def mousePressed():
    global x1, y1
    global x2, y2
    global x3, y3
    global step
    
    # Move a point according to step
    if step == 1:
        x1 = mouseX
        y1 = mouseY
        
        step += 1
    elif step == 2:
        x2 = mouseX
        y2 = mouseY
        
        step += 1
    elif step == 3:
        x3 = mouseX
        y3 = mouseY
        
        step = 1
    
    
