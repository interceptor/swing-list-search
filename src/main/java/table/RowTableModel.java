package table;

import java.util.*;
import javax.swing.table.*;

/**
 *  A TableModel that better supports the processing of rows of data. That
 *  is, the data is treated more like a row than an individual cell.
 *  This class can be used as a parent class instead of extending the
 *  AbstractTableModel when you need custom models that contain row related
 *  data.
 */
public abstract class RowTableModel<T> extends AbstractTableModel {

    private List<T> modelData;
    private List<String> columnNames;
    private Map<Integer, Class> columnClasses;
    private List<Boolean> editableColumns;
	private boolean isModelEditable = false;

    /**
     *  Constructs a <code>RowTableModel</code> with column names.
     *
     *  Each column's name will be taken from the <code>columnNames</code>
     *  List and the number of columns is determined by the number of items
     *  in the <code>columnNames</code> List.
     *
     * @param columnNames	   <code>List</code> containing the names
     *							of the new columns
     */
    public RowTableModel(List<String> columnNames) {
        this(new ArrayList<T>(), columnNames);
    }

    /**
     *  Constructs a <code>RowTableModel</code> with initial data and
     *  customized column names.
     *
     *  Each item in the <code>modelData</code> List must also be a List Object
     *  containing items for each column of the row.
     *
     *  Each column's name will be taken from the <code>columnNames</code>
     *  List and the number of columns is determined by the number of items
     *  in the <code>columnNames</code> List.
     *
     * @param modelData		 the data of the table
     * @param columnNames	   <code>List</code> containing the names
     *							of the new columns
     */
    public RowTableModel(List<T> modelData, List<String> columnNames) {
        setDataAndColumnNames(modelData, columnNames);
    }

	/**
	 *  Reset the data and column names of the model.
	 *
	 *	A fireTableStructureChanged event will be fired.
	 *
	 * @param modelData		 the data of the table
	 * @param columnNames	   <code>List</code> containing the names
	 *							of the new columns
	 */
	protected void setDataAndColumnNames(List<T> modelData, List<String> columnNames) {
		this.modelData = modelData;
		this.columnNames = columnNames;
		columnClasses = new HashMap<Integer, Class>(getColumnCount());
		editableColumns = new ArrayList<Boolean>(getColumnCount());
		fireTableStructureChanged();
	}

//
//  Implement the TableModel interface
//
	/**
	 *  Returns the Class of the queried <code>column</code>.

	 *  First it will check to see if a Class has been specified for the
	 *  <code>column</code> by using the <code>setColumnClass</code> method.
	 *  If not, then the superclass value is returned.
	 *
	 *  @param columnIndex  the column being queried
	 *  @return the Class of the column being queried
	*/
    @Override
	public Class getColumnClass(int columnIndex) {
		Class columnClass;
		//  Get the class, if set, for the specified column
            columnClass = columnClasses.get(columnIndex);
		//  Get the default class
		if (columnClass == null) {
            columnClass = super.getColumnClass(columnIndex);
        }
		return columnClass;
	}

	/**
	 * Returns the number of columns in this table model.
	 *
	 * @return the number of columns in the model
	 */
    @Override
	public int getColumnCount() {
		return columnNames.size();
	}

	/**
	 * Returns the column name.
	 *
	 * @return a name for this column using the string value of the
	 * appropriate member in <code>columnNames</code>. If
	 * <code>columnNames</code> does not have an entry for this index
	 * then the default name provided by the superclass is returned
	 */
    @Override
	public String getColumnName(int column) {
		Object columnName = null;
		if (column < columnNames.size()) {
			columnName = columnNames.get( column );
		}
		return (columnName == null) ? super.getColumnName(column) : columnName.toString();
	}

	/**
	 * Returns the number of rows in this table model.
	 *
	 * @return the number of rows in the model
	 */
    @Override
	public int getRowCount() {
		return modelData.size();
	}

	/**
	 * Returns true regardless of parameter values.
	 *
	 * @param   row		   the row whose value is to be queried
	 * @param   columnIndex		the column whose value is to be queried
	 * @return				  true
	 */
    @Override
	public boolean isCellEditable(int row, int columnIndex) {
		Boolean isEditable = null;
		//  Check is column editability has been set
        if (editableColumns.size() >= columnIndex) {
            isEditable = editableColumns.get(columnIndex);
        }
		return (isEditable == null) ? isModelEditable : isEditable;
	}
//
//  Implement custom methods
//
	/**
	 *  Adds a row of data to the end of the model.
	 *  Notification of the row being added will be fired.
	 *
	 * @param   rowData		 data of the row being added
	 */
	public void addRow(T rowData) {
		insertRow(getRowCount(), rowData);
	}

	/**
	 * Returns the Object of the requested <code>row</code>.
	 *
	 * @return the Object of the requested row.
	 */
	public T getRow(int row) {
		return modelData.get( row );
	}

	/**
	 * Returns a List of Objects for the requested <code>rows</code>.
	 *
	 * @return a List of Objects for the requested rows.
	 */
	public List<T> getRowsAsList(int... rows) {
		ArrayList<T> rowData = new ArrayList<T>(rows.length);
		for (int i = 0; i < rows.length; i++) {
			rowData.add(getRow(rows[i]));
		}
		return rowData;
	}

	/**
	 *  Insert a row of data at the <code>row</code> location in the model.
	 *  Notification of the row being added will be generated.
	 *
	 *  @param   row	  row in the model where the data will be inserted
	 *  @param   rowData  data of the row being added
	 */
	public void insertRow(int row, T rowData) {
		modelData.add(row, rowData);
		fireTableRowsInserted(row, row);
	}

	/**
	 *  Insert multiple rows of data at the <code>row</code> location in the model.
	 *  Notification of the row being added will be generated.
	 *
	 * @param   row	  row in the model where the data will be inserted
	 * @param   rowList  each item in the list is a separate row of data
	 */
	public void insertRows(int row, List<T> rowList) {
		modelData.addAll(row, rowList);
		fireTableRowsInserted(row, row + rowList.size() - 1);
	}

	/**
	 *  Insert multiple rows of data at the <code>row</code> location in the model.
	 *  Notification of the row being added will be generated.
	 *
	 * @param   row	  row in the model where the data will be inserted
	 * @param   rowArray  each item in the Array is a separate row of data
	 */
	public void insertRows(int row, T[] rowArray) {
		List<T> rowList = new ArrayList<T>(rowArray.length);
		for (int i = 0; i < rowArray.length; i++) {
			rowList.add( rowArray[i] );
		}
		insertRows(row, rowList);
	}

	/**
	 *  Moves one or more rows from the inlcusive range <code>start</code> to
	 *  <code>end</code> to the <code>to</code> position in the model.
	 *  After the move, the row that was at index <code>start</code>
	 *  will be at index <code>to</code>.
	 *  This method will send a <code>tableRowsUpdated</code> notification
	 *  message to all the listeners. <p>
	 *
	 *  <pre>
	 *  Examples of moves:
	 *  <p>
	 *  1. moveRow(1,3,5);
	 *		  a|B|C|D|e|f|g|h|i|j|k   - before
	 *		  a|e|f|g|h|B|C|D|i|j|k   - after
	 *  <p>
	 *  2. moveRow(6,7,1);
	 *		  a|b|c|d|e|f|G|H|i|j|k   - before
	 *		  a|G|H|b|c|d|e|f|i|j|k   - after
	 *  <p>
	 *  </pre>
	 *
	 * @param   start	   the starting row index to be moved
	 * @param   end		 the ending row index to be moved
	 * @param   to		  the destination of the rows to be moved
	 * @exception  IllegalArgumentException
	 *					  if any of the elements would be moved out
	 *					  of the table's range
	 */
	public void moveRow(int start, int end, int to) {
		if (start < 0) {
			String message = "Start index must be positive: " + start;
			throw new IllegalArgumentException( message );
		}
		if (end > getRowCount() - 1) {
			String message = "End index must be less than total rows: " + end;
			throw new IllegalArgumentException( message );
		}
		if (start > end) {
			String message = "Start index cannot be greater than end index";
			throw new IllegalArgumentException( message );
		}
		int rowsMoved = end - start + 1;

		if (to < 0 ||  to > getRowCount() - rowsMoved) {
			String message = "New destination row (" + to + ") is invalid";
			throw new IllegalArgumentException( message );
		}
		//  Save references to the rows that are about to be moved
		ArrayList<T> temp = new ArrayList<T>(rowsMoved);
		for (int i = start; i < end + 1; i++) {
			temp.add(modelData.get(i));
		}
		//  Remove the rows from the current location and add them back
		//  at the specified new location
		modelData.subList(start, end + 1).clear();
		modelData.addAll(to, temp);
		//  Determine the rows that need to be repainted to reflect the move
		int first;
		int last;
		if (to < start) {
			first = to;
			last = end;
		} else {
			first = start;
			last = to + end - start;
		}
		super.fireTableRowsUpdated(first, last);
	}

	/**
	 *  Remove the specified rows from the model. Rows between the starting
	 *  and ending indexes, inclusively, will be removed.
	 *  Notification of the rows being removed will be generated.
	 *
	 * @param   start		 starting row index
	 * @param   end		   ending row index
	 * @exception  ArrayIndexOutOfBoundsException
	 *								if any row index is invalid
	 */
	public void removeRowRange(int start, int end) {
		modelData.subList(start, end + 1).clear();
		fireTableRowsDeleted(start, end);
	}

	/**
	 *  Remove the specified rows from the model. The row indexes in the
	 *  array must be in increasing order.
	 *  Notification of the rows being removed will be generated.
	 *
	 * @param   rows  array containing indexes of rows to be removed
	 * @exception  ArrayIndexOutOfBoundsException
	 *				  if any row index is invalid
	 */
	public void removeRows(int... rows) {
		for (int i = rows.length - 1; i >= 0; i--) {
			int row = rows[i];
			modelData.remove(row);
			super.fireTableRowsDeleted(row, row);
		}
	}

	/**
	 *  Replace a row of data at the <code>row</code> location in the model.
	 *  Notification of the row being replaced will be generated.
	 *
	 * @param   row	  row in the model where the data will be replaced
	 * @param   rowData  data of the row to replace the existing data
	 * @exception  IllegalArgumentException  when the Class of the row data
	 *			 does not match the row Class of the model.
	 */
	public void replaceRow(int row, T rowData) {
		modelData.set(row, rowData);
		fireTableRowsUpdated(row, row);
	}

	/**
	 * Sets the Class for the specified column.
	 *
	 * @param  columnIndex	   the column whose Class is being changed
	 * @param  columnClass  the new Class of the column
	 */
	public void setColumnClass(int columnIndex, Class columnClass) {
        if (columnClasses.size() != 0 && columnClasses.size() >= columnIndex) {
            if(columnClasses.containsKey(columnIndex)) {
                columnClasses.remove(columnClasses.get(columnIndex));
                columnClasses.put(columnIndex, columnClass);
            }
        } else {
            columnClasses.put(columnIndex, columnClass);
        }
		fireTableRowsUpdated(0, getRowCount() - 1);
	}

	/**
	 * Sets the editability for the specified column.
	 *
	 * @param  columnIndex	   the column whose Class is being changed
	 * @param  isEditable   indicates if the column is editable or not
	 * @exception  ArrayIndexOutOfBoundsException
	 *						if an invalid column was given
	 */
	public void setColumnEditable(int columnIndex, boolean isEditable) {
		this.editableColumns.set(columnIndex, isEditable);
	}

	/**
	 *  Set the ability to edit cell data for the entire model
	 *
	 *  Note: values set by the setColumnEditable(...) method will have
	 *  prioritiy over this value.
	 *
	 * @param isModelEditable  true/false
	 */
	public void setModelEditable(boolean isModelEditable) {
		this.isModelEditable = isModelEditable;
	}

}
