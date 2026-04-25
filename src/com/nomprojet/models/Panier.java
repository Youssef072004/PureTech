import java.util.ArrayList;
import java.util.List;

public class Panier {
    private List<LigneCommande> lignes;
    private Client client;

    public Panier() {
        this.lignes = new ArrayList<>();
    }

    public Panier(Client client) {
        this.client = client;
        this.lignes = new ArrayList<>();
    }

    public void ajouterProduit(Produit produit, int quantite) {
        // Vérifier si le produit existe déjà dans le panier
        for (LigneCommande ligne : lignes) {
            if (ligne.getProduit().getId() == produit.getId()) {
                ligne.setQuantite(ligne.getQuantite() + quantite);
                return;
            }
        }
        // Ajouter une nouvelle ligne
        LigneCommande ligne = new LigneCommande(quantite, produit.getPrix(), produit);
        lignes.add(ligne);
    }

    public void vider() {
        lignes.clear();
    }

    public double getTotal() {
        double total = 0;
        for (LigneCommande ligne : lignes) {
            total += ligne.getPrixUnitaire() * ligne.getQuantite();
        }
        return total;
    }

    public List<LigneCommande> getLignes() {
        return lignes;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}