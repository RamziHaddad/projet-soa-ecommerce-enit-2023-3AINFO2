package org.acme.infrastructure;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.acme.domain.Description;
import org.acme.domain.Product;
import org.acme.domain.ProductId;
import org.acme.domain.SearchRepository;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SearchRepositoryImplSolr implements SearchRepository{

    @Inject
    SolrConnection solrConn;

    @Override
    public List<Product> findAll() throws SolrServerException, IOException {
        List<Product> result = new ArrayList<Product>();
        QueryResponse response = solrConn.search(new SolrQuery("*:*"));
        SolrDocumentList rs = response.getResults();
        for(SolrDocument document:rs){
            System.out.println();
        }
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public List<Product> findProductsByKeyword(String keyword) throws SolrServerException, IOException{
        List<Product> result = new ArrayList<Product>();
        QueryResponse response = solrConn.search(new SolrQuery("description:"+keyword));
        SolrDocumentList rs = response.getResults();
        for(SolrDocument doc:rs){
            try{
                result.add(Product.of(new ProductId(UUID.fromString(doc.getFieldValue("id").toString())), new Description(doc.getFieldValue("description").toString())));
            }catch (IllegalArgumentException e){
                continue;
            }
        }
        return result;
    }

    @Override
    public Product findProductById(ProductId productId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findProductById'");
    }

    @Override
    public void addProduct(Product product) throws SolrServerException, IOException {
        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id", product.getProductId().id().toString());
        doc.addField("description", product.getDescription().description());
        solrConn.addDocument(doc);
    }

    @Override
    public List<ProductId> findSimilarProductsOf(ProductId productId) throws SolrServerException, IOException {
        List<ProductId> resultat = new ArrayList<ProductId>();
        SolrQuery query = new SolrQuery("id:"+productId.id());
        query.set("qt", "/mlt");
        query.set("fl", "*,score");
        QueryResponse response = solrConn.search(query);
        for(SolrDocument doc : response.getResults()){
            resultat.add(new ProductId(UUID.fromString(doc.getFieldValue("id").toString())));
        }
        return resultat;

        
    }
    
}
