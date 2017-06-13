package nl.hu.v1wac.webservices;

/**
 * Created by dahir on 6/12/2017.
 */

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import nl.hu.v1wac.model.ServiceProvider;
import nl.hu.v1wac.model.Uitgave;
import nl.hu.v1wac.model.WorldService;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.json.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.StringReader;


@Path("/dashboard")
public class DashboardResource {
    private WorldService service = ServiceProvider.getWorldService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String loadUitgaves() {
        System.out.println("DashboardResource/loadUitgaves");
        JsonArrayBuilder jab = Json.createArrayBuilder();

        for (Uitgave u : service.getUitgaveByUserID(1)) {
            JsonObjectBuilder job = buildCountryObject(u);
            jab.add(job);
        }

        return jab.build().toString();

    }
    private JsonObjectBuilder buildCountryObject(Uitgave u) {
        JsonObjectBuilder job = Json.createObjectBuilder();
        job.add("uitgaveID", u.getUitgaveID())
                .add("userID", u.getUserID())
                .add("bedrag", u.getBedrag())
                .add("soortUitgave", u.getSoortUitgave())
                .add("kenmerknummer",u.getKenmerknummer())
                .add("aantalMaanden", u.getAantalMaanden())
                .add("link", u.getLink())
                .add("afbeelding", u.getAfbeelding())
                .add("uitgaveDatum", u.getUitgaveDatum())
                .add("beschrijving", u.getBeschrijving());
        return job;
    }
}

