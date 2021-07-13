package com.webencyclop.core.mftool.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

public class IOHelper {

    private IOHelper(){}

    private static final String FOLDER = "data";

    private static Path getFilePath(String fileName) {
        var date = LocalDate.now();
        var postfix = "" + date.getDayOfYear() + date.getMonthValue() + date.getDayOfMonth();
        return Paths.get(FOLDER + File.separator + fileName + postfix);
    }

    public static boolean isFilePresent(String fileName) {
        return Files.exists(getFilePath(fileName));
    }

}
