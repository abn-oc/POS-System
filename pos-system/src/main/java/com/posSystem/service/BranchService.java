package com.posSystem.service;

import com.posSystem.exception.UserException;
import com.posSystem.models.User;
import com.posSystem.payload.dto.BranchDto;

import java.util.List;


public interface BranchService {
    BranchDto createBranch(BranchDto branchDto, User user) throws UserException;
    BranchDto updateBranch(Long id, BranchDto branchDto, User user) throws Exception;
    void deleteBranch(Long id) throws Exception;
    List<BranchDto> getAllBranchesByStoreId(Long storeId);
    BranchDto getBranchById(Long id) throws Exception;
}
