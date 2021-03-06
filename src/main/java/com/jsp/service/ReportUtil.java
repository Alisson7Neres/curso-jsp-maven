package com.jsp.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import jakarta.servlet.ServletContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;

public class ReportUtil implements Serializable {

	private static final long serialVersionUID = -5321841348798779978L;
	
	public byte[] gerarRelatorioExcel(List listarDados, String relatorio, HashMap<String, Object> params, ServletContext servletContext) throws JRException {

		// Cria a lista de dados para o relatório com nossa lista de objetos
		JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(listarDados);

		// Carrega o caminho do arquivo Jasper compilado
		String caminhoJasper = servletContext.getRealPath("relatorio") + File.separator + relatorio + ".jasper";

		// Carrega o arquivo Jasper passando os dados
		JasperPrint impressoraJasper = JasperFillManager.fillReport(caminhoJasper, params,
				jrBeanCollectionDataSource);
		
		// Excel
		JRExporter exporter = new JRXlsExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, impressoraJasper);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
		exporter.exportReport();

		return baos.toByteArray();

	}
	
	// Retorna nosso PDF em Byte para download no navegador
	// sub relatorio
	public byte[] gerarRelatorio(List listarDados, String relatorio, HashMap<String, Object> params, ServletContext servletContext) throws JRException {

		// Cria a lista de dados para o relatório com nossa lista de objetos
		JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(listarDados);

		// Carrega o caminho do arquivo Jasper compilado
		String caminhoJasper = servletContext.getRealPath("relatorio") + File.separator + relatorio + ".jasper";

		// Carrega o arquivo Jasper passando os dados
		JasperPrint impressoraJasper = JasperFillManager.fillReport(caminhoJasper, params,
				jrBeanCollectionDataSource);

		// Exporta para byte[] para fazer o download do PDF
		return JasperExportManager.exportReportToPdf(impressoraJasper);

	}
	//-------

	// Retorna nosso PDF em Byte para download no navegador
	public byte[] gerarRelatorio(List listarDados, String relatorio, ServletContext servletContext) throws JRException {

		// Cria a lista de dados para o relatório com nossa lista de objetos
		JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(listarDados);

		// Carrega o caminho do arquivo Jasper compilado
		String caminhoJasper = servletContext.getRealPath("relatorio") + File.separator + relatorio + ".jasper";

		// Carrega o arquivo Jasper passando os dados
		JasperPrint impressoraJasper = JasperFillManager.fillReport(caminhoJasper, new HashMap(),
				jrBeanCollectionDataSource);

		// Exporta para byte[] para fazer o download do PDF
		return JasperExportManager.exportReportToPdf(impressoraJasper);

	}

}
