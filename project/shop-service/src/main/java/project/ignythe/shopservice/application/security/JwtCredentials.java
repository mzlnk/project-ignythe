package project.ignythe.shopservice.application.security;

import java.util.List;

public record JwtCredentials(String token,
                             List<String> roles) {
}
