void setup() {
  // Flipped Y (Top Left)
  // Canvas - (W, H)
  size(500, 400);
}

void draw() {
  // Background - 255 RGBA (A) || (R, G, B) || (R, G, B, A)
  background(0);
  
  drawingExamples();
  
  stroke(255, 255, 0);
  fill(255, 0, 100, 70);
  
  // Mouse Controls
  ellipse(mouseX, mouseY, 200, 200);
  
  if (mousePressed) {
    rect(300, 200, 150, 150);
  } else {
    ellipse(400, 300, 200, 200);
  }
}

void drawingExamples() {
  

  // Rect - (X, Y, W, H) - Top Left
  rect(150, 300, 200, 50);

  // Ellipse (Circle) - (X, Y, W, H) - Center
  ellipse(250, 200, 200, 200);

  // Stroke & Fill - 255 RGBA (A) || (R, G, B) || (R, G, B, A)
  stroke(0, 0, 255);
  fill(0, 0, 255, 30);
  rect(100, 40, 100, 75);
}
