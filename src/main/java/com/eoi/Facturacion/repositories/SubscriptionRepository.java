package com.eoi.Facturacion.repositories;

import com.eoi.Facturacion.entities.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
}
