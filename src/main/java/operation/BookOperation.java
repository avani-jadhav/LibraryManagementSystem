package operation;

import connection.MySqlConnection;
import java.sql.*;
import java.util.Scanner;

public class BookOperation {

    Connection con = MySqlConnection.getConnection();
    Scanner sc = new Scanner(System.in);

    // ================= ADD BOOK =================

    public void addBook() throws SQLException {

        System.out.print("Enter Book Name : ");
        String bookName = sc.nextLine();

        System.out.print("Enter Author Name : ");
        String authorName = sc.nextLine();

        System.out.print("Enter Publisher : ");
        String publisher = sc.nextLine();

        System.out.print("Enter Price : ");
        double price = sc.nextDouble();

        System.out.print("Enter Quantity : ");
        int quantity = sc.nextInt();
        sc.nextLine();

        String sql = "INSERT INTO books(book_name,author_name,publisher,price,quantity) VALUES(?,?,?,?,?)";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, bookName);
        ps.setString(2, authorName);
        ps.setString(3, publisher);
        ps.setDouble(4, price);
        ps.setInt(5, quantity);

        int rows = ps.executeUpdate();

        if(rows > 0)
            System.out.println("Book Added Successfully.");
        else
            System.out.println("Book Not Added.");
    }

    // ================= DISPLAY BOOKS =================

    public void displayBooks() throws SQLException {

        String sql = "SELECT * FROM books";

        Statement st = con.createStatement();

        ResultSet rs = st.executeQuery(sql);

        System.out.println("\n-------------------------------------------------------------");
        System.out.printf("%-5s %-20s %-20s %-15s %-10s %-10s%n",
                "ID","Book","Author","Publisher","Price","Qty");
        System.out.println("-------------------------------------------------------------");

        while(rs.next()) {

            System.out.printf("%-5d %-20s %-20s %-15s %-10.2f %-10d%n",
                    rs.getInt("book_id"),
                    rs.getString("book_name"),
                    rs.getString("author_name"),
                    rs.getString("publisher"),
                    rs.getDouble("price"),
                    rs.getInt("quantity"));
        }

    }

    // ================= SEARCH BOOK =================

    public void searchBook() throws SQLException {

        System.out.print("Enter Book ID : ");
        int id = sc.nextInt();
        sc.nextLine();

        String sql = "SELECT * FROM books WHERE book_id=?";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        if(rs.next()) {

            System.out.println("\nBook Found");
            System.out.println("Book ID : " + rs.getInt("book_id"));
            System.out.println("Book Name : " + rs.getString("book_name"));
            System.out.println("Author : " + rs.getString("author_name"));
            System.out.println("Publisher : " + rs.getString("publisher"));
            System.out.println("Price : " + rs.getDouble("price"));
            System.out.println("Quantity : " + rs.getInt("quantity"));

        } else {

            System.out.println("Book Not Found.");

        }

    }

    // ================= UPDATE BOOK =================

    public void updateBook() throws SQLException {

        System.out.print("Enter Book ID : ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter New Book Name : ");
        String bookName = sc.nextLine();

        System.out.print("Enter New Author Name : ");
        String authorName = sc.nextLine();

        System.out.print("Enter New Publisher : ");
        String publisher = sc.nextLine();

        System.out.print("Enter New Price : ");
        double price = sc.nextDouble();

        System.out.print("Enter New Quantity : ");
        int quantity = sc.nextInt();
        sc.nextLine();

        String sql = "UPDATE books SET book_name=?, author_name=?, publisher=?, price=?, quantity=? WHERE book_id=?";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, bookName);
        ps.setString(2, authorName);
        ps.setString(3, publisher);
        ps.setDouble(4, price);
        ps.setInt(5, quantity);
        ps.setInt(6, id);

        int rows = ps.executeUpdate();

        if(rows > 0)
            System.out.println("Book Updated Successfully.");
        else
            System.out.println("Book ID Not Found.");

    }

    // ================= DELETE BOOK =================

    public void deleteBook() throws SQLException {

        System.out.print("Enter Book ID : ");
        int id = sc.nextInt();
        sc.nextLine();

        String sql = "DELETE FROM books WHERE book_id=?";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, id);

        int rows = ps.executeUpdate();

        if(rows > 0)
            System.out.println("Book Deleted Successfully.");
        else
            System.out.println("Book ID Not Found.");

    }

}