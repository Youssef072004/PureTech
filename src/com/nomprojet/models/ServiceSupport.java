import java.util.ArrayList;
import java.util.List;

public class ServiceSupport {
    private List<Ticket> tickets;
    private List<Technicien> techniciens;

    public ServiceSupport() {
        this.tickets = new ArrayList<>();
        this.techniciens = new ArrayList<>();
    }

    public void ajouterTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public Ticket trouverTicket(int id) {
        for (Ticket ticket : tickets) {
            if (ticket.getId() == id) {
                return ticket;
            }
        }
        return null;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public List<Technicien> getTechniciens() {
        return techniciens;
    }

    public void ajouterTechnicien(Technicien technicien) {
        techniciens.add(technicien);
    }
}