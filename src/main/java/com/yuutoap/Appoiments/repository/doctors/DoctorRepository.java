package com.yuutoap.Appoiments.repository.doctors;

import com.yuutoap.Appoiments.model.doctors.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DoctorRepository extends JpaRepository<Doctor, UUID> {
    List<Doctor> findByTenantSlug(String tenant);
}
