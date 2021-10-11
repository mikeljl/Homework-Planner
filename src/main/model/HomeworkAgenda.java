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

    //MODIFIES: this
    //EFFECTS: remove the given homework from agenda
    public void deleteHomework(Homework hw) {
        agenda.remove(hw);
    }

    //EFFECTS: returns the number of Homework currently in agenda
    public Integer length() {
        return agenda.size();
    }

    //EFFECTS: get the Homework at index x in list
    public Homework get(int x) {
        return agenda.get(x);
    }

    // EFFECTS: returns subject
    public List<Homework> getAgenda() {
        return agenda;
    }




}

