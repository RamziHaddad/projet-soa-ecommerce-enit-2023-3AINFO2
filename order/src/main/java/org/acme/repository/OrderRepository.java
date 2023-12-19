package org.acme.repository;


import org.acme.domain.Order;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class OrderRepository implements PanacheRepository<Order> {
    // On peut injecter un EntityManager au lieu de travailler avec ORM Panache,
    // mais cela nécessite une configuration manuelle.
}