package com.epf.rentmanager.utils;

import com.epf.rentmanager.modele.Client;

import java.time.LocalDate;
import java.time.Period;

public class Clients {
    /**
     * Renvoie true si l’utilisateur passé en paramètre a un age >= 18 ans
     * @param client L'instance d’utilisateur à tester
     * @return Résultat du test (>= 18 ans)
     */

    public static boolean isLegal(Client client) {
        return
            (Period.between(client.getNaissance(), LocalDate.now()).getYears() >= 18);
    }

    /**
     * Renvoie true si l’utilisateur passé a un nom et un prénom supérieurs à 3 lettres
     * @param client L'instance d’utilisateur à tester
     * @return Résultat du test (> 3 lettres)
     */
    public static boolean namesLengthOK(Client client) {
        return
                (client.getNom().length() >= 3 && client.getPrenom().length() >= 3);
    }

}
