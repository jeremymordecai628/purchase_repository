package com.modex.webapp.repository;

import com.modex.webapp.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LoginRepository extends JpaRepository<Login, Long> {

    List<Login> findByUserId(Long userId);

    List<Login> findByStatus(String status);
}

