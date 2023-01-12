package Model;

public class Session {

	public static Connect con;

    public static Connect getConnection()
    {
        if (con == null)
        {
            con = con.getConnection();
            return con;
        }
        else
        {
            return con;
        }
    }
	public static Employee employee;
	
	public static void setEmployee(Employee e){
		Session.employee = e;
	}
	
	public Session() {
		// TODO Auto-generated constructor stub
	}
}
