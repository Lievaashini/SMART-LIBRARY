import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
public class FineManager {
    private ArrayList<Fine> fines;
    
    public FineManager() {
        fines = new ArrayList<>();
    }

    public void loadFinesFromFile() {
        try (Scanner sc = new Scanner(new File("fines.txt"))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] data = line.split(",");
                if (data.length == 3) {
                    int isbn = Integer.parseInt(data[0]);
                    String studentName = data[1];
                    int overdueDays = Integer.parseInt(data[2]);
                    addFine(isbn, studentName, overdueDays);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    public void saveFinesToFile() {
        try (PrintWriter pw = new PrintWriter(new File("fines.txt"))) {
            for (Fine fine : fines) {
                pw.println(fine.getIsbn() + "," + fine.getStudentName() + "," + fine.getOverdueDays());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error saving fines: " + e.getMessage());
        }
    }

    public void addFine(int isbn, String studentName, int overdueDays) {
        Fine fine = new Fine(isbn, studentName, overdueDays);
        fines.add(fine);
    }

    public void displayFines() {
        System.out.println("\n--- Fine Records ---");
        if (fines.isEmpty()) {
            System.out.println("No fines recorded.");
        } else {
            for (Fine fine : fines) {
                fine.displayFine();
                System.out.println("--------------------");
            }
        }
    }
}
