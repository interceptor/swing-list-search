package table;

import model.FileStats;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 19.06.13
 * Time: 22:30
 * To change this template use File | Settings | File Templates.
 */
public class FileStatsTableModel extends RowTableModel<FileStats> {

    public FileStatsTableModel(List<FileStatsTableColumns> columns) {
        super(getTableColumnNames(columns));
        setColumnClasses(columns);
        this.setModelEditable(false);
    }

    private static List<String> getTableColumnNames(List<FileStatsTableColumns> columns) {
        List<String> tableColumns = new ArrayList<String>();
        for (FileStatsTableColumns column : columns)
            tableColumns.add(column.getColumnDisplayName());
        return tableColumns;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        FileStats fileStats = getRow(rowIndex);
        Object value = null;
        if (columnIndex == FileStatsTableColumns.FILE_NAME.getColumnIndex()) {
            value = fileStats.getFileName();
        } else if (columnIndex == FileStatsTableColumns.FILE_PATH.getColumnIndex()) {
            value = fileStats.getFilePath();
        } else if (columnIndex == FileStatsTableColumns.FILE_SIZE.getColumnIndex()) {
            value = fileStats.getFileSize();
        } else if (columnIndex == FileStatsTableColumns.FILE_STATUS.getColumnIndex()) {
            value = fileStats.isReadOnly();
        } else if (columnIndex == FileStatsTableColumns.FILE_MODIFIED_DATE.getColumnIndex()) {
            value = fileStats.getLastModified();
        }
        return value;
    }

    public void addRows(List<FileStats> fileStats) {
        for (FileStats fileStat : fileStats) {
            super.addRow(fileStat);
        }
    }

    private void setColumnClasses(List<FileStatsTableColumns> columns) {
        for (FileStatsTableColumns column : columns) {
            this.setColumnClass(column.getColumnIndex(), column.getColumnClass());
        }
    }
}
