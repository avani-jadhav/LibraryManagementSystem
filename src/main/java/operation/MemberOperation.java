package operation;

import connection.MySqlConnection;
import java.sql.*;
import java.util.Scanner;

public class MemberOperation {

    Connection con = MySqlConnection.getConnection();
    Scanner sc = new Scanner(System.in);

    // ================= ADD MEMBER =================

    public void addMember() throws SQLException {

        System.out.print("Enter Member Name : ");
        String name = sc.nextLine();

        System.out.print("Enter Email : ");
        String email = sc.nextLine();

        System.out.print("Enter Mobile : ");
        String mobile = sc.nextLine();

        System.out.print("Enter Address : ");
        String address = sc.nextLine();

        String sql = "INSERT INTO members(member_name,email,mobile,address) VALUES(?,?,?,?)";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, name);
        ps.setString(2, email);
        ps.setString(3, mobile);
        ps.setString(4, address);

        int rows = ps.executeUpdate();

        if(rows > 0)
            System.out.println("Member Added Successfully.");
        else
            System.out.println("Member Not Added.");
    }

    // ================= DISPLAY MEMBERS =================

    public void displayMembers() throws SQLException {

        String sql = "SELECT * FROM members";

        Statement st = con.createStatement();

        ResultSet rs = st.executeQuery(sql);

        System.out.println("\n--------------------------------------------------------------------------");
        System.out.printf("%-5s %-20s %-25s %-15s %-20s%n",
                "ID","Name","Email","Mobile","Address");
        System.out.println("--------------------------------------------------------------------------");

        while(rs.next()) {

            System.out.printf("%-5d %-20s %-25s %-15s %-20s%n",
                    rs.getInt("member_id"),
                    rs.getString("member_name"),
                    rs.getString("email"),
                    rs.getString("mobile"),
                    rs.getString("address"));
        }

    }

    // ================= SEARCH MEMBER =================

    public void searchMember() throws SQLException {

        System.out.print("Enter Member ID : ");
        int id = sc.nextInt();
        sc.nextLine();

        String sql = "SELECT * FROM members WHERE member_id=?";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        if(rs.next()) {

            System.out.println("\nMember Found");
            System.out.println("Member ID : " + rs.getInt("member_id"));
            System.out.println("Member Name : " + rs.getString("member_name"));
            System.out.println("Email : " + rs.getString("email"));
            System.out.println("Mobile : " + rs.getString("mobile"));
            System.out.println("Address : " + rs.getString("address"));

        } else {

            System.out.println("Member Not Found.");

        }

    }

    // ================= UPDATE MEMBER =================

    public void updateMember() throws SQLException {

        System.out.print("Enter Member ID : ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter New Member Name : ");
        String name = sc.nextLine();

        System.out.print("Enter New Email : ");
        String email = sc.nextLine();

        System.out.print("Enter New Mobile : ");
        String mobile = sc.nextLine();

        System.out.print("Enter New Address : ");
        String address = sc.nextLine();

        String sql = "UPDATE members SET member_name=?, email=?, mobile=?, address=? WHERE member_id=?";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, name);
        ps.setString(2, email);
        ps.setString(3, mobile);
        ps.setString(4, address);
        ps.setInt(5, id);

        int rows = ps.executeUpdate();

        if(rows > 0)
            System.out.println("Member Updated Successfully.");
        else
            System.out.println("Member ID Not Found.");

    }

    // ================= DELETE MEMBER =================

    public void deleteMember() throws SQLException {

        System.out.print("Enter Member ID : ");
        int id = sc.nextInt();
        sc.nextLine();

        String sql = "DELETE FROM members WHERE member_id=?";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, id);

        int rows = ps.executeUpdate();

        if(rows > 0)
            System.out.println("Member Deleted Successfully.");
        else
            System.out.println("Member ID Not Found.");

    }

}