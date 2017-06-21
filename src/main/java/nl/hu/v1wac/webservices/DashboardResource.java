package nl.hu.v1wac.webservices;

import nl.hu.v1wac.model.*;

import javax.json.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.StringReader;


@Path("/dashboard")
public class DashboardResource {
    private WorldService service = ServiceProvider.getWorldService();

    @GET
    @Path("get/uitgaves/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String loadUitgaves(@PathParam("id") int id) {
        JsonArrayBuilder jab = Json.createArrayBuilder();

        for (Uitgave u : service.getUitgaveByUserID(id)) {
            JsonObjectBuilder job = buildUitgaveObject(u);
            jab.add(job);
        }

        return jab.build().toString();

    }

    @GET
    @Path("get/inkomsten/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String loadInkomsten(@PathParam("id") int id) {
        JsonArrayBuilder jab = Json.createArrayBuilder();

        for (Inkomst i : service.getInkomstByUserID(id)) {
            JsonObjectBuilder job = buildInkomstObject(i);
            jab.add(job);
        }

        return jab.build().toString();

    }

    @POST
    @Path("/uitgave/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUitgave(String json) {
        JsonObject object = stringToJson(json);
        Uitgave uitgave  = buildUitgave(object);
        service.addUitgave(uitgave);
        return Response.ok().build();
    }

    @POST
    @Path("/inkomst/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addInkomst(String json) {
        JsonObject object = stringToJson(json);
        Inkomst inkomst = buildInkomst(object);
        service.addInkomst(inkomst);
        return Response.ok().build();
    }

    @PUT
    @Path("/uitgave/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUitgave(String json) {
        JsonObject object = stringToJson(json);
        Uitgave uitgave = buildUitgaveUpdate(object);
        service.updateUitgave(uitgave);
        return Response.ok().build();
    }

    @PUT
    @Path("/inkomst/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateInkomst(String json) {
        JsonObject object = stringToJson(json);
        Inkomst inkomst = buildInkomstUpdate(object);
        service.updateInkomst(inkomst);
        return Response.ok().build();
    }


    @GET
    @Path("/uitgave/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUitgave(@PathParam("id") int id) {
        Uitgave u = service.getUitgaveById(id);
        JsonObjectBuilder job = buildUitgaveObject(u);
        return job.build().toString();
    }

    @GET
    @Path("/inkomst/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getInkomst(@PathParam("id") int id) {
        Inkomst i = service.getInkomstById(id);
        JsonObjectBuilder job = buildInkomstObject(i);
        return job.build().toString();
    }
    @GET
    @Path("/user/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUserInfo(@PathParam("id") int id) {
        User user = service.getUserById(id);
        JsonObjectBuilder job = buildUserObject(user);
        return job.build().toString();
    }



    @GET
    @Path("/inkomst/getsum/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getInkomstSum(@PathParam("id") int id) {
        int totaalInkomst = service.getInkomstSum(id);
        JsonObjectBuilder job = Json.createObjectBuilder();
        job.add("totaalInkomst", totaalInkomst);
        return job.build().toString();
    }

    @GET
    @Path("/uitgave/getsum/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUitgaveSum(@PathParam("id") int id) {
        int totalUitgave = service.getUitgaveSum(id);
        JsonObjectBuilder job = Json.createObjectBuilder();
        job.add("totalUitgave", totalUitgave);
        return job.build().toString();
    }

    @GET
    @Path("/total/getsum/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getRestTotaal(@PathParam("id") int id) {
        int totalUitgave = service.getUitgaveSum(id);
        int totalInkomst = service.getInkomstSum(id);
        int restTotal = totalInkomst - totalUitgave;
        JsonObjectBuilder job = Json.createObjectBuilder();
        job.add("restTotal", restTotal);
        return job.build().toString();
    }



    @DELETE
    @Path("/uitgave/delete/{id}")
    public Response deleteUitgave(@PathParam("id") int id) {
        Uitgave uitgave = service.getUitgaveById(id);
        boolean success = service.delete(uitgave);

        return Response.ok().build();
    }

    @DELETE
    @Path("/inkomst/delete/{id}")
    public Response deleteInkomst(@PathParam("id") int id) {
        Inkomst inkomst = service.getInkomstById(id);
        boolean success = service.delete(inkomst);

        return Response.ok().build();
    }

    private JsonObject stringToJson(String jsonString) {
        JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
        JsonObject object = jsonReader.readObject();
        jsonReader.close();
        return object;
    }

    private Inkomst buildInkomst(JsonObject object) {
        Inkomst inkomst = new Inkomst(Integer.parseInt(object.getString("user_id")));
        inkomst.setBedrag(Integer.parseInt(object.getString("bedrag")));
        inkomst.setSoortInkomen(object.getString("soort_inkomst"));
        inkomst.setInkomstDatum(object.getString("inkomst_datum"));
        inkomst.setBeschrijving((object.getString("beschrijving")));
        return inkomst;
    }
    private Inkomst buildInkomstUpdate(JsonObject object) {
        Inkomst inkomst = buildInkomst(object);
        inkomst.setInkomstID(Integer.parseInt(object.getString("inkomst_id")));
        return inkomst;
    }

    private Uitgave buildUitgave(JsonObject object){
        Uitgave uitgave = new Uitgave(Integer.parseInt(object.getString("user_id")));

        uitgave.setBedrag(Integer.parseInt(object.getString("bedrag")));
        uitgave.setSoortUitgave(object.getString("soort_uitgave"));

        if (object.getBoolean("betalingsregeling")){
            uitgave.setKenmerknummer((object.getString("kenmerknummer")));
            uitgave.setAantalMaanden(Integer.parseInt(object.getString("aantal_maanden")));
        } else {
            uitgave.setKenmerknummer("nvt");
            uitgave.setAantalMaanden(0);
        }
        if (object.getBoolean("tijdelijke_uitgave")) {
            uitgave.setLink((object.getString("link")));
            uitgave.setAfbeelding((object.getString("afbeelding")));
        } else{
            uitgave.setLink("nvt");
            uitgave.setAfbeelding("nvt");
        }
        uitgave.setUitgaveDatum(object.getString("uitgave_datum"));
        uitgave.setBeschrijving((object.getString("beschrijving")));

        return uitgave;
    }
    private Uitgave buildUitgaveUpdate(JsonObject object){
        Uitgave uitgave = buildUitgave(object);
        uitgave.setUitgaveID(Integer.parseInt(object.getString("uitgave_id")));
        return uitgave;
    }

    private JsonObjectBuilder buildUitgaveObject(Uitgave u) {
        JsonObjectBuilder job = Json.createObjectBuilder();
        job.add("uitgave_id", u.getUitgaveID())
                .add("bedrag", u.getBedrag())
                .add("soort_uitgave", u.getSoortUitgave())
                .add("uitgave_datum", u.getUitgaveDatumString())
                .add("beschrijving", u.getBeschrijving())
                .add("kenmerknummer", u.getKenmerknummer())
                .add("aantal_maanden", u.getAantalMaanden())
                .add("link", u.getLink())
                .add("afbeelding", u.getAfbeelding())
                .add("user_id", u.getUserID());
        return job;
    }

    private JsonObjectBuilder buildInkomstObject(Inkomst i) {
        JsonObjectBuilder job = Json.createObjectBuilder();
        job.add("inkomst_id", i.getInkomstID())
                .add("bedrag", i.getBedrag())
                .add("soort_inkomst", i.getSoortInkomen())
                .add("inkomst_datum", i.getInkomstDatumString())
                .add("beschrijving", i.getBeschrijving())
                .add("user_id", i.getUserID());
        return job;
    }
    private JsonObjectBuilder buildUserObject(User user) {
        JsonObjectBuilder job = Json.createObjectBuilder();
        job.add("volledigeNaam", user.getVoornaam()+ " " + user.getTussenvoegsel()+ " " +user.getAchternaam())
                .add("woonplaats", user.getWoonplaats())
                .add("adres", user.getAdres())
                .add("user_id", user.getUserID());
        return job;
    }
}

