package eu.amaxilat.jubatus.jubaanomaly.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by amaxilatis on 1/10/15.
 */
public class FileUtils {
    public static List<String> readLines(final String fileName) throws IOException {
        final List<String> lines = new ArrayList<>();
        String line = "";

        final BufferedReader br = new BufferedReader(new FileReader(fileName));
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
        return lines;

    }
}
