package it.alessiorossato.prenotaMensa.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("sicurezza.app")
public class JwtConfig
{
  /*  private String uri;
    private String refresh;
    private String header;
    private String prefix;
    private int expiration;
    private String secret;*/



  private String jwtSecret;
  private int jwtExpirationMs;
  private String header;

  public String getJwtSecret() {
    return jwtSecret;
  }

  public void setJwtSecret(String jwtSecret) {
    this.jwtSecret = jwtSecret;
  }

  public int getJwtExpirationMs() {
    return jwtExpirationMs;
  }

  public void setJwtExpirationMs(int jwtExpirationMs) {
    this.jwtExpirationMs = jwtExpirationMs;
  }

  public String getHeader() {
    return header;
  }

  public void setHeader(String header) {
    this.header = header;
  }
}
