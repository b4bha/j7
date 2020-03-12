import java.sql.*;
import java.io.*;
public class Slip7_2
{
	public static void main(String args[]) throws Exception
    {
    	Connection conn = null;
        ResultSet rs = null;
        Statement stmt = null;
        PreparedStatement ps1 = null, ps2 = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name;
        int r,choice;
        //Class.forName("org.postgresql.Driver");
        //conn = DriverManager.getConnection("jdbc:postgresql://192.168.1.253/ty48b1","ty48b1","");
  	    Class.forName("com.mysql.jdbc.Driver");
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","");		
	
        stmt = conn.createStatement();
        ps1 = conn.prepareStatement("Insert into student values(?,?,?)");
        ps2 = conn.prepareStatement("Update student set name = ? , per=? where rno = ? ");
        if(conn!=null)
        	System.out.println("Connection successfull..");
        do
        {
			System.out.println();                                	
            System.out.println("1: View All Records");
            System.out.println("2: Insert Records");
            System.out.println("3: Delete Records");
            System.out.println("4: Modify Records");
            System.out.println("5: Search Records");
            System.out.println("6: Exit");
            System.out.println("\nEnter your choice : ");
            choice = Integer.parseInt(br.readLine());
            switch(choice)
            {
            	case 1 :rs = stmt.executeQuery("Select * from student");
                		System.out.println("Roll Number\tName\t\tPercentage"); 
                        while(rs.next())
                        {
                            System.out.print("\n"+rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getFloat(3));
                        }
                        break;

				case 2 :System.out.println("Enter the roll no.: ");
                		r = Integer.parseInt(br.readLine());
                        System.out.println("Enter the Name : ");
                        name = br.readLine();
                        System.out.println("Enter the Percentage : ");
					    float per= Float.parseFloat(br.readLine()); 
                        ps1.setInt(1,r);
                        ps1.setString(2,name);
					    ps1.setFloat(3,per);
                        ps1.executeUpdate();
                        break;

				case 3 :System.out.println("Enter the roll no. to be deleted = ");
                		r = Integer.parseInt(br.readLine());
                        stmt.executeUpdate("Delete from student where rno =  " +r);
                        break;

				case 4 :System.out.println("Enter the roll no. to be modified = ");
                        r = Integer.parseInt(br.readLine());
                        System.out.println("Enter new name = ");
                        name = br.readLine();
					    System.out.println("Enter new percentage = ");
                        float p = Float.parseFloat(br.readLine());
					                   
					    ps2.setInt(3,r);
					    ps2.setFloat(2,p);
                        ps2.setString(1,name);
						ps2.executeUpdate();
                        break;

				case 5 :System.out.println("Enter the roll no. to be searched = ");
                		r = Integer.parseInt(br.readLine());
                        rs = stmt.executeQuery("Select * from student where rno = "+r);
                        if(rs.next())
                        {
                        	System.out.println("Roll Number = "+rs.getInt(1));
                            System.out.println("Name = "+rs.getString(2));
						    System.out.println("Percentage = "+rs.getString(3));
                        }
                        else
                        	System.out.println("Student not found.");
                        break;
			}
		}while(choice != 6);
        conn.close();
	}
}

