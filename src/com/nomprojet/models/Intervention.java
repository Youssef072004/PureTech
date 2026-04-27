import java.util.Date;

public class Intervention {
    private int id;
    private Date dateIntervention;
    private String rapport;
    private String statut;

    public Intervention() {
    }

    public Intervention(int id, Date dateIntervention, String rapport, String statut) {
        this.id = id;
        this.dateIntervention = dateIntervention;
        this.rapport = rapport;
        this.statut = statut;
    }

    public int getId() {
        return id;
    }

    public Date getDateIntervention() {
        return dateIntervention;
    }

    public String getRapport() {
        return rapport;
    }

    public String getStatut() {
        return statut;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDateIntervention(Date dateIntervention) {
        this.dateIntervention = dateIntervention;
    }

    public void setRapport(String rapport) {
        this.rapport = rapport;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}