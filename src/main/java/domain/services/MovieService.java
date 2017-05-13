package domain.services;

import domain.Comment;
import domain.Movie;

import java.util.List;
import java.util.ArrayList;

public class MovieService {
    private static List<Movie> db = new ArrayList<Movie>();
    private static int currentId = 1;
    
    public List<Movie> getAllMovies() {
        return db;
    }
    
    public Movie getMovie(int id){
        for(Movie movie : db) {
            if(movie.getId()==id)
                return movie;
        }
        return null;
    }
    
    public void addMovie(Movie movie) { 
        movie.setId(++currentId);
        db.add(movie);
    }
    
    public void updateMovie(Movie movie) {
        for(Movie _movie : db) {
            if(movie.getId()==movie.getId()){
                movie.setTitle(_movie.getTitle());
                movie.setDirector(_movie.getDirector());
                movie.setYear(_movie.getYear());
            }
        }
    }
    
    public void deleteMovie(Movie movie) {
        db.remove(movie);
    }
    
    public double getMark(int id){
        double mark = 0, commentsQuota = 1;
        for(Movie movie : db) {
            if (movie.getId()==id) {
                 commentsQuota = movie.getComments().size();
                for(Comment comment : movie.getComments()) {
                    mark += comment.getMark();
                }
            }
        }
        return mark/commentsQuota;
    }
}
