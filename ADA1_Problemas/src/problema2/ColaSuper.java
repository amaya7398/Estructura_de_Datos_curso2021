import java.util.LinkedList;
import java.util.Queue;

public class ColaSuper {
    private Queue<Cliente> cajaAbierta = new LinkedList<>();
    private static int cajas=0;
    private int numCaja;

    private static int tiempoEsperar(Cliente clienteActual){
        int espera = 0;
        //Cliente clienteActual = cajaAbierta.poll();
        espera = clienteActual.getChico()*250 +
                 clienteActual.getMediano()*500 +
                 clienteActual.getGrande()*1000;
        return espera/1000;
    }
    
    private static void esperando(int espera){
        try {
            Thread.sleep((long)espera*1000);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public ColaSuper(){
        cajas++;
        this.numCaja = cajas;
        System.out.println("Bievenidos. Caja #"+numCaja+" abierta...");
    }

    public void nuevoCliente(Cliente cliente){
        this.cajaAbierta.offer(cliente);
    }

    public void atenderClientes(){
        System.out.println("\nCaja #" + this.numCaja);
        while(this.cajaAbierta.peek() != null){
            Cliente clienteActual = cajaAbierta.poll();
            int espera = tiempoEsperar(clienteActual);
            System.out.println("Atendiendo al cliente... objetos: [ch:"
                                 +clienteActual.getChico()+", md:"+ clienteActual.getMediano()+", gd:"+ clienteActual.getGrande()
                                 +"]. Tiempo a esperar: "+ espera+" segundos");
            esperando(espera);
        }
        System.out.println("## Cliente ha pagado con éxito. Se hizo acabado así, né");
    }

    public static void main(String[] args) {
        
    }



}
