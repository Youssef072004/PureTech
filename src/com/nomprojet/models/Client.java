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
     * 
     * @param systeme la liste des utilisateurs du système
     * @return true si l'inscription réussit
     * @throws IllegalArgumentException si l'email existe déjà ou le mot de passe
     *                                  est trop court
     */
    public boolean sInscrire(List<Utilisateur> systeme) {
        if (systeme == null) {
            throw new IllegalArgumentException("Le système d'utilisateurs ne peut pas être nul");
        }

        for (Utilisateur utilisateur : systeme) {
            if (utilisateur.getEmail() != null && utilisateur.getEmail().equals(this.getEmail())) {
                throw new IllegalArgumentException("Un utilisateur avec cet email existe déjà");
            }
        }

        if (this.getMotDePasse() == null || this.getMotDePasse().length() < 8) {
            throw new IllegalArgumentException("Le mot de passe doit contenir au moins 8 caractères");
        }

        systeme.add(this);
        return true;
    }

    /**
     * Passe une commande à partir d'un panier.
     * 
     * @param panier le panier contenant les produits
     * @param stock  le stock pour vérifier la disponibilité
     * @return la commande créée
     * @throws IllegalStateException si le panier est vide ou stock insuffisant
     */
    public Commande passerCommande(Panier panier, Stock stock) {
        if (panier == null) {
            throw new IllegalArgumentException("Le panier ne peut pas être nul");
        }

        if (panier.getLignes().isEmpty()) {
            throw new IllegalStateException("Le panier est vide");
        }

        if (stock == null) {
            throw new IllegalArgumentException("Le stock ne peut pas être nul");
        }

        double total = panier.getTotal();
        for (LigneCommande ligne : panier.getLignes()) {
            Produit produit = ligne.getProduit();
            int quantiteDemandee = ligne.getQuantite();

            if (produit == null) {
                throw new IllegalStateException("Une ligne de commande contient un produit invalide");
            }

            if (!stock.verifierDisponibilite(produit, quantiteDemandee)) {
                throw new IllegalStateException("Stock insuffisant pour le produit: " + produit.getNom());
            }
        }

        for (LigneCommande ligne : panier.getLignes()) {
            stock.decrementerQuantite(ligne.getProduit(), ligne.getQuantite());
        }

        Commande commande = new Commande(generateId(), new Date(), "EN_ATTENTE", total);
        String montantFormate = String.format(Locale.US, "%.2f DT", total);
        commande.setMontantTotal(total);
        commande.setMontantFormate(montantFormate);
        commande.setLignes(new ArrayList<>(panier.getLignes()));

        this.commandes.add(commande);
        panier.vider();

        return commande;
    }

    /**
     * Déclare une panne pour un équipement.
     * 
     * @param equipement  l'équipement concerné
     * @param description la description de la panne
     * @param support     le service de support
     * @return le ticket créé
     */
    public Ticket declarerPanne(Equipement equipement, String description, ServiceSupport support) {
        if (equipement == null) {
            throw new IllegalArgumentException("L'équipement ne peut pas être nul");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("La description de la panne est requise");
        }
        if (support == null) {
            throw new IllegalArgumentException("Le service support ne peut pas être nul");
        }

        Ticket ticket = new Ticket(
                generateTicketId(),
                description,
                new Date(),
                "OUVERT",
                "NORMALE",
                this,
                equipement);

        support.ajouterTicket(ticket);
        this.tickets.add(ticket);
        return ticket;
    }

    /**
     * Suit le statut d'un ticket.
     * 
     * @param idTicket l'ID du ticket à suivre
     * @param support  le service de support
     * @return le statut du ticket
     * @throws IllegalArgumentException si le ticket n'existe pas ou n'appartient
     *                                  pas au client
     */
    public String suivreTicket(int idTicket, ServiceSupport support) {
        if (support == null) {
            throw new IllegalArgumentException("Le service support ne peut pas être nul");
        }

        Ticket ticket = support.trouverTicket(idTicket);
        if (ticket == null) {
            throw new IllegalArgumentException("Ticket non trouvé: " + idTicket);
        }

        if (ticket.getClient() == null || ticket.getClient().getId() != this.getId()) {
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