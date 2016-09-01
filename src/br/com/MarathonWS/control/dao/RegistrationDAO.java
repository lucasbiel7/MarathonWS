/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MarathonWS.control.dao;

import br.com.MarathonWS.model.GenericaDAO;
import br.com.MarathonWS.model.entity.Charity;
import br.com.MarathonWS.model.entity.Gender;
import br.com.MarathonWS.model.entity.Registration;
import br.com.MarathonWS.model.entity.RegistrationStatus;
import br.com.MarathonWS.model.entity.Runner;
import br.com.MarathonWS.model.util.CategoriaIdade;
import java.util.List;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author OCTI01
 */
public class RegistrationDAO extends GenericaDAO<Registration> {

    public Registration pegarPorRunner(Runner runner) {
        entity = (Registration) criteria.add(Restrictions.eq("runnerId", runner)).uniqueResult();
        closeSession();
        return entity;
    }

    public List<Registration> pegarPorCategoriaIdadeGender(CategoriaIdade categoriaIdade, Gender gender) {
        List<Runner> runners = new RunnerDAO().pegarPorCategoriaIdadeGender(categoriaIdade, gender);
        if (runners.isEmpty()) {
            closeSession();
            return entitys;
        }
        entitys = criteria.add(Restrictions.in("runnerId", runners)).list();
        closeSession();
        return entitys;
    }

    public List<Registration> pegarPorStatus(RegistrationStatus registrationStatus) {
        entitys = criteria.add(Restrictions.eq("registrationStatusId", registrationStatus)).list();
        closeSession();
        return entitys;
    }

    public List<Registration> pegarPorCharity(Charity charity) {
        entitys = criteria.add(Restrictions.eq("charityId", charity)).list();
        closeSession();
        return entitys;
    }

}
