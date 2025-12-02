package com.posSystem.controller;

import com.posSystem.payload.dto.ShiftReportDto;
import com.posSystem.service.ShiftReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shift-reports")
public class ShiftReportController {
    private final ShiftReportService service;

    @PostMapping("/start")
    public ResponseEntity<ShiftReportDto> startShift() throws Exception {
        return ResponseEntity.ok(
                service.startShift()
        );
    }

    @PatchMapping("/end")
    public ResponseEntity<ShiftReportDto> endShift() throws Exception {
        return ResponseEntity.ok(
                service.endShift()
        );
    }

    @GetMapping("/current")
    public ResponseEntity<ShiftReportDto> getCurrentShiftProgress() throws Exception {
        return ResponseEntity.ok(
                service.getCurrentShiftProgress(null)
        );
    }

    @GetMapping("/cashier/{cashierId}/by-date")
    public ResponseEntity<ShiftReportDto> getShiftReportByDate(
            @PathVariable Long cashierId,
            @RequestParam @DateTimeFormat (iso = DateTimeFormat.ISO.DATE)LocalDateTime start
            ) throws Exception {
        return ResponseEntity.ok(
                service.getShiftByCashierAndDate(cashierId,start)
        );
    }

    @GetMapping("/cashier/{cashierId}")
    public ResponseEntity<List<ShiftReportDto>> getShiftReportByCashier(
            @PathVariable Long cashierId
    ) throws Exception {
        return ResponseEntity.ok(
                service.getShiftReportByCashierId(cashierId)
        );
    }

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<ShiftReportDto>> getShiftReportByBranch(
            @PathVariable Long branchId
    ) throws Exception {
        return ResponseEntity.ok(
                service.getShiftReportByBranchId(branchId)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShiftReportDto> getShiftReportById(
            @PathVariable Long id
    ) throws Exception {
        return ResponseEntity.ok(
                service.getShiftReportById(id)
        );
    }


}
