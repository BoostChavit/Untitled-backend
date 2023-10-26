package com.example.UserProfileService.service;

import com.example.UserProfileService.common.Role;
import com.example.UserProfileService.common.TokenType;
import com.example.UserProfileService.entity.Profile;
import com.example.UserProfileService.entity.Token;
import com.example.UserProfileService.entity.User;
import com.example.UserProfileService.model.AuthRequest;
import com.example.UserProfileService.model.AuthResponse;
import com.example.UserProfileService.model.MeResponse;
import com.example.UserProfileService.model.RegisterRequest;
import com.example.UserProfileService.repository.ProfileRepository;
import com.example.UserProfileService.repository.TokenRepository;
import com.example.UserProfileService.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenRepository tokenRepository;

    //ใช้เช็คว่า email ถูกใช้ไปหรือยัง
    public boolean emailIsExisted(RegisterRequest request) {
        if(repository.findByEmail(request.getEmail()).isPresent()) {
            return true;
        }
        return false;
    }

    //ใช้สร้าง profile ของ user ใหม่
    public ResponseEntity register(RegisterRequest request) {
        Profile myProfile = new Profile();
        var user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .profile(myProfile)
                .build();
        profileRepository.save(myProfile);
        repository.save(user);
        return ResponseEntity.ok("Register Successfully");
    }

    //ใช้ในการ login ตรวจ email กับ password
    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    //ใช้บันทึก user ใหม่
    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    //ให้หน้่าบ้านเอาข้อมูลของ user คนนั้นๆ
    public MeResponse me(
            HttpServletRequest request
    ) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String token;
        final String userEmail;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return null;
        }
        token = authHeader.substring(7);
        userEmail = jwtService.extractUsername(token);

        if (userEmail != null) {
            var user = this.repository.findByEmail(userEmail)
                    .orElseThrow();
            var realtoken = this.tokenRepository.findByToken(token)
                    .orElseThrow();
            if(jwtService.isTokenValid(token, user) && !realtoken.isExpired()) {
                return MeResponse.builder().id(user.getId()).email(user.getEmail()).build();
            }
        }
        return null;
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.repository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

}
