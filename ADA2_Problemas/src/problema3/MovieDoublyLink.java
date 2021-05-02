public class MovieDoublyLink {
    public Movie dData; 
    public MovieDoublyLink next; 
    public MovieDoublyLink previous; 

    public MovieDoublyLink(Movie d) { 
        dData = d; 
    }

    public void displayLink() { 
        System.out.print(dData + ","); 
    }
} 