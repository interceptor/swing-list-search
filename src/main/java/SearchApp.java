import io.TestFiles;
import list.FilteredList;
import model.FileStats;
import table.FileStatsTable;
import table.FileStatsTableModel;
import table.FileStatsTableColumns;

import javax.swing.*;


import java.awt.*;
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
        JScrollPane scrollPane = null;
        if (runOptions == RunOptions.LIST) {
            scrollPane = new JScrollPane(getFilteredList(), ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        } else if (runOptions == RunOptions.TABLE) {
            scrollPane = new JScrollPane(getListTable(fileStats, Arrays.asList(FileStatsTableColumns.values())), ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        }
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.getContentPane().add(getFilteredList().getFilterListField(), BorderLayout.NORTH);
        frame.setPreferredSize(new Dimension(1000, 600));
        frame.pack();
        frame.setVisible(true);
    }

    private FileStatsTable getListTable(ArrayList<FileStats> fileStats, java.util.List<FileStatsTableColumns> tableColumns) {
        FileStatsTableModel tableModel = new FileStatsTableModel(tableColumns);
        tableModel.addRows(fileStats);
        return new FileStatsTable(tableModel);
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
