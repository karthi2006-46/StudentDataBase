import java.sql.*;
import java.util.*;

public class Studentmanager {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Add Student\n2. View Students\n3. Update Student\n4. Delete Student\n5. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1: addStudent(); break;
                case 2: viewStudents(); break;
                case 3: updateStudent(); break;
                case 4: deleteStudent(); break;
                case 5: System.exit(0);
            
                default: System.out.println("Invalid Choice!");
            }
        }
    }

    static void addStudent() {
        try {
            Connection conn = DBCONNECTION.getConnection();
            PreparedStatement pst = conn.prepareStatement("INSERT INTO students1(name, age, course, year) VALUES (?, ?, ?, ?)");

            System.out.print("Enter Name: ");
            String name = sc.next();
            System.out.print("Enter Age: ");
            int age = sc.nextInt();
            System.out.print("Enter Course: ");
            String course = sc.next();
            System.out.print("Enter year: ");
            int year = sc.nextInt();

            pst.setString(1, name);
            pst.setInt(2, age);
            pst.setString(3, course);
            pst.setInt(4, year);

            pst.executeUpdate();
            System.out.println("Student Added!");
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static void viewStudents() {
        try {
            Connection conn = DBCONNECTION.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM students1");

            System.out.println("\nID | Name | Age | Course | Year");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | " + rs.getString("name") + " | " + rs.getInt("age") + " | " + rs.getString("course") + " | " + rs.getInt("year"));
            }

            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static void updateStudent() {
        try {
            Connection conn = DBCONNECTION.getConnection();
            System.out.print("Enter ID to update: ");
            int id = sc.nextInt();

            PreparedStatement pst = conn.prepareStatement("UPDATE students1 SET name=?, age=?, course=?, year=? WHERE id=?");

            System.out.print("New Name: ");
            String name = sc.next();
            System.out.print("New Age: ");
            int age = sc.nextInt();
            System.out.print("New Course: ");
            String course = sc.next();
            System.out.print("New year: ");
            int year = sc.nextInt();

            pst.setString(1, name);
            pst.setInt(2, age);
            pst.setString(3, course);
            pst.setInt(4, year);
            pst.setInt(5, id);

            int i = pst.executeUpdate();
            if (i > 0) System.out.println("Student Updated!");
            else System.out.println("ID not found!");

            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static void deleteStudent() {
        try {
            Connection conn = DBCONNECTION.getConnection();
            System.out.print("Enter ID to delete: ");
            int id = sc.nextInt();

            PreparedStatement pst = conn.prepareStatement("DELETE FROM students1 WHERE id=?");
            pst.setInt(1, id);
            int i = pst.executeUpdate();

            if (i > 0) System.out.println("Student Deleted!");
            else System.out.println("ID not found!");

            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
