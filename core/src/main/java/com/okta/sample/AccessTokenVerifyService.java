package com.okta.sample;

import com.okta.jwt.AccessTokenVerifier;
import com.okta.jwt.Jwt;
import com.okta.jwt.JwtVerifiers;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

/**
 * Service to validate Okta access token.
 *
 * @author Eddie Yao
 */
@Component(service = AccessTokenVerifyService.class)
public class AccessTokenVerifyService {

  private static final Logger logger = LoggerFactory.getLogger(AccessTokenVerifyService.class);

  /**
   * Verifies access token.
   *
   * @param jwtString
   * @return verified decoded access token
   * @throws Exception
   */
  public Jwt verifyToken(String jwtString) throws Exception {

    AccessTokenVerifier jwtVerifier = JwtVerifiers.accessTokenVerifierBuilder()
      .setIssuer("") // todo: check if there needs to be multiple okta authorization servers
      .setAudience("") // todo: clarify if this is the correct audience for the authorization server
      .setConnectionTimeout(Duration.ofSeconds(1)) // defaults to 1000ms
      .setReadTimeout(Duration.ofSeconds(1))       // defaults to 1000ms
      .build();

    Jwt jwt = jwtVerifier.decode(jwtString);
    return jwt;
  }
}
