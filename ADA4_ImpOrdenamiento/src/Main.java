import java.util.Scanner;

public class Main {
    public Main(){
        System.out.println("hola mundo");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option;

        ListaMovie unu = new ListaMovie();
        //unu.show();
        do {
            System.out.println("\n[1] Binary Search \n[2] Sort by ID \n[3] Sort by title \n[4] Show list -/+ \n[5] Show list +/- \n[0] Leave");
            option = scanner.nextInt();
            switch(option){
                case 0:
                    System.out.println("grax");
                    break;
                case 1:
                    System.out.println("\nBinary search...\nInsert ID:");
                    int dataId = scanner.nextInt();
                    //greater than 0, and less than the la
                    Movie movie = ListaMovie.binarySearch(dataId);
                    System.out.println(movie);
                    break;
                case 2:
                    System.out.println("\nSORTING BY ID ...");
                    ListaMovie.sorting(true);   //True -> SortByID
                    break;
                case 3:
                    System.out.println("\nSORTING BY TITTLE ...");
                    ListaMovie.sorting(false);  //False -> SortByTittle
                    break;
                case 4:
                    System.out.println("###################\n# MOVIES LIST -/+ #\n################################################################################################################################################################################################################################");
                    ListaMovie.show(true);  //True -/+
                    System.out.println("################################################################################################################################################################################################################################");
                    break;
                case 5:
                    System.out.println("###################\n# MOVIES LIST +/- #\n################################################################################################################################################################################################################################");
                    ListaMovie.show(false); //false +/-
                    System.out.println("################################################################################################################################################################################################################################");
                    break;
                default:
                    System.out.println("\nERROR!\n");
            }

            
        } while (option != 0);
    }
}
