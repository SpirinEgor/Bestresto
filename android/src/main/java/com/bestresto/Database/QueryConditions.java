package com.bestresto.Database;

public class QueryConditions{

    private String tableName = "";
    private String whereCondition = "";
    private String orderByCondition = "";
    private String[] columns = {};

    String getWhereCondition() {
        return whereCondition;
    }

    String getOrderByCondition() {
        return orderByCondition;
    }

    String[] getColumns() {
        return columns;
    }

    String getTableName() {
        return tableName;
    }

    public void setWhereCondition(String whereCondition) {
        this.whereCondition = whereCondition;
    }

    public void setOrderByCondition(String orderByCondition) {
        this.orderByCondition = orderByCondition;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
