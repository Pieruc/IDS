package ids.controller;

import ids.model.Contributor;
import ids.model.Turista;
import ids.model.Utente;
import ids.model.UtenteFactory;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ids.repository.*;

import java.io.IOException;

@WebServlet(urlPatterns = {"/register","/login"})
public class UtenteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UtenteServlet(){
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String path=request.getServletPath();
        switch (path){
            case "/register":
                registerGet(request,response);
                break;
            case "/login":
                loginGet(request,response);
                break;
            }
    }

    private void registerGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.getWriter().append("Served at: ").append(request.getContextPath());

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/registrati.jsp");
        dispatcher.forward(request,response);
    }

    private void loginGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path=request.getServletPath();
        switch (path){
            case "/register":
                registerPost(request,response);
                break;
            case "/login":
                loginPost(request,response);
                break;
        }
    }

    private void registerPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        UtenteFactory factory = new UtenteFactory();

        String tipoUtente = request.getParameter("ruolo");
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Utente user = factory.getUtente(tipoUtente,nome,email,password);

        if(tipoUtente.equalsIgnoreCase("Turista")){
            TuristaRepository tRep = new TuristaRepository();
            tRep.addTurista((Turista) user);
        }

        if(tipoUtente.equalsIgnoreCase("Contributor")){
            ContributorRepository cRep = new ContributorRepository();
            cRep.addContributor((Contributor) user);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/registrati-success.jsp");
        dispatcher.forward(request,response);
    }

    private void loginPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

    }
}
