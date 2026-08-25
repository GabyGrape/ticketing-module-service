package com.oksys.backend.config;

import com.oksys.backend.util.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    // UserDetailsService dihapus dari sini karena service ini stateless (tidak pakai DB Auth)

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = parseJwt(request);

        if (token != null && jwtUtils.validateToken(token)) {
            // 1. Ambil claims langsung dari token menggunakan jwtUtils
            Claims claims = jwtUtils.getClaimsFromToken(token); // sesuaikan nama method di JwtUtils kamu
            String username = claims.getSubject();

            // 2. Ambil roles (dengan penanganan null-safety agar tidak NullPointerException)
            List<?> rawRoles = claims.get("roles", List.class);
            List<SimpleGrantedAuthority> authorities = Collections.emptyList();

            if (rawRoles != null) {
                authorities = rawRoles.stream()
                        .map(Object::toString)
                        .map(SimpleGrantedAuthority::new)
                        .toList();
            }

            // 3. Buat objek otentikasi
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(username, null, authorities);

            // 4. Tambahkan rincian request (IP Address, Session ID, dll)
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            // 5. Simpan ke SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }

        return null;
    }
}