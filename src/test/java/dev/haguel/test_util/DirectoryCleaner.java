package dev.haguel.test_util;

import java.io.File;

public class DirectoryCleaner {
    public static void deleteFilesInFolders(File directory, String folderNamePart) {
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    if (file.getName().contains(folderNamePart)) {
                    } else {
                        deleteFilesInFolders(file, folderNamePart);
                    }
                }
            }
        }
    }

    public static void deleteFiles(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    file.delete();
                } else if (file.isDirectory()) {
                    deleteFiles(file);
                }
            }
        }
    }
}
