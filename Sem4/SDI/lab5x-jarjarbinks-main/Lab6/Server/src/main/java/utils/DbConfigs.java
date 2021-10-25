package utils;

public class DbConfigs {


    private final String url;
    private final String uName;
    private final String passwd;

    public DbConfigs(String url, String uName, String passwd) {
        this.url = url;
        this.uName = uName;
        this.passwd = passwd;
    }

    public String getUrl() {
        return url;
    }

    public String getUserName() {
        return uName;
    }

    public String getPasswd() {
        return passwd;
    }
}
