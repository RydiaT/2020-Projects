class Game {
  int ptsToWin;
  int p_points = 0;
  int c_points = 0;
  
  int cheatRate = 3;
  
  int BEATS_ROCK = 2;
  int BEATS_PAPER = 1;
  int BEATS_SCISSORS = 0;
  
  private ArrayList<String> output = new ArrayList<String>();
  
  public Game(int ptsToWin) {
    this.ptsToWin = ptsToWin;
  }
  
  public ArrayList getLog() {
    return output;
  }
  
  public void clearLog() {
    output = new ArrayList<String>();
  }
  
  public int checkOutcome(int p_choice) {
    int c_choice = randint(0, 2);
    int howCheat = cheat();
    
    output.add("Computer threw...");
    
    if(howCheat == 1) {
      output.add("Dynamite?");
      return 3;
    } else {
      if(c_choice == 0) {
        output.add("Rock!");
      } else if (c_choice == 1) {
        output.add("Paper!");
      } else {
        output.add("Scissors!");
      }
    }
    
    if(c_choice == p_choice) {
      return 69;
    }
    
    // Rock
    if(p_choice == 0) {
      if(c_choice == BEATS_PAPER) {
        return 0;
      } else {
        if(howCheat == 2) {
          output.add("The computer demands a do over!");
          return checkOutcome(p_choice);
        } else {
          return 1;
        }
      }
    // Paper
    } else if (p_choice == 1) {
      if(c_choice == 0) {
        if(howCheat == BEATS_ROCK) {
          output.add("The computer demands a do over!");
          return checkOutcome(p_choice);
        } else {
          return 1;
        }
      } else {
        return 0;
      }
    // Scissors
    } else if (p_choice == 2) {
      if(c_choice == BEATS_SCISSORS) {
        return 0;
      } else {
        if(howCheat == 2) {
          output.add("The computer demands a do over!");
          return checkOutcome(p_choice);
        } else {
          return 1;
        }
      }
    }
    
    return -1;
  }
  
  public int gameOver() {
    if(p_points >= ptsToWin) {
      return 1;
    } else if (c_points >= ptsToWin) {
      return 0;
    } else {
      return -1;
    }
  }
  
  private int cheat() {
    if(randint(0, 10) > cheatRate) {
      int what_do = randint(0, 3);
      
      if(what_do == 0) {
        output.add("Uh oh, the scores got smudged...");
        
        if(p_points > c_points){
          output.add("SCORES SWAPPED");
          
          int temp = p_points;
          p_points = c_points;
          c_points = temp;
        } else {
          int penalty = randint(1, 5);
          output.add("SCORE -" + penalty);
          p_points -= penalty;
        }
        
        return 0;
      } else if (what_do == 1) {
        return 1;
      } else if (what_do == 2) {
        output.add("The computer looks a little mad...");
        
        return 2;
      } else if (what_do == 3) {
        output.add("The computer demands a rule change!");
        
        BEATS_ROCK = randint(0, 2);
        BEATS_PAPER = randint(0, 2);
        BEATS_SCISSORS = randint(0, 2);
        
        return 0;
      }
    } else {
      return 0;
    }
    
    return 0;
  }
  
  public int randint(int min, int max) {
    return min + (int) Math.floor(Math.random() * (max - min + 1));
  }
}
