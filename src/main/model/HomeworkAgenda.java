package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// Represents an agenda of homework

public class HomeworkAgenda {
    ArrayList<Homework> agenda;

    //EFFECT: Constructs an empty agenda
    public HomeworkAgenda() {
        agenda = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECT: add the given homework to agenda
    public void addHomework(Homework hw) {
        agenda.add(hw);
    }

    //REQUIRES: Homework hw is already in HomeworkAgenda
    //MODIFIES: this
    //EFFECTS: remove the given homework from agenda
    public void deleteHomework(Homework hw) {
        agenda.remove(hw);
    }

    //REQUIRES: HomeworkAgenda must have a homework with subject
    //MODIFIES: this
    //EFFECT: delete all homework with subject
    public void deleteHomeworkWithSubject(String subject) {

        HomeworkAgenda needToBeDeleted = new HomeworkAgenda();

        for (Homework next : this.agenda) {
            if (next.getSubject().equals(subject)) {
                needToBeDeleted.addHomework(next);
            }
        }

        agenda.removeAll(needToBeDeleted.agenda);
    }

    //EFFECTS: returns the number of Homework currently in agenda
    public Integer length() {
        return agenda.size();
    }

    //EFFECTS: get the Homework at index x in list
    public Homework get(int x) {
        return agenda.get(x);
    }

    // EFFECTS: returns agenda
    public List<Homework> getAgenda() {
        return agenda;
    }




}

