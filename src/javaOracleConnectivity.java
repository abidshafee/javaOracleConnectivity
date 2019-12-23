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
            ResultSet rs=stmt.executeQuery("select * from student");
            while(rs.next())
                System.out.println(rs.getString(1)+"  "+
                        rs.getString(2)+"  "+rs.getString(3)
                + " " + rs.getFloat(4));

            //inserting value to student table
            String insertSql = "insert into student values (?,?,?,?)";
            PreparedStatement prstmt = con.prepareStatement(insertSql);
            prstmt.setString(1,"16-0-52-020-01");
            prstmt.setString(2, "Labiba Yasmin");
            prstmt.setString(3, "cse");
            prstmt.setFloat(4, (float) 149.5);
            prstmt.executeQuery();

            /*
                PL/SQL Trigger: on Student Table
                _______________
                SET SERVEROUTPUT ON;
                  create trigger insertingTrigger
                    before INSERT ON Student
                        for each ROW
                    enable
                DECLARE
                    myVar VARCHAR(20);
                BEGIN
                    SELECT user into myVar FROM dual;
                    DBMS_OUTPUT.PUT_LINE('A new row inserted by '||myVar);
                end;
                /
            */

          //Calling PL/SQL in java
            CallableStatement myCall = con.prepareCall("{CALL insertingTrigger}");
            myCall.execute();

            //String triggRes = myCall.getString("");
            System.out.println(psRes);


            //close the connection object
            con.close();
        }catch (SQLException sq){
            System.err.println(sq.getMessage());
        }//endOfTryCatch


    }//end main
}