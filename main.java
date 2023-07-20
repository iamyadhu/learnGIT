

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class topFiles {

    public static void copyFiles(List<String> sF, String destinationFolder) {
        for (String filePath : sF) {
            try {
                Path sourcePath = Paths.get(filePath);
                Path destinationPath = Paths.get(destinationFolder, sourcePath.getFileName().toString());
                Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // Replace 'C:\\' with the path to your C drive
        String cDrivePath = "C:\"";

        // Get the list of largest files
        List<String> largestFiles = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("largest_files.txt"))) {
            while (scanner.hasNextLine()) {
                String filePath = scanner.nextLine().trim();
                largestFiles.add(filePath);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        // Get the list of smallest files
        List<String> smallestFiles = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("smallest_files.txt"))) {
            while (scanner.hasNextLine()) {
                String filePath = scanner.nextLine().trim();
                smallestFiles.add(filePath);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        // Create the destination folder for copying the files
        String destinationFolder = "C:\\CopiedFiles";
        try {
            Files.createDirectories(Paths.get(destinationFolder));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Copy the top 10 largest and top 10 smallest files to the destination folder
        copyFiles(largestFiles.subList(0, Math.min(10, largestFiles.size())), destinationFolder);
        copyFiles(smallestFiles.subList(0, Math.min(10, smallestFiles.size())), destinationFolder);

        System.out.println("Files copied successfully to " + destinationFolder);
    }
}
