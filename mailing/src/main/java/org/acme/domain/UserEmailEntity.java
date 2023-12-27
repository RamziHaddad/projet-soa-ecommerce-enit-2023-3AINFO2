package org.acme.domain;

import org.glassfish.jaxb.core.v2.model.core.ID;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
@ApplicationScoped
public class UserEmailEntity {
    @Id
    private ID id;
    private String Email;
}
