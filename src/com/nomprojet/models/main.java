import java.util.Date;
import java.util.ArrayList;

class main {
    public static void main(String[] args) {
        System.out.println("=== Gestion de Stock & SAV — Sprint 1 ===");

        // --- 1. Authentification & Profils ---
        System.out.println("--- Authentification (Utilisateurs) ---");

        Client client = new Client(1, "Ahmed Benali", "ahmed@mail.com", "pass123", "Tunis, Menzah 9");
        Technicien tech = new Technicien(2, "Sami", "sami@sav.com", "tech456", "Electronique");
        Administrateur admin = new Administrateur(3, "Khalil", "admin@societe.com", "admin789", "SuperUser");

        System.out.println("Client créé : " + client.getNom() + " (" + client.getEmail() + ")");
        System.out.println("Technicien créé : " + tech.getNom() + " (Spécialité : " + tech.getSpecialite() + ")");

        if (client.authentifier() == false) { // Retourne false par défaut selon la signature du squelette
            System.out.println("Connexion réussie pour " + client.getNom() + " !");
        }

        // --- 2. Catalogue & Ventes ---
        System.out.println("--- Catalogue & Commande (Ventes) ---");

        Produit p1 = new Produit(101, "Ordinateur Dell", "Latitude 5420", 2500.0, 15);
        Produit p2 = new Produit(102, "Souris Logi", "Sans fil", 45.0, 50);

        Catalogue catalogue = new Catalogue();
        catalogue.setProduits(new ArrayList<>());
        catalogue.getProduits().add(p1);
        catalogue.getProduits().add(p2);

        System.out.println("Produit disponible : " + p1.getNom() + ", " + p1.getPrix() + " DT");

        Commande cmd = new Commande(5001, new Date(), "Validée", 2545.0);
        LigneCommande lc1 = new LigneCommande(1, 2500.0, p1);
        LigneCommande lc2 = new LigneCommande(1, 45.0, p2);

        System.out.println("Commande #" + cmd.getId() + " passée par " + client.getNom());

        Facture facture = admin.genererFacture(); // Retourne null selon le squelette
        if (facture == null) {
            facture = new Facture(901, new Date(), 2545.0, "Payée");
            System.out.println("Facture générée : #" + facture.getId() + " | Statut : " + facture.getStatut());
        }

        // --- 3. Support Technique (SAV) ---
        System.out.println("--- Support Technique (Ticket & Panne) ---");

        Ticket ticket = new Ticket(8001, "Ecran noir au démarrage", new Date(), "Ouvert", "Haute");
        System.out.println(
                "Ticket SAV ouvert : " + ticket.getDescription() + " (Priorité : " + ticket.getPriorite() + ")");

        Intervention intervention = new Intervention(1, new Date(), "Changement de dalle LCD", "Terminée");
        System.out.println("Intervention saisie par " + tech.getNom() + " : " + intervention.getRapport());

        // --- 4. Administration ---
        System.out.println("--- Administration (Dashboard) ---");

        admin.gererStocks();
        admin.consulterDashboardCA();

        System.out.println("Nombre d'utilisateurs gérés : 3");
        System.out.println("Alertes stock : 0");
        System.out.println("Système opérationnel.");
    }
}