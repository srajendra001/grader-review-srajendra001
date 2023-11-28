import static org.junit.Assert.*;
import org.junit.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class sc implements StringChecker {
    public boolean checkString (String s) {
        if (s.equals("a")) 
            return true;
        return false;
    }
}

public class TestListExamples {
    @Test(timeout = 500)
    public void testFilter() { 
        List<String> list = new ArrayList<>();
        list.add("hi");
        list.add("hello");
        list.add("world");
        List<String> expected = new ArrayList<>();
        assertEquals(expected, ListExamples.filter(list, new sc()));
    }

    @Test(timeout = 500)
    public void testMerge() {
        List<String> list = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        list.add("apple");
        list.add("car");
        list2.add("banana");
        list2.add("dog");
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("apple");
        expectedResult.add("banana");
        expectedResult.add("car");
        expectedResult.add("dog");
        assertEquals(expectedResult, ListExamples.merge(list, list2));
    }

    @Test(timeout = 500)
    public void testFilter2() {
        List<String> list1 = Arrays.asList("a", "b", "a");
        List<String> list2 = Arrays.asList("a");

        List<String> expect1 = Arrays.asList("a", "a");
        List<String> expect2 = Arrays.asList("a");

        List<String> result1 = ListExamples.filter(list1, new sc());
        List<String> result2 = ListExamples.filter(list2, new sc());

        assertEquals(expect1, result1);
        assertEquals(expect2, result2);
    }
}