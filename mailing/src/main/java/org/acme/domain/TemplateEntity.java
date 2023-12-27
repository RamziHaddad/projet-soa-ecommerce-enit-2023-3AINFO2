package org.acme.domain;

import org.yaml.snakeyaml.events.Event.ID;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@ApplicationScoped
@Entity
public class TemplateEntity {
    @Id
    private ID id;
    private String nameTemplate;
    private String template;
}    
