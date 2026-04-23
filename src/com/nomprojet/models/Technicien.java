public class Technicien extends Utilisateur {
    private String specialite;

    public Technicien() {
    }

    public Technicien(int id, String nom, String email, String motDePasse, String specialite) {
        super(id, nom, email, motDePasse);
        this.specialite = specialite;
    }

    public void visualiserPlanning() {
    }

    public void saisirRapportIntervention() {
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }
}