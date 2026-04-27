import java.util.Date;

public class Ticket {
    private int id;
    private String description;
    private Date dateCreation;
    private String statut;
    private String priorite;

    public Ticket() {
    }

    public Ticket(int id, String description, Date dateCreation, String statut, String priorite) {
        this.id = id;
        this.description = description;
        this.dateCreation = dateCreation;
        this.statut = statut;
        this.priorite = priorite;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public String getStatut() {
        return statut;
    }

    public String getPriorite() {
        return priorite;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public void setPriorite(String priorite) {
        this.priorite = priorite;
    }
}