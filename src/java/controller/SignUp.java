package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import hibernate.HibernateUtil;
import hibernate.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Mail;
import model.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@WebServlet(name = "SignUp", urlPatterns = {"/SignUp"})
public class SignUp extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Gson gson = new Gson();
        JsonObject user = gson.fromJson(request.getReader(), JsonObject.class);

        String firstName = user.get("firstName").getAsString();
        String lastName = user.get("lastName").getAsString();
        String email = user.get("email").getAsString();
        String password = user.get("password").getAsString();

        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();

        //Hibernate Save
        User u = new User();
        u.setFirst_name(firstName);
        u.setLast_name(lastName);
        u.setEmail(email);
        u.setPassword(password);
        
        //generate verification code
        String verificationCode = Util.generateCode();
        u.setVerification(verificationCode);

        u.setCreated_at(new Date());

        s.save(u);
        s.beginTransaction().commit();
        //Hibernate Save
        
        //Send Email
        new Thread(new Runnable() {
            @Override
            public void run() {
                Mail.sendMail(email, "SmartTrade - Verification", "<h1>"+verificationCode+"</h1>");
            }
        }).start();
        //Send Email
    }

}
