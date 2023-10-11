package org.acme.api;

import java.io.IOException;
import java.util.List;

import org.acme.api.DTOs.AddProductDTO;
import org.acme.api.DTOs.FindProductByKeywordDTO;
import org.acme.api.DTOs.FindSimilarProductsOfDTO;
import org.acme.application.services.SearchService;
import org.acme.domain.Product;
import org.acme.domain.ProductId;
import org.apache.solr.client.solrj.SolrServerException;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
@Path("/search")
public class SearchResource {

    @Inject
    SearchService searchService;

    @POST
    @Path("/add-product")
    public void addProduct(AddProductDTO addProductDTO) throws SolrServerException, IOException {
        searchService.indexProduct(addProductDTO.productId(), addProductDTO.description());
    }

    @POST
    @Path("/find-by-keyword")
    public List<Product> findProductByKeyword(FindProductByKeywordDTO findProductByKeywordDTO)throws SolrServerException, IOException{
        return searchService.findProductsByKeyword(findProductByKeywordDTO.keyword());
    }

    @POST
    @Path("/find-by-similarity")
    public List<ProductId> findSimilarProductsOf(FindSimilarProductsOfDTO findProductByKeywordDTO)throws SolrServerException, IOException{
        return searchService.findSimilarProductsOf(findProductByKeywordDTO.productId());
    }
}
