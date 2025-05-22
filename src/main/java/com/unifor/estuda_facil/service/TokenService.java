package com.unifor.estuda_facil.service;

import com.unifor.estuda_facil.aspect.Loggable;
import com.unifor.estuda_facil.models.dto.TokenRequestDTO;
import com.unifor.estuda_facil.models.dto.TokenResponseDTO;
import com.unifor.estuda_facil.models.dto.TokenValidationDTO;
import com.unifor.estuda_facil.models.entity.Token;
import com.unifor.estuda_facil.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenRepository tokenRepository;
    private final Random random = new Random();

    @Loggable
    public TokenResponseDTO generate(TokenRequestDTO req) {
        int number = random.nextInt(9000) + 1000;
        String code = String.format("%04d", number);
        LocalDateTime expiresAt = LocalDateTime.now().plusMinutes(10);

        Token token = new Token();
        token.setEmail(req.getEmail());
        token.setCode(code);
        token.setExpiresAt(expiresAt);
        tokenRepository.save(token);

        TokenResponseDTO resp = new TokenResponseDTO();
        resp.setCode(code);
        resp.setExpiresAt(expiresAt);
        return resp;
    }
    @Loggable
    public boolean validate(TokenValidationDTO req) {
        return tokenRepository.findByEmailAndCode(req.getEmail(), req.getCode())
                .filter(t -> t.getExpiresAt().isAfter(LocalDateTime.now()))
                .isPresent();
    }
}
