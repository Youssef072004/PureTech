import java.util.Date;

public class Facture {
    private int id;
    private Date dateFacture;
    private double montantTotal;
    private String statut;

    public Facture() {
    }

    public Facture(int id, Date dateFacture, double montantTotal, String statut) {
        this.id = id;
        this.dateFacture = dateFacture;
        this.montantTotal = montantTotal;
        this.statut = statut;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateFacture() {
        return dateFacture;
    }

    public void setDateFacture(Date dateFacture) {
        this.dateFacture = dateFacture;
    }

    public double getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(double montantTotal) {
        this.montantTotal = montantTotal;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}