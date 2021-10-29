package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HomeworkAgendaTest {

    private HomeworkAgenda hwAgenda;

    @BeforeEach
    public void runBefore() {
         hwAgenda = new HomeworkAgenda();
    }


    @Test
    public void testConstructor() {
        assertEquals(0, hwAgenda.length());
    }

    @Test
    public void testAddHomeworkToEmtpyAgenda() {
        Homework homework = new Homework("math", "webwork");

        hwAgenda.addHomework(homework);

        assertEquals(1,hwAgenda.length());
        assertEquals("math", (hwAgenda.get(0)).getSubject());
        assertEquals("webwork", (hwAgenda.get(0)).getDescription());
    }



    @Test
    public void testLength() {
        Homework homework = new Homework("math", "webwork1");
        Homework homework1 = new Homework("science", "webwork2");
        Homework homework2 = new Homework("history", "webwork3");
        hwAgenda.addHomework(homework);
        hwAgenda.addHomework(homework1);
        hwAgenda.addHomework(homework2);


        assertEquals(3,hwAgenda.length());
    }

    @Test
    public void testGet() {
        Homework homework = new Homework("math", "webwork1");
        Homework homework1 = new Homework("science", "webwork2");
        Homework homework2 = new Homework("history", "webwork3");
        hwAgenda.addHomework(homework);
        hwAgenda.addHomework(homework1);
        hwAgenda.addHomework(homework2);

        String subject = hwAgenda.get(0).getSubject();
        String subject2 = hwAgenda.get(1).getSubject();

        assertEquals("math", subject);
        assertEquals("science", subject2);
    }

    @Test
    public void testGetAgenda() {
        Homework homework = new Homework("math", "webwork1");
        Homework homework1 = new Homework("science", "webwork2");
        Homework homework2 = new Homework("history", "webwork3");
        hwAgenda.addHomework(homework);
        hwAgenda.addHomework(homework1);
        hwAgenda.addHomework(homework2);

        String subject = hwAgenda.getAgenda().get(0).getSubject();
        String subject2 = hwAgenda.getAgenda().get(1).getSubject();
        String subject3 = hwAgenda.getAgenda().get(2).getSubject();
        String description = hwAgenda.getAgenda().get(0).getDescription();
        String description2 = hwAgenda.getAgenda().get(1).getDescription();
        String description3 = hwAgenda.getAgenda().get(2).getDescription();

        assertEquals("math", subject);
        assertEquals("science", subject2);
        assertEquals("webwork1", description);
        assertEquals("webwork2", description2);
        assertEquals("history", subject3);
        assertEquals("webwork3", description3);
    }

    @Test
    public void testDeleteHomeworkSmallAgenda() {
        Homework homework = new Homework("math", "webwork");
        hwAgenda.addHomework(homework);
        hwAgenda.deleteHomework(homework);

        assertEquals(0,hwAgenda.length());
    }

    @Test
    public void testDeleteHomeworkWithSubject() {
        Homework homework = new Homework("math", "webwork1");
        Homework homework1 = new Homework("science", "webwork2");
        Homework homework2 = new Homework("history", "webwork3");
        Homework homework3 = new Homework("math", "webwork4");
        hwAgenda.addHomework(homework);
        hwAgenda.addHomework(homework1);
        hwAgenda.addHomework(homework2);
        hwAgenda.addHomework(homework3);

        hwAgenda.deleteHomeworkWithSubject("math");

        assertEquals(2,hwAgenda.length());
        assertEquals("science", hwAgenda.get(0).getSubject());
        assertEquals("history", hwAgenda.get(1).getSubject());
        assertEquals("webwork2", hwAgenda.get(0).getDescription());
        assertEquals("webwork3", hwAgenda.get(1).getDescription());
    }

    @Test
    public void testDeleteHomeworkFromLargeAgenda() {
        Homework homework = new Homework("math", "webwork1");
        Homework homework1 = new Homework("science", "webwork2");
        Homework homework2 = new Homework("history", "webwork3");
        hwAgenda.addHomework(homework);
        hwAgenda.addHomework(homework1);
        hwAgenda.addHomework(homework2);

        hwAgenda.deleteHomework(homework);

        assertEquals(2,hwAgenda.length());
        assertEquals("science", hwAgenda.get(0).getSubject());
        assertEquals("history", hwAgenda.get(1).getSubject());
    }
    

}