package com.fiebtcc.barbersclub.barbersclub.repository;

import com.fiebtcc.barbersclub.barbersclub.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
}
