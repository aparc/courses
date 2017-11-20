package annotation;

public class SampleClass {
    String name;

    @Login(line = "qwerty")
    String login;

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }
}
