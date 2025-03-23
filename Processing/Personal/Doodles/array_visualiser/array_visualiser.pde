int[] things = new int[50];
int spacing = 0;
int highlightIndex1 = -5;
int highlightIndex2 = -5;

void setup() {
  size(1500, 500);
  //frameRate(3);

  for (int i = 0; i < things.length; i++) {
    things[i] = (int)random(0, things.length);
  }
}

void draw() {
  background(109, 111, 115);



  int w = ((width - spacing) / things.length) + 1;
  int max = getMax(things) + 1;
  for (int i = 0; i < things.length; i++) {
    if (i == highlightIndex1 || i == highlightIndex2) {
      fill(232, 206, 118);
    } else {
      fill(123, 159, 232);
    }
    
    int h = (int)(height * (things[i] * 1.0 / max)) + 50;
    rect(i * w + spacing, height - h, w, h);
  }

  if (!isSorted()) {
    bubbleSort();
  }
}

void swap(int idx1, int idx2) {
  int temp = things[idx1];
  things[idx1] = things[idx2];
  things[idx2] = temp;
}

int getMax(int[] stuff) {
  int max = -999;

  for (int thing : stuff) {
    if (thing > max) {
      max = thing;
    }
  }

  return max;
}

boolean isSorted() {
  for (int i = 0; i < things.length - 1; i++) {
    if (things[i] > things[i + 1]) {
      return false;
    }
  }
  return true;
}

void bubbleSort() {
  for (int i = 0; i < things.length - 1; i++) {
    highlightIndex1 = i;
    highlightIndex2 = i + 1;

    if (things[i] > things[i + 1]) {
      swap(things[i], things[i + 1]);
    }
    
    //draw();
  }
}
