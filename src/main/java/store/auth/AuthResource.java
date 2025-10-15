package store.auth;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import store.account.AccountOut;

@RestController
public class AuthResource implements AuthController {

    @Autowired
    private AuthService authService;

    @Override
    public ResponseEntity<TokenOut> register(RegisterIn in) {
        final String jwt = authService.register(
                in.name(), in.email(), in.password());
        return ResponseEntity
                .created(
                        ServletUriComponentsBuilder.fromCurrentRequest().build().toUri())
                .body(TokenOut.builder().jwt(jwt).build());
    }

    @Override
    public ResponseEntity<TokenOut> login(LoginIn in) {
        final String jwt = authService.login(
                in.email(),
                in.password());
        return ResponseEntity
                .created(
                        ServletUriComponentsBuilder.fromCurrentRequest().build().toUri())
                .body(TokenOut.builder().jwt(jwt).build());
    }

    @Override
    public ResponseEntity<Map<String, String>> solve(TokenOut in) {
        AccountOut account = authService.solve(in.jwt());
        return ResponseEntity.ok(
                Map.of(
                        "idAccount", account.id()));
    }

}