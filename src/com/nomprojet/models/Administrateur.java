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
    public void gererStocks() {
        // Logique à implémenter
    }

    public void affecterTechnicien() {
        // Logique à implémenter
    }

    public void gererComptesUtilisateurs() {
        // Logique à implémenter
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