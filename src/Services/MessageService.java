/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Message;
import Entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Yassine
 */
public class MessageService {

    Connection C = Connexion.getInstance().getCon();

    public ObservableList<Message> getMessagesUser(int id) {
        ObservableList<Message> data;
        data = FXCollections.observableArrayList();
        try {
            PreparedStatement pt = C.prepareStatement("SELECT * FROM message WHERE expediteur=? OR destinataire=?");
            pt.setInt(1, id);
            pt.setInt(2, id);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                if (rs.getInt("vu") == 0) {
                    data.add(new Message(rs.getInt("id"), rs.getInt("expediteur"), rs.getInt("destinataire"), false, Timestamp.valueOf(rs.getString("date")), rs.getString("contenu")));
                } else {
                    data.add(new Message(rs.getInt("id"), rs.getInt("expediteur"), rs.getInt("destinataire"), true, Timestamp.valueOf(rs.getString("date")), rs.getString("contenu")));
                }
            }
            return data;
        } catch (SQLException e) {
        }
        return null;
    }

    public ObservableList<Message> getConversation(int id1, int id2) {
        ObservableList<Message> data;
        data = FXCollections.observableArrayList();
        try {
            PreparedStatement pt = C.prepareStatement("SELECT * FROM message WHERE (expediteur=? AND destinataire=?) OR (expediteur=? AND destinataire=?) ORDER BY date ASC");
            pt.setInt(1, id1);
            pt.setInt(2, id2);
            pt.setInt(3, id2);
            pt.setInt(4, id1);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                if (rs.getInt("vu") == 0) {
                    data.add(new Message(rs.getInt("id"), rs.getInt("expediteur"), rs.getInt("destinataire"), false, Timestamp.valueOf(rs.getString("date")), rs.getString("contenu")));
                } else {
                    data.add(new Message(rs.getInt("id"), rs.getInt("expediteur"), rs.getInt("destinataire"), true, Timestamp.valueOf(rs.getString("date")), rs.getString("contenu")));
                }
            }
            return data;
        } catch (SQLException e) {
        }
        return null;
    }

    public void envoyerMessage(Message m) {
        try {
            PreparedStatement pt = C.prepareStatement("INSERT INTO message(destinataire,expediteur,contenu,date,vu) VALUE (?,?,?,?,0) ");
            pt.setInt(1, m.getDestinataire());
            pt.setInt(2, m.getExpediteur());
            pt.setString(3, m.getContenu());
            pt.setString(4, m.getDate().toString());
            pt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Message> getLastMessages(int id) {
        ObservableList<Message> data;
        data = FXCollections.observableArrayList();
        try {
            PreparedStatement pt = C.prepareStatement("SELECT * FROM message WHERE expediteur=? OR destinataire=? ORDER BY date DESC");
            pt.setInt(1, id);
            pt.setInt(2, id);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                boolean v = true;
                int destinataire = rs.getInt("destinataire");
                int expediteur = rs.getInt("expediteur");
                for (Message m : data) {
                    if (m.getDestinataire() == id) {
                        if (m.getExpediteur() == expediteur || m.getExpediteur() == destinataire) {
                            v = false;
                        }
                    }
                    else {
                        if (m.getDestinataire() == expediteur || m.getDestinataire() == destinataire) {
                            v = false;
                        }
                    }
                }
                if (v) {
                    if (rs.getInt("vu") == 0) {
                        data.add(new Message(rs.getInt("id"), rs.getInt("expediteur"), rs.getInt("destinataire"), false, Timestamp.valueOf(rs.getString("date")), rs.getString("contenu")));
                    } else {
                        data.add(new Message(rs.getInt("id"), rs.getInt("expediteur"), rs.getInt("destinataire"), true, Timestamp.valueOf(rs.getString("date")), rs.getString("contenu")));
                    }
                }
            }
            return data;
        } catch (SQLException e) {
        }
        return null;
    }
    
    public int getIdOtherUser(int id,int current) {
        try {
            PreparedStatement pt = C.prepareStatement("SELECT * FROM message WHERE id=?");
            pt.setInt(1, id);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                if (rs.getInt("expediteur")==current) return rs.getInt("destinataire");
                else return rs.getInt("expediteur");
            }
            return 0;
        } catch (SQLException e) {
            System.err.println(e);
        }
        return 0;
    }
    
    public void setSeen(int destinataire,int expediteur)
    {
        try {
            PreparedStatement pt = C.prepareStatement("UPDATE message SET vu=1 WHERE destinataire=? AND expediteur=?");
            pt.setInt(1, destinataire);
            pt.setInt(2, expediteur);
            pt.execute();
        } catch (SQLException e) {
            System.out.println(e.getCause());
            e.printStackTrace();
        }
    }
    
    public boolean checkUnseen(int id)
    {
        try {
            PreparedStatement pt = C.prepareStatement("SELECT * FROM message WHERE destinataire=? AND vu=0");
            pt.setInt(1, id);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println(e);
        }
        return false;
    }
}
