public class DELink {
    public Double dData;
    public DELink next; 
    
    public DELink(){ }
    public DELink(double dd) {
        dData = dd;
    }

    public Double getData(){return dData;}
    public void setData(Double data){this.dData = data;}

    public void displayLink() {
        System.out.print("{" + dData + "} ");   }
    
    @Override
    public String toString() {
        return "[{dData=" + dData + "}, next=> " + next + "]";  }
    
}