ArrayList<Particle> particles = new ArrayList();
int pSize = 20;

void setup() {
  size(500, 400);
  for (int i = 0; i < 100; i++) {
    int x = (int) Math.floor(Math.random() * (width - (pSize * 2)));
    int y = (int) Math.floor(Math.random() * (height - (pSize* 2)));

    particles.add(new Particle(x, y, pSize));
  }
}

void draw() {
  background(255, 255, 255);
  for (int i = 0; i < particles.size(); i++) {
    Particle thing = particles.get(i);
    thing.update();
    thing.show();
  }

  if (mousePressed) {
    for (int i = 0; i < particles.size(); i++) {
      Particle thing = particles.get(i);
      thing.push(5, 0);
      thing.report();
    }
  }
}
