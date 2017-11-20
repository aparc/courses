package annotation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginProcessorTest {

    @Test
    public void testStartProcess_validParams_writeReverseValue() throws NoSuchFieldException, IllegalAccessException {

        // setup
        SampleClass test = new SampleClass("qwerty");
        // when
        LoginProcessor.startProcess(test);
        //then
        Assertions.assertTrue(test.getLogin().equals(new StringBuilder(test.getName()).reverse().toString()));

    }
}
