package com.previred.periodos.servicio;

import com.previred.periodos.json.request.PeriodosPerdidosRequest;
import com.previred.periodos.json.response.PeriodosPerdidosResponse;
import com.previred.periodos.swagger.codegen.model.Periodo;
import com.previred.periodos.tools.RandomDate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author mgonzalez@previred.com
 */
@Service
public class PeriodosService {

    private final static int MIN = 90;
    private final static int MAX = 100;
    private final static int MAXPERIODOS = 99;
    private static final Logger LOG = LoggerFactory.getLogger(PeriodosService.class);

    /**
     * Genera un Objetos periodos, los rangos de fechas van de 1980 a
     * 2019 el rango de lista de fechas en el periodo va desde 90 a 100
     *
     * @return
     */
    public Periodo getPeriodos() {
        //Se asguran almenos 100 fechas distintas 2004 - 1013 
        RandomDate fechaInicial = new RandomDate(LocalDate.of(1980, 1, 1), LocalDate.of(2004, 1, 1));
        RandomDate fechaFin = new RandomDate(LocalDate.of(2013, 2, 1), LocalDate.of(2019, 1, 1));

        Periodo periodo = new Periodo();
        periodo.setId(1L);
        periodo.setFechaCreacion(fechaInicial.nextDate());
        periodo.setFechaFin(fechaFin.nextDate());
        RandomDate fechaPeriodos = new RandomDate(periodo.getFechaCreacion(), periodo.getFechaFin());

        Random aleatorio = new Random();
        int cantidadPeriodos = aleatorio.nextInt((MAX - MIN) + 1) + MIN;
        Set<LocalDate> fechas = new HashSet();
        while (fechas.size() <= cantidadPeriodos) {            
            fechas.add(fechaPeriodos.nextDate());
        }
        periodo.setFechas(fechas.stream()
                .sorted()
                .collect(Collectors.toList()));

        return periodo;
    }
    
    /**
     * Permite obtener fechas faltantes de un período dado
     *
     * @param periodosPerdidosRequest
     * @return periodosPerdidosResponse
     * 
     */
    public PeriodosPerdidosResponse obtenerPeriodosPerdidos(PeriodosPerdidosRequest periodosPerdidosRequest) throws ParseException {
        LOG.info("Inicio: obtenerPeriodosPerdidos " + periodosPerdidosRequest.toString());
        PeriodosPerdidosResponse periodosPerdidosResponse = new PeriodosPerdidosResponse();
        List<LocalDate> listaFechasAleatoria = null;
        List<LocalDate> listaFechasAux = new ArrayList<LocalDate>();
        List<String> listaFechasAuxFinal = new ArrayList<String>();
        Periodo periodo = new Periodo();
        try {
            if(periodosPerdidosRequest == null) {
                return periodosPerdidosResponse;
            }else{
                // Se obtiene lista de fechas del periodo dado
                listaFechasAleatoria = getListaEntreFechas(periodosPerdidosRequest.getFechaCreacion(), periodosPerdidosRequest.getFechaFin());
                listaFechasAux = listaFechasAleatoria;
                // comparan lista para verificar fechas
                for(int i = 0; i< periodosPerdidosRequest.getFechas().size(); i++){
                    for (int j = 0; j < listaFechasAleatoria.size(); j++){
                       if ((convertToLocalDate((periodosPerdidosRequest.getFechas().get(i)))).isEqual(listaFechasAleatoria.get(j))) {
                            listaFechasAux.remove(listaFechasAleatoria.get(j));
                           System.out.println("listaFechasAux: " + listaFechasAux + listaFechasAux.size());
                        }
                    }
                }

                DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                for(int l= 0; l < listaFechasAux.size(); l++){

                    String date = (formatoFecha.format(Date.from(listaFechasAux.get(l).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant())));

                    listaFechasAuxFinal.add(date);
                }
                System.out.println("listaFechasAux: " + listaFechasAux + listaFechasAux.size());
                //Llenando periodosPerdidosResponse
                periodosPerdidosResponse.setId(periodosPerdidosRequest.getId());
                periodosPerdidosResponse.setFechaCreacion(formatoFecha.format(periodosPerdidosRequest.getFechaCreacion()));
                periodosPerdidosResponse.setFechaFin(formatoFecha.format(periodosPerdidosRequest.getFechaFin()));
                periodosPerdidosResponse.setFechasFaltantes(listaFechasAuxFinal);
            }
        }
        catch (NumberFormatException e) {
          LOG.error("obtenerFechasFaltantes: Error al procesar el periodosPerdidosRequest -> {}", e);
        }
        
        LOG.info("Fin: obtenerPeriodosPerdidos " + periodosPerdidosResponse.toString());
        return periodosPerdidosResponse;
    }

    /**
     * Transfoma objeto de tipo Date a LocalDate
     * @param dateAntigua fecha a transformar
     * @return LocalDate
     */
    public LocalDate convertToLocalDate(Date dateAntigua) {
        return dateAntigua.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    /**
     * Calcula la cantidad de meses que hay en un rango de fechas
     * @param fechaInicio fecha inicio
     * @param fechaFin fecha fin
     * @return LocalDate
     */
    public Integer cantidadPeriodos(Date fechaInicio, Date fechaFin) {
        try {
            //Fecha inicio
            Calendar startCalendar = Calendar.getInstance();
            startCalendar.setTime(fechaInicio);
            //Fecha fin
            Calendar endCalendar = Calendar.getInstance();
            endCalendar.setTime(fechaFin);
            //Cálculo de meses para las fechas de inicio y fin
            int startMes = (startCalendar.get(Calendar.YEAR) * 12) + startCalendar.get(Calendar.MONTH);
            int endMes = (endCalendar.get(Calendar.YEAR) * 12) + endCalendar.get(Calendar.MONTH);
            //Diferencia en meses entre las dos fechas
            int diffMonth = endMes - startMes;
            return diffMonth + 1;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Método para obtener una lista con fechas en el intervalo dado
     * @param fechaInicio Fecha inicial del intervalo
     * @param fechaFin Fecha final del intervalo
     * @return Fecha final
     */
    public List<LocalDate> getListaEntreFechas(Date fechaInicio, Date fechaFin) {
        // Convertimos la fecha a Calendar
        LocalDate fIni;
        LocalDate fFin;
        LocalDate fAux;
        Calendar cInicio = Calendar.getInstance();
        cInicio.setTime(fechaInicio);
        Calendar cFin = Calendar.getInstance();
        cFin.setTime(fechaFin);

        fIni = convertToLocalDate(fechaInicio);
        fFin = convertToLocalDate(fechaFin);

        // Lista donde se irán almacenando las fechas
        List<LocalDate> listaFechas = new ArrayList<LocalDate>();

        // Para recorrer el intervalo
        while (!fIni.isAfter(fFin)) {

            listaFechas.add(fIni);
            fIni = fIni.plusMonths(1);

        }
        return listaFechas;
    }

}
