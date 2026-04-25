import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Technicien extends Utilisateur {
    private String specialite;

    public Technicien() {
    }

    public Technicien(int id, String nom, String email, String motDePasse, String specialite) {
        super(id, nom, email, motDePasse);
        this.specialite = specialite;
    }

    public List<Ticket> visualiserPlanning(ServiceMaintenance service) {
        if (service == null) {
            throw new IllegalArgumentException("Le service de maintenance ne peut pas être nul");
        }

        List<Ticket> planning = new ArrayList<>();
        if (service.getTickets() == null) {
            return planning;
        }

        for (Ticket ticket : service.getTickets()) {
            if (ticket == null || ticket.getTechnicien() == null) {
                continue;
            }

            if (ticket.getTechnicien().getId() == this.getId()
                    && !"RÉSOLU".equalsIgnoreCase(ticket.getStatut())) {
                planning.add(ticket);
            }
        }
        return planning;
    }

    public void saisirRapportIntervention(Ticket ticket, String rapport, double coutReparation) {
        if (ticket == null) {
            throw new IllegalArgumentException("Le ticket ne peut pas être nul");
        }
        if (ticket.getTechnicien() == null || ticket.getTechnicien().getId() != this.getId()) {
            throw new IllegalArgumentException("Ce ticket n'est pas assigné à ce technicien");
        }
        if (rapport == null || rapport.trim().isEmpty()) {
            throw new IllegalArgumentException("Le rapport d'intervention est requis");
        }
        if (coutReparation < 0) {
            throw new IllegalArgumentException("Le coût de réparation ne peut pas être négatif");
        }

        ticket.setRapport(rapport.trim());
        ticket.setStatut("RÉSOLU");
        ticket.setCoutReparation(String.format(Locale.US, "%.2f DT", coutReparation));
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }
}