package table;

/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 22.06.13
 * Time: 15:28
 * To change this template use File | Settings | File Templates.
 */
public interface TableColumn {

    public Class<?> getColumnClass();

    public int getColumnIndex();

    public String getColumnDisplayName();

}
