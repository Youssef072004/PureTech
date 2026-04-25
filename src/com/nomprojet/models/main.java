import java.util.*;

class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialisation des données simulées
        List<Utilisateur> utilisateurs = new ArrayList<>();
        Administrateur admin = new Administrateur(1, "Admin", "admin@system.com", "admin123", "Super");
        Technicien tech = new Technicien(2, "Tech", "tech@system.com", "tech123", "General");
        Client clientDef = new Client(3, "Client", "client@system.com", "client123", "Address");
        utilisateurs.add(admin);
        utilisateurs.add(tech);
        utilisateurs.add(clientDef);

        Catalogue catalogue = new Catalogue();
        List<Produit> produits = new ArrayList<>();
        Produit p1 = new Produit(101, "Ordinateur Dell", "Latitude 5420", 2500.0, 15);
        p1.setDevise("DT");
        Produit p2 = new Produit(102, "Souris Logitech", "Sans fil", 45.0, 50);
        p2.setDevise("DT");
        Produit p3 = new Produit(103, "CISA", "Autoclave de stérilisation", 45000.0, 10);
        p3.setDevise("DT");
        Produit p4 = new Produit(104, "Prohs", "Stérilisateur à vapeur", 38000.0, 10);
        p4.setDevise("DT");
        Produit p5 = new Produit(105, "Shlombo", "Générateur de vapeur propre", 15500.0, 10);
        p5.setDevise("DT");
        Produit p6 = new Produit(106, "Agicosta", "Machine de traitement d'eau", 12000.0, 10);
        p6.setDevise("DT");
        Produit p7 = new Produit(107, "Lequeux", "Stérilisateur de laboratoire", 22000.0, 10);
        p7.setDevise("DT");
        Produit p8 = new Produit(108, "GETTINGE", "Laveur-désinfecteur haut de gamme", 55000.0, 10);
        p8.setDevise("DT");
        Produit p9 = new Produit(109, "Miel / Miele", "Laveur de verrerie", 18500.0, 10);
        p9.setDevise("DT");
        produits.add(p1);
        produits.add(p2);
        produits.add(p3);
        produits.add(p4);
        produits.add(p5);
        produits.add(p6);
        produits.add(p7);
        produits.add(p8);
        produits.add(p9);
        catalogue.setProduits(produits);

        Stock stock = new Stock();
        stock.ajouterProduit(p1);
        stock.ajouterProduit(p2);
        stock.ajouterProduit(p3);
        stock.ajouterProduit(p4);
        stock.ajouterProduit(p5);
        stock.ajouterProduit(p6);
        stock.ajouterProduit(p7);
        stock.ajouterProduit(p8);
        stock.ajouterProduit(p9);

        ServiceMaintenance service = new ServiceMaintenance();
        List<Commande> commandes = new ArrayList<>();
        List<Facture> factures = new ArrayList<>();

        // Boucle principale
        while (true) {
            System.out.println("=== Menu Principal ===");
            System.out.println("1. Se connecter en tant que Client");
            System.out.println("2. Se connecter en tant que Technicien");
            System.out.println("3. Se connecter en tant qu'Administrateur");
            System.out.println("4. Créer un compte Client");
            System.out.println("5. Quitter");
            System.out.print("Choix: ");
            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                case 2:
                case 3:
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Mot de passe: ");
                    String mdp = scanner.nextLine();
                    Utilisateur user = null;
                    for (Utilisateur u : utilisateurs) {
                        try {
                            if (u.authentifier(email, mdp)) {
                                user = u;
                                break;
                            }
                        } catch (Exception e) {
                            System.err.println("Erreur d'authentification: " + e.getMessage());
                        }
                    }
                    if (user == null) {
                        System.err.println("Authentification échouée");
                        break;
                    }
                    boolean typeCorrect = false;
                    if (choix == 1 && user instanceof Client) {
                        menuClient((Client) user, scanner, catalogue, stock, service, commandes);
                        typeCorrect = true;
                    } else if (choix == 2 && user instanceof Technicien) {
                        menuTechnicien((Technicien) user, scanner, service);
                        typeCorrect = true;
                    } else if (choix == 3 && user instanceof Administrateur) {
                        menuAdmin((Administrateur) user, scanner, stock, service, commandes, factures, utilisateurs);
                        typeCorrect = true;
                    }
                    if (!typeCorrect) {
                        System.err.println("Type d'utilisateur incorrect pour ces identifiants");
                    }
                    break;
                case 4:
                    System.out.println("Type de compte:");
                    System.out.println("1. Client");
                    System.out.println("2. Technicien");
                    System.out.println("3. Administrateur");
                    System.out.print("Choix: ");
                    int typeChoix = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nom: ");
                    String nom = scanner.nextLine();
                    System.out.print("Email: ");
                    String emailC = scanner.nextLine();
                    System.out.print("Mot de passe: ");
                    String mdpC = scanner.nextLine();
                    Utilisateur newUser = null;
                    if (typeChoix == 1) {
                        System.out.print("Adresse: ");
                        String adresse = scanner.nextLine();
                        newUser = new Client(utilisateurs.size() + 1, nom, emailC, mdpC, adresse);
                    } else if (typeChoix == 2) {
                        System.out.print("Spécialité: ");
                        String specialite = scanner.nextLine();
                        newUser = new Technicien(utilisateurs.size() + 1, nom, emailC, mdpC, specialite);
                    } else if (typeChoix == 3) {
                        System.out.print("Rôle: ");
                        String role = scanner.nextLine();
                        newUser = new Administrateur(utilisateurs.size() + 1, nom, emailC, mdpC, role);
                    } else {
                        System.err.println("Type invalide");
                        break;
                    }
                    try {
                        // Assuming sInscrire is only for Client, or generalize it.
                        // For simplicity, just add to list, but check if email unique.
                        boolean emailExists = false;
                        for (Utilisateur u : utilisateurs) {
                            if (u.getEmail().equals(emailC)) {
                                emailExists = true;
                                break;
                            }
                        }
                        if (emailExists) {
                            System.err.println("Email déjà utilisé");
                        } else {
                            utilisateurs.add(newUser);
                            System.out.println("Compte créé avec succès");
                        }
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("Au revoir!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Choix invalide");
            }
        }
    }

    private static void menuClient(Client client, Scanner scanner, Catalogue catalogue, Stock stock,
            ServiceMaintenance service, List<Commande> commandes) {
        boolean connecte = true;
        Panier panier = new Panier(client);
        while (connecte) {
            System.out.println("=== Menu Client ===");
            System.out.println("1. Voir le catalogue");
            System.out.println("2. Ajouter au panier et passer commande");
            System.out.println("3. Déclarer une panne");
            System.out.println("4. Suivre un ticket");
            System.out.println("5. Se déconnecter");
            System.out.print("Choix: ");
            int choix = scanner.nextInt();
            scanner.nextLine();
            switch (choix) {
                case 1:
                    for (Produit p : catalogue.getProduits()) {
                        System.out.println(p.getId() + " - " + p.getNom() + " - " + p.getPrix() + " " + p.getDevise());
                    }
                    break;
                case 2:
                    System.out.print("ID Produit: ");
                    int idP = scanner.nextInt();
                    System.out.print("Quantité: ");
                    int qte = scanner.nextInt();
                    scanner.nextLine();
                    Produit prod = null;
                    for (Produit p : catalogue.getProduits()) {
                        if (p.getId() == idP) {
                            prod = p;
                            break;
                        }
                    }
                    if (prod == null) {
                        System.err.println("Produit non trouvé");
                        break;
                    }
                    try {
                        if (stock.verifierDisponibilite(prod, qte)) {
                            panier.ajouterProduit(prod, qte);
                            stock.decrementerQuantite(prod, qte);
                            Commande cmd = new Commande(commandes.size() + 1, new Date(), "Validée", panier.getTotal());
                            cmd.setLignes(new ArrayList<>(panier.getLignes()));
                            commandes.add(cmd);
                            panier.vider();
                            System.out.println("Commande passée: #" + cmd.getId());
                        } else {
                            System.err.println("Stock insuffisant");
                        }
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 3:
                    System.out.print("Description de la panne: ");
                    String desc = scanner.nextLine();
                    Ticket ticket = new Ticket(service.getTickets().size() + 1, desc, new Date(), "Ouvert", "Moyenne");
                    ticket.setClient(client);
                    service.ajouterTicket(ticket);
                    System.out.println("Ticket créé: #" + ticket.getId());
                    break;
                case 4:
                    System.out.print("ID Ticket: ");
                    int idT = scanner.nextInt();
                    scanner.nextLine();
                    Ticket t = null;
                    for (Ticket tk : service.getTickets()) {
                        if (tk.getId() == idT && tk.getClient() != null && tk.getClient().getId() == client.getId()) {
                            t = tk;
                            break;
                        }
                    }
                    if (t == null) {
                        System.err.println("Ticket non trouvé");
                    } else {
                        System.out.println("Statut: " + t.getStatut());
                        if (t.getRapport() != null) {
                            System.out.println("Rapport: " + t.getRapport());
                        }
                    }
                    break;
                case 5:
                    connecte = false;
                    break;
                default:
                    System.out.println("Choix invalide");
            }
        }
    }

    private static void menuTechnicien(Technicien tech, Scanner scanner, ServiceMaintenance service) {
        boolean connecte = true;
        while (connecte) {
            System.out.println("=== Menu Technicien ===");
            System.out.println("1. Visualiser planning");
            System.out.println("2. Saisir rapport d'intervention");
            System.out.println("3. Se déconnecter");
            System.out.print("Choix: ");
            int choix = scanner.nextInt();
            scanner.nextLine();
            switch (choix) {
                case 1:
                    System.out.println("Tickets assignés:");
                    for (Ticket t : service.getTickets()) {
                        if (t.getIdTechnicien() == tech.getId()) {
                            System.out.println(
                                    "Ticket #" + t.getId() + " - " + t.getDescription() + " - " + t.getStatut());
                        }
                    }
                    break;
                case 2:
                    System.out.print("ID Ticket: ");
                    int idT = scanner.nextInt();
                    scanner.nextLine();
                    Ticket t = null;
                    for (Ticket tk : service.getTickets()) {
                        if (tk.getId() == idT && tk.getIdTechnicien() == tech.getId()) {
                            t = tk;
                            break;
                        }
                    }
                    if (t == null) {
                        System.err.println("Ticket non trouvé ou non assigné");
                        break;
                    }
                    System.out.print("Rapport: ");
                    String rapport = scanner.nextLine();
                    System.out.print("Coût (DT): ");
                    double cout = scanner.nextDouble();
                    scanner.nextLine();
                    try {
                        t.setRapport(rapport);
                        t.setCoutReparation(String.valueOf(cout));
                        t.setStatut("Fermé");
                        Intervention inter = new Intervention(service.getInterventions().size() + 1, new Date(),
                                rapport, "Terminée");
                        service.ajouterIntervention(inter);
                        System.out.println("Intervention saisie");
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 3:
                    connecte = false;
                    break;
                default:
                    System.out.println("Choix invalide");
            }
        }
    }

    private static void menuAdmin(Administrateur admin, Scanner scanner, Stock stock, ServiceMaintenance service,
            List<Commande> commandes, List<Facture> factures, List<Utilisateur> utilisateurs) {
        boolean connecte = true;
        while (connecte) {
            System.out.println("=== Menu Admin ===");
            System.out.println("1. Gérer les stocks");
            System.out.println("2. Affecter technicien à un ticket");
            System.out.println("3. Générer une facture");
            System.out.println("4. Consulter le Dashboard");
            System.out.println("5. Se déconnecter");
            System.out.print("Choix: ");
            int choix = scanner.nextInt();
            scanner.nextLine();
            switch (choix) {
                case 1:
                    System.out.print("ID Produit: ");
                    int idP = scanner.nextInt();
                    System.out.print("Quantité à ajouter: ");
                    int qte = scanner.nextInt();
                    System.out.print("Nouveau prix (0 pour ne pas changer): ");
                    double prix = scanner.nextDouble();
                    scanner.nextLine();
                    Produit prod = null;
                    for (Produit p : stock.getProduits()) {
                        if (p.getId() == idP) {
                            prod = p;
                            break;
                        }
                    }
                    if (prod == null) {
                        System.err.println("Produit non trouvé");
                        break;
                    }
                    try {
                        admin.gererStocks(stock, prod, qte, prix);
                        System.out.println("Stock géré");
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 2:
                    System.out.print("ID Ticket: ");
                    int idT = scanner.nextInt();
                    System.out.print("ID Technicien: ");
                    int idTech = scanner.nextInt();
                    scanner.nextLine();
                    Ticket t = null;
                    for (Ticket tk : service.getTickets()) {
                        if (tk.getId() == idT) {
                            t = tk;
                            break;
                        }
                    }
                    Technicien tech = null;
                    for (Utilisateur u : utilisateurs) {
                        if (u instanceof Technicien && u.getId() == idTech) {
                            tech = (Technicien) u;
                            break;
                        }
                    }
                    if (t == null || tech == null) {
                        System.err.println("Ticket ou Technicien non trouvé");
                        break;
                    }
                    try {
                        admin.affecterTechnicien(t, tech);
                        System.out.println("Technicien affecté");
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 3:
                    System.out.print("ID Commande: ");
                    int idC = scanner.nextInt();
                    scanner.nextLine();
                    Commande cmd = null;
                    for (Commande c : commandes) {
                        if (c.getId() == idC) {
                            cmd = c;
                            break;
                        }
                    }
                    if (cmd == null) {
                        System.err.println("Commande non trouvée");
                        break;
                    }
                    try {
                        Facture f = new Facture(factures.size() + 1, new Date(), cmd.getMontantTotal(), "Générée");
                        factures.add(f);
                        System.out.println("Facture générée: #" + f.getId());
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 4:
                    double ca = 0;
                    for (Facture f : factures) {
                        ca += f.getMontantTotal();
                    }
                    System.out.println("CA total: " + ca + " DT");
                    break;
                case 5:
                    connecte = false;
                    break;
                default:
                    System.out.println("Choix invalide");
            }
        }
    }
}