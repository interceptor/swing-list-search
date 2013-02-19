package list;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 19.02.13
 * Time: 17:29
 * To change this template use File | Settings | File Templates.
 */
public class FilterListField extends JTextField implements DocumentListener {

    private FilterListModel filterListModel;

    //ctor
    public FilterListField(int width, FilterListModel model) {
        super(width);
        this.getDocument().addDocumentListener(this);
        this.filterListModel = model;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        filterListModel.refilter(this.getText());
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        filterListModel.refilter(this.getText());
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        filterListModel.refilter(this.getText());
    }
}
