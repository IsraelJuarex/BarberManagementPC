package barberadmin1;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.sql.Statement;

class validInputs{
    
    public static boolean validarExistUser(String user) {
        try {
            PreparedStatement selectUser = ((Connection)Objects.requireNonNull(DataBaseConnection.getConnectionToDB())).prepareStatement("select usuario from barberLara", 1005, 1007);

            boolean var4;
            label56: {
                try {
                    ResultSet resultSet = selectUser.executeQuery();
                    resultSet.beforeFirst();

                    while(resultSet.next()) {
                        String usuario = resultSet.getString(1);
                        if (usuario.equalsIgnoreCase(user)) {
                            var4 = true;
                            break label56;
                        }
                    }
                } catch (Throwable var6) {
                    if (selectUser != null) {
                        try {
                            selectUser.close();
                        } catch (Throwable var5) {
                            var6.addSuppressed(var5);
                        }
                    }

                    throw var6;
                }

                if (selectUser != null) {
                    selectUser.close();
                }

                return false;
            }

            if (selectUser != null) {
                selectUser.close();
            }

            return var4;
        } catch (SQLException var7) {
            var7.printStackTrace();
            return false;
        }
    }

    static boolean validCorrectPassword(String usuario, String contrasena) {
        try {
            PreparedStatement fechUsers = ((Connection)Objects.requireNonNull(DataBaseConnection.getConnectionToDB())).prepareStatement("select usuario , contrasena  from trabajadores where usuario = ?");

            label58: {
                boolean var6;
                try {
                    fechUsers.setString(1, usuario);
                    ResultSet resultSet = fechUsers.executeQuery();

                    String user;
                    String pass;
                    do {
                        if (!resultSet.next()) {
                            resultSet.close();
                            break label58;
                        }

                        user = resultSet.getString(1);
                        pass = resultSet.getString(2);
                    } while(!user.equalsIgnoreCase(usuario) || !pass.equals(contrasena));

                    resultSet.close();
                    var6 = true;
                } catch (Throwable var8) {
                    if (fechUsers != null) {
                        try {
                            fechUsers.close();
                        } catch (Throwable var7) {
                            var8.addSuppressed(var7);
                        }
                    }

                    throw var8;
                }

                if (fechUsers != null) {
                    fechUsers.close();
                }

                return var6;
            }

            if (fechUsers != null) {
                fechUsers.close();
            }
        } catch (SQLException var9) {
            var9.printStackTrace();
        } catch (NullPointerException var10) {
            JOptionPane.showMessageDialog(null, "Error conectado al servidor , Asegure que tenga una conexion a internet");
        }

        return false;
    }
}
