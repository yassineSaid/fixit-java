/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.QuizUser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author SELON
 */
public class QuizUserService {
    Connection c=Connexion.getInstance().getCon();
      public void ajouterQuizUser(QuizUser C){
		 try
	        {
	            PreparedStatement pt=c.prepareStatement("INSERT INTO quiz_user (idUser,idQuiz,tentative) Values (?,?,?)");
	            pt.setInt(1, C.getIdUser());
	            pt.setInt(2, C.getIdQuiz());
	            pt.setInt(3, C.getTentative());
	            pt.executeUpdate();
	        } 
	        catch (SQLException ex) 
	        {
	            System.out.println(ex);
	        }
    }
      public ObservableList<Integer> afficherQuizUser(int id) 
             {
        try
            {                    
                ObservableList<Integer> list = FXCollections.observableArrayList();

                PreparedStatement pt=c.prepareStatement("SELECT * from quiz_user where idUser=?");
                pt.setInt(1, id);
                ResultSet rs= pt.executeQuery();
                while(rs.next())
                {
            /*su.setIdUser(rs.getInt("idUser"));
            su.setIdQuiz(rs.getInt("idQuiz"));*/
            list.add(rs.getInt("idQuiz"));
                   
                }
                return list;
            }
            catch(SQLException ex)
            {
                System.out.println(ex);
            }  return null;  
    }
      public int getTentative(int idU,int idQ) 
             {
        try
            {                    
                //ObservableList<Integer> list = FXCollections.observableArrayList();
                    int a=0;

                PreparedStatement pt=c.prepareStatement("SELECT * from quiz_user where idUser=? and idQuiz=?");
                pt.setInt(1, idU);
                pt.setInt(2, idQ);
                ResultSet rs= pt.executeQuery();
                while(rs.next())
                {
                    a=rs.getInt("tentative");
                   
                }
                return a;
            }
            catch(SQLException ex)
            {
                System.out.println(ex);
            }  return 0;  
    }
       public void modifierQuizUser(QuizUser C)
    {
        try{
            
        PreparedStatement pt=c.prepareStatement("update quiz_user set tentative=? where idUser=? and idQuiz=? ");
        pt.setInt(1,C.getTentative());
        pt.setInt(2,C.getIdUser());
        pt.setInt(3,C.getIdQuiz());
        
        pt.executeUpdate();
        }
        catch(SQLException ex) {}
    }
       public boolean verifier(int idU,int idQ) 
             {
        try
            {                    
                //ObservableList<Integer> list = FXCollections.observableArrayList();

                PreparedStatement pt=c.prepareStatement("SELECT * from quiz_user where idUser=? and idQuiz=?");
                pt.setInt(1, idU);
                pt.setInt(2, idQ);
                ResultSet rs= pt.executeQuery();
            ObservableList<QuizUser> data = FXCollections.observableArrayList();
                while(rs.next())
                {
                    QuizUser q=new QuizUser();
                    q.setIdQuiz(idQ);
                    q.setIdUser(idU);
                    data.add(q);
                   
                }
                if(data.size()==0)
                {
                    
                return true;
                }
            }
            catch(SQLException ex)
            {
                System.out.println(ex);
            }  return false;  
    }
    
}
