import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import io.TestFiles;
import list.FilteredList;

/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 19.02.13
 * Time: 17:12
 * To change this template use File | Settings | File Templates.
 */
public class SearchApp implements Runnable {

//    private String[] listItems = {"Mike", "JiJi", "Manu", "Mum", "Juergen", "Ted"};
      private ArrayList<String> listItems = TestFiles.getTestFileNames();

    @Override
    public void run() {
        JFrame frame = new JFrame ("Search App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FilteredList filteredList = new FilteredList();
        for (String item : listItems) {
            filteredList.addItem(item);
        }
        JScrollPane scrollPane = new JScrollPane(filteredList, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.getContentPane().add(filteredList.getFilterListField(), BorderLayout.NORTH);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SearchApp searchApp = new SearchApp();
        SwingUtilities.invokeLater(searchApp);
    }
}
