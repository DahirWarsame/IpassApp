package nl.hu.v1wac.webservices;

import nl.hu.v1wac.model.ServiceProvider;
import nl.hu.v1wac.model.Uitgave;
import nl.hu.v1wac.model.WorldService;

import javax.json.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("/dashboard")
public class DashboardResource {
    private WorldService service = ServiceProvider.getWorldService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String loadUitgaves() {
        System.out.println("DashboardResource/loadUitgaves");
        JsonArrayBuilder jab = Json.createArrayBuilder();

        for (Uitgave u : service.getUitgaveByUserID(1)) {
            System.out.println(u.toString());
            JsonObjectBuilder job = buildUitgaveObject(u);
            jab.add(job);
        }

        return jab.build().toString();

    }
    private JsonObjectBuilder buildUitgaveObject(Uitgave u) {
        JsonObjectBuilder job = Json.createObjectBuilder();
        System.out.println(u.toString());
        job.add("uitgave_id", u.getUitgaveID())
                .add("user_id", u.getUserID())
                .add("bedrag", u.getBedrag())
                .add("soort_uitgave", u.getSoortUitgave())
                .add("kenmerknummer",u.getKenmerknummer())
                .add("aantal_maanden", u.getAantalMaanden())
                .add("link", u.getLink())
                .add("afbeelding", u.getAfbeelding())
                .add("uitgave_datum", u.getUitgaveDatum())
                .add("beschrijving", u.getBeschrijving());
        return job;
    }
}

