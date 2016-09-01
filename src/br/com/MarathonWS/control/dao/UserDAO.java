/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MarathonWS.control.dao;

import br.com.MarathonWS.model.GenericaDAO;
import br.com.MarathonWS.model.entity.Role;
import br.com.MarathonWS.model.entity.User;
import java.util.List;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author OCTI01
 */
public class UserDAO extends GenericaDAO<User> {

    public User pegarPorEmail(String text) {
        entity = (User) criteria.add(Restrictions.eq("email", text)).uniqueResult();
        closeSession();
        return entity;
    }

    public List<User> pegarPorRoleSearchOrder(Role role, String text, String order) {
        if (role != null) {
            criteria.add(Restrictions.eq("roleId", role));
        }
        entitys = criteria.add(Restrictions.or(Restrictions.like("firstName", text, MatchMode.ANYWHERE), Restrictions.like("lastName", text, MatchMode.ANYWHERE), Restrictions.like("email", text, MatchMode.ANYWHERE))).addOrder(Order.asc(order)).list();
        closeSession();
        return entitys;
    }

}
