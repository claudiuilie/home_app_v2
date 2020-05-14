package com.app.beans;

import com.app.helpers.SqlConnector;
import com.app.resources.SqlConfig;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ManagedBean(name="walletCreator")
@SessionScoped
public class WalletCreatorBean implements ActionListener {

    private Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

    private String income = params.get("income");
    private String outcome = params.get("outcome");
    private String totalIncome  = params.get("total_income");
    private String totalOutcome = params.get("total_outcome");
    private String month = "Ianuarie";
    private String year = "2020";


    @Override
    public void processAction(ActionEvent actionEvent) throws AbortProcessingException {
        System.out.println(params);
        System.out.println("WalletCreatorBean{" +
                ", income='" + income + '\'' +
                ", outcome='" + outcome + '\'' +
                ", totalIncome='" + totalIncome + '\'' +
                ", totalOutcome='" + totalOutcome + '\'' +
                ", month='" + month + '\'' +
                ", year=" + year +
                '}');

        SqlConnector sql = new SqlConnector();
        String insert = "INSERT INTO home_app.income (id,income,outcome,total_income,total_outcome,year,month) " +
                "VALUES(null,?,?,?,?,?,?)";

        int result = 0;
        try {
            Class.forName(SqlConfig.JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(SqlConfig.DB_URL,SqlConfig.USER,SqlConfig.PASS);
            PreparedStatement statement = conn.prepareStatement(insert);
            statement.setString(1,income);
            statement.setString(2,outcome);
            statement.setString(3,totalIncome);
            statement.setString(4,totalOutcome);
            statement.setString(5,year);
            statement.setString(6,month);
            result =  statement.executeUpdate();
            conn.close();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }finally {
            System.out.println(result);
        }

    }
}
