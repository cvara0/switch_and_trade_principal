package com.switch_and_trade.switch_and_trade_artifact.controladores;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static javax.servlet.RequestDispatcher.ERROR_STATUS_CODE;

@Controller
public class ErrorControlador implements ErrorController {
 /*   @RequestMapping("/error")
    public ModelAndView handleError(HttpServletRequest request) {

        int status = (int) request.getAttribute(ERROR_STATUS_CODE);

        String message;

        switch (status) {
            case 403:
                return new ModelAndView("error403.html");

            case 404:
                return new ModelAndView("error404.html");

            case 500:
                return new ModelAndView("error500.html");
            default:
                return new ModelAndView("error500.html");
        }
        //return new ModelAndView("error-inesperado.html");

    }*/
}
