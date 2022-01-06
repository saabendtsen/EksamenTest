package dtos;

import java.util.ArrayList;
import java.util.List;

public class MovieDTO {


    private String id;
    private String movieName;
    private List<String> placersToWatch;

    private String year;
    private String poster;
    private String genre;
    private String rated;
    private String runtime;
    private String imdbRating;
    private String boxOffices;


    public MovieDTO(String id, String movieName, List<String> placersToWatch) {
        this.id = id;
        this.movieName = movieName;
        this.placersToWatch = placersToWatch;
    }

    public MovieDTO(String imdbId) {
        this.id = imdbId;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public List<String> getPlacersToWatch() {
        return placersToWatch;
    }

    @Override
    public String toString() {
        return "MovieDTO{" +
                "placersToWatch=" + placersToWatch +
                '}';
    }

    public void setPlacersToWatch(List<String> placersToWatch) {
        this.placersToWatch = placersToWatch;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getBoxOffices() {
        return boxOffices;
    }

    public void setBoxOffices(String boxOffices) {
        this.boxOffices = boxOffices;
    }

    public void addPlacesToWatch(String location){
        if(this.placersToWatch == null){
            this.placersToWatch = new ArrayList<>();
        }
        this.placersToWatch.add(location);
    }
}
