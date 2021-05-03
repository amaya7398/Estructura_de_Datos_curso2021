import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ListaMovie {
    //private static Movie[] lista;
    private static int maxMovies;
    private static ArrayList<Movie> list = new ArrayList<>();
    private static final File csvFileMovies = new File("Movie.csv");

    ListaMovie(){
        loadMovie();
        preview();
        
    }

    private static void loadMovie() {
        String splitBy = ",";
        String line;
        boolean firstLine = true;

        //reader.close(); finally => try-catch-finally || try-with-resources
        try(BufferedReader reader = new BufferedReader(new FileReader(csvFileMovies))) {
            //BufferedReader reader = new BufferedReader(new FileReader(csvFileMovies));
            System.out.println("Cargando...\n");
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
            e.printStackTrace();
        }
    }

    private static Movie createMovie(String[] metadata){
        String vacio = "";
        
        //If any tokens is empty it should fill with null  OPERADOR TERNARIO
        int id             = (metadata[0]).equals(vacio)? 0 : Integer.parseInt(metadata[0]);
        String title       = (metadata[1]).equals(vacio)? "N/A" : metadata[1] ;
        int duration       = (metadata[2]).equals(vacio)? 0 : Integer.parseInt(metadata[2]) ;
        String color       = (metadata[3]).equals(vacio)? "N/A" : metadata[3] ;
        String language    = (metadata[4]).equals(vacio)? "N/A" : metadata[4] ;
        String country     = (metadata[5]).equals(vacio)? "N/A" : metadata[5] ;
        String rating      = (metadata[6]).equals(vacio)? "N/A" : metadata[6] ;
        long budget        = (metadata[7]).equals(vacio)? 0 : Long.parseLong(metadata[7]);
        int year           = (metadata[8]).equals(vacio)? 0 : Integer.parseInt(metadata[8]);
        double imdbScore   = (metadata[9]).equals(vacio)? 0 : Double.parseDouble(metadata[9]);
        String aspectRatio = (metadata[10]).equals(vacio)? "N/A" : metadata[10] ;
        String imdbLink    = (metadata[11]).equals(vacio)? "N/A" : metadata[11] ;

        return new Movie(id, title, duration, color, language, country, rating, budget, year, imdbScore, aspectRatio, imdbLink);
    }

    private static void persistirMovies(){
        System.out.println("Ordenando ...");
        System.out.println("CSV_ordenado.csv");
    }

    private static void preview(){
        for(int i=0 ; i<5 ; i++)
            System.out.println(list.get(i));
        System.out.println("...");
    }

    public static void show(boolean option){
        if(option){
            for (Movie movie : list)
                System.out.println(movie);
        } else {
            for (int i = list.size()-1 ; i>=0; i--)
                System.out.println(list.get(i));
        }
    }


    //Sorting then persistir Movies =>  moviesordenado.csv
    public static void sorting(boolean option){
        if(option){
            SelectionSort.sortByID(list);
        } else {
            SelectionSort.sortByTittle(list);
        }
        
        persistirMovies();
    }

    public static Movie binarySearch(int data){
        int index = search(list, data, 0, list.size());
        return list.get(index);
    }

    private static int search(ArrayList<Movie> list, int data, int first, int last) {
        //int mid = (first + last) / 2;
        int mid = (int) Math.floor((first + last) / 2);
        //
        if (last < first)                       return -1;
        if (data == list.get(mid).getId())      return mid;
        if (data < list.get(mid).getId())       return search(list, data, first, mid - 1);
        if (data > list.get(mid).getId())       return search(list, data, mid + 1, last);
        return -1;
    }
}
