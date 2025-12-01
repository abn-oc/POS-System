package com.posSystem.service.impl;

import com.posSystem.mapper.RefundMapper;
import com.posSystem.models.Branch;
import com.posSystem.models.Order;
import com.posSystem.models.Refund;
import com.posSystem.models.User;
import com.posSystem.payload.dto.RefundDto;
import com.posSystem.repository.OrderRepository;
import com.posSystem.repository.RefundRepository;
import com.posSystem.service.RefundService;
import com.posSystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RefundServiceImpl implements RefundService {
    private final UserService userService;
    private final OrderRepository orderRepository;
    private final RefundRepository refundRepository;
    @Override
    public RefundDto createRefund(RefundDto refund) throws Exception {
        User cashier=userService.getCurrentUser();
        Order order=orderRepository.findById(refund.getOrderId()).orElseThrow(
                ()-> new Exception("Order not found")
        );

        Branch branch= order.getBranch();

        Refund createdRefund=Refund.builder()
                .order(order)
                .cashier(cashier)
                .branch(branch)
                .reason(refund.getReason())
                .amount(refund.getAmount())
                .createdAt(refund.getCreatedAt())
                .build();

        Refund savedRefund = refundRepository.save(createdRefund);

        return RefundMapper.toDto(savedRefund);
    }

    @Override
    public List<RefundDto> getAllRefunds() throws Exception {
        return refundRepository.findAll().stream().map(RefundMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<RefundDto> getRefundByCashier(Long cashierId) throws Exception {
        return refundRepository.findByCashierId(cashierId).stream().map(RefundMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<RefundDto> geRefundByShiftReport(Long shiftReportId) throws Exception {
        return refundRepository.findByShiftReportId(shiftReportId).stream().map(RefundMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<RefundDto> getRefundByCashierAndDayRange(Long cashierId,
                                                         LocalDateTime startDate,
                                                         LocalDateTime endDate) throws Exception {
        return refundRepository.findByCashierIdAndCreatedAtBetween(
                cashierId, startDate, endDate
        ).stream().map(RefundMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<RefundDto> getRefundByBranch(Long branchId) throws Exception {
        return refundRepository.findByBranchId(branchId).stream().map(RefundMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public RefundDto getRefundById(Long refundId) throws Exception {
        return refundRepository.findById(refundId)
                .map(RefundMapper::toDto).orElseThrow(
                        ()-> new Exception("Refund not Found")
                );
    }

    @Override
    public void deleteRefund(Long refundId) throws Exception {
//        Refund refund = refundRepository.findById(refundId).orElseThrow(
//                ()->new Exception("Refund not found")
//        );
//
//        refundRepository.delete(refund);

        this.getRefundById(refundId);
        refundRepository.deleteById(refundId);
    }
}
