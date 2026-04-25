import java.util.ArrayList;
import java.util.List;

public class ServiceMaintenance {
    private List<Ticket> tickets;
    private List<Intervention> interventions;

    public ServiceMaintenance() {
        this.tickets = new ArrayList<>();
        this.interventions = new ArrayList<>();
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void ajouterTicket(Ticket ticket) {
        if (ticket != null) {
            tickets.add(ticket);
        }
    }

    public List<Intervention> getInterventions() {
        return interventions;
    }

    public void setInterventions(List<Intervention> interventions) {
        this.interventions = interventions;
    }

    public void ajouterIntervention(Intervention intervention) {
        if (intervention != null) {
            interventions.add(intervention);
        }
    }
}