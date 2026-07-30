package operation;

import connection.MySqlConnection;
import java.sql.*;
import java.util.Scanner;

public class IssueBookOperation {

    Connection con = MySqlConnection.getConnection();
    Scanner sc = new Scanner(System.in);

    public void issueBook() throws SQLException {

        System.out.print("Enter Book ID : ");
        int bookId = sc.nextInt();

        System.out.print("Enter Member ID : ");
        int memberId = sc.nextInt();
        sc.nextLine();

        // ================= CHECK BOOK =================

        PreparedStatement ps1 = con.prepareStatement(
                "SELECT quantity FROM books WHERE book_id=?");

        ps1.setInt(1, bookId);

        ResultSet rs1 = ps1.executeQuery();

        if (!rs1.next()) {
            System.out.println("Book ID does not exist.");
            return;
        }

        int quantity = rs1.getInt("quantity");

        if (quantity <= 0) {
            System.out.println("Book is currently not available.");
            return;
        }

        // ================= CHECK MEMBER =================

        PreparedStatement ps2 = con.prepareStatement(
                "SELECT member_id FROM members WHERE member_id=?");

        ps2.setInt(1, memberId);

        ResultSet rs2 = ps2.executeQuery();

        if (!rs2.next()) {
            System.out.println("Member ID does not exist.");
            return;
        }

        // ================= ISSUE DETAILS =================

        System.out.print("Enter Issue Date (yyyy-mm-dd): ");
        String issueDate = sc.nextLine();

        System.out.print("Enter Return Date (yyyy-mm-dd): ");
        String returnDate = sc.nextLine();

        // ================= INSERT ISSUE RECORD =================

        String sql = "INSERT INTO issue_book(book_id, member_id, issue_date, return_date, status) VALUES(?,?,?,?,?)";

        PreparedStatement ps3 = con.prepareStatement(sql);

        ps3.setInt(1, bookId);
        ps3.setInt(2, memberId);
        ps3.setDate(3, Date.valueOf(issueDate));
        ps3.setDate(4, Date.valueOf(returnDate));
        ps3.setString(5, "Issued");

        int rows = ps3.executeUpdate();

        if (rows > 0) {

            // Reduce Book Quantity
            PreparedStatement ps4 = con.prepareStatement(
                    "UPDATE books SET quantity = quantity - 1 WHERE book_id=?");

            ps4.setInt(1, bookId);
            ps4.executeUpdate();

            System.out.println("Book Issued Successfully.");

        } else {

            System.out.println("Book could not be issued.");

        }
    }
}