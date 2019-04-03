package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Entities.User;

public class UserService {
	Connection C=Connexion.getInstance().getCon();
	public boolean checkUser(String username, String password)
	{
		String crypted="$2y$13$ANVjZ0bnQfSMtHrt053Nh.oxKcesoimGIRwgQHf5i/mnKpl6bOlvG";
		//String crypted1="$2a"+crypted.substring(3);
		//System.out.println(BCrypt.checkpw("00100", crypted1));
		try
		{
			PreparedStatement pt=C.prepareStatement("SELECT * FROM user where username=?");
			pt.setString(1, username);
			ResultSet rs=pt.executeQuery();
			while (rs.next())
			{
				String crypted1="$2a"+rs.getString("password").substring(3);
				if (BCrypt.checkpw(password, crypted1)) return true;
			}
			return false;
			//return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public User connect(String username)
	{
		User U=new User();
		try {
			PreparedStatement pt=C.prepareStatement("SELECT * FROM user where username=?");
			pt.setString(1, username);
			ResultSet rs=pt.executeQuery();
			while (rs.next())
			{
				U.setEmail(rs.getString("email"));
				U.setFirstname(rs.getString("firstname"));
				U.setLastname(rs.getString("lastname"));
				U.setId(rs.getInt("id"));
				U.setUsername(rs.getString("username"));
				U.setImage(rs.getString("image"));
				if (rs.getString("roles").contains("ADMIN"))
					U.setRoles("admin");
				else
					U.setRoles("user");
			}
			return U;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
        public void modifierNomPrenom(User U,String nom,String prenom)
        {
            try
		{
			PreparedStatement pt=C.prepareStatement("UPDATE user SET firstname=?,lastname=? WHERE id=?");
			pt.setString(1, prenom);
			pt.setString(2, nom);
			pt.setInt(3, U.getId());
			pt.execute();
		} catch (SQLException e) {
                    System.out.println(e.getCause());
			e.printStackTrace();
		}
            //return U;
        }
}
