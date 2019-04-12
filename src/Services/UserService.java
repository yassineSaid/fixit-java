package Services;

import Entities.Langue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Entities.User;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserService {

    Connection C = Connexion.getInstance().getCon();

    public boolean checkUser(String username, String password) {
        String crypted = "$2y$13$ANVjZ0bnQfSMtHrt053Nh.oxKcesoimGIRwgQHf5i/mnKpl6bOlvG";
        //String crypted1="$2a"+crypted.substring(3);
        //System.out.println(BCrypt.checkpw("00100", crypted1));
        try {
            PreparedStatement pt = C.prepareStatement("SELECT * FROM user where username=?");
            pt.setString(1, username);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                String crypted1 = "$2a" + rs.getString("password").substring(3);
                if (BCrypt.checkpw(password, crypted1)) {
                    return true;
                }
            }
            return false;
            //return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User connect(String username) {
        User U = new User();
        try {
            PreparedStatement pt = C.prepareStatement("SELECT * FROM user where username=?");
            pt.setString(1, username);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                U.setEmail(rs.getString("email"));
                U.setFirstname(rs.getString("firstname"));
                U.setLastname(rs.getString("lastname"));
                U.setId(rs.getInt("id"));
                U.setUsername(rs.getString("username"));
                U.setImage(rs.getString("image"));
                U.setSolde(rs.getInt("solde"));
                if (rs.getString("roles").contains("ADMIN")) {
                    U.setRoles("admin");
                } else {
                    U.setRoles("user");
                }
            }
            return U;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public void modifierNomPrenom(User U, String nom, String prenom) {
        try {
            PreparedStatement pt = C.prepareStatement("UPDATE user SET firstname=?,lastname=? WHERE id=?");
            pt.setString(1, prenom);
            pt.setString(2, nom);
            pt.setInt(3, U.getId());
            pt.execute();
        } catch (SQLException e) {
            System.out.println(e.getCause());
            e.printStackTrace();
        }
    }

    public void modifierSolde(User U, int scoin) {
        try {
            PreparedStatement pt = C.prepareStatement("UPDATE user SET solde=? WHERE id=?");
            pt.setInt(1, scoin + U.getSolde());
            pt.setInt(2, U.getId());
            pt.execute();
        } catch (SQLException e) {
            System.out.println(e.getCause());
            e.printStackTrace();
        }
    }

    public boolean checkEmail(String email) {
        try {
            PreparedStatement pt = C.prepareStatement("select * from user where email_canonical=?");
            pt.setString(1, email.toLowerCase());
            ResultSet rs = pt.executeQuery();
            ObservableList<Langue> data = FXCollections.observableArrayList();
            while (rs.next()) {
                data.add(new Langue(rs.getInt(1), rs.getString(2), null));
            }
            if (data.size() < 1) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkUsername(String username) {
        try {
            PreparedStatement pt = C.prepareStatement("select * from user where username_canonical=?");
            pt.setString(1, username.toLowerCase());
            ResultSet rs = pt.executeQuery();
            ObservableList<Langue> data = FXCollections.observableArrayList();
            while (rs.next()) {
                data.add(new Langue(rs.getInt(1), rs.getString(2), null));
            }
            if (data.size() < 1) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void persistUser(User user) {
        try {
            PreparedStatement pt = C.prepareStatement("INSERT INTO user("
                    + "username,username_canonical,email,email_canonical,enabled,password,roles,firstname,lastname,address,zip_code,city,phone,solde"
                    + ") VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pt.setString(1, user.getUsername());
            pt.setString(2, user.getUsername_canonical());
            pt.setString(3, user.getEmail());
            pt.setString(4, user.getEmail_canonical());
            pt.setInt(5, 1);
            pt.setString(6, user.getPassword());
            pt.setString(7, user.getRoles());
            pt.setString(8, user.getFirstname());
            pt.setString(9, user.getLastname());
            pt.setString(10, user.getAddress());
            pt.setString(11, user.getZip_code());
            pt.setString(12, user.getCity());
            pt.setInt(13, user.getPhone());
            pt.setInt(14, 0);
            pt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void inscription(String username, String email, String password, String firstname, String lastname, String address, String zip_code, String city, String phone) {
        User user = new User();
        user.setUsername(username);
        user.setUsername_canonical(username.toLowerCase());
        user.setEmail(email);
        user.setEmail_canonical(email.toLowerCase());
        user.setPassword(createPwd(password));
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setAddress(address);
        user.setZip_code(zip_code);
        user.setCity(city);
        user.setRoles("a:0:{}");
        if (phone.length()>0) user.setPhone(Integer.parseInt(phone));
        persistUser(user);
    }

    public String createPwd(String password) {
        String newPass;
        newPass = BCrypt.hashpw(password, BCrypt.gensalt());
        return newPass;
    }

    public boolean validEmail(String email) {
        String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
