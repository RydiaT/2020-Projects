// Particle class
class Particle {
  float drag = 0.98;
  
  PVector position; // Position of the particle
  PVector velocity; // Velocity of the particle
  PVector acceleration; // Acceleration of the particle
  float mass; // Mass of the particle
  float radius; // Radius of the particle (based on mass)
  float maxLife;
  float life;
  boolean isDying; // Flag to indicate if the particle is dying
  boolean isSpawning;
  
  // Needed to stop insane amounts of lag, lol.
  boolean fertile = true;
  
  color c;
  
  String[] mouths = new String[]{"<", ">", "D", "3", "o", "(", ")", "p"};
  String[] eyebrows = new String[]{"<", ">", ""};
  String face;

  Particle(float x, float y, float mass, float maxLife, color c) {
    this.position = new PVector(x, y);
    this.velocity = new PVector(random(-2, 2), random(-2, 2));
    this.acceleration = new PVector(0, 0);
    this.mass = mass;
    this.radius = mass; // Radius is proportional to mass
    this.maxLife = maxLife;
    this.life = 0;
    
    this.isDying = false;
    this.isSpawning = true;
    
    this.c = c;
    
    int mouthIdx = (int) random(mouths.length);
    int eyebrowIdx = (int) random(eyebrows.length);
    
    this.face = String.format("%s:%s", eyebrows[eyebrowIdx], mouths[mouthIdx]);
  }
  
  public void setFertility(boolean val) {
    this.fertile = val;
  }

  void applyForce(PVector force) {
    // Newton's second law: F = ma, so a = F/m
    PVector f = PVector.div(force, mass);
    acceleration.add(f);
  }

  void update() {
    if(this.isSpawning) {
      life += 10;
      
      if(life >= maxLife) {
        isSpawning = false;
      }
    }
      
    // Update velocity and position
    velocity.add(acceleration);
    position.add(velocity);

    // Reset acceleration
    acceleration.mult(0);

    //// Bounce off edges of the screen
    //if (position.x < radius || position.x > width - radius) {
    //  velocity.x *= -1;
    //}
    //if (position.y < radius || position.y > height - radius) {
    //  velocity.y *= -1;
    //}
    
    if (position.x > width + radius) {
      position.x = -radius;
    } else if (position.x < -radius) {
      position.x = width + radius;
    }
    if (position.y > height + radius) {
      position.y = -radius;
    } else if (position.y < -radius) {
      position.y = height + radius;
    }

    // Apply some damping to simulate energy loss
    velocity.mult(drag);

    // Reduce life if the particle is dying
    if (isDying) {
      life -= 2; // Faster life reduction for smoother transition
    } else {
      life--;
    }
    
  }

  void display() {
    // Draw the particle
    fill(c, (255 * (life / maxLife)));
    noStroke();
    ellipse(position.x, position.y, radius * 2, radius * 2);
    
    if(fertile) {
      textSize(this.radius);
      fill(0);
      float w = textWidth(face);
      text(face, position.x - (w / 2), position.y + (radius / 2));
    }
  }

  void checkCollision(Particle other) {
    // Calculate distance between particles
    PVector distVector = PVector.sub(other.position, position);
    float distance = distVector.mag();

    // Check if particles are colliding
    if (distance < radius + other.radius) {
      // Calculate collision response (elastic collision)
      PVector normal = PVector.sub(other.position, position).normalize();
      PVector tangent = new PVector(-normal.y, normal.x);

      // Project velocities onto normal and tangent vectors
      float v1n = normal.dot(velocity);
      float v1t = tangent.dot(velocity);
      float v2n = normal.dot(other.velocity);
      float v2t = tangent.dot(other.velocity);

      // Calculate new normal velocities after collision
      float m1 = mass;
      float m2 = other.mass;
      float v1nFinal = (v1n * (m1 - m2) + 2 * m2 * v2n) / (m1 + m2);
      float v2nFinal = (v2n * (m2 - m1) + 2 * m1 * v1n) / (m1 + m2);

      // Update velocities
      velocity.set(normal.x * v1nFinal + tangent.x * v1t, normal.y * v1nFinal + tangent.y * v1t);
      other.velocity.set(normal.x * v2nFinal + tangent.x * v2t, normal.y * v2nFinal + tangent.y * v2t);

      // Separate particles to avoid overlap
      float overlap = radius + other.radius - distance;
      position.sub(PVector.mult(normal, overlap / 2));
      other.position.add(PVector.mult(normal, overlap / 2));
    }
  }

  public boolean isAlive() {
    return this.life > 0;
  }

  public void kill() {
    this.isDying = true; // Mark the particle for death
  }
}
