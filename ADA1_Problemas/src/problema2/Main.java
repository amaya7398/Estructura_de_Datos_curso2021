import java.util.Scanner;


public class Main {
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        //Clientes inicializados, solo para tener algo xd
        Cliente cliente1 = new Cliente(5, 3, 1);    //Cliente( chico, mediano, grande)
        Cliente cliente2 = new Cliente(2, 3, 4);    //tiempo de espera por producto:
        Cliente cliente3 = new Cliente(1, 5, 3);    //ch: 250ms     md: 500ms   gd: 1000ms
        Cliente cliente4 = new Cliente(2, 5, 5);

        ColaSuper caja1 = new ColaSuper();
        ColaSuper caja2 = new ColaSuper();
        ColaSuper caja3 = new ColaSuper();
        ColaSuper caja4 = new ColaSuper();

        caja1.nuevoCliente(cliente1);
        caja2.nuevoCliente(cliente2);
        caja3.nuevoCliente(cliente3);
        caja4.nuevoCliente(cliente4);
    
        while(true){
            System.out.println("\n===================================================");
            System.out.println("Opción?");
            System.out.println("1.-Añadir cliente.\n2.-Mostrar caja\n3.-Salir");
            int opcion = input.nextInt();
            
            switch (opcion) {
                case 1:
                    System.out.println("\nAñadiendo cliente... A cuál caja ir? ");
                    System.out.println("1.-Caja #1.\n2.-Caja #2\n3.-Caja #3\n4.-Caja #4\n5.-Eliminar cliente");
                    opcion = input.nextInt();

                    if(opcion==5) break;    //Salir si "Eliminar cliente"
                    int chico = (int) Math.floor(Math.random()*11);
                    int mediano = (int) Math.floor(Math.random()*11);
                    int grande = (int) Math.floor(Math.random()*11);
                    Cliente nuevoCliente = new Cliente(chico, mediano, grande);
                    
                    if(opcion==1) caja1.nuevoCliente(nuevoCliente);
                    if(opcion==2) caja2.nuevoCliente(nuevoCliente);
                    if(opcion==3) caja3.nuevoCliente(nuevoCliente);
                    if(opcion==4) caja4.nuevoCliente(nuevoCliente);
                    opcion=0;
                    break;
                case 2:
                    System.out.println("\n¿Que numero de caja iniciar?");
                    System.out.println("1.-Caja #1.\n2.-Caja #2\n3.-Caja #3\n4.-Caja #4\n5.-Todas");
                    opcion = input.nextInt();
                    if(opcion==1) caja1.atenderClientes();
                    if(opcion==2) caja2.atenderClientes();
                    if(opcion==3) caja3.atenderClientes();
                    if(opcion==4) caja4.atenderClientes();
                    if(opcion==5){
                        caja1.atenderClientes();
                        caja2.atenderClientes();
                        caja3.atenderClientes();
                        caja4.atenderClientes();
                    }
                    opcion=0;
                    break;
                case 3:
                    System.out.println("\nGracias por visitar Gualmar");
                    input.close();
                    break;
                default:
                    System.out.println("\nNo te estés comiendo el yogurt por los pasillos, te observamos");
                    break;
            }

            if(opcion==3){
                break;
            }
        }
    }
}