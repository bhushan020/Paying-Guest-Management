package com.cdac.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.entity.Room;
import com.cdac.repository.RoomRepository;

@Service
public class RoomService {

	@Autowired
	private RoomRepository roomRepository;

	public Room addRoom(Room room) {
		return roomRepository.save(room);
	}

	public List<Room> getAvailableRooms() {
		return roomRepository.findByIsAvailable(true);
	}
}
