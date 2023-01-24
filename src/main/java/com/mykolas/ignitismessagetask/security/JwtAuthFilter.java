package com.mykolas.ignitismessagetask.security;

import com.mykolas.ignitismessagetask.user.User;
import com.mykolas.ignitismessagetask.user.UserRepository;
import liquibase.util.StringUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

    private JwtMaker jwtTokenMaker;
    private UserRepository userRepository;

    public JwtAuthFilter(JwtMaker  jwtTokenMaker, UserRepository userRepository){
        this.jwtTokenMaker = jwtTokenMaker;
        this.userRepository = userRepository;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            String token = getJwtFromRequest(request);
            if(StringUtils.hasText(token) && jwtTokenMaker.validateToken(token)) {
                String email = jwtTokenMaker.getUsernameFromJWT(token);

                Optional<User> user = this.userRepository.findByEmail(email);
                if(user.isEmpty()){
                    throw new UnavailableException("User does not exist.");
                }

                /*
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        user.get(),null, user.get().ge
                )
                */


            }


    }

    //++
    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }

        return null;
    }
}
