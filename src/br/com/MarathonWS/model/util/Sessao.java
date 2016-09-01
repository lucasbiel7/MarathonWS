/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MarathonWS.model.util;

import br.com.MarathonWS.control.dao.EventDAO;
import br.com.MarathonWS.control.dao.MarathonDAO;
import br.com.MarathonWS.model.entity.Event;
import br.com.MarathonWS.model.entity.Marathon;
import br.com.MarathonWS.model.entity.User;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author OCTI01
 */
public class Sessao {

    public static User user;
    public static Marathon marathon;
    public static Event event;

    static {
        marathon = new MarathonDAO().pegarPorAno((short) Calendar.getInstance().get(Calendar.YEAR));
        List<Event> events = new EventDAO().pegarProximoPorMaratona(marathon);
        if (!events.isEmpty()) {
            event = events.get(0);
        }
    }
}
