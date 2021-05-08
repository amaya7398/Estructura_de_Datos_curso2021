import java.util.Scanner;

public class Main {
    public Main(){
        System.out.println("hola mundo");
    }

    public static void main(String[] args) {    //Movie.csv moviesOrdenado.csv
        Scanner scanner = new Scanner(System.in);
        int L2H;
        boolean lowToHighBool;
        int option;
        long timeInit, timeEnd, time;

        if(args.length == 0){     ListaMovie unu = new ListaMovie("Movie.csv", "moviesOrdenado.csv");}
        if(args.length == 1){     ListaMovie unu = new ListaMovie(args[0], "moviesOrdenado.csv"); }
        if(args.length >= 2){     ListaMovie unu = new ListaMovie(args[0], args[1]); }

        do {
            time = 0;
            System.out.println("\n[1] Binary Search \n[2] Sort by ID \n[3] Sort by title \n[0] Leave"); // \n[4] Show list -/+ \n[5] Show list +/- \n[0] Leave");
            option = scanner.nextInt();

            switch(option){
                case 0:
                    System.out.println("Todos los derechos reservados");
                    break;
                case 1:
                    ListaMovie.sorting(true, true); //SORt BY ID
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
                    System.out.println("[1] -/+ \n[0] +/-");
                    L2H = scanner.nextInt();
                    if(L2H == 1)    lowToHighBool = true;
                    else            lowToHighBool = false;
                    
                    timeInit = System.currentTimeMillis();
                        ListaMovie.sorting(true, lowToHighBool);   //True -> SortByID
                    timeEnd = System.currentTimeMillis();
                    
                    ListaMovie.persistirMovies(lowToHighBool);
                    
                    time = timeEnd - timeInit;
                    break;
                case 3:
                    System.out.println("\nSORTING BY TITTLE ...");
                    System.out.println("[1] -/+ \n[0] +/-");
                    L2H = scanner.nextInt();
                    if(L2H == 1)    lowToHighBool = true;
                    else            lowToHighBool = false;
                    
                    timeInit = System.currentTimeMillis();
                        ListaMovie.sorting(false, lowToHighBool);  //False -> SortByTittle
                    timeEnd = System.currentTimeMillis();
                    
                    time = timeEnd - timeInit;
                    ListaMovie.persistirMovies(lowToHighBool);
                    break;
                default:
                    System.out.println("\nERROR!\n");
            }
            if(option>=0 && option<4)    System.out.println("Execution time in milliseconds: "+ time);
        } while (option != 0);
        scanner.close();
    }
}

/*case 4:
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
                    break;*/
