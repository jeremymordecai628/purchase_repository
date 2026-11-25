package com.modex.webapp.repository;

import com.modex.webapp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // Find orders associated with a particular bid
    List<Order> findByBid_BidId(Long bidId);

    // Find orders by status
    List<Order> findByStatus(String status);

    // Find orders by office
    List<Order> findByOffice(String office);

    // Find orders by organization
    List<Order> findByOrderOrganization(String organization);

    // Find orders by bid and status
    List<Order> findByBid_BidIdAndStatus(Long bidId, String status);
}

