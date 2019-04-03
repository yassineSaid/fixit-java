/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

/**
 *
 * @author SL-WASSIM
 */
import Services.CategorieOutilService;
import Entities.CategorieOutil;
import Services.OutilService;
import Entities.Outil;
public class test {
     public static void main(String[] args) {
        /*CategorieOutilService sm = new CategorieOutilService();
         CategorieOutil M1 =new CategorieOutil("slim","baba");
         //sm.ajouterCategorie(M1);
         M1.setNom("mohsen");
         M1.setId(15);
         //sm.modifierCategorie(M1);
         sm.supprimerCategorie(17);
         sm.afficherCategorie();*/
        Outil o = new Outil("wassim",2,6,84,"tunis",4444,"ppp","mmmm",7);
        OutilService om = new OutilService();
        o.setPrix(999);
        o.setId(28);
        //om.ajouterOutil(o);
        om.modifierOutil(o);
        om.afficherOutil();
         
    }
    
}
