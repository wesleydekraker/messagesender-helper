package nl.hu.bep.friendspammer.helper;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import nl.hu.bep.friendspammer.helper.Email;

public class EmailTest {
    @Test
    public void testSplitSingleEmailAddress() {
        String to = "info@example.com";

        List<String> actual = Email.splitAddresses(to);

        List<String> expected = new ArrayList<>();
        expected.add("info@example.com");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSplitMultipleEmailAddress() {
        String to = "info@example.com,admin@example.com";

        List<String> actual = Email.splitAddresses(to);

        List<String> expected = new ArrayList<>();
        expected.add("info@example.com");
        expected.add("admin@example.com");

        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void testValidEmailAddress() {
        String emailAddress = "info@example.com";

        boolean result = Email.isValidEmailAddress(emailAddress);

        Assert.assertTrue(result);
    }
    
    @Test
    public void testInvalidEmailAddress() {
        String emailAddress = "fsdfa";

        boolean result = Email.isValidEmailAddress(emailAddress);

        Assert.assertFalse(result);
    }

}
