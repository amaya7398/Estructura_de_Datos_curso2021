public class Movie {
    private long   id;          //0
    private String title;       //1
    private int    duration;    //2
    private String color;       //3
    private String language;    //4
    private String country;     //5
    private String rating;      //6    
    private long   budget;      //7
    private int    year;        //8
    private double imdbScore;   //9
    private String aspectRatio; //10
    private String imdbLink;    //11

    public Movie(long id, String title, int duration, String color, String language, String country, String rating,
            long budget, int year, double imdbScore, String aspectRatio, String imdbLink) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.color = color;
        this.language = language;
        this.country = country;
        this.rating = rating;
        this.budget = budget;
        this.year = year;
        this.imdbScore = imdbScore;
        this.aspectRatio = aspectRatio;
        this.imdbLink = imdbLink;
    }

    public long     getId(){                                return id;}
    public void     setId(          long    id){            this.id = id;}
    public String   getTitle(){                             return title;}
    public void     setTitle(       String  title){         this.title = title;}
    public int      getDuration(){                          return duration;}
    public void     setDuration(    int     duration){      this.duration = duration;}
    public String   getColor(){                             return color;}
    public void     setColor(       String  color){         this.color = color;}
    public String   getLanguage(){                          return language;}
    public void     setLanguage(    String  language){      this.language = language;}
    public String   getCountry(){                           return country;}
    public void     setCountry(     String  country){       this.country = country;}
    public String   getRating(){                            return rating;}
    public void     setRating(      String  rating){        this.rating = rating;}
    public long     getBudget(){                            return budget;}
    public void     setBudget(      long    budget){        this.budget = budget;}
    public int      getYear(){                              return year;}
    public void     setYear(        int     year){          this.year = year;}
    public double   getImdbScore(){                         return imdbScore;}
    public void     setImdbScore(   double  imdbScore){     this.imdbScore = imdbScore;}
    public String   getAspectRatio(){                       return aspectRatio;}
    public void     setAspectRatio( String  aspectRatio){   this.aspectRatio = aspectRatio;}
    public String   getImdbLink(){                          return imdbLink;}
    public void     setImdbLink(    String  imdbLink){      this.imdbLink = imdbLink;}

    @Override
    public String toString() {
        return "Movie [aspectRatio=" + aspectRatio + ", budget=" + budget + ", color=" + color + ", country=" + country
                + ", duration=" + duration + ", id=" + id + ", imdbLink=" + imdbLink + ", imdbScore=" + imdbScore
                + ", language=" + language + ", rating=" + rating + ", title=" + title + ", year=" + year + "]";
    }
}