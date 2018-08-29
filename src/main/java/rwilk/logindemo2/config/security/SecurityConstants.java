package rwilk.logindemo2.config.security;

import java.util.concurrent.TimeUnit;

public class SecurityConstants {

  public static final String SECRET = "secretKey";
  public static final String TOKEN_PREFIX = "Bearer ";
  public static final String HEADER_STRING = "Authorization";
  public static final long EXPIRATION_TIME = TimeUnit.DAYS.toSeconds(1); //864_000_000L;

}
