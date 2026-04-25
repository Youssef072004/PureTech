public class Equipement {
    private int id;
    private String nom;
    private String type;
    private String reference;
    private Client proprietaire;

    public Equipement() {
    }

    public Equipement(int id, String nom, String type, String reference) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.reference = reference;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Client getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(Client proprietaire) {
        this.proprietaire = proprietaire;
    }
}