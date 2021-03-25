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
import org.innova.entity.Autos;
import org.innova.session.AutosFacadeLocal;

@ManagedBean(name = "informeSegurosMBean")
public class InformeSegurosMBean {

    @EJB
    private AutosFacadeLocal autosFacadeLocal;
	private Integer idAuto;
        
        
        public List<Autos> getAutoses(){
            return autosFacadeLocal.findAllOrdered();
        }
        

	public String imprimir() {
		try {
			Class.forName("org.postgresql.Driver");
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/innova", "postgres",
					"06071997");
			URL reportPathUrl = InformeSegurosMBean.class.getResource("InformeSeguro.jasper");
			String reportPathName = reportPathUrl.getFile();
                        //Crear el mapa de parametros
                        Map<String,Object> parameters = new HashMap<>();
                        if(idAuto==null){
                            parameters.put("idAuto",0);
                        }else{
                            parameters.put("idAuto",idAuto);
                        }
			JasperPrint jasperPrint = JasperFillManager.fillReport(reportPathName, parameters, conn);
			String webContentPath = InformeSegurosMBean.class.getResource("").getFile().substring(0,
					InformeSegurosMBean.class.getResource("").getFile().indexOf("WEB-INF"));
			JasperExportManager.exportReportToPdfFile(jasperPrint, webContentPath + "InformeSeguro.pdf");
                        System.out.println("Impresion realizada");
		} catch (Exception e) {
			e.printStackTrace();
		}
                this.idAuto = 0;
		return "INFORME_SEGURO";
	}

	// Getters y Setters

    public Integer getIdAuto() {
        return idAuto;
    }

    public void setIdAuto(Integer idAuto) {
        this.idAuto = idAuto;
    }
	
}