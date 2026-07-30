import java.sql.SQLException;
import java.util.Scanner;

import operation.BookOperation;
import operation.DisplayOperation;
import operation.IssueBookOperation;
import operation.MemberOperation;
import operation.ReturnBookOperation;

public class Main {

    public static void main(String[] args) throws SQLException {

        Scanner sc = new Scanner(System.in);

        BookOperation book = new BookOperation();
        MemberOperation member = new MemberOperation();
        IssueBookOperation issue = new IssueBookOperation();
        ReturnBookOperation ret = new ReturnBookOperation();
        DisplayOperation display = new DisplayOperation();

        while (true) {

            System.out.println("\n========== LIBRARY MANAGEMENT SYSTEM ==========");
            System.out.println("1. Add Book");
            System.out.println("2. Display Books");
            System.out.println("3. Search Book");
            System.out.println("4. Update Book");
            System.out.println("5. Delete Book");

            System.out.println("-------------------------------------");

            System.out.println("6. Add Member");
            System.out.println("7. Display Members");
            System.out.println("8. Search Member");
            System.out.println("9. Update Member");
            System.out.println("10. Delete Member");

            System.out.println("-------------------------------------");

            System.out.println("11. Issue Book");
            System.out.println("12. Return Book");
            System.out.println("13. Display Issued Books");
            System.out.println("13. Display Issued Books");
            System.out.println("14. Show Available Books");
            System.out.println("15. Show Returned Books");

        
            System.out.println("16. Exit");

            System.out.print("\nEnter your choice : ");
            int choice = sc.nextInt();

            switch (choice) {

                // BOOK

                case 1:
                    book.addBook();
                    break;

                case 2:
                    book.displayBooks();
                    break;

                case 3:
                    book.searchBook();
                    break;

                case 4:
                    book.updateBook();
                    break;

                case 5:
                    book.deleteBook();
                    break;

                // MEMBER

                case 6:
                    member.addMember();
                    break;

                case 7:
                    member.displayMembers();
                    break;

                case 8:
                    member.searchMember();
                    break;

                case 9:
                    member.updateMember();
                    break;

                case 10:
                    member.deleteMember();
                    break;

                // ISSUE BOOK

                case 11:
                    issue.issueBook();
                    break;

                // RETURN BOOK

                case 12:
                    ret.returnBook();
                    break;

                // DISPLAY ISSUED BOOKS

                case 13:
                    display.displayIssuedBooks();
                    break;
                    
                case 14:
                    display.showAvailableBooks();
                    break;

                case 15:
                    display.showReturnedBooks();
                    break;

                case 16:
                    System.out.println("Thank You!");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}