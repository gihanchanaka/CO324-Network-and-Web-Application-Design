package stockTicker;

import java.io.*;
import java.util.logging.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(urlPatterns = {"/stream"})
public class PriceUpdate extends HttpServlet {

    final Stock stock = new Stock();

    @Override
    public void init(ServletConfig config) {
        stock.start();
    }

    /**
     * Processes requests for HTTP <code>GET</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/event-stream");
        response.flushBuffer();
        Logger.getGlobal().log(Level.INFO, "Beginning update stream.");

        try (PrintWriter out = response.getWriter()) {
            while (!Thread.interrupted())
                synchronized (stock) {
                    stock.wait();
                    out.print("data: {\n");
                    out.print("data: \"price\": \""+stock.price+"\",\n");
                    out.print("data: \"timeStamp\": \""+stock.timeStamp+"\"\n");
                    out.print("data: }\n\n");
                    out.println();
                    out.flush();
                }
        } catch (InterruptedException e) {
            Logger.getGlobal().log(Level.INFO, "Terminating updates.");
            response.setStatus(HttpServletResponse.SC_GONE);
        }
    }

    @Override
    public void destroy() {
        stock.interrupt();
    }
}
