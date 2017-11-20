package annotation;

public class SampleClass {

    private String name;

    @Login()
    private String login;

    public SampleClass(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }
}
