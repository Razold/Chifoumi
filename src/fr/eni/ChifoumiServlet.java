package fr.eni;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Random;

@WebServlet("/chifoumi")
public class ChifoumiServlet extends HttpServlet {
    private int nbEssai;
    private int nbVictoire;
    private int hasard;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        nbEssai++;

            Random choix = new Random();
            this.hasard=  choix.nextInt(3)+1;

            RequestDispatcher rd;//Je déclare mon RequestDispatcher
            rd = request.getRequestDispatcher("WEB-INF/jouer.jsp");//Je définis l'url  de destination
            //response
            rd.forward(request,response);
            // TODO On on envoie la requête forward sur le jsp jouer  puis on récupère la méthode post

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bouton= request.getParameter("bouton");

        String texteFinal =reglesChifoumi(hasard,bouton);
        request.setAttribute("texteFinal",texteFinal);
        RequestDispatcher rd;//Je déclare mon RequestDispatcher
        rd = request.getRequestDispatcher("WEB-INF/resultat.jsp");//Je définis l'url  de destination

        rd.forward(request,response);
        // TODO On vérifie puis on forward dans le post vers la page résultat qui reviens dans le servlet si on appuie sur rejouer
    }

    String reglesChifoumi(int hasard, String bouton){
        int numeroBouton =0;
        String texteResultat="";
        texteResultat=texteResultat+"L'utilisateur a choisi : ";
        switch(bouton){
            case "chi":numeroBouton=1;
                texteResultat=texteResultat+"PIERRE ";
            break;
            case "fou":numeroBouton=2;
                texteResultat=texteResultat+"FEUILLE ";
            break;
            case "mi":numeroBouton=3;
                texteResultat=texteResultat+"CISEAU ";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + bouton);
        }
        texteResultat=texteResultat+" l'ordinateur a choisi : ";
        switch(hasard){
            case 1:
                texteResultat=texteResultat+"PIERRE \n";
                break;
            case 2:
                texteResultat=texteResultat+"FEUILLE \n";
                break;
            case 3:
                texteResultat=texteResultat+"CISEAU \n";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + hasard);

        }
        if(hasard == numeroBouton){
            texteResultat=texteResultat+"EGALITE";
        }else{
            if((numeroBouton == 1 && hasard ==2)||(numeroBouton == 2 && hasard ==3)||(numeroBouton == 3 && hasard ==1)){
                texteResultat=texteResultat+"DEFAITE";
            }else{
                nbVictoire++;
                texteResultat=texteResultat+"VICTOIRE";
            }
        }

        return texteResultat;
    }

    @Override
    public void init() throws ServletException {
        this.nbEssai=0;
        this.nbVictoire=0;
        Random choix = new Random();
        this.hasard=  choix.nextInt(3)+1;


    }
}
