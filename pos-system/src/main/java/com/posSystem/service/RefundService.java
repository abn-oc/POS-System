package com.posSystem.service;

import com.posSystem.models.Refund;
import com.posSystem.payload.dto.RefundDto;

import java.time.LocalDateTime;
import java.util.List;

public interface RefundService {
    RefundDto createRefund(RefundDto refund) throws Exception;
    List<RefundDto> getAllRefunds() throws Exception;
    List<RefundDto> getRefundByCashier(Long cashierId) throws Exception;
    List<RefundDto> geRefundByShiftReport(Long shiftReportId) throws Exception;
    List<RefundDto> getRefundByCashierAndDayRange(Long cashierId,
                                                  LocalDateTime startDate,
                                                  LocalDateTime endDate) throws Exception;

    List<RefundDto> getRefundByBranch(Long branchId) throws Exception;
    RefundDto getRefundById(Long refundId) throws Exception;
    //admin only can
    void deleteRefund(Long refundId) throws Exception;

}
