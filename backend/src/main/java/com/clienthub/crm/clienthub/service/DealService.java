package com.clienthub.crm.clienthub.service;

import com.clienthub.crm.clienthub.model.Deal;
import com.clienthub.crm.clienthub.repository.DealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DealService {
    private final DealRepository dealRepository;

    public List<Deal> getAllDeals() {
        return dealRepository.findAll();
    }

    public Deal getDealById(Long id) {
        return dealRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Deal non trouvé"));
    }

    public Deal createDeal(Deal deal) {
        return dealRepository.save(deal);
    }

    public Deal updateDeal(Long id, Deal dealDetails) {
        Deal deal = getDealById(id);
        deal.setTitle(dealDetails.getTitle());
        deal.setValue(dealDetails.getValue());
        deal.setProbability(dealDetails.getProbability());
        deal.setStage(dealDetails.getStage());
        deal.setExpectedCloseDate(dealDetails.getExpectedCloseDate());
        deal.setCompany(dealDetails.getCompany());
        deal.setAssignedTo(dealDetails.getAssignedTo());
        deal.setDescription(dealDetails.getDescription());
        return dealRepository.save(deal);
    }

    public void deleteDeal(Long id) {
        Deal deal = getDealById(id);
        dealRepository.delete(deal);
    }
}
