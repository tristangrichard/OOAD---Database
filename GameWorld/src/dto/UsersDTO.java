package dto;

public class UsersDTO
{
	private int Uid;
	private String Fname;
	private String Lname;
	private String DOB;
	private String pass;
	private String email;
	private boolean sex;
	private String role;

	public UsersDTO(int Uid, String Fname, String Lname, String DOB, String pass, String email, boolean sex, String rolle)
	{
		this.Uid = Uid;
		this.Fname = Fname;
		this.Lname = Lname;
		this.DOB = DOB;
		this.pass = pass;
		this.email = email;
		this.sex = sex;
		this.role = rolle;
	}

	public int getUid(){return Uid;}
	public void setUid(int Uid){this.Uid = Uid;}

	public String getFname(){return Fname;}
	public void setFname(String Fname){this.Fname = Fname;}

	public String getLname(){return Lname;}
	public void setLname(String Lname){this.Lname = Lname;}

	public String getDob(){return DOB;}
	public void setDob(String DOB){this.DOB = DOB;}

	public String getPass(){return pass;}
	public void setPass(String pass){this.pass = pass;}

	public String getEmail(){return email;}
	public void setEmail(String email){this.email = email;}

	public boolean getSex(){return sex;}
	public void setSex(boolean sex){this.sex = sex;}
	
	public String getRole(){return role;}
	public void setRole(String role){this.role = role;}
}

