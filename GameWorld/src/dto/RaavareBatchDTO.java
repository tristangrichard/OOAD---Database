package dto;

public class RaavareBatchDTO {
	private int rbId;                     // i omraadet 1-99999999
	private int raavareId;             // i omraadet 1-99999999
	private double maengde;             // kan vaere negativ 


	public RaavareBatchDTO(){
	}

	public RaavareBatchDTO(int rbId, int raavareId, double maengde) {
		this.rbId = rbId;
		this.raavareId = raavareId;
		this.maengde = maengde;
	}

	public int getRbId() { return rbId; }
	public void setRbId(int rbId) { this.rbId = rbId; }
	public int getRaavareId() { return raavareId; }
	public void setRaavareId(int raavareId) { this.raavareId = raavareId; }
	public double getMaengde() { return maengde; }
	public void setMaengde(double maengde) { this.maengde = maengde; }
	public String toString() { 
		return rbId + "\t" + raavareId +"\t" + maengde; 
	}
}