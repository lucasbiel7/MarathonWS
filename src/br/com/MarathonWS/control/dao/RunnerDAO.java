/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MarathonWS.control.dao;

import br.com.MarathonWS.model.GenericaDAO;
import br.com.MarathonWS.model.entity.Gender;
import br.com.MarathonWS.model.entity.Runner;
import br.com.MarathonWS.model.entity.User;
import br.com.MarathonWS.model.util.CategoriaIdade;
import java.util.List;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author OCTI01
 */
public class RunnerDAO extends GenericaDAO<Runner> {

    public Runner pegarPorUsuario(User user) {
        entity = (Runner) criteria.add(Restrictions.eq("email", user)).uniqueResult();
        closeSession();
        return entity;
    }

    public List<Runner> pegarPorCategoriaIdadeGender(CategoriaIdade categoriaIdade, Gender gender) {
        entitys = criteria.add(Restrictions.eq("gender", gender)).add(Restrictions.sqlRestriction("(datediff(now(),dateOfBirth)/365) between "+categoriaIdade.getMenor()+" and "+categoriaIdade.getMaior())).list();
        closeSession();
        return entitys;
    }

}
