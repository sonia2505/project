import java.io.*;

public class FileHandlingDemo {
    public static void main(String[] args) {
        try {
            // Create a directory named "Demo" in the current working directory
            File directory = new File("Demo");
            if (!directory.exists()) {
                if (directory.mkdir()) {
                    System.out.println("Directory created: " + directory.getName());
                } else {
                    System.out.println("Failed to create the directory.");
                    return;
                }
            }

            // Create a text file named "example.txt" inside the "Demo" directory
            File file = new File(directory, "example.txt");
            if (!file.exists()) {
                if (file.createNewFile()) {
                    System.out.println("File created: " + file.getName());
                } else {
                    System.out.println("Failed to create the file.");
                    return;
                }
            }

            // Write some sample text content to the file
            FileWriter writer = new FileWriter(file);
            writer.write("Hello, this is some sample text.");
            writer.close();

            // Read the contents of the file and display them on the console
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;
            System.out.println("Contents of " + file.getName() + ":");
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

            reader.close();

            // Update the content of the file by appending new text
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write("\nThis is the appended text.");
            fileWriter.close();

            // Read the updated contents of the file and display them on the console
            FileReader updatedReader = new FileReader(file);
            BufferedReader updatedBufferedReader = new BufferedReader(updatedReader);

            String updatedLine;
            System.out.println("Updated contents of " + file.getName() + ":");
            while ((updatedLine = updatedBufferedReader.readLine()) != null) {
                System.out.println(updatedLine);
            }

            updatedReader.close();

            // Delete the file "example.txt"
            if (file.delete()) {
                System.out.println("File deleted: " + file.getName());
            } else {
                System.out.println("Failed to delete the file.");
            }

            // Delete the directory "Demo"
            if (directory.delete()) {
                System.out.println("Directory deleted: " + directory.getName());
            } else {
                System.out.println("Failed to delete the directory.");
            }

            // Create a new text file named "byteExample.txt" and write some text using byte streams
            File byteFile = new File("byteExample.txt");
            FileOutputStream byteOutputStream = new FileOutputStream(byteFile);
            byte[] bytes = "This is some text written using byte streams.".getBytes();
            byteOutputStream.write(bytes);
            byteOutputStream.close();

            // Read the contents of "byteExample.txt" using byte streams and display them on the console
            FileInputStream byteInputStream = new FileInputStream(byteFile);
            int data;
            System.out.println("Contents of byteExample.txt:");
            while ((data = byteInputStream.read()) != -1) {
                System.out.print((char) data);
            }
            byteInputStream.close();

            // Create a new text file named "characterExample.txt" and write some text using character streams
            File characterFile = new File("characterExample.txt");
            FileWriter characterWriter = new FileWriter(characterFile);
            characterWriter.write("This is some text written using character streams.");
            characterWriter.close();

            // Read the contents of "characterExample.txt" using character streams and display them on the console
            FileReader characterReader = new FileReader(characterFile);
            BufferedReader characterBufferedReader = new BufferedReader(characterReader);

            String characterLine;
            System.out.println("\nContents of characterExample.txt:");
            while ((characterLine = characterBufferedReader.readLine()) != null) {
                System.out.println(characterLine);
            }

            characterReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}

