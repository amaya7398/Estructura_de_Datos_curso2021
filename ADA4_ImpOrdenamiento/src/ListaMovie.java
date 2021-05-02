import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

public class ListaMovie {
    //private static Movie[] lista;
    private static int maxMovies;
    private static List<Movie> lista;
    private static final File csvFileMovies = new File("Movie.csv");

    ListaMovie(){
        loadMovie();
    }



    private static void loadMovie() {
        String splitBy = ",";
        String line;

        //reader.close(); finally => try-catch-finally || try-with-resources
        try(BufferedReader reader = new BufferedReader(new FileReader(csvFileMovies))) {
            //BufferedReader reader = new BufferedReader(new FileReader(csvFileMovies));
            while( ((line = reader.readLine()) != null) && maxMovies <= 1000  ){
                String[] raw = line.split(splitBy);
                
                Movie newMovie = new Movie( Long.parseLong(raw[0]), raw[1], Integer.parseInt(raw[2]), raw[3], raw[4],
                                            raw[5], raw[6], Long.parseLong(raw[7]), Integer.parseInt(raw[8]),
                                            Double.parseDouble(raw[9]), raw[10], raw[11]);
                lista.add(newMovie);
                maxMovies++;
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}
