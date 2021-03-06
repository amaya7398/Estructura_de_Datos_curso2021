import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ListaMovie {
    //private static Movie[] lista;
    private static int maxMovies;
    private static ArrayList<Movie> list = new ArrayList<>();
    private static File csvFileMovies = null ;
    //private static final String csvMoviesOut = "moviesOrdenado.csv";
    private static String csvMoviesOut = null;
    private static File sortedMoviesOut = null ;
    ;

    ListaMovie(String in, String out ){
        try {
            loadFile(in, out);
            loadMovies();
            preview(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void loadFile(String in, String out){
        
        csvFileMovies = new File(in);
        sortedMoviesOut = new File(out);
        csvMoviesOut = out;
        
        /*Scanner scanner = new Scanner(System.in);
        System.out.println("INSERT PATH FILE || [0] : Movie.csv ");
        String file = scanner.next();

        if(file.equals("0")){
            csvFileMovies = new File("Movie.csv");
        } else {
            csvFileMovies = new File(file);
        }*/
        //scanner.close();
    }

    private static void loadMovies() {
        String splitBy = ",";
        String line;
        boolean firstLine = true;
        
        //reader.close(); finally => try-catch-finally ==> try-with-resources
        try(BufferedReader reader = new BufferedReader(new FileReader(csvFileMovies))) {
            //BufferedReader reader = new BufferedReader(new FileReader(csvFileMovies));
            System.out.println("Cargando...");
            while( ((line = reader.readLine()) != null) && maxMovies < 10  ){
                //skip header
                if(firstLine){
                    firstLine = false;
                    continue;
                } 
                String[] raw = line.split(splitBy);
                Movie newMovie = createMovie(raw);
                list.add(newMovie);
                maxMovies++;
            }
            System.out.println("CARGA EXITOSA\n");
        } catch (IOException e) {
            System.out.println("ERROR! ... StackTrace");
            e.printStackTrace();
        }
    }

    private static Movie createMovie(String[] metadata){
        String vacio = "";
        
        //If any token is empty it should fill with null.  "OPERADOR TERNARIO"
        int id             = (metadata[0]).equals(vacio)? 0 : Integer.parseInt(metadata[0]) ;
        String title       = (metadata[1]).equals(vacio)? "N/A" : metadata[1] ;
        int duration       = (metadata[2]).equals(vacio)? 0 : Integer.parseInt(metadata[2]) ;
        String color       = (metadata[3]).equals(vacio)? "N/A" : metadata[3] ;
        String language    = (metadata[4]).equals(vacio)? "N/A" : metadata[4] ;
        String country     = (metadata[5]).equals(vacio)? "N/A" : metadata[5] ;
        String rating      = (metadata[6]).equals(vacio)? "N/A" : metadata[6] ;
        long budget        = (metadata[7]).equals(vacio)? 0 : Long.parseLong(metadata[7]) ;
        int year           = (metadata[8]).equals(vacio)? 0 : Integer.parseInt(metadata[8]) ;
        double imdbScore   = (metadata[9]).equals(vacio)? 0 : Double.parseDouble(metadata[9]) ;
        String aspectRatio = (metadata[10]).equals(vacio)? "N/A" : metadata[10] ;
        String imdbLink    = (metadata[11]).equals(vacio)? "N/A" : metadata[11] ;

        return new Movie(id, title, duration, color, language, country, rating, budget, year, imdbScore, aspectRatio, imdbLink);
    }

    private static void preview(boolean option ){
        System.out.println("PREVIEW:");
        
        if(option){
            for(int i=0 ; i<5 ; i++)
                System.out.println(list.get(i));
        } else {
            for (int i = list.size()-1 ; i>list.size()-6; i--)
                System.out.println(list.get(i));      //SAME
        }
        
        System.out.println("...");
    }

    public static void persistirMovies(boolean side){
        String line = null;
        
        try(FileWriter fichero = new FileWriter(csvMoviesOut)) {
            
            if(sortedMoviesOut.createNewFile())    sortedMoviesOut.mkdirs(); //Just create fichero.csv
            // Lower to Higher
            if(side){
                System.out.println("Persevere ... -/+");
                for(Movie m : list){
                    line = createLine(m);
                    fichero.write(line);
                }
                
            } else {    // Higher to Lower
                System.out.println("Persevere ... +/-");
                for (int i = list.size()-1 ; i>=0; i--){
                    line = createLine(list.get(i));
                    fichero.write(line);
                }
            }
            fichero.flush();
        } catch (Exception e) {
            sortedMoviesOut.mkdirs();
            e.printStackTrace();
        }
    }

    private static String createLine(Movie m){
        return  m.getId()+","+m.getTitle()+","+m.getDuration()+","+m.getColor()+","+
                m.getLanguage()+","+m.getCountry()+","+m.getRating()+","+m.getBudget()+","+
                m.getYear()+","+m.getImdbScore()+","+m.getAspectRatio()+","+m.getImdbLink()+"\n";
    }

    public static void show(boolean option){
        if(option)      preview(option); // -/+
        if(!option)     preview(option);// +/-
        /*
        if(option){
            for (Movie movie : list)
                //System.out.println(movie);  //Just print execution time, compare and swap, CHANGE IT FOR THE "preview()" FUNCTION
        } else {
            for (int i = list.size()-1 ; i>=0; i--)
                //System.out.println(list.get(i));      //SAME
        }*/
    }

    //Sorting then persistir Movies =>  moviesordenado.csv
    public static void sorting(boolean option){
        if(option){
            SelectionSort.sortByID(list);
        } else {
            SelectionSort.sortByTittle(list);
        }
    }

    public static Movie binarySearch(int data){
        int index = search(list, data, 0, list.size());
        Movie movie;
        if (index < 0){
            movie = new Movie();
        } else {
            movie = list.get(index);
        }
        return movie;
    }

    private static int search(ArrayList<Movie> list, int data, int first, int last) {
        //int mid = (first + last) / 2;
        int mid = (int) Math.floor((first + last) / 2);
        //
        if (data == list.get(mid).getId())      return mid;
        if (last <= first)                       return -1;
        if (data < list.get(mid).getId())       return search(list, data, first, mid - 1);
        if (data > list.get(mid).getId())       return search(list, data, mid + 1, last);
        return -1;
    }
}
