package com.jonak.model;

/**
 * Created by Fahim on 10/11/2014.
 */
public class ChamberModel extends BaseModel
{
    private int id;
    private int user_id;
    private String address;
    private String telephone;
    private String visiting_hour;
    private String visiting_days;
    private String fees;
    private String update;
    private String delete;

    public ChamberModel()
    {
        super();
        this.tableName = "chamber_detail";
    }

    protected void prepareFields() {
        // again no need for id

        // database field name              field type                          getter method
        this.fields.add("user_id");      this.types.add("int");        this.values.add(this.getUser_id());
        this.fields.add("address");      this.types.add("String");     this.values.add(this.getAddress());
        this.fields.add("telephone");    this.types.add("String");     this.values.add(this.getTelephone());
        this.fields.add("visiting_hour");this.types.add("String");     this.values.add(this.getVisiting_hour());
        this.fields.add("visiting_days");this.types.add("String");     this.values.add(this.getVisiting_days());
        this.fields.add("fees");         this.types.add("String");     this.values.add(this.getFees());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getVisiting_hour() {
        return visiting_hour;
    }

    public void setVisiting_hour(String visiting_hour) {
        this.visiting_hour = visiting_hour;
    }

    public String getVisiting_days() {
        return visiting_days;
    }

    public void setVisiting_days(String visiting_days) {
        this.visiting_days = visiting_days;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public String getDelete() {
        return delete;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }

}