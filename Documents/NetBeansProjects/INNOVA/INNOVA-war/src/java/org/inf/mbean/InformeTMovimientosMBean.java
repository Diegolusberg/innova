package org.inf.mbean;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@ManagedBean(name = "informeTMovimientosMBean")
public class InformeTMovimientosMBean {
	private Integer desde;
	private Integer hasta;

	public String imprimir() {
		try {
			Class.forName("org.postgresql.Driver");
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/innova", "postgres",
					"06071997");
			URL reportPathUrl = InformeTMovimientosMBean.class.getResource("InformeTMovimientos.jasper");
			String reportPathName = reportPathUrl.getFile();
                        Map<String,Object> parameters = new HashMap<String,Object>();
                        if(desde==null){
                            parameters.put("idDsd",0);
                        }else{
                            parameters.put("idDsd",desde);
                        }
                        if(hasta==null){
                             parameters.put("idHst",99999);
                        }else{
                            parameters.put("idHst", hasta);
                        }
			JasperPrint jasperPrint = JasperFillManager.fillReport(reportPathName, parameters, conn);
			String webContentPath = InformeTMovimientosMBean.class.getResource("").getFile().substring(0,
					InformeTMovimientosMBean.class.getResource("").getFile().indexOf("WEB-INF"));
			JasperExportManager.exportReportToPdfFile(jasperPrint, webContentPath + "InformeTMovimientos.pdf");
                        System.out.println("Impresion realizada");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "INFORME_TMOVIMIENTO";
	}

	// Getters y Setters
	public Integer getDesde() {
		return desde;
	}

	public void setDesde(Integer desde) {
		this.desde = desde;
	}

	public Integer getHasta() {
		return hasta;
	}

	public void setHasta(Integer hasta) {
		this.hasta = hasta;
	}
}