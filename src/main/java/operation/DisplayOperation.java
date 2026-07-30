package operation;

import connection.MySqlConnection;

import java.sql.*;

public class DisplayOperation {

    Connection con = MySqlConnection.getConnection();

    public void displayIssuedBooks() throws SQLException {

        String sql="SELECT issue_id,book_name,member_name,issue_date,return_date,status "
                +"FROM issue_book "
                +"JOIN books ON issue_book.book_id=books.book_id "
                +"JOIN members ON issue_book.member_id=members.member_id";

        Statement st=con.createStatement();

        ResultSet rs=st.executeQuery(sql);

        System.out.println("-----------------------------------------------------------------------------------------------");

        System.out.printf("%-5s %-20s %-20s %-15s %-15s %-10s%n",
                "ID","Book","Member","Issue Date","Return Date","Status");

        System.out.println("-----------------------------------------------------------------------------------------------");

        while(rs.next()){

            System.out.printf("%-5d %-20s %-20s %-15s %-15s %-10s%n",

                    rs.getInt("issue_id"),
                    rs.getString("book_name"),
                    rs.getString("member_name"),
                    rs.getDate("issue_date"),
                    rs.getDate("return_date"),
                    rs.getString("status"));

        }

    }
    public void showAvailableBooks() throws SQLException {

        String sql = "SELECT * FROM books WHERE quantity > 0";

        Statement st = con.createStatement();

        ResultSet rs = st.executeQuery(sql);

        System.out.println("\n================ AVAILABLE BOOKS ================");

        System.out.printf("%-5s %-20s %-20s %-15s %-10s %-10s%n",
                "ID","Book","Author","Publisher","Price","Qty");

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
    
    public void showReturnedBooks() throws SQLException {

        String sql =
                "SELECT issue_id, book_name, member_name, issue_date, return_date " +
                "FROM issue_book " +
                "JOIN books ON issue_book.book_id = books.book_id " +
                "JOIN members ON issue_book.member_id = members.member_id " +
                "WHERE status='Returned'";

        Statement st = con.createStatement();

        ResultSet rs = st.executeQuery(sql);

        System.out.println("\n================ RETURNED BOOKS ================");

        System.out.printf("%-5s %-20s %-20s %-15s %-15s%n",
                "ID","Book","Member","Issue Date","Return Date");

        while(rs.next()) {

            System.out.printf("%-5d %-20s %-20s %-15s %-15s%n",

                    rs.getInt("issue_id"),
                    rs.getString("book_name"),
                    rs.getString("member_name"),
                    rs.getDate("issue_date"),
                    rs.getDate("return_date"));
        }

    }

}