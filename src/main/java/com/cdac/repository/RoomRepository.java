package com.cdac.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
	List<Room> findByIsAvailable(boolean isAvailable);
}