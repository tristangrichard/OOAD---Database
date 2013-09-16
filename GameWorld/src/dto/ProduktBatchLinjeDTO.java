package dto;

public class ProduktBatchLinjeDTO {
	private int raavareid;
	private String raavarenavn;
	private double maengde;
	private double tolerance;
	private double tara;
	private double netto;
	private int raavarebatch;
	private String opr;

	public ProduktBatchLinjeDTO() {
		super();
	}

	public ProduktBatchLinjeDTO (String raavarenavn, double tolerance, int raavareid, double maengde){
		this.raavareid = raavareid;
		this.raavarenavn = raavarenavn;
		this.tolerance = tolerance;
		this.maengde = maengde;
	}

	public int getRaavareid() {
		return raavareid;
	}

	public void setRaavareid(int raavareid) {
		this.raavareid = raavareid;
	}

	public String getRaavarenavn() {
		return raavarenavn;
	}

	public void setRaavarenavn(String raavarenavn) {
		this.raavarenavn = raavarenavn;
	}

	public double getMaengde() {
		return maengde;
	}

	public void setMaengde(double maengde) {
		this.maengde = maengde;
	}

	public double getTolerance() {
		return tolerance;
	}

	public void setTolerance(double tolerance) {
		this.tolerance = tolerance;
	}

	public double getTara() {
		return tara;
	}

	public void setTara(double tara) {
		this.tara = tara;
	}

	public double getNetto() {
		return netto;
	}

	public void setNetto(double netto) {
		this.netto = netto;
	}

	public int getRaavarebatch() {
		return raavarebatch;
	}

	public void setRaavarebatch(int raavarebatch) {
		this.raavarebatch = raavarebatch;
	}

	public String getOpr() {
		return opr;
	}

	public void setOpr(String opr) {
		this.opr = opr;
	}
}