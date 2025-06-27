package com.app;

import java.io.*;
import java.util.Scanner;

public class NotesApp {

    private static final String FILE_NAME = "notes.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Notes App ---");
            System.out.println("1. Write a Note");
            System.out.println("2. View Notes");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    writeNote(scanner);
                    break;
                case 2:
                    readNotes();
                    break;
                case 3:
                    System.out.println("Exiting Notes App.");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 3);

        scanner.close();
    }

    private static void writeNote(Scanner scanner) {
        System.out.print("Enter your note: ");
        String note = scanner.nextLine();

        try (FileWriter writer = new FileWriter(FILE_NAME, true)) { // true enables append mode
            writer.write(note + System.lineSeparator());
            System.out.println("Note saved successfully.");
        } catch (IOException e) {
            System.err.println("An error occurred while writing the note.");
            e.printStackTrace();
        }
    }

    private static void readNotes() {
        System.out.println("\n--- Your Notes ---");
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            boolean empty = true;
            while ((line = reader.readLine()) != null) {
                empty = false;
                System.out.println("- " + line);
            }
            if (empty) {
                System.out.println("No notes found.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("No notes file found. Create a note first.");
        } catch (IOException e) {
            System.err.println("An error occurred while reading the notes.");
            e.printStackTrace();
        }
    }
}
