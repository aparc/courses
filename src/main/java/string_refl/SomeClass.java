package string_refl;

public class SomeClass {

    private int number;
    private String line;
    private User user;

    {
        number = 1;
        line = "quqq";
        user = new User();
    }

    public static class User{
        private int id = 6;
        private String str = "abs";
    }
}
