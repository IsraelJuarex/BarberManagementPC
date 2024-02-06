    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
    package barberadmin1;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.sql.PreparedStatement;
    import java.sql.Statement;
    import java.util.Objects;
    import java.util.logging.Level;
    import java.util.logging.Logger;
    import javax.swing.JOptionPane;

    /**
     *
     * @author israel
     */
    public class DatabaseData extends SQLException {

         static protected int getTotalEfectivo(){
            try {
                Statement st = DataBaseConnection.getConnectionToDB().createStatement();
                ResultSet resultset = st.executeQuery("select total from trabajadores where admin = 0");
                int total = 0;
                while(resultset.next()){
                    total = total + resultset.getInt(1);
                }
                return total;

            } catch (SQLException ex) {
                Logger.getLogger(DatabaseData.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 0;
        }

        static protected int getTotalCortes(){
            try {
                Statement st = DataBaseConnection.getConnectionToDB().createStatement();
                ResultSet resultset = st.executeQuery("select cortes from trabajadores where admin = 0");
                int total = 0;
                while(resultset.next()){
                    total = total + resultset.getInt(1);
                }
                return total;

            } catch (SQLException ex) {
                Logger.getLogger(DatabaseData.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 0;
        }
        static protected int getTotalBarbas(){
            try {
                Statement st = DataBaseConnection.getConnectionToDB().createStatement();
                ResultSet resultset = st.executeQuery("select barbas from trabajadores where admin = 0");
                int total = 0;
                while(resultset.next()){
                    total = total + resultset.getInt(1);
                }
                return total;

            } catch (SQLException ex) {
                Logger.getLogger(DatabaseData.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 0;
        }
        static protected int getTotalCejas(){
            try {
                Statement st = DataBaseConnection.getConnectionToDB().createStatement();
                ResultSet resultset = st.executeQuery("select cejas from trabajadores where admin = 0");
                            int total = 0;
                while(resultset.next()){
                    total =total + resultset.getInt(1);
                }
                return total;

            } catch (SQLException ex) {
                Logger.getLogger(DatabaseData.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 0;
        }
        static protected int getTotalOnline(){
            try {
                Statement st = DataBaseConnection.getConnectionToDB().createStatement();
                ResultSet resultset = st.executeQuery("select status from trabajadores where admin = 0 && status != 'Offline'");
                            int total = 0;
                while(resultset.next()){
                    total =total + 1;
                }
                return total;

            } catch (SQLException ex) {
                Logger.getLogger(DatabaseData.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 0;
        } 


        static protected void deleteTrabajador(String usuario){
            try {
                Statement st = DataBaseConnection.getConnectionToDB().createStatement();
                st.execute("DELETE FROM trabajadores WHERE usuario = '"+ usuario +"' ");

                        } catch (SQLException ex) {
                Logger.getLogger(DatabaseData.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        static protected void setPrecios(String corte, String barba , String ceja){

            try {
                Statement st = DataBaseConnection.getConnectionToDB().createStatement();
                st.executeUpdate("update mercancia set Precio = '"+corte+"' where Servicio = 'corte' ");
                st.executeUpdate("update mercancia set Precio = '"+barba+"' where Servicio = 'barba' ");
                st.executeUpdate("update mercancia set Precio = '"+ceja+"' where Servicio = 'ceja' ");

            } catch (SQLException ex) {

                Logger.getLogger(DatabaseData.class.getName()).log(Level.SEVERE, null, ex);
            }


        }


        static protected String getPrecioCorte(){
            try {
                Statement st = DataBaseConnection.getConnectionToDB().createStatement();
                ResultSet resultset = st.executeQuery("select * from mercancia where Servicio = 'corte'");
                if (resultset.next()){
                   return resultset.getString(2);
                }
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseData.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }

            static protected String getPrecioBarba(){
            try {
                Statement st = DataBaseConnection.getConnectionToDB().createStatement();
                ResultSet resultset = st.executeQuery("select * from mercancia where Servicio = 'barba'");
                if (resultset.next()){
                    return resultset.getString(2);
                }
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseData.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }

                static protected String getPrecioCeja(){
            try {
                Statement st = DataBaseConnection.getConnectionToDB().createStatement();
                ResultSet resultset = st.executeQuery("select * from mercancia where Servicio = 'ceja'");
                if (resultset.next()){
                    return resultset.getString(2);
                }
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseData.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }



        static protected void RegistrarUsuario(String usuario, String contrasena, String nombre, String alias ) {
            try {
                try (PreparedStatement updateUsuario = Objects.requireNonNull(DataBaseConnection.getConnectionToDB()).prepareStatement("INSERT INTO trabajadores (usuario,contrasena,nombre,alias)VALUES (?, ?, ?, ?)")) {
                    updateUsuario.setString(1, usuario);
                    updateUsuario.setString(2, contrasena);
                    updateUsuario.setString(3, nombre);
                    updateUsuario.setString(4, alias);
                   // updateUsuario.setBoolean(5,admin);
                   // updateUsuario.setInt(6,estacion);
                    updateUsuario.executeUpdate();
                }
                JOptionPane.showMessageDialog(null, "Trabajador registrado con exito");

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al registrar usuario, Contactar administrador");
                e.printStackTrace();
            }
        }  
    }


