package com.example.springbooks.controller;

import com.example.springbooks.forms.TransportForm;
import com.example.springbooks.forms.UpdateTransportForm;
import com.example.springbooks.model.Transport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TransportController {

    private static List<Transport> transports = new ArrayList<>();

    static {
        transports.add(new Transport("Bus","13A",10));
        transports.add(new Transport("Tram","1",8));
        transports.add(new Transport("Bus","16",10));
    }

    @Value("${welcome.message}")
    private String message;
    @Value("${error.message}")
    private String errorMessage;

    @RequestMapping(value = {"/alltransport"}, method = RequestMethod.GET)
    public ModelAndView getAllTransport(Model model){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("transportlist");
        model.addAttribute("transports", transports);

        return modelAndView;
    }

    @RequestMapping(value = {"/addtransport"}, method = RequestMethod.GET)
    public ModelAndView showAddTranPage(Model model){
        ModelAndView modelAndView = new ModelAndView("addtransport");
        TransportForm transportForm = new TransportForm();
        model.addAttribute("transportform",transportForm);

        return  modelAndView;
    }

    @RequestMapping(value = {"/addtransport"}, method = RequestMethod.POST)
    public ModelAndView saveTran(Model model,
                                 @ModelAttribute("transportform") TransportForm transportForm){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("transportlist");
        String number = transportForm.getNumber();
        String type = transportForm.getType();
        int cost = transportForm.getCost();

        if(number!=null && number.length()>0
                && type != null && type.length()>0){
            Transport newTransport = new Transport(type,number, cost);
            transports.add(newTransport);
            model.addAttribute("transports", transports);
            return modelAndView;
        }
        model.addAttribute("errorMessage", errorMessage);
        modelAndView.setViewName("addtransport");
        return  modelAndView;
    }

    @RequestMapping(value = {"/deltransport"}, method = RequestMethod.GET)
    public ModelAndView showDelTranPage(Model model){
        ModelAndView modelAndView = new ModelAndView("deltransport");
        TransportForm transportForm = new TransportForm();
        model.addAttribute("transportform",transportForm);

        return  modelAndView;
    }

    @RequestMapping(value = {"/deltransport"}, method = RequestMethod.POST)
    public ModelAndView delTran(Model model,
                                @ModelAttribute("transportform") TransportForm transportForm){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("transportlist");
        String number = transportForm.getNumber();
        String type = transportForm.getType();

        if(type!=null && type.length()>0
                && number != null && number.length()>0){
            int index = 0;
            for (Transport transport: transports){
                if(transport.getType().equals(type) && transport.getNumber().equals(number)){
                    transports.remove(index);
                    break;
                }
                index++;
            }
            model.addAttribute("transports", transports);
            return modelAndView;
        }
        model.addAttribute("errorMessage", errorMessage);
        modelAndView.setViewName("deltransport");
        return  modelAndView;
    }

    @RequestMapping(value = {"/updatetransport"}, method = RequestMethod.GET)
    public ModelAndView showUpdateTranPage(Model model){
        ModelAndView modelAndView = new ModelAndView("updatetransport");
        UpdateTransportForm transportForm = new UpdateTransportForm();
        model.addAttribute("transportform",transportForm);

        return  modelAndView;
    }

    @RequestMapping(value = {"/updatetransport"}, method = RequestMethod.POST)
    public ModelAndView updateTran(Model model,
                                @ModelAttribute("transportform") UpdateTransportForm transportForm){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("transportlist");
        String number = transportForm.getNumber();
        String type = transportForm.getType();
        int newCost = transportForm.getNewCost();

        if(number!=null && number.length()>0
                && type != null && type.length()>0){
            for (Transport transport: transports){
                if(transport.getType().equals(type) && transport.getNumber().equals(number)){
                    transport.setCost(newCost);
                    model.addAttribute("transports", transports);
                    return modelAndView;
                }
            }
        }
        model.addAttribute("errorMessage", errorMessage);
        modelAndView.setViewName("updatetransport");
        return  modelAndView;
    }


}
