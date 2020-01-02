import java.sql.*;

public class javaOracleConnectivity {
    public static void main(String [] args){

        try{
            //load driver (optional!)
            //Class.forName("oracle.jdbc.driver.OracleDriver");

            //ConnectionString
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:abiddb/abid@//localhost:1521/orcl");
                        //here 'orcl' is the service name, 'thin' is the driver,
                        // 'jdbc' is the API, and 'oracle' is the database


            Statement stmt = con.createStatement();

            //execute query
            ResultSet rs = stmt.executeQuery("select * from student");
            while(rs.next())
                System.out.println(rs.getString(1)+"  "+
                        rs.getString(2)+"  "+rs.getString(3)
                + " " + rs.getFloat(4));



            // Specific Query: Query No: 5
            //String Query5 = "select name, semester from instructor i inner join teaches t on i.id = t.id where i.name = 'Dr. Waliur Rahman' and t.semester = 'winter';";

            PreparedStatement stmtQfive = con.prepareStatement("select name, semester from instructor i inner join teaches t on i.id = t.id where i.name =? and t.semester = ?");
            stmtQfive.setString(1, "Dr. Waliur Rahman");
            stmtQfive.setString(2, "winter");

            ResultSet rs2 = stmtQfive.executeQuery();
            rs2.next();
                System.out.println(rs2.getString("name") + " | " +
                        rs2.getString("semester"));


            //inserting value to student table
          /*
            String insertSql = "insert into student values (?,?,?,?)";

            PreparedStatement prstmt = con.prepareStatement(insertSql);

            prstmt.setString(1,"16-0-52-020-25");
            prstmt.setString(2, "Shah jalal");
            prstmt.setString(3, "cse");
            prstmt.setFloat(4, (float) 136.5);
            prstmt.executeQuery();

         */

          /*
            //Deleting data from table
            String delData = "delete from student where id like '%065'";

            Statement stmt2 = con.createStatement();

            int rowAffectedFeed = stmt2.executeUpdate(delData);
            System.out.println(rowAffectedFeed + " row deleted");

           */

            /*
               PL/SQL Trigger: on Student Table
               _______________
               SET SERVEROUTPUT ON;
               create or replace trigger insertingTrigger
               before INSERT or DELETE or UPDATE ON Student
               for each ROW
               enable

               DECLARE
                  myVar VARCHAR(20);
               BEGIN
                    SELECT user into myVar FROM dual;
                    if INSERTING then
                        DBMS_OUTPUT.PUT_LINE('A new row inserted by '|| myVar);
                    elsif deleting then
                        DBMS_OUTPUT.PUT_LINE('Data d eleted from student by '|| myVar);
                    elsif updating then
                        DBMS_OUTPUT.PUT_LINE('A new row updated by '|| myVar);
                    end if;
               END;
                /
            */

          //Calling PL/SQL in java
            /*
            CallableStatement myCall = con.prepareCall("{CALL log_insertingTrigger}");
            myCall.execute();

            String trigRs = myCall.getString("{}");
            System.out.println(trigRs);
            */

            //close the connection object

            con.close();
        }catch (SQLException sq){

            System.err.println(sq.getMessage());

        }//endOfTryCatch
        
    }//end main
}