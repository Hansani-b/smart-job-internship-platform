
package com.hansani.smartjob.service;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hansani.smartjob.entity.User;
import com.hansani.smartjob.repository.UserRepository;

@Service
public class AdminService {

    private final UserRepository userRepository;

    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> getUsersByRole(String role) {
        return userRepository.findByRole(role.toUpperCase());
        
    }

    public List<User> getUsersByStatus(boolean active) {
    return userRepository.findByActive(active);
    }

    public User updateUserStatus(Long userId, boolean active) {

        User user = getUserById(userId);

        String currentAdminEmail = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        if (user.getEmail().equals(currentAdminEmail) && !active) {
            throw new RuntimeException("Admin cannot deactivate their own account");
        }

        if ("ADMIN".equals(user.getRole()) && !active) {
            long activeAdminCount = userRepository
                    .findByRoleAndActive("ADMIN", true)
                    .size();

            if (activeAdminCount <= 1) {
                throw new RuntimeException("Cannot deactivate the last active admin");
            }
        }

        user.setActive(active);

        return userRepository.save(user);
    }

    public void deleteUser(Long userId) {

        User user = getUserById(userId);

        String currentAdminEmail = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        if (user.getEmail().equals(currentAdminEmail)) {
            throw new RuntimeException("Admin cannot delete their own account");
        }

        if ("ADMIN".equals(user.getRole())) {

            long activeAdminCount = userRepository
                    .findByRoleAndActive("ADMIN", true)
                    .size();

            if (user.isActive() && activeAdminCount <= 1) {
                throw new RuntimeException("Cannot delete the last active admin");
            }
        }

         user.setActive(false);

        userRepository.save(user);
    }
}
