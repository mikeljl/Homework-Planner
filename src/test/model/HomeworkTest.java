
package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class HomeworkTest {

    private Homework homework;


    @BeforeEach
    public void runBefore() {
        homework = new Homework("aa", "bb");
    }

    @Test
    private void testGetSubject() {
        assertEquals("aa", homework.getSubject());
    }

    @Test
    private void testGetDescription() {
        assertEquals("bb", homework.getDescription());
    }

}




