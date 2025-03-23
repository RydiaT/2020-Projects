import processing.sound.*;

boolean muted = true;

// List to store all particles
ArrayList<Particle> particles;
ArrayList<Particle> explodyBits;
boolean isTransitioning = false; // Flag to indicate if the transition is active

float gravity = 0.3;
// Add a little push
float xWind = random(-3.0, 3.0);
float yWind = random(-3.0, 3.0);

color white = color(255);
color lightGrey = color(100);
color black = color(0);

color backgroundColor = black;

int maxParticles = 500;

SoundFile pop1;
SoundFile pop2;
SoundFile pop3;
SoundFile pop4;
SoundFile pop5;
SoundFile pipe;

public Particle randParticle() {
  float x = random(width);
  float y = random(height);
  float size = random(10, 30);
  int life = (int) size * 30;
  color c = randColor();
  
  return new Particle(x, y, size, life, c);
}

public color randColor() {
  return color(random(255), random(255), random(255));
}

public color randColor(int maxRGB, float alpha) {
  return color(random(maxRGB), random(maxRGB), random(maxRGB), alpha);
}

public void randSound() {
  float r = random(0, 1000);
  
  if(r <= 100) {
    pop1.play();
  } else if (r <= 200) {
    pop2.play();
  } else if (r <= 300) {
    pop3.play();
  } else if (r <= 400) {
    pop4.play();
  } else if (r <= 500) {
    pop5.play();
  } else if (r >= 999) {
    pipe.play();
  } else {
    randSound();
  }
}

void setup() {
  // size(800, 600);
  fullScreen();
  
  if(!muted) {
    pop1 = new SoundFile(this, "Sounds/pop1.mp3");
    pop2 = new SoundFile(this, "Sounds/pop2.mp3");
    pop3 = new SoundFile(this, "Sounds/pop3.mp3");
    pop4 = new SoundFile(this, "Sounds/pop4.mp3");
    pop5 = new SoundFile(this, "Sounds/pop5.mp3");
    pipe = new SoundFile(this, "Sounds/metal_pipe.mp3");
  }
  
  particles = new ArrayList<Particle>();
  explodyBits = new ArrayList<Particle>();

  // Create some initial particles
  for (int i = 0; i < 10; i++) {
    particles.add(randParticle());
  }
}

void draw() {
  fill(backgroundColor);
  rect(0, 0, width, height);
  
  drawWindArrow();
  
  textSize(50);
  fill(white);
  
  String particleText = "Bubbles: " + particles.size();
  float textWidth = textWidth(particleText);
  
  text(particleText, (width / 2) - (textWidth / 2), height / 2);
  
  textSize(20);
  fill(lightGrey);
  
  String windText = String.format("Wind: < %.2f, %.2f >", xWind, yWind);
  textWidth = textWidth(windText);
  
  text(windText, (width / 2) - (textWidth / 2), (height / 2) + 20);

  checkParticles(particles);
  checkParticles(explodyBits);

  // Start transition if particle count exceeds 100
  if (particles.size() > maxParticles && !isTransitioning) {
    isTransitioning = true;
    for (Particle p : particles) {
      p.kill(); // Gradually kill all particles
    }
  }

  // Reset the simulation once all particles are dead
  if (isTransitioning && particles.size() == 0) {
    isTransitioning = false;
    
    xWind = random(-3.0, 3.0);
    yWind = random(-3.0, 3.0);
    
    for (int i = 0; i < 10; i++) {
      particles.add(randParticle()); // Add new particles
    }
  }
}

float curArrowX = 0;
float curArrowY = 0;

void drawWindArrow() {
  // Arrow properties
  float arrowLength = 400; // Base length of the arrow
  float pointerSize = 150; // Size of the arrowhead
  float thiccness = 50;
  float arrowX = (width / 2); // X position of the arrow
  float arrowY = (height / 2); // Y position of the arrow
  color arrowColor = color(150, 30);

  // Calculate the direction and magnitude of the wind
  float goalX = lerp(xWind, curArrowX, 0.9);
  float goalY = lerp(yWind, curArrowY, 0.9);
  
  curArrowX = goalX;
  curArrowY = goalY;
  
  PVector wind = new PVector(goalX, goalY);
  
  wind.normalize().mult(arrowLength); // Scale the wind vector to the arrow length

  // Draw the arrow line with rounded ends
  stroke(arrowColor);
  strokeWeight(thiccness);
  strokeCap(ROUND); // Make the line ends rounded
  line(arrowX - (wind.x / 2), arrowY - (wind.y / 2), arrowX + wind.x, arrowY + wind.y);

  // Draw the detached triangle pointer
  float angle = atan2(wind.y, wind.x); // Angle of the wind vector
  float pointerX = arrowX + wind.x; // X position of the pointer
  float pointerY = arrowY + wind.y; // Y position of the pointer

  // Calculate the two points for the "V" shape
  float offsetX1 = cos(angle + HALF_PI) * pointerSize / 2;
  float offsetY1 = sin(angle + HALF_PI) * pointerSize / 2;
  float offsetX2 = cos(angle - HALF_PI) * pointerSize / 2;
  float offsetY2 = sin(angle - HALF_PI) * pointerSize / 2;

  // Draw the two lines of the pointer
  stroke(arrowColor);
  line(pointerX, pointerY, pointerX - wind.x * 0.2 + offsetX1, pointerY - wind.y * 0.2 + offsetY1);
  line(pointerX, pointerY, pointerX - wind.x * 0.2 + offsetX2, pointerY - wind.y * 0.2 + offsetY2);
}


public void checkParticles(ArrayList<Particle> list) {
    // Update and display all particles
  for (int i = 0; i < list.size(); i++) {
    Particle p = list.get(i);

    if (p.isAlive() || p.isSpawning) {
      p.applyForce(new PVector(0, gravity)); // Apply gravity
      p.applyForce(new PVector(xWind, yWind)); // Apply gravity
      p.update();
      p.display();
    } else if (!p.isDying) {
      
      list.remove(p);
      
      if(p.fertile) {
        if(!muted) {
          randSound();
        }
        
        createExplosion(p.position.x, p.position.y, p.c);
        
        list.add(randParticle());
        list.add(randParticle());
      }
    } else {
      if(p.fertile){
        if(!muted) {
          randSound();
        }
        
        createExplosion(p.position.x, p.position.y, p.c);
      }
      
      list.remove(p);
    }
  }

  // Check for collisions between particles
  for (int i = 0; i < particles.size(); i++) {
    for (int j = i + 1; j < particles.size(); j++) {
      Particle p1 = particles.get(i);
      Particle p2 = particles.get(j);
      p1.checkCollision(p2);
    }
  }
}

void mousePressed() {
  xWind = random(-3.0, 3.0);
  yWind = random(-3.0, 3.0);
}

// Function to create an explosion of smaller particles
void createExplosion(float x, float y, color c) {
  backgroundColor = randColor(50, 50);
  
  int numParticles = 20; // Number of smaller particles in the explosion
  for (int i = 0; i < numParticles; i++) {
    float size = random(2, 5); // Smaller size for explosion particles
    int life = (int) random(10, 30); // Shorter lifespan for explosion particles
    Particle p = new Particle(x, y, size, life, c);
    p.velocity = PVector.random2D().mult(random(2, 5)); // Random velocity for explosion effect
    p.setFertility(false);
    explodyBits.add(p);
  }
}
