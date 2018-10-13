/*
    CO324 Lab 02
    E/14/158 gihanchanaka@gmail.com
    04-02-2018
*/

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 *
 * @author Nathasha
 */

@WebServlet(
        name = "AddressBookServlet",
        urlPatterns = "/AddressBookServlet",
        initParams = {@WebInitParam(name="filename", value="/addressBook.txt")}

)
public class AddressBookServlet extends HttpServlet {
       
    String filename;
    
    //Servlet initialization
    public void init() throws ServletException {
        
        ServletConfig config = getServletConfig();
        
        //get the value of the init-parameter
        filename = config.getInitParameter("filename");
        ServletContext sc = config.getServletContext();
        filename = sc.getRealPath(filename);
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
            
    
    protected void processGetRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try{
	        HttpSession session = request.getSession(true);
	        

	        //This is to make a new AddressBook instance when a new user access the Servlet 
	        if(null==session.getAttribute("AddressBookData")){
	        	session.setAttribute("AddressBookData",new AddressBook(filename));
	        }

    
        	AddressBook ab=(AddressBook)session.getAttribute("AddressBookData");
            if(request.getParameter("name").length()<1){
                response.sendError(411);//Length Required:The request did not specify the length of its content, which is required by the requested resource.
            }       
            else{
                String reply=ab.search(request.getParameter("name"));
                PrintWriter out = response.getWriter();
                out.println(reply);
//                if(reply.equals("Contact not found"))response.sendError(404);//Resource not found error
            }
        }
        catch(Exception e){
            response.sendError(500);//Server error
        }
        
    }

    protected void processPostRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try{
	        HttpSession session = request.getSession(true);
	        

	        //This is to make a new AddressBook instance when a new user access the Servlet
	        if(null==session.getAttribute("AddressBookData")){
	        	session.setAttribute("AddressBookData",new AddressBook(filename));
	        }


	        AddressBook ab=(AddressBook)session.getAttribute("AddressBookData");
	        ab.contacts.put(request.getParameter("name"), new String[]{request.getParameter("phoneNo"),request.getParameter("email")});



	        PrintWriter out = response.getWriter();
	        out.println("<html><title>Added</title><body>");
	        out.println("<h2>Added</h2>");
	        out.println("<h3><a href=search.html> Click here Go back to your updated addressbook</a></h3>");
	        out.println("</body></html>");
        }
        catch(Exception e){
        	response.sendError(500);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processGetRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processPostRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servelet to handle Address Book searches";
    }// </editor-fold>

}
