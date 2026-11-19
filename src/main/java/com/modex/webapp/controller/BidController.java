package com.modex.webapp.controller;

import com.modex.webapp.dto.BidRequest;
import com.modex.webapp.model.Bid;
import com.modex.webapp.service.BidService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders/{orderId}/bids")
public class BidController {

    private final BidService bidService;

    public BidController(BidService bidService) {
        this.bidService = bidService;
    }

    // Submit a bid
    @PostMapping
    public ResponseEntity<Bid> createBid(
            @PathVariable Long orderId,
            @RequestBody BidRequest request) {

        Bid bid = bidService.createBid(orderId, request);

        return ResponseEntity.status(201).body(bid);
    }

    // View all bids for an order
    @GetMapping
    public ResponseEntity<List<Bid>> getOrderBids(
            @PathVariable Long orderId) {

        return ResponseEntity.ok(
            bidService.getOrderBids(orderId)
        );
    }

    // Select a winning bid
    @PostMapping("/{bidId}/select")
    public ResponseEntity<Bid> selectBid(
            @PathVariable Long orderId,
            @PathVariable Long bidId) {

        Bid bid = bidService.selectBid(orderId, bidId);

        return ResponseEntity.ok(bid);
    }
}

