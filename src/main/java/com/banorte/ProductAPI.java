package com.banorte;

import com.banorte.entity.Product;
import com.banorte.repositories.ProductRepository;
import jakarta.ws.rs.*;
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
        return productRepository.listProduct();
    }

    @GET
    @Path("/{Id}")
    public Product getById(@PathParam("Id") Long id){
        return productRepository.findProductById(id);
    }

    @POST
    public Response add(Product product){
        productRepository.createProduct(product);
        return Response.ok().build();
    }

    @DELETE
    public Response delete(Product product){
        productRepository.deleteProduct(product);
        return Response.ok().build();
    }

    @PUT
    public Response update(Product p) {
        Product product = productRepository.findProductById(p.getId());
        product.setCode(p.getCode());
        product.setName(p.getName());
        product.setDescription(p.getDescription());
        productRepository.updateProduct(product);
        return Response.ok().build();
    }
}
