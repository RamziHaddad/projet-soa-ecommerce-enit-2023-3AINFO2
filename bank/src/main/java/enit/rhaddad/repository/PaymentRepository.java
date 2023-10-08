package enit.rhaddad.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import enit.rhaddad.domain.Payment;

@ApplicationScoped
@Transactional
public class PaymentRepository {
    @Inject
    EntityManager em;

    
    public void persistPayment(Payment p){
        em.persist(p);
    }
    
    public List<Payment> queryallPayments(){
        return em.createQuery("from Payment p ",Payment.class).getResultList();
    }
   
    public Optional<Payment> queryPaymentById(UUID id){
        Payment o = em.find(Payment.class,id);
        return Optional.ofNullable(o);
    }
}
