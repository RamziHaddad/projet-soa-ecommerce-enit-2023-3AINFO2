package org.acme.infrastructure;


import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.acme.domain.model.Order;

@ApplicationScoped
@Transactional
public class OrderRepository implements PanacheRepository<Order> {

    // On peut injecter un EntityManager au lieu de travailler avec ORM Panache,
    // mais cela nécessite une configuration manuelle.
}
