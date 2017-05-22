package rest;

import domain.Product;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
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
@Stateless
public class ProductResources {    
    @PersistenceContext
    EntityManager em;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getAllProducts() {
        return em.createNamedQuery("product.all", Product.class).getResultList();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProduct(Product product) {
        em.persist(product);
        return Response.ok(product.getId()).build();
    }
    
    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") int id) {
        Product result;
        try {
            result = em.createNamedQuery("product.id", Product.class)
                    .setParameter("productId", id)
                    .getSingleResult();
        } catch(NoResultException n) {
            return Response.status(404).build();
        }
        return Response.ok(result).build();
    }
    
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, Product p) {
        Product result;
        try {
            result = em.createNamedQuery("product.id", Product.class)
                    .setParameter("productId", id)
                    .getSingleResult();
        } catch(NoResultException n) {
            return Response.status(404).build();
        }
        result.setCategory(p.getCategory());
        result.setName(p.getName());
        result.setPrice(p.getPrice());
        return Response.ok(result).build();
    }
    
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        Product result;
        try {
            result = em.createNamedQuery("product.id", Product.class)
                    .setParameter("productId", id)
                    .getSingleResult();
        } catch(NoResultException n) {
            return Response.status(404).build();
        }
        em.remove(result);
        return Response.ok().build();
    }
    
    @GET
    @Path("/category/{categoryId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Product> getCategory(@PathParam("categoryId") int id, Product p) {
        return em.createNamedQuery("product.category", Product.class)
                .setParameter("categoryId", id)
                .getResultList();
    }
}
    
