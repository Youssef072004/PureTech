import java.util.List;

public class Administrateur extends Utilisateur {
    // 1. Champs privés
    private String role;

    // 2. Constructeurs
    public Administrateur() {
        super();
    }

    public Administrateur(int id, String nom, String email, String motDePasse, String role) {
        super(id, nom, email, motDePasse);
        this.role = role;
    }

    // 3. Méthodes métier (signatures seulement)
    public void gererStocks(Stock stock, Produit produit, int quantiteAjoutee, double nouveauPrix) {
        if (stock == null || produit == null) {
            throw new IllegalArgumentException("Stock et Produit ne peuvent pas être null");
        }
        if (quantiteAjoutee < 0) {
            throw new IllegalArgumentException("La quantité ajoutée ne peut pas être négative");
        }
        stock.incrementerQuantite(produit, quantiteAjoutee);
        if (nouveauPrix > 0) {
            produit.setPrix(nouveauPrix);
            produit.setDevise("DT");
        }
    }

    public void affecterTechnicien(Ticket ticket, Technicien technicien) {
        if (ticket == null || technicien == null) {
            throw new IllegalArgumentException("Ticket et Technicien ne peuvent pas être null");
        }
        if ("RÉSOLU".equals(ticket.getStatut()) || "ANNULÉ".equals(ticket.getStatut())) {
            throw new IllegalStateException("Le ticket est déjà résolu ou annulé");
        }
        ticket.setIdTechnicien(technicien.getId());
        ticket.setStatut("ASSIGNÉ");
    }

    public void gererComptesUtilisateurs(List<Utilisateur> baseUtilisateurs, Utilisateur cible, String action) {
        if (baseUtilisateurs == null || cible == null || action == null) {
            throw new IllegalArgumentException("Paramètres ne peuvent pas être null");
        }
        switch (action) {
            case "SUPPRIMER":
                if (this.getId() == cible.getId()) {
                    throw new IllegalArgumentException("L'administrateur ne peut pas se supprimer lui-même");
                }
                baseUtilisateurs.remove(cible);
                break;
            case "RÉINITIALISER_MDP":
                cible.setMotDePasse("Admin123!");
                break;
            default:
                throw new IllegalArgumentException("Action inconnue: " + action);
        }
    }

    public Facture genererFacture() {
        return null;
    }

    public void consulterDashboardCA() {
        // Logique à implémenter
    }

    // 4. Getters et Setters
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}