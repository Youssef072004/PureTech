public abstract class Utilisateur {
    private int id;
    private String nom;
    private String email;
    private String motDePasse;

    public Utilisateur() {
    }

    public Utilisateur(int id, String nom, String email, String motDePasse) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.motDePasse = motDePasse;
    }

    public boolean authentifier(String emailSaisi, String motDePasseSaisi) {
        if (emailSaisi == null || motDePasseSaisi == null || this.email == null || this.motDePasse == null) {
            return false;
        }
        return this.email.equals(emailSaisi) && this.motDePasse.equals(motDePasseSaisi);
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
}