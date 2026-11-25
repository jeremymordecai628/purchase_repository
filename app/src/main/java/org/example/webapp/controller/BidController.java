package com.modex.webapp.controller;

import com.modex.webapp.dto.BidRequest;
import com.modex.webapp.model.Bid;
import com.modex.webapp.service.BidService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bids")
public class BidController {

    private final BidRepository bidRepository;
    private final OrderRepository orderRepository;
    private final OrganizationRepository organizationRepository;

    public BidController(BidRepository bidRepository, OrderRepository orderRepository, OrganizationRepository organizationRepository) {
        this.bidRepository = bidRepository;
        this.orderRepository = orderRepository;
        this.organizationRepository = organizationRepository;
    }

    // Submit a bid
    @PostMapping
    public ResponseEntity<Order> createBid(@RequestBody CreateOrderRequest request)   {

        //Find the Order from the orderId user submitted
        Order order = orderRepository.findById(request.getOrderId()).orElseThrow(() -> new RuntimeException("Order not found: " + request.getOrderId()));

        //Find the Organization
        Organization organization = organizationRepository.findById(request.getOrganizationId())
            .orElseThrow(() -> new RuntimeException("Organization not found: " + request.getOrganizationId()));
        
        //Create the Bid - same pattern as your Order
        Bid bid = new Bid();
        bid.setOrder(order);
        bid.setOrganization(organization);
        bid.setBidAmount(request.getBidAmount());
        bid.setDescription(request.getDescription());
        bid.setStatus("PENDING");
        bid.setSubmissionDate(LocalDateTime.now());

        Bid savedBid = bidRepository.save(bid);
        return ResponseEntity.ok(savedBid);
    }


    // View all bids for an order
    @GetMapping
    public ResponseEntity<List<Bid>> getOrderBids(@PathVariable Long orderId) {

        return ResponseEntity.ok(bidRepository.findByOrder_OrderId(orderId));

    }

    // PUT /api/bids/10/select-winner
    // 10 = the bidId that won
    @PutMapping("/{bidId}/select-winner")
    public ResponseEntity<Bid> selectWinningBid(@PathVariable Long bidId) {
        // Find the winning bid
        Bid winningBid = bidRepository.findById(bidId).orElseThrow(() -> new RuntimeException("Bid not found: " + bidId));
        
        //Get its Order
        Order order = winningBid.getOrder();

        //Get ALL bids for that same order
        List<Bid> allBids = bidRepository.findByOrder_OrderId(order.getOrderId());

        //Loop - winner = ACCEPTED, everyone else = REJECTED
        for (Bid bid : allBids) {
            if (bid.getBidId().equals(bidId)) {
                bid.setStatus("ACCEPTED"); // <-- winner
            } else {
                bid.setStatus("REJECTED"); // <-- losers
            }
        }

        bidRepository.saveAll(allBids);
        
        //Optional: also update the Order itself
        order.setStatus("PENDING-DELIVERY");
        orderRepository.save(order);
        return ResponseEntity.ok(winningBid);
    }

}

