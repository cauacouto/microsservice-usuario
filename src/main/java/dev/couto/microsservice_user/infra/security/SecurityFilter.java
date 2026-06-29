package dev.couto.microsservice_user.infra.security;

import dev.couto.microsservice_user.Repository.UsuarioRepository;
import dev.couto.microsservice_user.domin.Usuario;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SecurityFilter  extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = this.recoverToken(request);

        if (token != null) {
            String email = tokenService.verifyToken(token);


            if (email != null) {
                Usuario user = usuarioRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("usuario não encontrado"));
                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);

            }

        }
        filterChain.doFilter(request, response);
    }


        private String recoverToken (HttpServletRequest request){
            var authHeader = request.getHeader("authorization");
            if (authHeader == null) return null;

            return authHeader.replace("Bearer ", "");
        }
    }

