package com.clienthub.crm.clienthub.service;

import com.clienthub.crm.clienthub.repository.CompanyRepository;
import com.clienthub.crm.clienthub.repository.DealRepository;
import com.clienthub.crm.clienthub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DashboardService {
    private final CompanyRepository companyRepository;
    private final DealRepository dealRepository;
    private final UserRepository userRepository;

    public Map<String, Object> getGlobalStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalCompanies", companyRepository.count());
        stats.put("totalDeals", dealRepository.count());
        stats.put("totalUsers", userRepository.count());
        stats.put("wonDeals", dealRepository.findAll().stream().filter(d -> d.getStage().toString().equals("WON")).count());
        stats.put("lostDeals", dealRepository.findAll().stream().filter(d -> d.getStage().toString().equals("LOST")).count());
        stats.put("totalRevenue", dealRepository.findAll().stream()
            .filter(d -> d.getStage().toString().equals("WON"))
            .map(d -> d.getValue() == null ? BigDecimal.ZERO : d.getValue())
            .reduce(BigDecimal.ZERO, BigDecimal::add));
        return stats;
    }

    public Map<String, Object> getUserStats(Long userId) {
        Map<String, Object> stats = new HashMap<>();
        // Deals assignés à l'utilisateur
        var deals = dealRepository.findAll().stream().filter(d -> userId.equals(d.getAssignedTo()));
        long totalDeals = deals.count();
        deals = dealRepository.findAll().stream().filter(d -> userId.equals(d.getAssignedTo())); // reset stream
        long wonDeals = deals.filter(d -> d.getStage().toString().equals("WON")).count();
        deals = dealRepository.findAll().stream().filter(d -> userId.equals(d.getAssignedTo())); // reset stream
        long lostDeals = deals.filter(d -> d.getStage().toString().equals("LOST")).count();
        deals = dealRepository.findAll().stream().filter(d -> userId.equals(d.getAssignedTo())); // reset stream
        BigDecimal totalRevenue = deals.filter(d -> d.getStage().toString().equals("WON"))
            .map(d -> d.getValue() == null ? BigDecimal.ZERO : d.getValue())
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        long assignedCompanies = companyRepository.findAll().stream().filter(c -> userId.equals(c.getAssignedTo())).count();
        stats.put("totalDeals", totalDeals);
        stats.put("wonDeals", wonDeals);
        stats.put("lostDeals", lostDeals);
        stats.put("totalRevenue", totalRevenue);
        stats.put("assignedCompanies", assignedCompanies);
        return stats;
    }
}
