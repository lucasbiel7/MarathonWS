/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MarathonWS.control.dao;

import br.com.MarathonWS.model.GenericaDAO;
import br.com.MarathonWS.model.entity.Marathon;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author OCTI01
 */
public class MarathonDAO extends GenericaDAO<Marathon> {

    public Marathon pegarPorAno(short year) {
        entity = (Marathon) criteria.add(Restrictions.eq("yearHeld", year)).uniqueResult();
        closeSession();
        return entity;
    }

}
