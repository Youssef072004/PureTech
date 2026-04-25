import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Client extends Utilisateur {
    private String adresse;
    private List<Commande> commandes;
    private List<Ticket> tickets;

    public Client() {
        this.commandes = new ArrayList<>();
        this.tickets = new ArrayList<>();
    }

    public Client(int id, String nom, String email, String motDePasse, String adresse) {
        super(id, nom, email, motDePasse);
        this.adresse = adresse;
        this.commandes = new ArrayList<>();
        this.tickets = new ArrayList<>();
    }

    /**
     * Inscrit le client dans le système après validation.
     * @param systeme la liste des utilisateurs du système
     * @return true si l'inscription réussit
     * @throws IllegalArgumentException si l'email existe déjà ou le mot de passe est trop court
     */
    public boolean sInscrire(List<Utilisateur> systeme) {
        // Vérifier que l'email n'existe pas déjà
        for (Utilisateur u : systeme) {
            if (u.getEmail().equals(this.getEmail())) {
                throw new IllegalArgumentException("Un utilisateur avec cet email existe déjà");
            }
        }
        
        // Vérifier la longueur du mot de passe
        if (this.getMotDePasse() == null || this.getMotDePasse().length() < 8) {
            throw new IllegalArgumentException("Le mot de passe doit contenir au moins 8 caractères");
        }
        
        // Ajouter le client au système
        systeme.add(this);
        return true;
    }

    /**
     * Passe une commande à partir d'un panier.
     * @param panier le panier contenant les produits
     * @param stock le stock pour vérifier la disponibilité
     * @return la commande créée
     * @throws IllegalStateException si le panier est vide ou stock insuffisant
     */
    public Commande passerCommande(Panier panier, Stock stock) {
        if (panier == null || panier.getLignes().isEmpty()) {
            throw new IllegalStateException("Le panier est vide");
        }
        
        // Calculer le total et vérifier la disponibilité
        double total = 0;
        for (LigneCommande ligne : panier.getLignes()) {
            Produit produit = ligne.getProduit();
            int quantiteDemandee = ligne.getQuantite();
            
            // Vérifier la disponibilité en stock
            if (!stock.verifierDisponibilite(produit, quantiteDemandee)) {
                throw new IllegalStateException("Stock insuffisant pour le produit: " + produit.getNom());
            }
            
            total += ligne.getPrixUnitaire() * quantiteDemandee;
        }
        
        // Décrémenter les quantités en stock
        for (LigneCommande ligne : panier.getLignes()) {
            stock.decrementerQuantite(ligne.getProduit(), ligne.getQuantite());
        }
        
        // Créer la commande
        Commande commande = new Commande(
            generateId(),
            new Date(),
            "EN_ATTENTE",
            total
        );
        
        // Formater le montant avec la devise DT
        String montantFormaté = String.format(Locale.US, "%.2f DT", total);
        commande.setMontantTotal(total);
        
        // Ajouter les lignes de commande
        commande.setLignes(new ArrayList<>(panier.getLignes()));
        
        // Ajouter la commande au client
        this.commandes.add(commande);
        
        // Vider le panier
        panier.vider();
        
        return commande;
    }

    /**
     * Déclare une panne pour un équipement.
     * @param equipement l'équipement concerné
     * @param description la description de la panne
     * @param support le service de support
     * @return le ticket créé
     */
    public Ticket declarerPanne(Equipement equipement, String description, ServiceSupport support) {
        // Créer un nouveau ticket avec le statut OUVERT
        Ticket ticket = new Ticket(
            generateTicketId(),
            description,
            new Date(),
            "OUVERT",
            "NORMALE"
        );
        
        // Ajouter le ticket au support
        support.ajouterTicket(ticket);
        
        // Ajouter le ticket au client
        this.tickets.add(ticket);
        
        return ticket;
    }

    /**
     * Suit le statut d'un ticket.
     * @param idTicket l'ID du ticket à suivre
     * @param support le service de support
     * @return le statut du ticket
     * @throws IllegalArgumentException si le ticket n'existe pas ou n'appartient pas au client
     */
    public String suivreTicket(int idTicket, ServiceSupport support) {
        // Chercher le ticket dans le support
        Ticket ticket = support.trouverTicket(idTicket);
        
        if (ticket == null) {
            throw new IllegalArgumentException("Ticket non trouvé: " + idTicket);
        }
        
        // Vérifier que le ticket appartient à ce client
        if (!this.tickets.contains(ticket)) {
            throw new IllegalArgumentException("Ce ticket n'appartient pas à ce client");
        }
        
        return ticket.getStatut();
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public List<Commande> getCommandes() {
        return commandes;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    private int generateId() {
        return (int) (System.currentTimeMillis() % Integer.MAX_VALUE);
    }

    private int generateTicketId() {
        return (int) (System.currentTimeMillis() % Integer.MAX_VALUE);
    }
}