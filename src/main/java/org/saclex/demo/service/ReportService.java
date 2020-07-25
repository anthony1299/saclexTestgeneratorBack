package org.saclex.demo.service;

import net.sf.jasperreports.engine.JRException;

import java.io.IOException;
import java.sql.SQLException;

public interface ReportService {

    void getPdfEval(Long idEval) throws IOException, JRException, SQLException;
}
