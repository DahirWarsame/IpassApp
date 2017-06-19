package nl.hu.v1wac.webservices;

import nl.hu.v1wac.model.Inkomst;
import nl.hu.v1wac.model.ServiceProvider;
import nl.hu.v1wac.model.Uitgave;
import nl.hu.v1wac.model.WorldService;

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
        System.out.println("DashboardResource/loadUitgaves");
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
        System.out.println("DashboardResource/loadInkomsten");
        JsonArrayBuilder jab = Json.createArrayBuilder();

        for (Inkomst i : service.getInkomstByUserID(id)) {
            JsonObjectBuilder job = buildInkomstObject(i);
            jab.add(job);
        }

        return jab.build().toString();

    }

    @POST
    @Path("/uitgave/add")
    @Consumes("application/json")
    public Response addUitgave(@FormParam("user_id") int id, String json) {
        System.out.println(json);
        JsonObject object = stringToJson(json);
        Uitgave uitgave  = buildUitgave(object,id);
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
    public Response updateUitgave(@PathParam("id") int id, String json) {
        System.out.println(json);
        JsonObject object = stringToJson(json);
        Uitgave uitgave = buildUitgave(object,id);
        service.updateUitgave(uitgave);
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
        int getUitgaveSum = service.getInkomstSum(id);
        JsonObjectBuilder job = Json.createObjectBuilder();
        job.add("getUitgaveSum", getUitgaveSum);
        return job.build().toString();
    }



    @DELETE
    @Path("/uitgave/delete/{id}")
    public Response deleteUitgave(@PathParam("id") int id) {
        System.out.println(id);
        Uitgave uitgave = service.getUitgaveById(id);
        System.out.println(uitgave);
        boolean success = service.delete(uitgave);

        return Response.ok().build();
    }

    @DELETE
    @Path("/inkomst/delete/{id}")
    public Response deleteInkomst(@PathParam("id") int id) {
        System.out.println(id);
        Inkomst inkomst = service.getInkomstById(id);
        System.out.println(inkomst);
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

    private Uitgave buildUitgave(JsonObject object, int id){
        Uitgave uitgave = new Uitgave(id);

        uitgave.setBedrag(Integer.parseInt(object.getString("bedrag")));
        uitgave.setSoortUitgave(object.getString("soort_uitgave"));

        uitgave.setKenmerknummer(Integer.parseInt(object.getString("kenmerknummer")));
        uitgave.setAantalMaanden(Integer.parseInt(object.getString("aantal_maanden")));

        uitgave.setLink((object.getString("link")));
        uitgave.setAfbeelding((object.getString("afbeelding")));

        uitgave.setUitgaveDatum(java.sql.Date.valueOf(object.getString("uitgave_datum")));
        uitgave.setBeschrijving((object.getString("beschrijving")));

        System.out.println(uitgave);

        return uitgave;
    }

    private JsonObjectBuilder buildUitgaveObject(Uitgave u) {
        JsonObjectBuilder job = Json.createObjectBuilder();
        job.add("uitgave_id", u.getUitgaveID())
                .add("user_id", u.getUserID())
                .add("bedrag", u.getBedrag())
                .add("soort_uitgave", u.getSoortUitgave())
                .add("kenmerknummer", u.getKenmerknummer())
                .add("aantal_maanden", u.getAantalMaanden())
                .add("link", u.getLink())
                .add("afbeelding", u.getAfbeelding())
                .add("uitgave_datum", u.getUitgaveDatum())
                .add("beschrijving", u.getBeschrijving());
        return job;
    }

    private JsonObjectBuilder buildInkomstObject(Inkomst i) {
        JsonObjectBuilder job = Json.createObjectBuilder();
        job.add("inkomst_id", i.getInkomstID())
                .add("user_id", i.getUserID())
                .add("bedrag", i.getBedrag())
                .add("soort_inkomst", i.getSoortInkomen())
                .add("inkomst_datum", i.getInkomstDatumString())
                .add("beschrijving", i.getBeschrijving());
        return job;
    }
}

