package org.acme.application.services;

import java.io.IOException;
import java.util.List;

import org.acme.domain.Description;
import org.acme.domain.Product;
import org.acme.domain.ProductId;
import org.acme.domain.SearchRepository;
import org.apache.solr.client.solrj.SolrServerException;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SearchServiceImpl implements SearchService {
    @Inject 
    SearchRepository searchRepository;

    @Override
    public void indexProduct(ProductId productId, Description description) throws SolrServerException, IOException {
        searchRepository.addProduct(Product.of(productId, description));
    }

    @Override
    public List<Product> findProductsByKeyword(String keyword) throws SolrServerException, IOException {
        return searchRepository.findProductsByKeyword(keyword);
    }

    @Override
    public List<ProductId> findSimilarProductsOf(ProductId productId) throws SolrServerException, IOException {
        return searchRepository.findSimilarProductsOf(productId);
    }
    
}
