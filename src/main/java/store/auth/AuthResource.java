package store.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthResource implements AuthController {

    @Autowired
    private AuthService authService;

    @Override
    public ResponseEntity<TokenOut> register(RegisterIn in) {
        final String jwt = authService.register(
            in.name(), in.email(), in.password()
        );
        return ResponseEntity
            .ok(TokenOut.builder().jwt(jwt).build());
    }

    @Override
    public ResponseEntity<TokenOut> login(LoginIn in) {
        final String jwt = authService.login(
            in.email(),
            in.password()
        );
        return ResponseEntity
            .ok(TokenOut.builder().jwt(jwt).build());
    }

}
