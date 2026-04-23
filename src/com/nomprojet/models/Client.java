public class Client extends Utilisateur {
    private String adresse;

    public Client() {
    }

    public Client(int id, String nom, String email, String motDePasse, String adresse) {
        super(id, nom, email, motDePasse);
        this.adresse = adresse;
    }

    public void sInscrire() {
    }

    public Commande passerCommande() {
        return null;
    }

    public Ticket declarerPanne() {
        return null;
    }

    public String suivreTicket() {
        return null;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}