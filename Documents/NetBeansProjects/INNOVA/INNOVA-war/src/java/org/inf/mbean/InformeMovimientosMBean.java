package org.inf.mbean;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.innova.entity.Tipomovimiento;
import org.innova.session.TipomovimientoFacadeLocal;

@ManagedBean(name = "informeMovimientosMBean")
public class InformeMovimientosMBean {

    @EJB
    private TipomovimientoFacadeLocal tipomovimientoFacade;
	private Integer tipoMov;
        
        
        public List<Tipomovimiento> getTipomovimientos(){
            return tipomovimientoFacade.findAll();
        }
        

	public String imprimir() {
		try {
			Class.forName("org.postgresql.Driver");
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/innova", "postgres",
					"06071997");
			URL reportPathUrl = InformeMovimientosMBean.class.getResource("InformeMovimiento.jasper");
			String reportPathName = reportPathUrl.getFile();
                        //Crear el mapa de parametros
                        Map<String,Object> parameters = new HashMap<String,Object>();
                        if(tipoMov==null){
                            parameters.put("tipoMov",0);
                        }else{
                            parameters.put("tipoMov",tipoMov);
                        }
			JasperPrint jasperPrint = JasperFillManager.fillReport(reportPathName, parameters, conn);
			String webContentPath = InformeMovimientosMBean.class.getResource("").getFile().substring(0,
					InformeMovimientosMBean.class.getResource("").getFile().indexOf("WEB-INF"));
			JasperExportManager.exportReportToPdfFile(jasperPrint, webContentPath + "InformeMovimiento.pdf");
                        System.out.println("Impresion realizada");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "INFORME_MOVIMIENTO";
	}

	// Getters y Setters

    public Integer getTipoMov() {
        return tipoMov;
    }

    public void setTipoMov(Integer tipoMov) {
        this.tipoMov = tipoMov;
    }
	
}