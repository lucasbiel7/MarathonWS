/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MarathonWS.control.dao;

import br.com.MarathonWS.model.GenericaDAO;
import br.com.MarathonWS.model.entity.Event;
import br.com.MarathonWS.model.entity.EventType;
import br.com.MarathonWS.model.entity.Marathon;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author OCTI01
 */
public class EventDAO extends GenericaDAO<Event> {

    public List<Event> pegarProximoPorMaratona(Marathon marathon) {
        entitys = criteria.add(Restrictions.eq("marathonId", marathon)).add(Restrictions.ge("startDateTime", new Date())).addOrder(Order.asc("startDateTime")).list();
        closeSession();
        return entitys;
    }

    public List<Event> pegarPorMaratona(Marathon marathon) {
        entitys = criteria.add(Restrictions.eq("marathonId", marathon)).addOrder(Order.asc("startDateTime")).list();
        closeSession();
        return entitys;
    }

    public Event pegarPorMaratonaType(Marathon marathon, EventType eventType) {
        entity = (Event) criteria.add(Restrictions.eq("marathonId", marathon)).add(Restrictions.eq("eventTypeId", eventType)).uniqueResult();
        closeSession();
        return entity;
    }

}
