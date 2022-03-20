package ui.gui;

import java.awt.*;

public class EditDialog extends AddDialog {

    public EditDialog(Window owner) {
        super(owner);
        addButton.setText("Apply");
    }

    @Override
    public Boolean setUp() {
        this.setTitle("Edit Homework");
        this.setModal(true);
        this.setSize(200,200);
        relocate();
        this.setVisible(true);

        return accepted;

    }

}
