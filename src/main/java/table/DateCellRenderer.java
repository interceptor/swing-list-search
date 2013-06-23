package table;

import javax.swing.table.DefaultTableCellRenderer;
import java.text.SimpleDateFormat;

/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 21.06.13
 * Time: 22:28
 * To change this template use File | Settings | File Templates.
 */
public class DateCellRenderer extends DefaultTableCellRenderer {

    private static SimpleDateFormat dateFormat = null;

    public DateCellRenderer(String datePattern) {
        super();
        dateFormat = new SimpleDateFormat(datePattern);
    }

    @Override
    public void setValue(Object value) {
        if (value != null) {
            this.setText(dateFormat.format(value));
        }
    }
}
