package io;

import model.FileStats;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 19.02.13
 * Time: 19:44
 * To change this template use File | Settings | File Templates.
 */
public class TestFiles {

    //ctor
    public TestFiles() {
    }

    public static ArrayList<String> getTestFileNames(String directoryPath) {
        ArrayList<String> testFileNames = new ArrayList<String>();
        for (File file : getFilesFromDirectory(directoryPath)) {
            String fileName = file.getName();
            testFileNames.add(fileName);
        }
        return testFileNames;
    }

    public static ArrayList<FileStats> getFileStatsList(String directoryPath) {
        ArrayList<FileStats> fileStats = new ArrayList<FileStats>(42);
        for (File file : getFilesFromDirectory(directoryPath)) {
            BasicFileAttributes fileAttributes = getAttributes(file.getPath());
            fileStats.add(new FileStats(file.getName(), file.getAbsolutePath(), fileAttributes.size(), false, new Date(fileAttributes.lastModifiedTime().toMillis())));
        }
        return fileStats;
    }

    private static File[] getFilesFromDirectory(String directoryPath) {
        File[] files = null;
        File testPath = new File(directoryPath);
        if (testPath.exists()) {
            if (testPath.isDirectory()) {
                files = testPath.listFiles();
            }
        }
        return files;
    }

    private static BasicFileAttributes getAttributes(String pathStr) {
        BasicFileAttributes fileAttributes = null;
        try {
            fileAttributes = Files.getFileAttributeView(Paths.get(pathStr), BasicFileAttributeView.class).readAttributes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileAttributes;
    }

}
