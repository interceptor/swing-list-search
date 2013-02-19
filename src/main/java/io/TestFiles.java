package io;

import java.io.File;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 19.02.13
 * Time: 19:44
 * To change this template use File | Settings | File Templates.
 */
public class TestFiles {

    //ctor
    public  TestFiles() {
    }

    public static ArrayList<String> getTestFileNames() {
        ArrayList<String> testFileNames = new ArrayList<String>();
        File testPath = new File("d:/web-downloads");
        if(testPath.exists()) {
            if(testPath.isDirectory()){
                File[] files = testPath.listFiles();
                for (int i = 0; i < files.length; i++) {
                    String fileName = files[i].getName();
                    testFileNames.add(fileName);
                }
            }
        }
        return testFileNames;
    }

}
