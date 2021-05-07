public class MovieDoublyLink {
    public Movie dData; 
    public MovieDoublyLink next; 
    public MovieDoublyLink previous; 

    public MovieDoublyLink(Movie d) { 
        dData = d; 
    }

    public Movie getMovie(){
        return dData;
    }

    public void displayLink() { 
        System.out.print(dData); 
    }

    @Override
    public String toString() {
        return "<=[previous="  + ", dData=" + dData + ", next="  + "]=>";
    }

    
} 