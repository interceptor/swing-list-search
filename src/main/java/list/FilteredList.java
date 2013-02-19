package list;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 19.02.13
 * Time: 17:24
 * To change this template use File | Settings | File Templates.
 */
public class FilteredList extends JList {

    private FilterListField filterListField;
    private int DEFAULT_FIELD_WIDTH = 20;

    //ctor
    public FilteredList() {
        super();
        FilterListModel model = new FilterListModel();
        this.setModel(model);
        filterListField = new FilterListField(DEFAULT_FIELD_WIDTH, model);

    }

    @Override
    public void setModel(ListModel model) {
     if ((model instanceof FilterListModel) == false) {
         throw  new IllegalArgumentException("FilteredList must use a model of the type FilterListModel");
     }
        super.setModel(model);
    }

    public void addItem(Object item) {
        ((FilterListModel)getModel()).addItem(item);
    }

    public FilterListField getFilterListField() {
        return filterListField;
    }
}
