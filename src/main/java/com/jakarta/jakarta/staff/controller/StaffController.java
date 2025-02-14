package com.jakarta.jakarta.staff.controller;

import com.jakarta.jakarta.response.Response;
import com.jakarta.jakarta.staff.dto.StaffRequestDto;
import com.jakarta.jakarta.staff.dto.StaffResponseDto;
import com.jakarta.jakarta.staff.dto.StaffUpdateRequestDto;
import com.jakarta.jakarta.staff.service.StaffService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Staff",
        description = "Endpoints for Staff"
)
@RestController
@RequestMapping(path = "/api/v1/staff")
public class StaffController {

    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @PostMapping
    public ResponseEntity<Response<StaffResponseDto>> createStaff(@RequestBody StaffRequestDto staffRequestDto) {
        return Response.success(HttpStatus.CREATED.value(), "Success Create New Staff", staffService.createStaff(staffRequestDto));
    }

    @GetMapping(path = "/{staffId}")
    public ResponseEntity<Response<StaffResponseDto>> getStaff(@PathVariable("staffId") long id) {
        return Response.success(HttpStatus.OK.value(), "Success Get Staff", staffService.getStaff(id));
    }

    @GetMapping
    public ResponseEntity<Response<Page<StaffResponseDto>>> getAllStaff(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<StaffResponseDto> getStaffs = staffService.getAllStaff(pageable);
        return Response.success(HttpStatus.OK.value(), "Success Get Staff", getStaffs);
    }

    @PutMapping(path = "/{staffId}")
    public ResponseEntity<Response<StaffResponseDto>> updateStaff(@PathVariable("staffId") long id,@RequestBody StaffUpdateRequestDto staffRequestDto) {
        return Response.success(HttpStatus.OK.value(), "Success Updating Staff",staffService.updateStaff(id, staffRequestDto));
    }

    @DeleteMapping(path = "/{staffId}")
    public ResponseEntity<Response<StaffResponseDto>> deleteStaff(@PathVariable("staffId") long id) {
        staffService.deleteStaff(id);
        return Response.success(HttpStatus.OK.value(), "Success Deleting Staff");
    }
}
