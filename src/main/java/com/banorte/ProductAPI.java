package com.banorte;

import com.banorte.entity.Product;
import com.banorte.repositories.ProductRepository;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/product")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductAPI {

    private ProductRepository productRepository;

    @Inject
    public ProductAPI(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GET
    public List<Product> list(){
        return productRepository.findAll();
    }

    @GET
    @Path("/{Id}")
    public Product getById(@PathParam("Id") Long id){
        return productRepository.findById(id).orElse(new Product());
    }

    @POST
    public Response add(Product product){
        productRepository.save(product);
        return Response.ok().build();
    }

    @DELETE
    public Response delete(Product product){
        productRepository.delete(product);
        return Response.ok().build();
    }

    @PUT
    public Response update(Product p) {
        Product product = productRepository.findById(p.getId()).orElse(new Product());
        product.setCode(p.getCode());
        product.setName(p.getName());
        product.setDescription(p.getDescription());
        productRepository.save(product);
        return Response.ok().build();
    }
}
