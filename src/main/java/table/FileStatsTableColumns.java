package table;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 21.06.13
 * Time: 23:25
 * To change this template use File | Settings | File Templates.
 */
public enum FileStatsTableColumns implements TableColumn {

    FILE_NAME(0, "File Name", String.class),
    FILE_PATH(1, "File Path", String.class),
    FILE_SIZE(2, "File Size", Long.class),
    FILE_STATUS(3, "File Status", Boolean.class),
    FILE_MODIFIED_DATE(4, "Date Modified", Date.class);
    private final int columnIndex;
    private final String columnDisplayName;
    private final Class columnClass;

    private FileStatsTableColumns(int columnIndex, String columnDisplayName, Class<?> columnClass) {
        this.columnIndex = columnIndex;
        this.columnDisplayName = columnDisplayName;
        this.columnClass = columnClass;
    }

    @Override
    public Class<?> getColumnClass() {
        return columnClass;
    }

    @Override
    public int getColumnIndex() {
        return columnIndex;
    }

    @Override
    public String getColumnDisplayName() {
        return columnDisplayName;
    }
}
