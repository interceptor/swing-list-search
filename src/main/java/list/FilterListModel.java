package list;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 19.02.13
 * Time: 17:26
 * To change this template use File | Settings | File Templates.
 */
public class FilterListModel extends AbstractListModel {

    private ArrayList items;
    private ArrayList filteredItems;
    //private FilterListField filterListField;

    public FilterListModel() {
        super();
        //this.filterListField = filterListField;
        items = new ArrayList();
        filteredItems = new ArrayList();
    }

    public void addItem(Object item) {
        items.add(item);
        //this.fireContentsChanged(this, 0, getSize());
        refilter("");
    }

    public void refilter(String searchTerm) {
        filteredItems.clear();
        for (int i =0 ; i < items.size(); i++) {
            if(items.get(i).toString().indexOf(searchTerm, 0) != -1) { //TODO
                filteredItems.add(items.get(i));
                this.fireContentsChanged(this, 0, getSize());
            }
        }
    }

    @Override
    public int getSize() {
        return filteredItems.size();
    }

    @Override
    public Object getElementAt(int index) {
        if (index < filteredItems.size()) {
            return filteredItems.get(index);
        } else {
            return null;
        }

    }


}
