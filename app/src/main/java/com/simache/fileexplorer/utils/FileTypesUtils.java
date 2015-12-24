package com.simache.fileexplorer.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @auth Administrator
 * @date 2015/12/21 0021
 * @copyright aispeech.com
 */
public class FileTypesUtils {

    public static List<String> getFilesList(String filePath) {
        List<String> filesList = new ArrayList<>();
        String[] list = null;
        File file = new File(filePath);
        if (file.isDirectory()) {
            list = file.list();
        }

        if (null != list) {
            for (String s : list) {
                filesList.add(s);
            }
            filesList = Arrays.asList(list);
        }

        return filesList;
    }
}
