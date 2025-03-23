Button rock;
Button paper;
Button scissors;

Game rps = new Game(10);

String winText = "";
int winState = -1;

boolean playing = true;
int roundNum = 1;

public void setup() {
  size(600, 600);

  rock = new Button((width / 2), (height / 2) + 140, 250, 50);
  rock.setText("Throw Rock");
  paper = new Button((width / 2), (height / 2) + 200, 250, 50);
  paper.setText("Throw Paper");
  scissors = new Button((width / 2), (height / 2) + 265, 250, 50);
  scissors.setText("Throw Scissors");
}

public void draw() {
  if (playing) {
    background(0);

    rock.show();
    paper.show();
    scissors.show();

    textSize(30);
    String scoreText = String.format("Player: %d        Computer: %d", rps.p_points, rps.c_points);

    float stringW = textWidth(scoreText);

    fill(255);
    text(scoreText, (width / 2) - (stringW / 2), 50);

    scoreText = String.format("Best %d out of %d", rps.ptsToWin, (int) ((float) rps.ptsToWin * 1.5));
    stringW = textWidth(scoreText);
    text(scoreText, (width / 2) - (stringW / 2), 80);

    textSize(20);
    text("Current Rules: ", (width / 2) - 275, (height / 2) + 90);

    String ruleText = "Beats ";

    if (rps.BEATS_ROCK == 0) {
      ruleText += "Rock";
    } else if (rps.BEATS_ROCK == 1) {
      ruleText += "Paper";
    } else {
      ruleText += "Scissors";
    }

    text(ruleText, (width / 2) - 275, (height / 2) + 140);

    ruleText = "Beats ";

    if (rps.BEATS_PAPER == 0) {
      ruleText += "Rock";
    } else if (rps.BEATS_PAPER == 1) {
      ruleText += "Paper";
    } else {
      ruleText += "Scissors";
    }

    text(ruleText, (width / 2) - 275, (height / 2) + 200);

    ruleText = "Beats ";

    if (rps.BEATS_SCISSORS == 0) {
      ruleText += "Rock";
    } else if (rps.BEATS_SCISSORS == 1) {
      ruleText += "Paper";
    } else {
      ruleText += "Scissors";
    }

    text(ruleText, (width / 2) - 275, (height / 2) + 260);

    textSize(30);

    if (rps.p_points == rps.ptsToWin - 1 || rps.c_points == rps.ptsToWin - 1) {
      fill(255, 255, 0);

      scoreText = "MATCH POINT";
      stringW = textWidth(scoreText);

      text(scoreText, (width / 2) - (stringW / 2), 100);
    }

    ArrayList<String> log = rps.getLog();

    for (int i = 0; i < log.size(); i++) {
      String line = log.get(i);

      fill(255);
      stringW = textWidth(line);

      text(line, (width / 2) - (stringW / 2), 140 + (40 * i));
    }

    if (!winText.isEmpty()) {
      if (winState == 1) {
        fill(0, 255, 0);
      } else if (winState == 0) {
        fill(255, 0, 0);
      } else {
        fill(0, 0, 255);
      }

      stringW = textWidth(winText);

      text(winText, (width / 2) - (stringW / 2), 400);
    }
  } else {
    String text = "";

    if (rps.p_points > rps.c_points) {
      text = "YOU WON :D";
    } else {
      text = "YOU LOST :<";
    }

    fill(rps.randint(0, 255), rps.randint(0, 255), rps.randint(0, 255));

    for (int i = 0; i < 3; i++) {
      text(text, rps.randint(0, width), rps.randint(0, height));
    }
  }
}

public void doRound(int p_choice) {
  rps.clearLog();
  winText = "";
  roundNum++;

  int outcome = rps.checkOutcome(p_choice);

  winState = outcome;

  if (outcome == 1) {
    winText = "ROUND WON";

    rps.p_points += 1;
  } else if (outcome == 0) {
    winText = "ROUND LOST";

    rps.c_points += 1;
  } else if (outcome == 69) {
    winText = "Whoopsie, it's a tie!";
  } else if (outcome == 3) {
    winText = "SCORE -1, ROUND LOST";

    winState = 0;

    rps.p_points -= 1;
    rps.c_points += 1;
  }

  if (rps.p_points >= rps.ptsToWin || rps.c_points >= rps.ptsToWin) {
    playing = false;
  }
}

public void mousePressed() {
  if (playing) {
    if (rock.isHovered()) {
      doRound(0);
    } else if (paper.isHovered()) {
      doRound(1);
    } else if (scissors.isHovered()) {
      doRound(2);
    }
  }
}
