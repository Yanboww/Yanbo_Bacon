import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        //ArrayList<SimpleMovie> movies = MovieDatabaseBuilder.getMovieDB("src/movie_data");
        ArrayList<String[]> baconActors = MovieDatabaseBuilder.getActor("src/bacon_actors");
        Scanner s = new Scanner(System.in);
        String response = "wwwww";
        while(!response.equals("q"))
        {
            System.out.println();
            System.out.print("Enter an actor's name or (q) to quit: ");
            response = s.nextLine();
            int test1 = arrayContains(response,baconActors);
            if(test1>-1)
            {
                String movie = baconActors.get(test1)[1];
                if(baconActors.get(test1).length==3) movie = baconActors.get(test1)[2];
                System.out.println();
                System.out.println("Actor name: " + response);
                System.out.println(response + " --> " + movie + " --> Kevin Bacon");
                System.out.println("Bacon Number: 1");
            }
            else{
                ArrayList<String[]> baconActorsLevel2 = MovieDatabaseBuilder.getActor("src/bacon_level2");
                int test2 = arrayContains(response,baconActorsLevel2);
                if(test2>-1)
                {
                    String movie = baconActorsLevel2.get(test2)[1];
                    String prevActor = baconActorsLevel2.get(test2)[2];
                    if(baconActorsLevel2.get(test2).length==4)
                    {
                        movie = baconActorsLevel2.get(test2)[2];
                        prevActor = baconActorsLevel2.get(test2)[3];
                    }
                    test1 = arrayContains(prevActor,baconActors);
                    String prevMovie = baconActors.get(test1)[1];
                    if(baconActors.get(test1).length==3) prevMovie = baconActors.get(test1)[2];
                    System.out.println();
                    System.out.println("Actor name: " + response);
                    System.out.println(response + " --> " + movie + " --> "+ prevActor + " --> " + prevMovie + " --> " + "Kevin Bacon");
                    System.out.println("Bacon Number: 2");
                }
                else{
                    ArrayList<String[]> baconActorsLevel3 = MovieDatabaseBuilder.getActor("src/bacon_level3");
                    int test3 = arrayContains(response,baconActorsLevel3);
                    ArrayList<String[]> baconActorsLevel3Part2 = new ArrayList<String[]>();
                    if(test3==-1)
                    {
                        baconActorsLevel3Part2 = MovieDatabaseBuilder.getActor("src/bacon_level3_part2");
                        test3 = arrayContains(response,baconActorsLevel3Part2);
                        baconActorsLevel3 = baconActorsLevel3Part2;
                    }
                    if(test3>-1)
                    {
                        String movie = baconActorsLevel3.get(test3)[1];
                        String prevActor = baconActorsLevel3.get(test3)[2];
                        if(baconActorsLevel3.get(test3).length==4)
                        {
                            movie = baconActorsLevel3.get(test3)[2];
                            prevActor = baconActorsLevel3.get(test3)[3];
                        }
                        test2 = arrayContains(prevActor,baconActorsLevel2);
                        String prevMovie = baconActorsLevel2.get(test2)[1];
                        String prevPrevActor = baconActorsLevel2.get(test2)[2];
                        if(baconActorsLevel2.get(test2).length==4)
                        {
                            prevMovie = baconActorsLevel2.get(test2)[2];
                            prevPrevActor = baconActorsLevel2.get(test2)[3];
                        }
                        test1 = arrayContains(prevPrevActor,baconActors);
                        String prevPrevMovie = baconActors.get(test1)[1];
                        if(baconActors.get(test1).length==3) prevPrevMovie = baconActors.get(test1)[2];
                        System.out.println();
                        System.out.println("Actor Name: " + response);
                        System.out.println(response + " --> " + movie + " --> " + prevActor+ " --> " + prevMovie + " --> " + prevPrevActor + " --> " + prevPrevMovie+" --> " + " Kevin Bacon");
                        System.out.println("Bacon number: 3");
                    }
                    else{
                        if(response.toLowerCase().equals("kevin bacon")) System.out.println("Bacon Number: 0");
                        else System.out.println("Bacon number is over 3");
                    }
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
        System.out.println(actorList.size());
        //System.out.println(Arrays.toString(actorList.get(26519)));
        List<String[]> actorBaconSub = actorList.subList(26519,actorList.size());
        File f;
        FileWriter fw;
        int count =0;
        ArrayList<String> tempActors = new ArrayList<String>();
        tempActors.add("Kevin Bacon");
        try {
            f = new File("src/bacon_level3_part2");
            fw = new FileWriter(f);
            for(String[] actorBacon : actorBaconSub)
            {
                String actorName = actorBacon[0];
                System.out.println(actorName);
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
            if(info[0].contains("(")) value=1;
            if(info[value].toLowerCase().equals(key)) return index;
            index++;
        }
        return -1;
    }

}