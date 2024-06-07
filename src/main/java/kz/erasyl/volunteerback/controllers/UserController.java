package kz.erasyl.volunteerback.controllers;

import kz.erasyl.volunteerback.models.User;
import kz.erasyl.volunteerback.requests.ChangePasswordRequest;
import kz.erasyl.volunteerback.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/username/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }


    @PatchMapping
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePasswordRequest changePasswordRequest,
             Principal connectedUser
    ){
        if (connectedUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        userService.changePassword(changePasswordRequest, connectedUser);
        return ResponseEntity.accepted().build();
    }

//    @PostMapping("/avatar")
//    public ResponseEntity<?> saveAvatar(@RequestParam MultipartFile file, Principal connectedUser) {
//        if (connectedUser == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//        userService.saveAvatar(file, connectedUser);
//        return ResponseEntity.accepted().build();
//    }
//
//    @GetMapping("/avatar")
//    public ResponseEntity<ByteArrayResource> getUserAvatar(Principal connectedUser) {
//        byte[] avatar = userService.getUserAvatar(connectedUser);
//        System.out.println("WOOOOOOORKS");
//
//        if (avatar == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        ByteArrayResource resource = new ByteArrayResource(avatar);
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.IMAGE_JPEG)  // You may need to dynamically determine the media type
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"avatar.jpg\"")
//                .body(resource);
//    }

}
