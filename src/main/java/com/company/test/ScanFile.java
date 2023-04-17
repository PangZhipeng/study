package com.company.test;

import java.io.File;

public class ScanFile {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\pangzhipeng");
        if (file.isDirectory()){
            for (File listFile : file.listFiles()) {
                long fileSize = listFile.length();
                System.out.println(listFile.getAbsolutePath() + "------------- " + listFile.isDirectory()+"-----------" + listFile.length());
            }
        }
    }

}

