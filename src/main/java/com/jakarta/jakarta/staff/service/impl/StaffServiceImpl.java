package com.jakarta.jakarta.staff.service.impl;

import com.jakarta.jakarta.staff.dto.StaffRequestDto;
import com.jakarta.jakarta.staff.dto.StaffResponseDto;
import com.jakarta.jakarta.staff.dto.StaffUpdateRequestDto;
import com.jakarta.jakarta.staff.entity.Staff;
import com.jakarta.jakarta.staff.mapper.StaffMapper;
import com.jakarta.jakarta.staff.repository.StaffRepository;
import com.jakarta.jakarta.staff.service.StaffService;
import com.jakarta.jakarta.user.entity.User;
import com.jakarta.jakarta.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;
    private final UserRepository userRepository;
    private final StaffMapper staffMapper;

    public StaffServiceImpl(StaffRepository staffRepository, UserRepository userRepository, StaffMapper staffMapper) {
        this.staffRepository = staffRepository;
        this.userRepository = userRepository;
        this.staffMapper = staffMapper;
    }

    @Override
    public StaffResponseDto createStaff(StaffRequestDto staffRequestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentEmail = authentication.getName();
        User currentUser = userRepository.findByEmail(currentEmail).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.FORBIDDEN, "Forbidden")
        );
        Staff staff = staffMapper.toEntity(staffRequestDto);
        staff.setUser(currentUser);

        return staffMapper.toDto(staffRepository.save(staff));
    }

    @Override
    public StaffResponseDto getStaff(Long staffId) {
        Staff staff = isAuthorized(staffId);
        return staffMapper.toDto(staff);
    }

    @Override
    public Page<StaffResponseDto> getAllStaff(Pageable pageable) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentEmail = authentication.getName();
        User currentUser = userRepository.findByEmail(currentEmail).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.FORBIDDEN, "Forbidden")
        );
        Page<Staff> staffList = staffRepository.findAllByUserOrderByFirstNameAsc(currentUser, pageable);
        return staffList.map(staffMapper::toDto);
    }

    @Override
    public StaffResponseDto updateStaff(Long staffId, StaffUpdateRequestDto staffRequestDto) {
        Staff staff = isAuthorized(staffId);
        staff.setFirstName(staffRequestDto.getFirstName());
        staff.setLastName(staffRequestDto.getLastName());
        staff.setPhoneNumber(staffRequestDto.getPhoneNumber());

        return staffMapper.toDto(staffRepository.save(staff));
    }

    @Override
    public void deleteStaff(Long staffId) {
        Staff staff = isAuthorized(staffId);
        staffRepository.delete(staff);

    }

    private Staff isAuthorized(Long staffId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentEmail = authentication.getName();
        User currentUser = userRepository.findByEmail(currentEmail).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.FORBIDDEN, "Forbidden")
        );
        return staffRepository.findByIdAndUser(staffId, currentUser).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Staff not found")
        );
    }
}
