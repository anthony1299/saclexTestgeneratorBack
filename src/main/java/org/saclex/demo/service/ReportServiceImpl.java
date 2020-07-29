package org.saclex.demo.service;

import net.sf.jasperreports.engine.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import net.sf.jasperreports.engine.JasperReport;
import org.saclex.demo.entities.Evaluation;
import org.saclex.demo.repositories.EvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService{

    private final EvaluationRepository evaluationRepository;
    @Autowired
    JdbcTemplate jdbcTemplate;



    public ReportServiceImpl(EvaluationRepository evaluationRepository) {
        this.evaluationRepository = evaluationRepository;
    }

    public String getPdfEval(Long idEval) throws IOException, JRException, SQLException {


        Evaluation evaluation=evaluationRepository.findById(idEval).get();


        Connection connection = Objects.requireNonNull(jdbcTemplate.getDataSource()).getConnection();

        File file= ResourceUtils.getFile("classpath:evalReport.jrxml");

        //JasperDesign jasperDesign= JRXmlLoader.load("C:\\Users\\consty\\Documents\\workspace\\scoma\\web\\target\\report5.jrxml");

        JasperReport jasperReport= JasperCompileManager.compileReport(file.getAbsolutePath());

        Map<String, Object> parameters=new HashMap<>();

        parameters.put("idEval",idEval);
        parameters.put("image", new File("src/main/resources/jasper/images/logoOrange.jpg").getAbsolutePath());
        JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport,parameters,connection);

        Path local= Paths.get(System.getProperty("user.home")+"/Documents/"+evaluation.getIdEvaluation()+".pdf");

        JasperExportManager.exportReportToPdfFile(jasperPrint,local.toString());
        connection.close();
        return local.toString();
    }

    }
