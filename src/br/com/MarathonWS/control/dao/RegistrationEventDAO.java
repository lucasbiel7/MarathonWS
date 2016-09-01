/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MarathonWS.control.dao;

import br.com.MarathonWS.model.GenericaDAO;
import br.com.MarathonWS.model.entity.Event;
import br.com.MarathonWS.model.entity.Gender;
import br.com.MarathonWS.model.entity.Marathon;
import br.com.MarathonWS.model.entity.Registration;
import br.com.MarathonWS.model.entity.RegistrationEvent;
import br.com.MarathonWS.model.entity.RegistrationStatus;
import br.com.MarathonWS.model.util.CategoriaIdade;
import java.util.List;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author OCTI01
 */
public class RegistrationEventDAO extends GenericaDAO<RegistrationEvent> {

    public List<RegistrationEvent> pegarPorMarathona(Marathon marathon) {
        List<Event> events = new EventDAO().pegarPorMaratona(marathon);
        if (events.isEmpty()) {
            closeSession();
            return entitys;
        }
        entitys = criteria.add(Restrictions.in("eventId", events)).addOrder(Order.asc("bibNumber")).list();
        closeSession();
        return entitys;
    }

    public RegistrationEvent pegarPorEventRegistration(Event event, Registration registration) {
        entity = (RegistrationEvent) criteria.add(Restrictions.eq("eventId", event)).add(Restrictions.eq("registrationId", registration)).uniqueResult();
        closeSession();
        return entity;
    }

    public List<RegistrationEvent> pegarPorEvent(Event event) {
        entitys = criteria.add(Restrictions.eq("eventId", event)).addOrder(Order.desc("bibNumber")).list();
        closeSession();
        return entitys;
    }

    public List<RegistrationEvent> pegarPorEventOrderRaceTime(Event event) {
        entitys = criteria.add(Restrictions.eq("eventId", event)).addOrder(Order.asc("raceTime")).list();
        closeSession();
        return entitys;
    }

    public List<RegistrationEvent> pegarPorRegistration(Registration registration) {
        entitys = criteria.add(Restrictions.eq("registrationId", registration)).list();
        closeSession();
        return entitys;
    }

    public List<RegistrationEvent> pegarPorEventCategory(Event eventId, CategoriaIdade categoriaIdade, Gender gender) {
        List<Registration> registrations = new RegistrationDAO().pegarPorCategoriaIdadeGender(categoriaIdade, gender);
        if (registrations.isEmpty()) {
            closeSession();
            return entitys;
        }
        entitys = criteria.add(Restrictions.eq("eventId", eventId)).add(Restrictions.in("registrationId", registrations)).addOrder(Order.asc("raceTime")).list();
        closeSession();
        return entitys;
    }

    public List<RegistrationEvent> pegarPorEventStatus(Event event, RegistrationStatus registrationStatus) {
        List<Registration> registrations = new RegistrationDAO().pegarPorStatus(registrationStatus);
        if (registrations.isEmpty()) {
            closeSession();
            return entitys;
        }
        entitys = criteria.add(Restrictions.eq("eventId", event)).add(Restrictions.in("registrationId", registrations)).list();
        closeSession();
        return entitys;
    }
}
