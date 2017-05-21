package rest;

import domain.Product;
import javax.ws.rs.Path;
import domain.services.ShopService;
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

@Path("/product")
public class ProductResources {
    private ShopService db = new ShopService();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getAllProducts() {
        return db.getAllProducts();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProduct(Product product) {
        db.addProduct(product);
        return Response.ok(product.getId()).build();
    }
    
    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") int id) {
        Product result = db.getProduct(id);
        if(result==null) {
            return Response.status(404).build();
        }
        return Response.ok(result).build();
    }
    
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, Product product) {
        Product result = db.getProduct(id);
        if(result==null) {
            return Response.status(404).build();
        }
        return Response.ok(result).build();
    }
    
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        Product result = db.getProduct(id);
        if(result==null) 
            return Response.status(404).build();
    db.deleteProduct(result);
    return Response.ok().build();
    }
    
    @GET
    @Path("/category/{categoryId}")
    public Response getCategory(@PathParam("categoryId") int id) {
        if(db.getCategory(id)==null) {
            return Response.status(404).build();
        }
        return Response.ok(db.getCategory(id)).build();
    }
}
    
