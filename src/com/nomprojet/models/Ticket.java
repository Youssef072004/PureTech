import java.util.Date;

public class Ticket {
    private int id;
    private String description;
    private Date dateCreation;
    private String statut;
    private String priorite;
    private Client client;
    private Equipement equipement;
    private Technicien technicien;
    private String rapport;
    private String coutReparation;

    public Ticket() {
    }

    public Ticket(int id, String description, Date dateCreation, String statut, String priorite) {
        this.id = id;
        this.description = description;
        this.dateCreation = dateCreation;
        this.statut = statut;
        this.priorite = priorite;
    }

    public Ticket(int id, String description, Date dateCreation, String statut, String priorite, Client client,
            Equipement equipement) {
        this.id = id;
        this.description = description;
        this.dateCreation = dateCreation;
        this.statut = statut;
        this.priorite = priorite;
        this.client = client;
        this.equipement = equipement;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Equipement getEquipement() {
        return equipement;
    }

    public void setEquipement(Equipement equipement) {
        this.equipement = equipement;
    }

    public Technicien getTechnicien() {
        return technicien;
    }

    public void setTechnicien(Technicien technicien) {
        this.technicien = technicien;
    }

    public String getRapport() {
        return rapport;
    }

    public void setRapport(String rapport) {
        this.rapport = rapport;
    }

    public String getCoutReparation() {
        return coutReparation;
    }

    public void setCoutReparation(String coutReparation) {
        this.coutReparation = coutReparation;
    }
}