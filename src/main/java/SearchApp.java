import event.TimedEventSinkUtil;
import io.TestFiles;
import list.FilteredList;
import model.FileStats;
import table.FileStatsTable;
import table.FileStatsTableModel;
import table.FileStatsTableColumns;
import table.util.TableColumnAutoResizer;

import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;
import java.util.ArrayList;


/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 19.02.13
 * Time: 17:12
 * To change this template use File | Settings | File Templates.
 */
public class SearchApp implements Runnable {

    public static final String DIRECTORY_PATH = "D:/web-downloads";
    private static RunOptions runOptions;
    private ArrayList<String> listItems = TestFiles.getTestFileNames(DIRECTORY_PATH);
    private ArrayList<FileStats> fileStats = TestFiles.getFileStatsList(DIRECTORY_PATH);

    public SearchApp() {
        runOptions = RunOptions.TABLE;
    }

    public static void main(String[] args) {
        if (args[0].equals("table")) {
            runOptions = RunOptions.TABLE;
        } else if (args[0].equals("list")) {
            runOptions = RunOptions.LIST;
        }
        SearchApp searchApp = new SearchApp();
        SwingUtilities.invokeLater(searchApp);
    }

    @Override
    public void run() {
        JFrame frame = new JFrame("Search App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JScrollPane tableScrollpane = null;
        if (runOptions == RunOptions.LIST) {
            tableScrollpane = new JScrollPane(getFilteredList(), ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        } else if (runOptions == RunOptions.TABLE) {
            tableScrollpane = new JScrollPane(getListTable(fileStats, Arrays.asList(FileStatsTableColumns.values())), ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        }
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(tableScrollpane, BorderLayout.CENTER);
        frame.getContentPane().add(getFilteredList().getFilterListField(), BorderLayout.NORTH);
        frame.getContentPane().add(getControlPanel(), BorderLayout.SOUTH);
        frame.setPreferredSize(new Dimension(1600, 800));
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel getControlPanel() {
         JPanel controlPanel = new JPanel();
        JButton button = new JButton("Generate Test Events");
        button.setAction(new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {
            }

            @Override
            public void setEnabled(boolean b) {
            }

            @Override
            public boolean isEnabled() {
                return true;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {
            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                AWTEvent event = TimedEventSinkUtil.sinkEvent(new ActionEvent(this, 0, null), 2000);
                System.out.println(String.format("Returned Event is [%s]", event));
            }
        });
        controlPanel.add(button) ;
        return controlPanel;
    }

    private FileStatsTable getListTable(ArrayList<FileStats> fileStats, java.util.List<FileStatsTableColumns> tableColumns) {
        FileStatsTableModel tableModel = new FileStatsTableModel(tableColumns);
        FileStatsTable fileStatsTable = new FileStatsTable(tableModel);
        tableModel.addRows(fileStats);
        TableColumnAutoResizer.adjustColumnPreferredWidth(fileStatsTable);
        return fileStatsTable;
    }

    private FilteredList getFilteredList() {
        FilteredList filteredList = new FilteredList();
        for (String item : listItems) {
            filteredList.addItem(item);
        }
        return filteredList;
    }

    private enum RunOptions {
        TABLE,
        LIST
    }
}
