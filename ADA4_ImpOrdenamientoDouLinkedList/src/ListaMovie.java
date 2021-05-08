import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ListaMovie {
    private static int maxMovies = 50; // < here and line 40, if u dont want a limit, erase it. (IN  loadFila())
    private static File csvFileMovies = null ;
    private static String csvMoviesOut = null;
    private static File sortedMoviesOut = null ;
    private static MovieDoublyLinkedList list = new MovieDoublyLinkedList();

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
    }

    private static void loadMovies() {
        String splitBy = ",";
        String line;
        boolean firstLine = true;
        int cont=0;
        
        //reader.close(); finally => try-catch-finally ==> try-with-resources
        try(BufferedReader reader = new BufferedReader(new FileReader(csvFileMovies))) {
            System.out.println("Loading...");
            while( ((line = reader.readLine()) != null) && (cont < maxMovies)  ){  //<== change value "10"
                //skip header
                if(firstLine){
                    firstLine = false;
                    continue;
                } 
                String[] raw = line.split(splitBy);
                Movie newMovie = createMovie(raw);
                list.insertLast(newMovie);
                cont++;
            }
            System.out.println(cont+" movies loaded"); 
            System.out.println("LOAD DONE\n");
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

    public static void show(boolean option){
        preview(option);        
    }

    private static void preview(boolean option ){
        System.out.println("PREVIEW:");
        if(option)      list.displayForward(5);
        else            list.displayBackward(5);
        System.out.println("...");
    }

    public static void persistirMovies(boolean side){
        String line = null;
        
        try(FileWriter fichero = new FileWriter(csvMoviesOut)) {
            MovieDoublyLink  current = null;
            
            if(sortedMoviesOut.createNewFile())    sortedMoviesOut.mkdirs(); //Just create fichero.csv
            // Lower to Higher
            if(side){
                System.out.println("Persevere ... -/+");
                
                current = list.firstLinked(); //left to right
                while(current != null){
                    line = createLine(current.getMovie());
                    fichero.write(line);
                    current = current.next;
                }
            } else {    // Higher to Lower
                System.out.println("Persevere ... +/-");
                
                current = list.lastLinked(); //right to left
                while(current != null){
                    line = createLine(current.getMovie());
                    fichero.write(line);
                    current = current.previous;
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

    //Sorting then persistir Movies =>  moviesordenado.csv
    public static void sorting(boolean option, boolean lowToHigh){
        SelectionSort.sortingBy(list, option, lowToHigh);
    }

    //##############################################################################################
    //############### BINARY SEARCH, COULD BE ANOTHER CLASS ########################################
    //##############################################################################################

    public static Movie binarySearch(int data){
        MovieDoublyLink first = list.firstLinked();
        MovieDoublyLink last = list.lastLinked();
        
        Movie movie;
        movie = search(list, data,first, last);
        return movie;
    }

    private static Movie search(MovieDoublyLinkedList list, int data ,MovieDoublyLink first, MovieDoublyLink last) {
        MovieDoublyLink mid = mid(first, last);

        try {
            if (data == mid.getMovie().getId())          return mid.getMovie();
            //if (last == first)                           return new Movie(); //Basically NULL
            if (data > mid.getMovie().getId())           return search(list, data, mid.next, last);
            if (data < mid.getMovie().getId())           return search(list, data, first, mid);
        } catch (NullPointerException e){
            System.out.println("\n#### NO MOVIE ####");
        }
        return new Movie();
    }

    private static MovieDoublyLink mid(MovieDoublyLink first, MovieDoublyLink last){
        if (first == null) 
            return null; 
        MovieDoublyLink slow = first; 
        MovieDoublyLink fast = null ; // first.next; 
        if(first.next != null){
            fast = first.next; 
        } else {
            fast = first; 
        }
        
        while (fast != last) { 
        fast = fast.next; 
            if (fast != last) { 
                slow = slow.next; 
                fast = fast.next; 
            } 
        } 
    return slow;
    }
}
