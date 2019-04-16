package Services;

import Entities.Like_Dislike;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Like_DislikeService {

    Connection c = Connexion.getInstance().getCon();

    public int countLikes(int idUser) {
        try {
            PreparedStatement pt = c.prepareStatement("SELECT COUNT(*) FROM like_dislike where userliked=? and lidis=1");
            pt.setInt(1, idUser);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                return rs.getInt("COUNT(*)");
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return -1;

    }

    public int countDislikes(int idUser) {
        try {
            PreparedStatement pt = c.prepareStatement("SELECT COUNT(*) FROM like_dislike where userliked=? and lidis=0");
            pt.setInt(1, idUser);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                return rs.getInt("COUNT(*)");
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return -1;
    }

    public boolean verifyLike(int idUser,int idUserliked) {
         try {
            PreparedStatement pt = c.prepareStatement("SELECT COUNT(*) FROM like_dislike where userlike=? and userliked=? and lidis=1");
            pt.setInt(1, idUser);
            pt.setInt(2, idUserliked);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                if(rs.getInt("COUNT(*)")>0)
                {
                    return true;
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
         return false;
    }
        public boolean verifyDislike(int idUser,int idUserliked) {
         try {
            PreparedStatement pt = c.prepareStatement("SELECT COUNT(*) FROM like_dislike where userlike=? and userliked=? and lidis=0");
            pt.setInt(1, idUser);
            pt.setInt(2, idUserliked);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                if(rs.getInt("COUNT(*)")>0)
                {
                    return true;
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
         return false;
    }
    
    public void removeLikes(int idUser,int idUserLiked)
    {
        try {
            PreparedStatement pt = c.prepareStatement("Delete from like_dislike where userlike=? and userliked=?");
            pt.setInt(1, idUser);
            pt.setInt(2, idUserLiked);
            pt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void insertLikeDislike(Like_Dislike l)
    {
        try {
            PreparedStatement pt = c.prepareStatement("INSERT INTO like_dislike VALUES (?,?,?)");
            pt.setInt(1,l.getUserlike());
            pt.setInt(2, l.getUserliked());
            pt.setInt(3, l.getLikedislike());           
            pt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public int getInfo(int idUser,int idUserliked)
    {
         try {
             int i ;
            PreparedStatement pt = c.prepareStatement("SELECT * FROM like_dislike where userlike=? and userliked=?");
            pt.setInt(1, idUser);
            pt.setInt(2, idUserliked);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                System.out.println("return"+rs.getInt("lidis"));
                if(rs.getInt("lidis")==0)
                {
                    return 0;
                }
                else if(rs.getInt("lidis")==1)
                {
                    return 1;
                }
                else
                {
                    return -1;
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
         return -1;
    }

}
