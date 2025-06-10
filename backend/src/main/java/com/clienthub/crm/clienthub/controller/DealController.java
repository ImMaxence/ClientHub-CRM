package com.clienthub.crm.clienthub.controller;

import com.clienthub.crm.clienthub.model.Deal;
import com.clienthub.crm.clienthub.service.DealService;
import com.clienthub.crm.clienthub.dto.DealRequest;
import com.clienthub.crm.clienthub.dto.DealResponse;
import com.clienthub.crm.clienthub.dto.mapper.DealResponseMapper;
import com.clienthub.crm.clienthub.dto.mapper.DealRequestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/deals")
@RequiredArgsConstructor
public class DealController {
    private final DealService dealService;

    @GetMapping
    public List<DealResponse> getAllDeals() {
        return dealService.getAllDeals().stream().map(DealResponseMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DealResponse> getDealById(@PathVariable Long id) {
        return ResponseEntity.ok(DealResponseMapper.toDto(dealService.getDealById(id)));
    }

    @PostMapping
    public ResponseEntity<DealResponse> createDeal(@RequestBody DealRequest dealRequest) {
        Deal created = dealService.createDeal(DealRequestMapper.toEntity(dealRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(DealResponseMapper.toDto(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DealResponse> updateDeal(@PathVariable Long id, @RequestBody DealRequest dealRequest) {
        Deal updated = dealService.updateDeal(id, DealRequestMapper.toEntity(dealRequest));
        return ResponseEntity.ok(DealResponseMapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeal(@PathVariable Long id) {
        dealService.deleteDeal(id);
        return ResponseEntity.noContent().build();
    }
}
