package org.acme.infrastructure;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.json.JsonQueryRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Singleton;

@Singleton
public class SolrConnection  {
    private final SolrClient solrClient;

    public SolrConnection() {
        String solrUrl = "http://localhost:8983/solr/products-index"; 
        solrClient = new HttpSolrClient.Builder(solrUrl).build();
    }

    public QueryResponse search(SolrQuery query) throws SolrServerException, IOException {
        return solrClient.query(query);
    }

    public void addDocument(SolrInputDocument doc) throws SolrServerException, IOException {
        this.solrClient.add(doc);
        this.solrClient.commit();
    }
}
