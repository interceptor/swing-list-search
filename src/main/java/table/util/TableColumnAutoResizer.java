/*
 * Copyright: (C) 2013 CSS Versicherung
 * Name:      $Id: TableColumnResizer.java,v 1.1 2013/06/03 11:22:25 p14589 Exp $
 */
package table.util;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class TableColumnAutoResizer {

   private TableColumnAutoResizer() {
   }

   /**
    * Sets every table column to the width of the widest cell (also takes column header into consideration) That way all column
    * headers and its content are always fully visible
    * 
    * @param JTable table
    */
   public static void adjustColumnPreferredWidth(final JTable table) {
      if (table != null) {
         final TableColumnModel colModel = table.getColumnModel();
         for (int col = 0; col < table.getColumnCount(); col++) {
            int maxWidth = 0;
            for (int row = 0; row < table.getRowCount(); row++) {
               final TableCellRenderer cellRenderer = table.getCellRenderer(row, col);
               final Object value = table.getValueAt(row, col);
               final Component component = cellRenderer.getTableCellRendererComponent(table, value, false, false, row, col);
               maxWidth = Math.max(component.getPreferredSize().width, maxWidth);
            }
            final TableColumn column = colModel.getColumn(col);
            TableCellRenderer headerRenderer = column.getHeaderRenderer();
            if (headerRenderer == null) {
               headerRenderer = table.getTableHeader().getDefaultRenderer();
            }
            final Object headerValue = column.getHeaderValue();
            final Component headerComponent = headerRenderer.getTableCellRendererComponent(table, headerValue, false, false, 0, col);
            maxWidth = Math.max(maxWidth, headerComponent.getPreferredSize().width);
            column.setPreferredWidth(maxWidth);
         }
      }
   }

}
