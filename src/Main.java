import java.lang.reflect.Array;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        ArrayList<SimpleMovie> movies = MovieDatabaseBuilder.getMovieDB("src/movie_data");
        //addBaconActors(movies); //creates bacon_actors file;
        ArrayList<String[]> baconActors = MovieDatabaseBuilder.getActor("src/bacon_actors");
        ArrayList<String[]> baconActorsLevel2 = MovieDatabaseBuilder.getActor("src/bacon_level2");
        baconLvl2(movies,baconActorsLevel2);
        System.out.print("Enter an actor's name or (q) to quit: ");
        Scanner s = new Scanner(System.in);
        String response = s.nextLine();
        while(!response.equals("q"))
        {
            System.out.print("Enter an actor's name or (q) to quit: ");
            response = s.nextLine();
            int test1 = arrayContains(response,baconActors);
            if(test1>-1)
            {
                String movie = baconActors.get(test1)[1];
                if(baconActors.get(test1).length==3) movie = baconActors.get(test1)[2];
                System.out.println("Actor name: " + response);
                System.out.println(response + "-->" + movie + "--> Kevin Bacon");
            }
            else{
                int test2 = arrayContains(response,baconActorsLevel2);
                if(test2>-1)
                {
                    String movie = baconActorsLevel2.get(test2)[1];
                    String prevActor = baconActorsLevel2.get(test2)[2];
                    String prevMovie = baconActors.get(test1)[1];
                    if(baconActors.get(test1).length==3) movie = baconActors.get(test1)[2];
                    if(baconActors.get(test1).length==4)
                    {
                        movie = baconActorsLevel2.get(test2)[2];
                        prevActor = baconActorsLevel2.get(test2)[3];
                    }
                    System.out.println("Actor name: " + response);
                    System.out.println(response + "-->" + movie + "-->"+ prevActor + "-->");
                }
            }

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

                            System.out.println("Y");
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

    public static void baconLvl2(ArrayList<SimpleMovie> movies, ArrayList<String[]> actorList)
    {
        File f;
        FileWriter fw;
        int count =0;
        ArrayList<String> tempActors = new ArrayList<String>();
        tempActors.add("Kevin Bacon");
        try {
            f = new File("src/bacon_level3");
            fw = new FileWriter(f);
            for(String[] actorBacon : actorList)
            {
                String actorName = actorBacon[0];
                if(actorName.contains("(actor)")) actorName = actorBacon[1];
                for(SimpleMovie movie : movies)
                {
                    ArrayList<String> actors = movie.getActors();
                    if(actors.contains(actorName))
                    {
                        for(String actor : actors)
                        {
                            if(!tempActors.contains(actor) && arrayContains(actor, actorList)<0) {
                                System.out.println(count);
                                count++;
                                String title = movie.getTitle();
                                fw.write(actor + "|"+ title + "|"+actorName+"\n");
                                tempActors.add(actor);
                            }
                        }
                    }
                }
            }
        }
        catch (IOException ioe) {
            System.out.println("Writing file failed");
        }
    }

    public static int arrayContains(String key, ArrayList<String[]> list)
    {
        key = key.toLowerCase();
        int index =0;
        for(String[] info : list)
        {
            int value =0;
            if(info[0].contains("(actor)")) value=1;
            if(info[value].toLowerCase().equals(key)) return index;
            index++;
        }
        return -1;
    }

}