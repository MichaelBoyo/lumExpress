package africa.semicolon.lumexpress.service.userService;

import africa.semicolon.lumexpress.data.dto.request.LoginRequest;
import africa.semicolon.lumexpress.data.dto.response.LoginResponse;
import africa.semicolon.lumexpress.data.models.LumExpressUser;

import java.util.Optional;

public interface UserService {
    LoginResponse login(LoginRequest loginRequest);
    LumExpressUser getUserByEmail(String email);
}
