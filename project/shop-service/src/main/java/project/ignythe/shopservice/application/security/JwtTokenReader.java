package project.ignythe.shopservice.application.security;

import io.vavr.control.Option;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;

public class JwtTokenReader {

    public String readTokenFromHeader(HttpServletRequest request) {
        return Option.of(request.getHeader("Authorization"))
                .map(header -> header.substring(7))
                .getOrNull();
    }

}
