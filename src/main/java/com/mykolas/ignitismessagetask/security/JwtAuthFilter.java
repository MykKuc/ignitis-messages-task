package com.mykolas.ignitismessagetask.security;

import com.mykolas.ignitismessagetask.user.User;
import com.mykolas.ignitismessagetask.user.UserQueries;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtMaker jwtTokenMaker;
    private final UserQueries userQueries;

    public JwtAuthFilter(JwtMaker  jwtTokenMaker, UserQueries userQueries){
        this.jwtTokenMaker = jwtTokenMaker;
        this.userQueries = userQueries;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

            String token = getJwtFromRequest(request);

            if(StringUtils.hasText(token) && jwtTokenMaker.validateToken(token)) {
                String email = jwtTokenMaker.getUsernameFromJWT(token);

                User user = userQueries.fetchUserByEmail(email);
                if(user == null){
                    throw new UnavailableException("User does not exist.");
                }
                            // Add authorities here.
                                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        user,null);
                        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
            filterChain.doFilter(request, response);
    }

    //++
    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
