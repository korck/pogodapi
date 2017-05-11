package domain;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private int id;
    private String title;
    private String director;
    private int year;
    List<Actor> actors = new ArrayList<>();
    List<Comment> comments = new ArrayList<>();
    
}
