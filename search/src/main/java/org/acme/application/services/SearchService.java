package org.acme.application.services;

import java.io.IOException;
import java.util.List;

import org.acme.domain.Description;
import org.acme.domain.Product;
import org.acme.domain.ProductId;
import org.apache.solr.client.solrj.SolrServerException;

public interface SearchService {
    void indexProduct(ProductId productId, Description description) throws SolrServerException, IOException;
    List<Product> findProductsByKeyword(String keyword) throws SolrServerException, IOException;
    List<ProductId> findSimilarProductsOf(ProductId productId) throws SolrServerException, IOException;
    
}
