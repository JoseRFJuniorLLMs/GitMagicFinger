/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manageBeans;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 *
 * @author chevo
 */
public final class funcionesGenerales {
        public static String getDiaFecha(Date fecha){
            int dia = fecha.getDay();
            switch (dia){
                    case 0:
                        return "Domingo";
                    case 1:
                        return "Lunes";
                    case 2:
                        return "Martes";
                    case 3:
                        return "Miércoles";
                    case 4:
                        return "Jueves";
                    case 5:
                        return "Viernes";
                    case 6:
                        return "Sabado";
            }
            return null;
        }
        public static Date[] buscaBloque(int bloque){
            Date nueva[] = new Date[2];
            
            Date inicio = new Date();
            Date fin = new Date();
            inicio.setSeconds(0);
            fin.setSeconds(0);
            switch (bloque){
                case 1:
                    inicio.setHours(8);
                    inicio.setMinutes(0);
                    fin.setHours(9);
                    fin.setMinutes(30);
                    break;
                case 2:
                    inicio.setHours(9);
                    inicio.setMinutes(40);
                    fin.setHours(11);
                    fin.setMinutes(10);
                    break;
                case 3:
                    inicio.setHours(11);
                    inicio.setMinutes(20);
                    fin.setHours(12);
                    fin.setMinutes(50);
                    break;
                case 4:
                    inicio.setHours(13);
                    inicio.setMinutes(50);
                    fin.setHours(15);
                    fin.setMinutes(20);
                    break;
                case 5:
                    inicio.setHours(15);
                    inicio.setMinutes(30);
                    fin.setHours(17);
                    fin.setMinutes(00);
                    break;
                case 6:
                    inicio.setHours(17);
                    inicio.setMinutes(10);
                    fin.setHours(18);
                    fin.setMinutes(40);
                    break;
                case 7:
                    inicio.setHours(19);
                    inicio.setMinutes(0);
                    fin.setHours(20);
                    fin.setMinutes(10);
                    break;
                case 8:
                    inicio.setHours(20);
                    inicio.setMinutes(20);
                    fin.setHours(22);
                    fin.setMinutes(00);
                    break;
                case 9:
                    inicio.setHours(22);
                    inicio.setMinutes(0);
                    fin.setHours(23);
                    fin.setMinutes(00);
                    break;
               default:
                   return null;
            }
            nueva[0] = inicio;
            nueva[1] = fin;
            return nueva;
        }
        public static Date sumaMinutosFecha(Date fecha, int minutos){ 
            Calendar cal = Calendar.getInstance(); 
            cal.setTime(fecha); 
            cal.add(Calendar.MINUTE, minutos); 
            return cal.getTime(); 
        }
}
