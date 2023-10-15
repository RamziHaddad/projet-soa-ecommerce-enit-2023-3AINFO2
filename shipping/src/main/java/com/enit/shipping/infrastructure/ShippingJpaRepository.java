package com.enit.shipping.infrastructure;

import com.enit.shipping.domain.Shipping;
import com.enit.shipping.domain.ShippingRepository;
import org.springframework.data.jpa.repository.JpaRepository;

// pour encapsuler les détails de Spring Data JPA :
public interface ShippingJpaRepository extends JpaRepository<Shipping,Long> {
}
