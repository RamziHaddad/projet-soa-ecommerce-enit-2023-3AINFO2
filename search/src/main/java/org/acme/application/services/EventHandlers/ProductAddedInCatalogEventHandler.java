package org.acme.application.services.EventHandlers;

import java.io.IOException;

import org.acme.application.services.SearchService;
import org.acme.domain.Events.ProductAddedInCatalog;
import org.apache.solr.client.solrj.SolrServerException;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import jakarta.inject.Inject;

public class ProductAddedInCatalogEventHandler {
    @Inject
    SearchService searchService;

    @Incoming("catalog_topic")
    public void handle(ProductAddedInCatalog event) throws SolrServerException, IOException {
        searchService.indexProduct(event.productId(), event.description());
    }
}