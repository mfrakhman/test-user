package com.jakarta.jakarta.staff.repository;

import com.jakarta.jakarta.staff.entity.Staff;
import com.jakarta.jakarta.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, Long> {

    List<Staff> findAllByUser(User user);

    Page<Staff> findAllByUserOrderByFirstNameAsc(User user, Pageable pageable);

    Optional<Staff> findByIdAndUser(Long id, User user);
}
