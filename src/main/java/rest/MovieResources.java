package rest;

import domain.Comment;
import domain.Movie;
import javax.ws.rs.Path;
import domain.services.MovieService;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/movie")
public class MovieResources {
    private MovieService db = new MovieService();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movie> getAllMovies() {
        return db.getAllMovies();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addMovie(Movie movie) {
        db.addMovie(movie);
        return Response.ok(movie.getId()).build();
    }
    
    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") int id) {
        Movie result = db.getMovie(id);
        if(result==null) {
            return Response.status(404).build();
        }
        return Response.ok(result).build();
    }
    
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, Movie movie) {
        Movie result = db.getMovie(id);
        if(result==null) {
            return Response.status(404).build();
        }
        return Response.ok(result).build();
    }
    
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        Movie result = db.getMovie(id);
        if(result==null) 
            return Response.status(404).build();
    db.updateMovie(result);
    return Response.ok().build();
    }
    
    @GET
    @Path("{movieId}/comments")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Comment> getComments(@PathParam("movieId") int movieId) {
        Movie result = db.getMovie(movieId);
        if(result==null)
            return null;
        if(result.getComments()==null)
            result.setComments(new ArrayList<>());
        return result.getComments();
    }
    
    @POST
    @Path("{id}/comments")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addComment(@PathParam("id") int movieId, Comment comment) {
        Movie result = db.getMovie(movieId);
        if(result==null)
            return Response.status(404).build();
        if(result.getComments()==null)
            result.setComments(new ArrayList<Comment>());
        result.getComments().add(comment);
        return Response.ok().build();
    }
    
    @GET
    @Path("/{id}/mark")
    public Response getMark(@PathParam("id") int id) {
        double result = db.getMark(id);
        if(result!=0)
            return Response.ok(result).build();
        else
            return Response.status(404).build();
    }
}
    
