package com.bestresto.Database;

public class QueryConditions{

    private String tableName = "";
    private String whenCondition = "";
    private String orderByCondition = "";
    private String[] columns = {};

    String getWhenCondition() {
        return whenCondition;
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

    public void setWhenCondition(String whenCondition) {
        this.whenCondition = whenCondition;
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
