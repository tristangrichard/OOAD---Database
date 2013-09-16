package dto;

/**
 * Operatoer Data Access Objekt
 * 
 * @author mn/tb
 * @version 1.2
 */

public class BrugerDTO
{
	/** Operatoer-identifikationsnummer (opr_id) i omraadet 1-99999999. Vaelges af brugerne */
	private int oprId;                     
	/** Operatoernavn (opr_navn) min. 2 max. 20 karakterer */
	private String oprNavn;                
	/** Operatoer-initialer min. 2 max. 3 karakterer */
	private String ini;                 
	/** Operatoer cpr-nr 10 karakterer */
	private String cpr;                 
	/** Operatoer password min. 7 max. 8 karakterer */
	private String password;
	private String rolle;

	public BrugerDTO() {
		super();
	}

	public BrugerDTO(int oprId, String oprNavn, String ini, String cpr, String password, String rolle) {
		this.oprId = oprId;
		this.oprNavn = oprNavn;
		this.ini = ini;
		this.cpr = cpr;
		this.password = password;
		this.rolle = rolle;
	}

	public int getOprId() { return oprId; }
	public void setOprId(int oprId) { this.oprId = oprId; }
	public String getOprNavn() { return oprNavn; }
	public void setOprNavn(String oprNavn) { this.oprNavn = oprNavn; }
	public String getIni() { return ini; }
	public void setIni(String ini) { this.ini = ini; }
	public String getCpr() { return cpr; }
	public void setCpr(String cpr) { this.cpr = cpr; }
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	public String getRolle() { return rolle; }
	public void setRolle(String rolle) { this.rolle = rolle;}
	public String toString() { return oprId + "\t" + oprNavn + "\t" + ini + "\t" + cpr + "\t" + password; }
}