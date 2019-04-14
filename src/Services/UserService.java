package Services;

import Entities.Langue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Entities.User;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserService {

    Connection C = Connexion.getInstance().getCon();

    public boolean checkUser(String username, String password) {
        try {
            PreparedStatement pt = C.prepareStatement("SELECT * FROM user where username=? AND enabled=1");
            pt.setString(1, username);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                String crypted = "$2a" + rs.getString("password").substring(3);
                if (BCrypt.checkpw(password, crypted)) {
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
        if (phone.length() > 0) {
            user.setPhone(Integer.parseInt(phone));
        }
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

    public String generateCode() {
        String characters = "0123456789";
        String randomString = "";
        int length = 5;

        Random rand = new Random();

        char[] text = new char[length];

        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(rand.nextInt(characters.length()));
        }

        for (int i = 0; i < text.length; i++) {
            randomString += text[i];
        }
        return randomString;
    }

    public String recupPassword(String email) throws Exception {
        String code = generateCode();
        String mailContent = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n"
                + "<html>\n"
                + "<head>\n"
                + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n"
                + "<!--[if !mso]><!-->\n"
                + "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\n"
                + "<!--<![endif]-->\n"
                + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n"
                + "<title></title>\n"
                + "<style type=\"text/css\">\n"
                + "* {\n"
                + "	-webkit-font-smoothing: antialiased;\n"
                + "}\n"
                + "body {\n"
                + "	Margin: 0;\n"
                + "	padding: 0;\n"
                + "	min-width: 100%;\n"
                + "	font-family: Arial, sans-serif;\n"
                + "	-webkit-font-smoothing: antialiased;\n"
                + "	mso-line-height-rule: exactly;\n"
                + "}\n"
                + "table {\n"
                + "	border-spacing: 0;\n"
                + "	color: #333333;\n"
                + "	font-family: Arial, sans-serif;\n"
                + "}\n"
                + "img {\n"
                + "	border: 0;\n"
                + "}\n"
                + ".wrapper {\n"
                + "	width: 100%;\n"
                + "	table-layout: fixed;\n"
                + "	-webkit-text-size-adjust: 100%;\n"
                + "	-ms-text-size-adjust: 100%;\n"
                + "}\n"
                + ".webkit {\n"
                + "	max-width: 600px;\n"
                + "}\n"
                + ".outer {\n"
                + "	Margin: 0 auto;\n"
                + "	width: 100%;\n"
                + "	max-width: 600px;\n"
                + "}\n"
                + ".full-width-image img {\n"
                + "	width: 100%;\n"
                + "	max-width: 600px;\n"
                + "	height: auto;\n"
                + "}\n"
                + ".inner {\n"
                + "	padding: 10px;\n"
                + "}\n"
                + "p {\n"
                + "	Margin: 0;\n"
                + "	padding-bottom: 10px;\n"
                + "}\n"
                + ".h1 {\n"
                + "	font-size: 21px;\n"
                + "	font-weight: bold;\n"
                + "	Margin-top: 15px;\n"
                + "	Margin-bottom: 5px;\n"
                + "	font-family: Arial, sans-serif;\n"
                + "	-webkit-font-smoothing: antialiased;\n"
                + "}\n"
                + ".h2 {\n"
                + "	font-size: 18px;\n"
                + "	font-weight: bold;\n"
                + "	Margin-top: 10px;\n"
                + "	Margin-bottom: 5px;\n"
                + "	font-family: Arial, sans-serif;\n"
                + "	-webkit-font-smoothing: antialiased;\n"
                + "}\n"
                + ".one-column .contents {\n"
                + "	text-align: left;\n"
                + "	font-family: Arial, sans-serif;\n"
                + "	-webkit-font-smoothing: antialiased;\n"
                + "}\n"
                + ".one-column p {\n"
                + "	font-size: 14px;\n"
                + "	Margin-bottom: 10px;\n"
                + "	font-family: Arial, sans-serif;\n"
                + "	-webkit-font-smoothing: antialiased;\n"
                + "}\n"
                + ".two-column {\n"
                + "	text-align: center;\n"
                + "	font-size: 0;\n"
                + "}\n"
                + ".two-column .column {\n"
                + "	width: 100%;\n"
                + "	max-width: 300px;\n"
                + "	display: inline-block;\n"
                + "	vertical-align: top;\n"
                + "}\n"
                + ".contents {\n"
                + "	width: 100%;\n"
                + "}\n"
                + ".two-column .contents {\n"
                + "	font-size: 14px;\n"
                + "	text-align: left;\n"
                + "}\n"
                + ".two-column img {\n"
                + "	width: 100%;\n"
                + "	max-width: 280px;\n"
                + "	height: auto;\n"
                + "}\n"
                + ".two-column .text {\n"
                + "	padding-top: 10px;\n"
                + "}\n"
                + ".three-column {\n"
                + "	text-align: center;\n"
                + "	font-size: 0;\n"
                + "	padding-top: 10px;\n"
                + "	padding-bottom: 10px;\n"
                + "}\n"
                + ".three-column .column {\n"
                + "	width: 100%;\n"
                + "	max-width: 200px;\n"
                + "	display: inline-block;\n"
                + "	vertical-align: top;\n"
                + "}\n"
                + ".three-column .contents {\n"
                + "	font-size: 14px;\n"
                + "	text-align: center;\n"
                + "}\n"
                + ".three-column img {\n"
                + "	width: 100%;\n"
                + "	max-width: 180px;\n"
                + "	height: auto;\n"
                + "}\n"
                + ".three-column .text {\n"
                + "	padding-top: 10px;\n"
                + "}\n"
                + ".img-align-vertical img {\n"
                + "	display: inline-block;\n"
                + "	vertical-align: middle;\n"
                + "}\n"
                + "@media only screen and (max-device-width: 480px) {\n"
                + "table[class=hide], img[class=hide], td[class=hide] {\n"
                + "	display: none !important;\n"
                + "}\n"
                + ".contents1 {\n"
                + "	width: 100%;\n"
                + "}\n"
                + ".contents1 {\n"
                + "	width: 100%;\n"
                + "}\n"
                + "</style>\n"
                + "</head>\n"
                + "\n"
                + "<body style=\"Margin:0;padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;min-width:100%;background-color:#f3f2f0;\">\n"
                + "<center class=\"wrapper\" style=\"width:100%;table-layout:fixed;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;background-color:#f3f2f0;\">\n"
                + "  <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"background-color:#f3f2f0;\" bgcolor=\"#f3f2f0;\">\n"
                + "    <tr>\n"
                + "      <td width=\"100%\"><div class=\"webkit\" style=\"max-width:600px;Margin:0 auto;\"> \n"
                + "          <table class=\"outer\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-spacing:0;Margin:0 auto;width:100%;max-width:600px;\">\n"
                + "            <tr>\n"
                + "              <td style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\"><!-- ======= start header ======= -->\n"
                + "                \n"
                + "                <table border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\"  >\n"
                + "                  <tr>\n"
                + "                    <td><table style=\"width:100%;\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n"
                + "                        <tbody>\n"
                + "                          <tr>\n"
                + "                            <td align=\"center\"><center>\n"
                + "                                <table border=\"0\" align=\"center\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"Margin: 0 auto;\">\n"
                + "                                  <tbody>\n"
                + "                                    <tr>\n"
                + "                                      <td class=\"one-column\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\" bgcolor=\"#FFFFFF\"><!-- ======= start header ======= -->\n"
                + "                                        \n"
                + "                                        <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\" bgcolor=\"#f3f2f0\">\n"
                + "                                          <tr>\n"
                + "                                            <td class=\"two-column\" style=\"padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;text-align:left;font-size:0;\" >                                          \n"
                + "                                              </td>\n"
                + "                                          </tr>\n"
                + "                                          <tr>\n"
                + "                                            <td>&nbsp;</td>\n"
                + "                                          </tr>\n"
                + "                                        </table></td>\n"
                + "                                    </tr>\n"
                + "                                  </tbody>\n"
                + "                                </table>\n"
                + "                              </center></td>\n"
                + "                          </tr>\n"
                + "                        </tbody>\n"
                + "                      </table></td>\n"
                + "                  </tr>\n"
                + "                </table>\n"
                + "                \n"
                + "                <!-- ======= end header ======= --> \n"
                + "                \n"
                + "                <!-- ======= start hero ======= -->\n"
                + "                \n"
                + "                <table class=\"one-column\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-spacing:0; border-left:1px solid #e8e7e5; border-right:1px solid #e8e7e5; border-bottom:1px solid #e8e7e5; border-top:1px solid #e8e7e5\" bgcolor=\"#FFFFFF\">\n"
                + "                  <tr>\n"
                + "                    <td background=\"https://gallery.mailchimp.com/fdcaf86ecc5056741eb5cbc18/images/42ba8b72-65d6-4dea-b8ab-3ecc12676337.jpg\" bgcolor=\"#2f9780\" width=\"100\" height=\"100\" valign=\"top\" align=\"center\" style=\"padding:50px 50px 50px 50px\">\n"
                + " \n"
                + "                      \n"
                + "                      <div>\n"
                + "                        <br />\n"
                + "                        <br />\n"
                + "                        <br />\n"
                + "                        <br />\n"
                + "                        <p style=\"color:#ffffff; font-size:60px; text-align:center; font-family: Verdana, Geneva, sans-serif\">FIX IT</p>\n"
                + "                  \n"
                + "                      </div>\n"
                + "                      </td>\n"
                + "                  </tr>\n"
                + "                </table>\n"
                + "                \n"
                + "                <table class=\"one-column\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-spacing:0; border-left:1px solid #e8e7e5; border-right:1px solid #e8e7e5; border-bottom:1px solid #e8e7e5; border-top:1px solid #e8e7e5\" bgcolor=\"#FFFFFF\">\n"
                + "                  <tr>\n"
                + "                    <td align=\"center\" style=\"padding:50px 50px 50px 50px\"><p style=\"color:#262626; font-size:24px; text-align:center; font-family: Verdana, Geneva, sans-serif\">Votre code de vérification: <strong>" + code + "</strong></p>\n"
                + "                        <br />\n"
                + "                        <br />\n"
                + "                      </p>\n"
                + "                     \n"
                + "                          </tr>\n"
                + "                        </tbody>\n"
                + "                      </table>\n"
                + "                  </tr>\n"
                + "                </table>\n"
                + "                \n"
                + "                </td>\n"
                + "            </tr>\n"
                + "          </table>\n"
                + "        </div></td>\n"
                + "    </tr>\n"
                + "  </table>\n"
                + "</center>\n"
                + "</body>\n"
                + "</html>";
        MailService ms = new MailService(email, "Récupération de mot de passe", mailContent);
        ms.sendEmail();

        return code;
    }

    public void changerMdp(String email,String mdp) {
        String password=createPwd(mdp);
        try {
            PreparedStatement pt = C.prepareStatement("UPDATE user SET password=? WHERE email_canonical=?");
            pt.setString(1, password);
            pt.setString(2, email);
            pt.execute();
        } catch (SQLException e) {
            System.out.println(e.getCause());
            e.printStackTrace();
        }
    }
    
    public void ajouterImage(String image,int id) {
        try {
            PreparedStatement pt = C.prepareStatement("UPDATE user SET image=? WHERE id=?");
            pt.setString(1, image);
            pt.setInt(2, id);
            pt.execute();
        } catch (SQLException e) {
            System.out.println(e.getCause());
            e.printStackTrace();
        }
    }
    
    public ObservableList<User> getUsers(int id)
    {
        ObservableList<User> data;
        data=FXCollections.observableArrayList();
        try {
            PreparedStatement pt = C.prepareStatement("SELECT * FROM user WHERE id!=?");
            pt.setInt(1, id);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                if (rs.getString("roles").contains("ADMIN"))
                    data.add(new User(rs.getInt("id"),rs.getInt("enabled"),rs.getInt("phone"),rs.getInt("solde"),rs.getString("email"),rs.getString("username"),rs.getString("firstname"),rs.getString("lastname"),"Administrateur",rs.getString("image"),rs.getString("address"),rs.getString("zip_code"),rs.getString("city")));
                else
                    data.add(new User(rs.getInt("id"),rs.getInt("enabled"),rs.getInt("phone"),rs.getInt("solde"),rs.getString("email"),rs.getString("username"),rs.getString("firstname"),rs.getString("lastname"),"Utilisateur",rs.getString("image"),rs.getString("address"),rs.getString("zip_code"),rs.getString("city")));
            }
            return data;
        } catch (SQLException e) {
        }
        return null;
    }
    
    public void promouvoir(int id)
    {
        try {
            PreparedStatement pt = C.prepareStatement("UPDATE user SET roles='a:1:{i:0;s:10:\"ROLE_ADMIN\";}' WHERE id=?");
            pt.setInt(1, id);
            pt.execute();
        } catch (SQLException e) {
            System.out.println(e.getCause());
            e.printStackTrace();
        }
    }
    
    public void retrograder(int id)
    {
        try {
            PreparedStatement pt = C.prepareStatement("UPDATE user SET roles='a:0:{}' WHERE id=?");
            pt.setInt(1, id);
            pt.execute();
        } catch (SQLException e) {
            System.out.println(e.getCause());
            e.printStackTrace();
        }
    }
    
    public void bloquer(int id)
    {
        try {
            PreparedStatement pt = C.prepareStatement("UPDATE user SET enabled=0 WHERE id=?");
            pt.setInt(1, id);
            pt.execute();
        } catch (SQLException e) {
            System.out.println(e.getCause());
            e.printStackTrace();
        }
    }
    
    public void debloquer(int id)
    {
        try {
            PreparedStatement pt = C.prepareStatement("UPDATE user SET enabled=1 WHERE id=?");
            pt.setInt(1, id);
            pt.execute();
        } catch (SQLException e) {
            System.out.println(e.getCause());
            e.printStackTrace();
        }
    }
    
    public void supprimer(int id)
    {
        try {
            PreparedStatement pt = C.prepareStatement("DELETE FROM user WHERE id=?");
            pt.setInt(1, id);
            pt.execute();
        } catch (SQLException e) {
            System.out.println(e.getCause());
            e.printStackTrace();
        }
    }
}
