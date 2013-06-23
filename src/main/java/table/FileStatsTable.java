package table;

import javax.swing.*;
import javax.swing.table.TableColumn;

/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 19.06.13
 * Time: 21:08
 * To change this template use File | Settings | File Templates.
 */
public class FileStatsTable extends JTable {

    private final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    public FileStatsTable(FileStatsTableModel fileStatsTableModel) {
        this.setModel(fileStatsTableModel);
        this.setAutoCreateRowSorter(true);
        TableColumn fileModifiedDateColumn = this.getColumn(FileStatsTableColumns.FILE_MODIFIED_DATE.getColumnDisplayName());
        fileModifiedDateColumn.setCellRenderer(new DateCellRenderer(DATE_FORMAT));
        TableColumn fileSizeColumn = this.getColumn(FileStatsTableColumns.FILE_SIZE.getColumnDisplayName());
        fileSizeColumn.setCellRenderer(new FileSizeCellRenderer());
    }

}
