package nl.hu.v1wac;

import nl.hu.v1wac.persistence.UserDAO;

/**
 * Created by dahir on 6/14/2017.
 */
public class test {
    public static void main(String[] args) {
        UserDAO dao = new UserDAO();
        String user = dao.findUserForUsernameAndPassword("dahirwarsame@gmail.com", "geheim");
        System.out.println(user);
    }
}
