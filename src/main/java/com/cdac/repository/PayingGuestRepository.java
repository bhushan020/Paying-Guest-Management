package com.cdac.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.entity.PayingGuest;

public interface PayingGuestRepository extends JpaRepository<PayingGuest, Long> {
	List<PayingGuest> findByUserId(Long userId); // For fetching user's paying guests
}
