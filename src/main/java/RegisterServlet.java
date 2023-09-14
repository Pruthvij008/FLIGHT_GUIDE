package customPackage;

import org.example.Main;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Arrays;

import static org.example.Main.findFlights;


public class RegisterServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        String mysources, mydest;
        out.println("<html>");
        out.println("<head>");
        out.println("<h2>ROUTES</h2>");
        out.println("</head>");
        out.println("<body>");
         mysources=request.getParameter("ss");
         mydest=request.getParameter("dd");

      //  ArrayList<Main.Flight> flights = createFlightList();
        String result = findFlights(mysources, mydest);

            out.println("<div class=\"result\">");
            out.println("<h2 class=\"result-item\">Final: <span>" + result + "</span></h2>");
           // out.println("<h2 class=\"result-item\">Religion Selected: <span>" + mydest + "</span></h2>");
            // out.println("<h1 class=\"result-item\">RESULT: <span>" + result + "</span></h1>");

            out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
