/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MarathonWS.control.dao;

import br.com.MarathonWS.model.GenericaDAO;
import br.com.MarathonWS.model.entity.Charity;
import br.com.MarathonWS.model.entity.Registration;
import br.com.MarathonWS.model.entity.Sponsorship;
import java.util.List;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author OCTI01
 */
public class SponsorshipDAO extends GenericaDAO<Sponsorship> {

    public List<Sponsorship> pegarPorRegistration(Registration registration) {
        entitys = criteria.add(Restrictions.eq("registrationId", registration)).list();
        closeSession();
        return entitys;
    }

    public List<Sponsorship> pegarPorCharity(Charity charity) {
        List<Registration> registrations = new RegistrationDAO().pegarPorCharity(charity);
        if (registrations.isEmpty()) {
            closeSession();
            return entitys;
        }
        entitys = criteria.add(Restrictions.in("registrationId", registrations)).list();
        closeSession();
        return entitys;
    }

}
