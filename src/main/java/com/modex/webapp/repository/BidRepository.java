package com.modex.webapp.repository;

import com.modex.webapp.model.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BidRepository extends JpaRepository<Bid, Long> {

    List<Bid> findByOrder_OrderId(Long orderId);

    List<Bid> findByOrder_OrderIdAndStatus(
        Long orderId, String status
    );

    boolean existsByOrder_OrderIdAndOrganization_OrganizationId(
        Long orderId, Long organizationId
    );
}

