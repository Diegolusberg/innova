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
import org.innova.entity.Clientes;
import org.innova.session.AutosFacadeLocal;
import org.innova.session.ClientesFacadeLocal;

@ManagedBean(name = "informeAlquileresMBean")
public class InformeAlquileresMBean {

    @EJB
    private ClientesFacadeLocal clientesFacade;

    @EJB
    private AutosFacadeLocal autosFacade;
	private Integer idAuto;
	private Integer idCliente;
        
        
        public List<Autos> getAutoses(){
            return autosFacade.findAllOrdered();
        }
        
        public List<Clientes> getClienteses(){
            return clientesFacade.findAll();
        }
        

	public String imprimir() {
		try {
			Class.forName("org.postgresql.Driver");
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/innova", "postgres",
					"06071997");
			URL reportPathUrl = InformeAlquileresMBean.class.getResource("InformeAlquileres.jasper");
			String reportPathName = reportPathUrl.getFile();
                        Map<String,Object> parameters = new HashMap<>();
                        if(idAuto==null){
                            parameters.put("idAuto",0);
                        }else{
                            parameters.put("idAuto",idAuto);
                        }
                        if(idCliente==null){
                             parameters.put("idCliente",0);
                        }else{
                            parameters.put("idCliente", idCliente);
                        }
			JasperPrint jasperPrint = JasperFillManager.fillReport(reportPathName, parameters, conn);
			String webContentPath = InformeAlquileresMBean.class.getResource("").getFile().substring(0,
					InformeAlquileresMBean.class.getResource("").getFile().indexOf("WEB-INF"));
			JasperExportManager.exportReportToPdfFile(jasperPrint, webContentPath + "InformeAlquileres.pdf");
                        System.out.println("Impresion realizada");
		} catch (Exception e) {
			e.printStackTrace();
		}
                this.idAuto = 0;
                this.idCliente = 0;
		return "INFORME_ALQUILER";
	}

	// Getters y Setters

    public Integer getIdAuto() {
        return idAuto;
    }

    public void setIdAuto(Integer idAuto) {
        this.idAuto = idAuto;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }
	
}