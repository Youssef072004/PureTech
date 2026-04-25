import java.util.ArrayList;
import java.util.List;

public class Stock {
    private List<Produit> produits;

    public Stock() {
        this.produits = new ArrayList<>();
    }

    public boolean verifierDisponibilite(Produit produit, int quantite) {
        for (Produit p : produits) {
            if (p.getId() == produit.getId()) {
                return p.getQuantiteStock() >= quantite;
            }
        }
        return false;
    }

    public void decrementerQuantite(Produit produit, int quantite) {
        for (Produit p : produits) {
            if (p.getId() == produit.getId()) {
                p.setQuantiteStock(p.getQuantiteStock() - quantite);
                return;
            }
        }
    }

    public void incrementerQuantite(Produit produit, int quantite) {
        for (Produit p : produits) {
            if (p.getId() == produit.getId()) {
                p.setQuantiteStock(p.getQuantiteStock() + quantite);
                return;
            }
        }
    }

    public void ajouterProduit(Produit produit) {
        produits.add(produit);
    }

    public List<Produit> getProduits() {
        return produits;
    }
}