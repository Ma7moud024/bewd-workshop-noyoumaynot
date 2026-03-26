package han.aim.se.noyoumaynot.movie.service;

import han.aim.se.noyoumaynot.movie.repository.UserToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class AuthenticationService {
  ArrayList<UserToken> userTokens = new ArrayList<>();

  public UserToken login(String username, String password) {
    if (username.equals("admin") && password.equals("secret")) {
      UserToken userToken = new UserToken(username);
      userTokens.add(userToken);
      return userToken;
    }
    return null;
  }

  public boolean isValidToken(String token) {
    return userTokens.stream()
            .anyMatch(userToken -> userToken.getToken().equals(token));
  }

  public String getUsername(String token) {
    return userTokens.stream()
            .filter(userToken -> userToken.getToken().equals(token))
            .map(UserToken::getUsername)
            .findFirst()
            .orElse(null);
  }
}