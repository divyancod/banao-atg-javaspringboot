package com.banao.task.Contoller;

import com.banao.task.Model.CustomUserDetails;
import com.banao.task.Exceptions.NotAdminException;
import com.banao.task.Exceptions.UserNotFound;
import com.banao.task.JwtUtils.JwtTokenResponse;
import com.banao.task.Model.MyUser;
import com.banao.task.JwtUtils.MyJwtUtil;
import com.banao.task.Services.MyUserDetailService;
import com.banao.task.Services.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class APIController {
    @Autowired
    MyJwtUtil jwtUtil;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    MyUserDetailService userDetailService;

    @Autowired
    MainService mainService;

    @ResponseBody
    @PostMapping("/auth/login")
    public ResponseEntity<JwtTokenResponse> loginAdmin(@RequestParam("email") String email, @RequestParam("password") String password) throws UserNotFound {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        } catch (BadCredentialsException e) {
            throw new UserNotFound("User not found");
        }

        final CustomUserDetails userDetails = userDetailService.loadUserByUsername(email);
        final String token = jwtUtil.generateToken(userDetails);
        JwtTokenResponse jwtTokenResponse = new JwtTokenResponse(
                userDetails.getUserID(), userDetails.getFirstname(), userDetails.getAuthorities().toString(),
                userDetails.getUsername(), token, LocalDateTime.now().toString());

        return new ResponseEntity<>(jwtTokenResponse, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/users/list")
    public ResponseEntity<List<MyUser>> getAllList() throws NotAdminException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean hasUserRole = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("admin"));
        if (!hasUserRole)
            throw new NotAdminException("Token is not of admin");
        return new ResponseEntity<>(mainService.fetchAllUserDetails(), HttpStatus.OK);
    }
}
