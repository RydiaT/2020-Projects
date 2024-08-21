import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Cubes {
    public static void main(String[] args) throws Exception {
        Scanner cubeText = new Scanner(new File("cubes.txt"));

        int total = 0;

        int totalPower = 0;

        while(cubeText.hasNextLine()){
            String line = cubeText.nextLine();
            int id = getId(line);
            int numGames = getNumGames(line);
            String[] games = getGames(line, numGames);

            boolean isPossible = checkPossibility(games);

            if(isPossible){
                total += id;
            }

            int[] colorMax = getMax(games);

            totalPower += colorMax[0] * colorMax[1] * colorMax[2];

        }

        System.out.println(total);
        System.out.println(totalPower);
    }

    public static int getId(String line){
        return Integer.parseInt(line.substring(5, line.indexOf(":")));
    }

    public static int getNumGames(String line){
        int count = 0;

        for(char c : line.toCharArray()){
            if(c == ';'){
                count++;
            }
        }

        return count + 1;
    }

    public static String[] getGames(String line, int numGames){
        String[] games = new String[numGames];

        int startingIndex = line.indexOf(":") + 2;

        String currentGame= "  ";
        int gameIndex = 0;

        for(int i = startingIndex; i < line.length(); i++){
            char check = line.charAt(i);

            if(check != ';'){
                currentGame += check + "";
            } else {
                games[gameIndex] = currentGame;
                gameIndex++;
                currentGame = " ";
            }
        }

        games[gameIndex] = currentGame;

        return games;
    }

    public static boolean checkPossibility(String[] games){
        String[] colorNames = {"red", "green", "blue"};

        int[] colorMaxes = {0, 0, 0};

        for(String game : games){

            for(int i = 0; i < colorNames.length; i++){

                String color = colorNames[i];

                int colorIndex = game.indexOf(color);

                if(colorIndex != -1){
                    String amountRaw = game.substring(colorIndex - 3, colorIndex - 1);
                    if(amountRaw.charAt(0) == ' '){
                        amountRaw = amountRaw.replace(" ", "");
                    }

                    int amount = Integer.parseInt(amountRaw);

                    if(amount > colorMaxes[i]){
                        colorMaxes[i] = amount;
                    }
                }
            }
        }

        return colorMaxes[0] <= 12 && colorMaxes[1] <= 13 && colorMaxes[2] <= 14;
    }

    public static int[] getMax(String[] games){
        int[] colorMaxes = {0,0,0};

        String[] colorNames = {"red", "green", "blue"};

        for(String game : games){

            for(int i = 0; i < colorNames.length; i++){

                String color = colorNames[i];

                int colorIndex = game.indexOf(color);

                if(colorIndex != -1){
                    String amountRaw = game.substring(colorIndex - 3, colorIndex - 1);
                    if(amountRaw.charAt(0) == ' '){
                        amountRaw = amountRaw.replace(" ", "");
                    }

                    int amount = Integer.parseInt(amountRaw);

                    if(amount > colorMaxes[i]){
                        colorMaxes[i] = amount;
                    }
                }
            }
        }

        return colorMaxes;
    }
}
