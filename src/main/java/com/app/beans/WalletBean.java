package com.app.beans;

import com.app.helpers.SqlConnector;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;

@ManagedBean(name = "wallet")
@SessionScoped
public class WalletBean implements Serializable {

    private LinkedHashSet<String> months = new LinkedHashSet<>();
    private LinkedHashSet<Integer> years = new LinkedHashSet<>();
    private String filterYear;
    private String filterMonth;

    public WalletBean() throws SQLException {

        SqlConnector sql = new SqlConnector();
        String select = "SELECT year FROM prod_app.income";
        ResultSet resutls = sql.selectResults(select);

        while(resutls.next()){
            this.years.add(resutls.getInt(1));
        }

    }

    public String getFilterMonth() {
        return filterMonth;
    }

    public void setFilterMonth(String filterMonth) {
        this.filterMonth = filterMonth;
    }

    public String getFilterYear() {
        return filterYear;
    }

    public void setFilterYear(String filterYear) {
        this.filterYear = filterYear;
    }

    public LinkedHashSet<String> getMonths() {
        return months;
    }

    public void setMonths(LinkedHashSet<String> months) {
        this.months = months;
    }

    public LinkedHashSet<Integer> getYears() {
        return years;
    }

    public void setYears(LinkedHashSet<Integer> years) {
        this.years = years;
    }

    public void getMonthsAndYears() throws SQLException {

        if(!filterYear.equals("0")){
            SqlConnector sql = new SqlConnector();
            String select = "SELECT month_name FROM prod_app.income where year = '" +filterYear+ "'";

            ResultSet results = sql.selectResults(select);
            this.months = new LinkedHashSet<>();
            while(results.next()){
                this.months.add(results.getString(1));
            }
        }
    }

    public void selectMonth() throws SQLException {

        if(!filterMonth.equals("0")){
            SqlConnector sql = new SqlConnector();
            String select = "SELECT month_name FROM prod_app.income where year = '" +filterYear+ "' and month_name ='" +filterMonth+ "'";

            ResultSet results = sql.selectResults(select);
            System.out.println(results);
            while(results.next()){
//                this.months.add(results.getString(1));
            }
        }

    }

}
