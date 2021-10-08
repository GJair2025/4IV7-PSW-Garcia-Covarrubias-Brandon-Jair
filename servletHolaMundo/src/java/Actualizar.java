
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//de poder realizar la conexion con la bd
import java.sql.Connection;
import java.sql.DriverManager;
//de poder realizar las sentencias sql, create, insert, delete, drop, update, alter
import java.sql.Statement;
//de poder realizar las consultas a la bd
import java.sql.ResultSet;
import javax.servlet.ServletConfig;



public class Actualizar extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request peticiones por parte del cliente
     * @param response servlet response son las respuestas por parte del
     * servidor
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /*
    el servlet para poderse conectar con la bd, es necesario inicializar sus elementos
    voy a necesitar de 3 objetos que vienen de la clase sql
     */
    private Connection con;
    private Statement set;
    private ResultSet rs;

    //vamos a crear el metodo constructor
    @Override
    public void init(ServletConfig cfg) throws ServletException {
        //para conectarnos con la bd
        String url = "jdbc:mysql:3306//localhost/registroo";
        //driver:gestorbd:puerto//IP/nombrebd

        String userName = "root";
        String password = "Arctic1!";

        try {

            Class.forName("com.mysql.jdbc.Driver");
            /*
            a veces el driver ya maneja por defecto el puerto de comunicacion
            es por ello que pueden mandar un error, en ese caso
            url = "jdbc:mysql://localhost/registro4iv7";
             */
            url = "jdbc:mysql://localhost/registroo";
            con = DriverManager.getConnection(url, userName, password);
            set = con.createStatement();

            System.out.println("Se ha conectado a la BD");

        } catch (Exception e) {
            System.out.println("No se ha conectado a la BD");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());

        }
    }

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            //manipular los datos del formulario
            String nom, appat, apmat, correo;
            int edad, id;

            nom = request.getParameter("nombre_2");
            appat = request.getParameter("appat_2");
            apmat = request.getParameter("apmat_2");
            correo = request.getParameter("email_2");

            id = Integer.parseInt(request.getParameter("idactualizar"));
            edad = Integer.parseInt(request.getParameter("edad_2"));

            try {

                String q = "update usuarios set nom_usu='" + nom + "',appat_usu='" + appat + "',apmat_usu='" + apmat + "',edad_usu=" + edad + ",email_usu='" + correo + "' where id_usu=" + id;
                set.executeUpdate(q);

                String ql = "select from usuarios where id_usu=" + id;

                set = con.createStatement();

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Gestión de usuarios</title>");
                out.println("</head>");
                out.println("<link rel=\"stylesheet\" href=\"./CSS/masterstyle.css\">");
                out.println("<body>"
                        + "<br>Tu nombre es:" + nom);
                out.println("<br>"
                        + "Tu Apellido Paterno es:" + appat
                        + "<br>"
                        + "Tu Apellido Materno es:" + apmat
                        + "<br>"
                        + "Tu Edad es:" + edad
                        + "<br>"
                        + "Tu correo electronico es:" + correo
                        + "<br>"
                        + "<br>");
                out.println("<a href='Consultar' class='boton'>Consultar la Tabla General de Usuarios</a>");
                out.println("</body>");
                out.println("</html>");

                System.out.println("Consulta exitosa");
                set.close();
                con.close();

                System.out.println("Datos actualizados en la tabla");

            } catch (Exception e) {

                System.out.println(e.getMessage());
                System.out.println(e.getStackTrace());
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Registro de Usuarios</title>");
                out.println("</head>");
                out.println("<body>"
                        + "<h1>No se pudo registrar, hubo un error</h1>"
                        + "<a href='index.html'>Regresar al Formulario</a>");

                out.println("</body>");
                out.println("</html>");
            }

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
        processRequest(request, response);
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     */
    @Override
    public void destroy() {
        try {
            con.close();

        } catch (Exception e) {
            super.destroy();

        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
