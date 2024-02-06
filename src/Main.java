import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        ArrayList<SimpleMovie> movies = MovieDatabaseBuilder.getMovieDB("src/movie_data");
        //addBaconActors(movies); creates bacon_actors file;
        ArrayList<String[]> baconActors = MovieDatabaseBuilder.getActor("src/bacon_actors");
        System.out.print("Enter an actor's name or (q) to quit: ");
        Scanner s = new Scanner(System.in);
        String response = s.nextLine();
        int baconNum = 0;
        while(!response.equals("q"))
        {

            System.out.print("Enter an actor's name or (q) to quit: ");
            response = s.nextLine();
        }

    }

    public static void addBaconActors(ArrayList<SimpleMovie> movies)
    {
        File f;
        FileWriter fw;
        ArrayList<String> tempActors = new ArrayList<String>();
        tempActors.add("Kevin Bacon");
        try {
            f = new File("src/bacon_actors");
            fw = new FileWriter(f);
            for(SimpleMovie movie : movies)
            {
                ArrayList<String> actors = movie.getActors();
                if(actors.contains("Kevin Bacon"))
                {
                    for(String actor : actors)
                    {
                        if(!tempActors.contains(actor)) {

                            String title = movie.getTitle();
                            fw.write(actor + "|"+ title + "\n");
                            tempActors.add(actor);
                        }
                    }
                }
            }
        }
        catch (IOException ioe) {
            System.out.println("Writing file failed");
        }
    }

    public static boolean arrayContains(String key, ArrayList<String[]> list)
    {
        for(String[] info : list)
        {
            if(info[0].equals(key)) return true;
        }
        return false;
    }

}