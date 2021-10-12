package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HomeworkAgendaTest {

    private HomeworkAgenda hwAgenda;

    @BeforeEach
    public void runBefore() {
         hwAgenda = new HomeworkAgenda();
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
        Homework homework = new Homework("math", "webwork");
        Homework homework1 = new Homework("science", "webwork");
        Homework homework2 = new Homework("history", "webwork");
        hwAgenda.addHomework(homework);
        hwAgenda.addHomework(homework1);
        hwAgenda.addHomework(homework2);


        assertEquals(3,hwAgenda.length());
    }

    @Test
    public void testGet() {
        Homework homework = new Homework("math", "webwork");
        Homework homework1 = new Homework("science", "webwork");
        Homework homework2 = new Homework("history", "webwork");
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
        Homework homework = new Homework("math", "webwork");
        Homework homework1 = new Homework("science", "webwork");
        Homework homework2 = new Homework("history", "webwork");
        hwAgenda.addHomework(homework);
        hwAgenda.addHomework(homework1);
        hwAgenda.addHomework(homework2);

        String subject = hwAgenda.getAgenda().get(0).getSubject();
        String subject2 = hwAgenda.getAgenda().get(1).getSubject();

        assertEquals("math", subject);
        assertEquals("science", subject2);
    }

    @Test
    public void testDeleteHomeworkSmallAgenda() {
        Homework homework = new Homework("math", "webwork");
        hwAgenda.addHomework(homework);
        hwAgenda.deleteHomework(homework);

        assertEquals(0,hwAgenda.length());
    }

    @Test
    public void testDeleteHomeworkFromLargeAgenda() {
        Homework homework = new Homework("math", "webwork");
        Homework homework1 = new Homework("science", "webwork");
        Homework homework2 = new Homework("history", "webwork");
        hwAgenda.addHomework(homework);
        hwAgenda.addHomework(homework1);
        hwAgenda.addHomework(homework2);

        hwAgenda.deleteHomework(homework);

        assertEquals(2,hwAgenda.length());
        assertEquals("science", hwAgenda.get(0).getSubject());
        assertEquals("history", hwAgenda.get(1).getSubject());
    }



}