/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.previred.periodos.servicio.controller;

import com.previred.periodos.json.request.PeriodosPerdidosRequest;
import com.previred.periodos.servicio.PeriodosService;
import com.previred.periodos.swagger.codegen.model.Periodo;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.previred.periodos.json.response.PeriodosPerdidosResponse;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Keylis Valles
 */
@RestController
public class PeriodosPerdidosController {

    private static final Logger LOG = LoggerFactory.getLogger(PeriodosPerdidosController.class);

    @Autowired
    private PeriodosService procesarPeriodosService;


    /**
     * Permite obtener fechas faltantes de un período dado
     * @param  json contiene una fecha inicial una fecha final y una lista fechas.
     * @return respJson contiene las fechas faltantes del un período dado
     * 
     */
    @RequestMapping (value = "/obtenerPeriodosPerdidos", method = RequestMethod.POST)
    public @ResponseBody String obtenerPeriodosPerdidos(@RequestBody String json) {
        LOG.info("Inicio obtenerPeriodosPerdidos ");
        String respJson = "";
        PeriodosPerdidosResponse periodosPerdidosResponse = new PeriodosPerdidosResponse();
        Periodo periodo = new Periodo();
        if (json!=null && !json.equalsIgnoreCase("")){

            PeriodosPerdidosRequest periodosFechasRequest = new Gson().fromJson(json, PeriodosPerdidosRequest.class);
            try {
                periodosPerdidosResponse = procesarPeriodosService.obtenerPeriodosPerdidos(periodosFechasRequest);

                if (periodosPerdidosResponse!=null){

                    LOG.info("Se culmina consumo de servicio que obtiene fechas faltantes de un perido dado " + periodosPerdidosResponse.toString());
                    
                } else{

                    LOG.info("Error, No hay fechas para procesar");
                }

            } catch (Exception e) {
                LOG.error("Exception en ProcesarPeriodosController", e);
            }
        }else {
            LOG.info("PeriodosPerdidosRequest vacío");
        }
        LOG.info("Fin obtenerPeriodosPerdidos ");
        respJson = new Gson().toJson(periodosPerdidosResponse);
        return respJson ;
    }
}
