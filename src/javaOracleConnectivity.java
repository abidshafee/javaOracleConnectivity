import java.sql.*;
public class javaOracleConnectivity {

    public static void main(String [] args){

        try{

            //load driver
            //Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:abiddb/abid@//localhost:1521/orcl");
                        //here 'orcl' is the service name, 'thin' is the driver,
                        // 'jdbc' is the API, and 'oracle' is the database

            Statement stmt = con.createStatement();

            //step4 execute query
            ResultSet rs=stmt.executeQuery("select * from student");
            while(rs.next())
                System.out.println(rs.getString(1)+"  "+
                        rs.getString(2)+"  "+rs.getString(3)
                + " " + rs.getFloat(4));

            String insertSql = "insert into student values (?,?,?,?)";
            PreparedStatement prstmt = con.prepareStatement(insertSql);
            prstmt.setString(1,"16-0-52-020-061");
            prstmt.setString(2, "Sojib Ahmed");
            prstmt.setString(3, "eee");
            prstmt.setFloat(4, (float) 138.5);
            prstmt.executeQuery();

            //step5 close the connection object
            con.close();

        }catch (SQLException sq){
            System.err.println(sq.getMessage());
        }


    }

    //CallableStatement myCall =
}
