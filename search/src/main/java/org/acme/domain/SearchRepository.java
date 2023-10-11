package org.acme.domain;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;

public interface SearchRepository {
    void addProduct(Product product) throws SolrServerException, IOException;
    List<Product> findAll()throws SolrServerException, IOException;
    List<Product> findProductsByKeyword(String keyword)throws SolrServerException, IOException;
    Product findProductById(ProductId productId)throws SolrServerException, IOException;
    List<ProductId> findSimilarProductsOf(ProductId productId) throws SolrServerException, IOException;
}
