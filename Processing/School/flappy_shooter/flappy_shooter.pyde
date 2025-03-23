from random import randint

# Settings
STAR_MAX_SPEED = 25.0
METEOR_MAX_SPEED = 20.0
MIN_SPEED = 5

NUM_STARS = 50
STAR_R = 5

NUM_METEORS = 5
METEOR_R = 100

LASER_LIFETIME = 0.5 * 60 # seconds * FPS = frames
LASER_MAX_WIDTH = 10

DIFF_INTERVAL = 10
SPEED_MULT = 1.2

# Place holders
meteor_img = None
game_running = True

# Star Data
star_x = []
star_y = []
star_speed = []

# Meteor Data
meteor_x = []
meteor_y = []
meteor_speed = []

# Laser Data
laser_timer = 0
laser_x = 0
laser_y = 0

# Player Data
score = 0
display_mult = 1
time = 1

def setup():
    global meteor_img
    # size(800, 800)
    fullScreen()
    
    meteor_img = loadImage("meteor.png")

    create_stars()
    create_meteor()
    
def draw():
    global laser_timer
    
    background(0)
    
    if game_running:
        draw_stars()
        draw_meteor()
        
        # If laser is alive
        if laser_timer > 0:
            draw_laser()
            
            laser_timer -= 1
        
        draw_player()
        
        check_collision()
        
        draw_info()
    else:
        draw_endscreen()
    
def create_stars():
    global star_x, star_y, star_speed
    
    for i in range(NUM_STARS):
        # Populate star arrays
        star_x.append(randint(0, width))
        star_y.append(randint(-STAR_R * 5, -STAR_R))
        star_speed.append(float(randint(MIN_SPEED, STAR_MAX_SPEED)))
    
def draw_stars():
    global star_y, star_x, STAR_R, star_speed
    
    noStroke()
    
    for i in range(len(star_x)):
        # Draw star
        fill(252, 240, 166)
        circle(star_x[i], star_y[i], STAR_R)
        
        star_y[i] += star_speed[i]
        
        if star_y[i] >= width + STAR_R:
            # Wrap Arround
            star_y[i] = -STAR_R
            star_x[i] = randint(0, width)
            star_speed[i] = float(randint(MIN_SPEED, STAR_MAX_SPEED))
    
def create_meteor():
    global meteor_x, meteor_y, meteor_speed
    
    for i in range(NUM_METEORS):
        # Populate Meteor Arrays
        meteor_x.append(randint(0, width))
        meteor_y.append(-METEOR_R)
        meteor_speed.append(float(randint(MIN_SPEED, METEOR_MAX_SPEED)))
    
def draw_meteor():
    global meteor_y, meteor_x, METEOR_R, meteor_speed
    global meteor_img
    
    noStroke()
    
    for i in range(len(meteor_x)):
        fill(93, 67, 25)
        
        # :p
        image(meteor_img, meteor_x[i], meteor_y[i], METEOR_R, METEOR_R)
        
        meteor_y[i] += meteor_speed[i]
        
        if meteor_y[i] >= width + METEOR_R:
            # Wrap Around
            meteor_y[i] = -METEOR_R
            meteor_x[i] = randint(0, width)
            meteor_speed[i] = float(randint(MIN_SPEED, METEOR_MAX_SPEED))
        
def draw_player():
    global time
    
    # Mouth
    noStroke()
    fill(255, 100, 0)
    rect(mouseX - 1, mouseY - 40, 10, 20)
    
    # Body + Eyes
    fill(255, 255, 0)
    circle(mouseX, mouseY, 50)
    fill(255)
    circle(mouseX - 10, mouseY - 5, 20)
    fill(0)
    circle(mouseX - 10, mouseY - 5, 10)
    
    # Put it here so we only update ingame, and not durring an endscreen
    time += 1

def shoot_laser():
    global laser_timer, laser_x, laser_y
    
    # Update laser
    laser_timer = LASER_LIFETIME
    laser_x = mouseX
    laser_y = mouseY

def draw_laser():
    global meteor_y, meteor_x, score
    
    noStroke()
    # Used for dynamic width / opacity
    percentLife = float(laser_timer) / float(LASER_LIFETIME)
    
    fill(200, 200, 255, 255 * percentLife)
    rect(laser_x, laser_y, LASER_MAX_WIDTH * percentLife, -height)
    
    for i in range(NUM_METEORS):
        # Hit detection
        if meteor_y[i] <= laser_y and (dist(meteor_x[i], 0, laser_x, 0) <= METEOR_R) and meteor_y[i] >= 0:
            meteor_y[i] = -METEOR_R
            meteor_x[i] = randint(0, width)
            
            score += 1
            update_difficulty()
            
def frames_to_time(frames):
    minutes = (frames // 60) // 60
    seconds = (frames // 60) % 60
    
    # I hate python
    if minutes <= 9:
        minutes = "0" + str(minutes)
    else:
        minutes = str(minutes)
        
    if seconds <= 9:
        seconds = "0" + str(seconds)
    else:
        seconds = str(seconds)
        
    return str(minutes) + ":" + str(seconds)

def draw_info():
    # This just displays score and stuff
    score_text = "SCORE: " + str(score) +  " METEORS"
    time_text = frames_to_time(time)
    mult_text = "SPEED MULT: " + str(round(display_mult, 2))
    
    fill(255)
    
    textSize(30)
    score_width = textWidth(score_text)
    text(score_text, (width / 2) - (score_width / 2), 30)
    
    textSize(20)
    time_width = textWidth(time_text)
    text(time_text, (width / 2) - (time_width / 2), 60)
    
    textSize(20)
    time_width = textWidth(mult_text)
    text(mult_text, (width / 2) - (time_width / 2), 80)
    
def mousePressed():
    if game_running:
        shoot_laser()
    
def keyPressed():
    global game_running, score, time, display_mult
    global star_x, star_y, star_speed
    global meteor_x, meteor_y, meteor_speed, STAR_MAX_SPEED, METEOR_MAX_SPEED
    
    if key == ' ' and game_running:
        shoot_laser()
    elif key == 'r' or key == 'R' and not game_running:
        # Resets the game to its original state
        game_running = True
        score = 0
        display_mult = 1
        time = 1
        
        star_x, star_y, star_speed = [], [], []
        meteor_x, meteor_y, meteor_speed = [], [], []
        
        STAR_MAX_SPEED = 25.0
        METEOR_MAX_SPEED = 20.0
        
        create_stars()
        create_meteor()
    
def check_collision():
    global game_running
    
    for i in range(NUM_METEORS):
        if dist(mouseX, mouseY, meteor_x[i], meteor_y[i]) <= 50:
            # Kill Player
            game_running = False

def draw_endscreen():
    global game_running, score, time
    global star_x, star_y, star_speed
    global meteor_x, meteor_y, meteor_speed
    
    # Display final scores
    
    status_text = "YOU DIED L + BOZO"
    score_text = "SCORE: " + str(score) +  " METEORS"
    time_text = frames_to_time(time)
    restart_text = "(press R to restart)"
    
    fill(255, 0, 0)
    textSize(50)
    text_width = textWidth(status_text)
    text(status_text, (width / 2) - (text_width / 2), (height / 2) - 150)
    
    fill(255)
    textSize(30)
    score_width = textWidth(score_text)
    text(score_text, (width / 2) - (score_width / 2), (height / 2) - 30)
    
    textSize(30)
    time_width = textWidth(time_text)
    text(time_text, (width / 2) - (time_width / 2), (height / 2) + 30)
    
    textSize(20)
    restart_width = textWidth(restart_text)
    text(restart_text, (width / 2) - (restart_width / 2), (height / 2) + 130)

def update_difficulty():
    global meteor_speed, star_speed
    global STAR_MAX_SPEED, METEOR_MAX_SPEED
    global SPEED_MULT, display_mult
    
    # Make the game harder as time goes on
    
    if score > 0 and score % DIFF_INTERVAL == 0:
        STAR_MAX_SPEED = int(float(STAR_MAX_SPEED) * SPEED_MULT)
        METEOR_MAX_SPEED = int(float(METEOR_MAX_SPEED) * SPEED_MULT)
        
        for i in range(NUM_METEORS):
            meteor_speed[i] = round(meteor_speed[i] * SPEED_MULT, 2)
        
        for i in range(NUM_STARS):
            star_speed[i] = round(star_speed[i] * SPEED_MULT, 2)
        
        display_mult *= SPEED_MULT
