package project.ignythe.shopservice.application.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.servlet.http.HttpServletRequest;

public class AuthUserDetailsFilter extends AbstractPreAuthenticatedProcessingFilter {

    private final JwtTokenReader tokenReader;
    private final JwtService jwtService;

    public AuthUserDetailsFilter(JwtTokenReader tokenReader, JwtService jwtService,
                                 AuthenticationManager authenticationManager) {
        this.tokenReader = tokenReader;
        this.jwtService = jwtService;

        this.setAuthenticationManager(authenticationManager);
    }

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        return jwtService.getPrincipal(tokenReader.readTokenFromHeader(request));
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return jwtService.getCredentials(tokenReader.readTokenFromHeader(request));
    }
}
