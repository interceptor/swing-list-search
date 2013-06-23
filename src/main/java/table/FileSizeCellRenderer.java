package table;

import javax.swing.table.DefaultTableCellRenderer;
import java.text.DecimalFormat;

/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 21.06.13
 * Time: 23:53
 * To change this template use File | Settings | File Templates.
 */
public class FileSizeCellRenderer extends DefaultTableCellRenderer {

    private static final String DECIMAL_FORMAT = "#,###,###,##0.00";
    private static final double KB = 1024;
    private static final double MB = 1024 * 1024;
    private static final double GB = 1024 * 1024 * 1024;
    private final DecimalFormat decimalFormat = new DecimalFormat(DECIMAL_FORMAT);

    public FileSizeCellRenderer() {
        super();
    }

    @Override
    public void setValue(Object value) {
        if (value != null) {
            Long fileSize = (Long) value;
            this.setText(fileSize(fileSize));
        }
    }

    private String fileSize(Long fileSizeBytes) {
        String fileSize = "0 bytes";
        if (fileSizeBytes >= GB) {
            fileSize = String.valueOf(decimalFormat.format(fileSizeBytes / GB)) + " GB";
        } else if (fileSizeBytes >= MB) {
            fileSize = String.valueOf(decimalFormat.format(fileSizeBytes / MB)) + " Mb";
        } else if (fileSizeBytes <= MB) {
            fileSize = String.valueOf(decimalFormat.format(fileSizeBytes / KB)) + " kb";
        } else if (fileSizeBytes < KB) {
            fileSize = String.valueOf(fileSizeBytes + "bytes");
        }
        return fileSize;
    }
}
