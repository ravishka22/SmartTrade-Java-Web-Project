package model;

import io.github.cdimascio.dotenv.Dotenv;

/**
 *
 * @author Ravishka
 */
public class Config {
    private static final Dotenv dotenv = Dotenv.load();

    public static final String APP_EMAIL = dotenv.get("APP_EMAIL");
    public static final String APP_PASSWORD = dotenv.get("APP_PASS");
}
