
package com.hansani.smartjob.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hansani.smartjob.entity.User;
import com.hansani.smartjob.service.AdminService;

@RestController
@RequestMapping("/api/admin/users")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public ResponseEntity<List<AdminUserResponse>> getAllUsers() {

        List<AdminUserResponse> users = adminService.getAllUsers()
                .stream()
                .map(this::toResponse)
                .toList();

        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId) {

        try {
            User user = adminService.getUserById(userId);
            return ResponseEntity.ok(toResponse(user));

        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<AdminUserResponse>> getUsersByRole(
            @PathVariable String role) {

        List<AdminUserResponse> users = adminService.getUsersByRole(role)
                .stream()
                .map(this::toResponse)
                .toList();

        return ResponseEntity.ok(users);
    }

    @GetMapping("/status")
public ResponseEntity<List<AdminUserResponse>> getUsersByStatus(
        @RequestParam boolean active) {

    List<AdminUserResponse> users = adminService.getUsersByStatus(active)
            .stream()
            .map(this::toResponse)
            .toList();

    return ResponseEntity.ok(users);
    }

    @PutMapping("/{userId}/status")
    public ResponseEntity<?> updateUserStatus(
            @PathVariable Long userId,
            @RequestParam boolean active) {

        try {
            User user = adminService.updateUserStatus(userId, active);
            return ResponseEntity.ok(toResponse(user));

        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {

        try {
            adminService.deleteUser(userId);
            return ResponseEntity.ok("User deleted successfully");

        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    private AdminUserResponse toResponse(User user) {
        return new AdminUserResponse(
                user.getId(),
                user.getEmail(),
                user.getRole(),
                user.isActive()
        );
    }

    public record AdminUserResponse(
            Long id,
            String email,
            String role,
            boolean active
    ) {
    }
}
