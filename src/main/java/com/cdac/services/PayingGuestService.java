package com.cdac.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.entity.PayingGuest;
import com.cdac.entity.Room;
import com.cdac.repository.PayingGuestRepository;
import com.cdac.repository.RoomRepository;

@Service
public class PayingGuestService {

	@Autowired
	private PayingGuestRepository payingGuestRepository;
	@Autowired
	private RoomRepository roomRepository;

	public PayingGuest addPayingGuest(PayingGuest guest, Long roomId) {
		Room room = roomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("Room not found"));
		guest.setRoom(room);
		guest.setRentPaid(false);
		return payingGuestRepository.save(guest);
	}

	public List<PayingGuest> getAllGuestsForUser(Long userId) {
		return payingGuestRepository.findByUserId(userId);
	}

	public PayingGuest payRent(Long guestId, double amount) {
		PayingGuest guest = payingGuestRepository.findById(guestId)
				.orElseThrow(() -> new RuntimeException("Paying guest not found"));
		guest.setRentPaid(true);
		guest.setRentAmount(amount);
		return payingGuestRepository.save(guest);
	}
}
