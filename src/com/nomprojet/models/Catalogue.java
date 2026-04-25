import java.util.List;
import java.util.ArrayList;

public class Catalogue {
    private List<Produit> produits = new ArrayList<>();

    public Catalogue() {
    }

    public List<Produit> rechercherProduit(String motCle) {
        List<Produit> resultat = new ArrayList<>();
        if (motCle == null || motCle.trim().isEmpty()) {
            return resultat;
        }

        String recherche = motCle.trim().toLowerCase();
        for (Produit produit : produits) {
            if (produit != null && produit.getNom() != null && produit.getNom().toLowerCase().contains(recherche)) {
                resultat.add(produit);
            }
        }
        return resultat;
    }

    public void ajouterProduit(Produit produit) {
        if (produit == null) {
            throw new IllegalArgumentException("Le produit ne peut pas être nul");
        }
        if (produit.getPrix() <= 0) {
            throw new IllegalArgumentException("Le prix du produit doit être strictement positif");
        }

        for (Produit existant : produits) {
            if (existant != null && existant.getId() == produit.getId()) {
                throw new IllegalArgumentException("Un produit avec cet identifiant existe déjà dans le catalogue");
            }
        }

        produits.add(produit);
    }

    public List<Produit> filtrerParPrixMax(double prixMax) {
        List<Produit> resultat = new ArrayList<>();
        if (prixMax < 0) {
            return resultat;
        }

        for (Produit produit : produits) {
            if (produit != null && produit.getPrix() <= prixMax) {
                resultat.add(produit);
            }
        }
        return resultat;
    }

    public void supprimerProduit(int idProduit) {
        Produit produitASupprimer = null;
        for (Produit produit : produits) {
            if (produit != null && produit.getId() == idProduit) {
                produitASupprimer = produit;
                break;
            }
        }
        if (produitASupprimer == null) {
            throw new IllegalArgumentException("Aucun produit trouvé avec l'ID " + idProduit);
        }
        produits.remove(produitASupprimer);
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }
}