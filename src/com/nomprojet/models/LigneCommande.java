public class LigneCommande {
    // 1. Champs privés
    private int quantite;
    private double prixUnitaire;
    private Produit produit; // Relation vers Produit issue du DC

    // 2. Constructeurs
    public LigneCommande() {
    }

    public LigneCommande(int quantite, double prixUnitaire, Produit produit) {
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
        this.produit = produit;
    }

    // 3. Méthodes métier
    // Aucune méthode métier spécifiée dans le diagramme pour cette classe.

    // 4. Getters et Setters
    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }
}