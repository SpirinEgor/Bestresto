package com.bestresto.Database;

import android.os.Parcel;
import android.os.Parcelable;

public class QueryConditions implements Parcelable{

    public static final String TAG = "QueryCondition";

    private String tableName = "";
    private String whereCondition = "";
    private String orderByCondition = "";
    private String[] columns = {};

    public QueryConditions(){}

    protected QueryConditions(Parcel in) {
        tableName = in.readString();
        whereCondition = in.readString();
        orderByCondition = in.readString();
        columns = in.createStringArray();
    }

    public static final Creator<QueryConditions> CREATOR = new Creator<QueryConditions>() {
        @Override
        public QueryConditions createFromParcel(Parcel in) {
            return new QueryConditions(in);
        }

        @Override
        public QueryConditions[] newArray(int size) {
            return new QueryConditions[size];
        }
    };

    String getWhereCondition() {
        return whereCondition;
    }

    String getOrderByCondition() {
        return orderByCondition;
    }

    String[] getColumns() {
        return columns;
    }

    public String getTableName() {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(tableName);
        dest.writeString(whereCondition);
        dest.writeString(orderByCondition);
        dest.writeStringArray(columns);
    }
}
