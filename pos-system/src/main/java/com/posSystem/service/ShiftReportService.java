package com.posSystem.service;

import com.posSystem.exception.UserException;
import com.posSystem.models.User;
import com.posSystem.payload.dto.ShiftReportDto;

import java.time.LocalDateTime;
import java.util.List;

public interface ShiftReportService {
    ShiftReportDto startShift() throws Exception;
    ShiftReportDto endShift() throws Exception;
    ShiftReportDto getShiftReportById(Long shiftReportId) throws Exception;
    List<ShiftReportDto> getAllShifts();
    List<ShiftReportDto> getShiftReportByBranchId(Long branchId);
    List<ShiftReportDto> getShiftReportByCashierId(Long cashierId);
    ShiftReportDto getCurrentShiftProgress(Long cashierId) throws Exception;
    ShiftReportDto getShiftByCashierAndDate(Long cashierId, LocalDateTime date) throws Exception;
}
