package nl.hu.v1wac.webservices;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import nl.hu.v1wac.persistence.UserDAO;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.Key;
import java.util.Calendar;


@Path("/authentication")
public class AuthenticationResource {
    final static public Key key = MacProvider.generateKey();

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response authenticateUser(@FormParam("email") String email,
                                     @FormParam("password") String password) {

        UserDAO dao = new UserDAO();
        String user = dao.authenticateUser(email, password);

        if (user == null) {
            throw new IllegalArgumentException("No user found!");
        }

        return Response.ok(user).build();

    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response registerUser(@FormParam("email") String email,
                                 @FormParam("password") String password,
                                 @FormParam("password_confirmation") String password_confirmation) {
        boolean success = false;
        try {
            UserDAO dao = new UserDAO();
            if (password.equals(password_confirmation)) {
                success = dao.registerUser(email, password);
            }

            if (success) {
                String user = dao.authenticateUser(email, password);
                // test met user
                //TODO:: add actual token
                return Response.ok(user).build();
            }

        } catch (JwtException | IllegalArgumentException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }


        return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }
}

