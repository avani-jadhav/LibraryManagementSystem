package operation;

import connection.MySqlConnection;

import java.sql.*;
import java.util.Scanner;

public class ReturnBookOperation {

    Connection con = MySqlConnection.getConnection();
    Scanner sc = new Scanner(System.in);

    public void returnBook() throws SQLException {

        System.out.print("Enter Issue ID : ");
        int issueId=sc.nextInt();

        // Find Book ID
        PreparedStatement ps=con.prepareStatement(
                "SELECT book_id FROM issue_book WHERE issue_id=? AND status='Issued'");

        ps.setInt(1,issueId);

        ResultSet rs=ps.executeQuery();

        if(rs.next()){

            int bookId=rs.getInt("book_id");

            PreparedStatement ps2=con.prepareStatement(
                    "UPDATE issue_book SET status='Returned' WHERE issue_id=?");

            ps2.setInt(1,issueId);

            ps2.executeUpdate();

            PreparedStatement ps3=con.prepareStatement(
                    "UPDATE books SET quantity=quantity+1 WHERE book_id=?");

            ps3.setInt(1,bookId);

            ps3.executeUpdate();

            System.out.println("Book Returned Successfully.");

        }
        else{

            System.out.println("Issue ID Not Found.");

        }

    }

}