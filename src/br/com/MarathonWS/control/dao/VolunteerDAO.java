/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MarathonWS.control.dao;

import br.com.MarathonWS.model.GenericaDAO;
import br.com.MarathonWS.model.entity.Volunteer;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author OCTI01
 */
public class VolunteerDAO extends GenericaDAO<Volunteer> {

    public List<Volunteer> pegarTodosOrder(String order) {
        entitys = criteria.addOrder(Order.asc(order)).list();
        closeSession();
        return entitys;
    }

}
