package business.Data;

public class UserData {
    private static final String PASSWORD = "secret_sauce";

    private static final String STANDARD_USER = "standard_user";

    private static final String LOCKED_OUT_USER = "locked_out_user";

    private static final String VISUAL_USER = "visual_user";

    private static final String PERFORMANCE_GLITCH_USER = "performance_glitch_user";

    public static String getPassword() {
        return PASSWORD;
    }

    public static String getStandardUser() {
        return STANDARD_USER;
    }

    public static String getLockedUser() {
        return LOCKED_OUT_USER;
    }

    public static String getVisualUser() {
        return VISUAL_USER;
    }

    public static String getPerformanceGlitchUser(){
        return  PERFORMANCE_GLITCH_USER;
    }
}
