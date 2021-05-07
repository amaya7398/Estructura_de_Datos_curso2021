import java.util.Scanner;

public class Main {
    public Main(){
        System.out.println("hola mundo");
    }

    public static void main(String[] args) {    //Movie.csv moviesOrdenado.csv
        Scanner scanner = new Scanner(System.in);
        int option;
        long timeInit, timeEnd, time;

        ListaMovie unu = new ListaMovie(args[0], args[1]);

        do {
            time = 0;
            System.out.println("\n[1] Binary Search \n[2] Sort by ID \n[3] Sort by title \n[4] Show list -/+ \n[5] Show list +/- \n[0] Leave");
            option = scanner.nextInt();
            switch(option){
                case 0:
                    System.out.println("grax");
                    break;
                case 1:
                    ListaMovie.sorting(true); //SORt BY ID
                    System.out.println("\nBinary search...\nInsert ID:");
                    int dataId = scanner.nextInt();
                    timeInit = System.currentTimeMillis();
                        Movie movie = ListaMovie.binarySearch(dataId);
                        System.out.println(movie);
                    timeEnd = System.currentTimeMillis();
                    time = timeEnd - timeInit;
                    break;
                case 2:
                    System.out.println("\nSORTING BY ID ...");
                    timeInit = System.currentTimeMillis();
                        ListaMovie.sorting(true);   //True -> SortByID
                    timeEnd = System.currentTimeMillis();
                    time = timeEnd - timeInit;
                    ListaMovie.persistirMovies(true);
                    break;
                case 3:
                    System.out.println("\nSORTING BY TITTLE ...");
                    timeInit = System.currentTimeMillis();
                        ListaMovie.sorting(false);  //False -> SortByTittle
                    timeEnd = System.currentTimeMillis();
                    time = timeEnd - timeInit;
                    ListaMovie.persistirMovies(true);
                    break;
                case 4:
                    System.out.println("\n###################\n# MOVIES LIST -/+ #\n################################################################################################################################################################################################################################");
                    ListaMovie.show(true);  //True -/+
                    System.out.println("################################################################################################################################################################################################################################");
                    ListaMovie.persistirMovies(true);
                    break;
                case 5:
                    System.out.println("\n###################\n# MOVIES LIST +/- #\n################################################################################################################################################################################################################################");
                    ListaMovie.show(false); //false +/-
                    System.out.println("################################################################################################################################################################################################################################");
                    ListaMovie.persistirMovies(false);
                    break;
                default:
                    System.out.println("\nERROR!\n");
            }
            if(option>0 && option<4)    System.out.println("Execution time in milliseconds: "+ time);
        } while (option != 0);
        scanner.close();
    }
}
