package com.jonak.model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Vector;

// import custom
import com.jonak.lib.MySQLDatabase;
import com.jonak.lib.SessionLib;
import com.jonak.model.ContentModel;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;
/**
 * Created by Fahim on 14/11/2014.
 */
public class ContentCategory extends ContentCategoryModel
{
    //get last content id
    public static int getID() throws SQLException
    {
        MySQLDatabase db = new MySQLDatabase();

        String  _tableName = "content",
                _fieldName = "*";
        ArrayList   _fields = new ArrayList(),
                _types  = new ArrayList(),
                _values = new ArrayList();

        //_fields.add("user_id");            _types.add("int");            _values.add(SessionLib.getId()); //find current user

        ResultSet rs = db.executeSelectQuery( _tableName, _fieldName, _fields, _types, _values); //search experience using content id
        rs.afterLast();
        while (rs.previous()) {
            int id = rs.getInt("id");
            return id;
        }
        return 0;
    }
    //find using content_id
    public static ResultSet find(int content_id) throws SQLException
    {
        MySQLDatabase db = new MySQLDatabase();

        String  _tableName = "category_content_relation",
                _fieldName = "*";
        ArrayList   _fields = new ArrayList(),
                _types  = new ArrayList(),
                _values = new ArrayList();
        _fields.add("content_id"); _types.add("int"); _values.add(content_id);

        ResultSet rs = db.executeSelectQuery( _tableName, _fieldName, _fields, _types, _values); //search using user id

        return rs;
    }


}
