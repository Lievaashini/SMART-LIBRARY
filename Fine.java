public class Fine {
    
    private int isbn;
    private String studentName;
    private int overdueDays;
    private double amount;

    public Fine(int isbn, String studentName, int overdueDays) {
        this.isbn = isbn;
        this.studentName = studentName;
        this.overdueDays = overdueDays;
        this.amount = overdueDays * 0.5;
    }

    public int getIsbn() {
        return isbn;
    }

    public String getStudentName() {
        return studentName;
    }

    public int getOverdueDays() {
        return overdueDays;
    }

    public double getAmount() {
        return amount;
    }

    public void displayFine() {
        System.out.println("ISBN: " + isbn);
        System.out.println("Student Name: " + studentName);
        System.out.println("Overdue Days: " + overdueDays);
        System.out.println("Fine Amount: RM" + String.format("%.2f", amount));
    }
}
