package store.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import store.account.AccountController;
import store.account.AccountIn;

@Service
public class AuthService {

    @Autowired
    private AccountController accountController;

    public String register(String name, String email, String password) {

        // Salvar no servico de Account
        accountController.create(AccountIn.builder()
            .name(name)
            .email(email)
            .password(password)
            .build()
        );




        // Gera um token

        // Retorna o token

        return null;
    }

    public String login(String email, String password) {
        return null;
    }

}
