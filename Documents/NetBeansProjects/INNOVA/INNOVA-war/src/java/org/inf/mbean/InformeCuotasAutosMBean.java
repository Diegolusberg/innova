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
import org.innova.entity.Tipomovimiento;
import org.innova.session.AutosFacadeLocal;
import org.innova.session.TipomovimientoFacadeLocal;

@ManagedBean(name = "informeCuotasAutosMBean")
public class InformeCuotasAutosMBean {

    @EJB
    private AutosFacadeLocal autosFacadeLocal;
	private Integer idAuto;
        
        
        public List<Autos> getAutoses(){
            return autosFacadeLocal.findAll();
        }
        

	public String imprimir() {
		try {
			Class.forName("org.postgresql.Driver");
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/innova", "postgres",
					"06071997");
			URL reportPathUrl = InformeCuotasAutosMBean.class.getResource("informeCuotasAutos.jasper");
			String reportPathName = reportPathUrl.getFile();
                        //Crear el mapa de parametros
                        Map<String,Object> parameters = new HashMap<>();
                        if(idAuto==null){
                            parameters.put("idAuto",0);
                        }else{
                            parameters.put("idAuto",idAuto);
                        }
			JasperPrint jasperPrint = JasperFillManager.fillReport(reportPathName, parameters, conn);
			String webContentPath = InformeCuotasAutosMBean.class.getResource("").getFile().substring(0,
					InformeCuotasAutosMBean.class.getResource("").getFile().indexOf("WEB-INF"));
			JasperExportManager.exportReportToPdfFile(jasperPrint, webContentPath + "InformeCuotasAutos.pdf");
                        System.out.println("Impresion realizada");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "INFORME_CUOTAS";
	}

	// Getters y Setters

    public Integer getIdAuto() {
        return idAuto;
    }

    public void setIdAuto(Integer idAuto) {
        this.idAuto = idAuto;
    }

    
	
}