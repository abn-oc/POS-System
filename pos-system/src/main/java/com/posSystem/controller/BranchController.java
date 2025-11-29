package com.posSystem.controller;

import com.posSystem.exception.UserException;
import com.posSystem.models.Branch;
import com.posSystem.payload.dto.BranchDto;
import com.posSystem.payload.response.ApiResponse;
import com.posSystem.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/branches")
public class BranchController {
    private final BranchService branchService;

    @PostMapping
    public ResponseEntity<BranchDto> createBranch(
            @RequestBody BranchDto branchDto) throws UserException {
        BranchDto createdBranch=branchService.createBranch(branchDto, null);
        return ResponseEntity.ok(
                createdBranch
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<BranchDto> getBranchById(
            @PathVariable Long id) throws Exception {
        BranchDto createdBranch=branchService.getBranchById(id);
        return ResponseEntity.ok(
                createdBranch
        );
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<BranchDto>> getAllBranchByStoreId(
            @PathVariable Long storeId) throws Exception {
        List<BranchDto> createdBranch=branchService.getAllBranchesByStoreId(storeId);
        return ResponseEntity.ok(
                createdBranch
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<BranchDto> updateBranch(
            @PathVariable Long id,
            @RequestBody BranchDto branchDto) throws Exception {
        BranchDto createdBranch=branchService.updateBranch(id,branchDto,null);
        return ResponseEntity.ok(
                createdBranch
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteBranch(
            @PathVariable Long id) throws Exception {
        branchService.deleteBranch(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("branch deleted successfully");
        return ResponseEntity.ok(
                apiResponse
        );
    }
}
