package com.yuutoap.Appoiments.repository.branches;

import com.yuutoap.Appoiments.model.branches.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BranchRepository extends JpaRepository<Branch, UUID> {
}
