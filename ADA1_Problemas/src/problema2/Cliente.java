public class Cliente {
    private int chico;
    private int mediano;
    private int grande;

    public Cliente(int chico, int mediano, int grande){
        this.chico = chico;
        this.mediano = mediano;
        this.grande = grande;
    }

    public int getChico() {return chico;}
    public void setChico(int chico) {this.chico = chico;}
    
    public int getMediano() {return mediano;}
    public void setMediano(int mediano) {this.mediano = mediano;}
    
    public int getGrande() {return grande;}
    public void setGrande(int grande) {this.grande = grande;}

    @Override
    public String toString() {
        return "Cliente [chico=" + chico + ", grande=" + grande + ", mediano=" + mediano + "]";
    }
    
    

}
