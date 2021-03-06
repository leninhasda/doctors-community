package com.jonak.controller;
import com.jonak.lib.MySQLDatabase;
import com.jonak.lib.SessionLib;

import com.jonak.lib.Tools;
import com.jonak.model.*;

import java.sql.*;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * Created by Fahim on 14/11/2014.
 */
public class ContentController extends BaseController
{
    public ContentController()
    {
        super();
    }

    public Vector<Category> output = new Vector<Category>();
    public Vector<Comment> comment = new Vector<Comment>();

    public String setCategory() throws Exception
    {
        SessionLib.set("id",0);
        output = Category.findCategory(); //get category list
        return this.SUCCESS;
    }

    public void saveContent() throws Exception
    {
        Content content;

        String  strId = Tools.get("id"),
                title = Tools.get("title"),
                description = Tools.get("description"),
                strPrivacy = Tools.get("privacy"),
                strType = Tools.get("type"),
                strAllowComment = Tools.get("allow_comment"),
                strCategoryId = Tools.get("categoryId"),
                strUserId = Tools.get( "user_id" ),
                strParentId = Tools.get("parent_id");

        int     flag = 0,
                intId = Tools.toInt( strId ),
                privacy = Tools.toInt(strPrivacy),
                type = Tools.toInt( strType ),
                allowComment = Tools.toInt( strAllowComment ),
                categoryId = Tools.toInt( strCategoryId ),
                userId = Tools.toInt(strUserId),
                parentId = Tools.toInt(strParentId);

        if( intId > 0 )
        {
            content = Content.findByID( intId );
            if( ! content.getTitle().equals( title ) ) {
                content.setTitle(title);
            }
            if( ! content.getDescription().equals(description) ) {
                content.setDescription(description);
            }
            if( content.getType() != type ) {
                content.setType( type);
            }
            if( content.getPrivacy() != privacy ) {
                content.setPrivacy( privacy );
            }
            if( content.getAllow_comment() != allowComment ) {
                content.setAllow_comment( allowComment );
            }

            content.save();
            // set category

            System.out.println("save content:"+content.getId()+":"+categoryId);

            ContentCategory.set( content.getId(), categoryId );

            flag = 1;
        }
        else
        {
            content = new Content();

            content.setUser_id( userId );
            content.setTitle( title );
            content.setDescription( description );
            content.setType( type );
            content.setPrivacy( privacy );
            content.setAllow_comment( allowComment );
            content.setComment_counter(0);
            content.setParent_id( parentId );
            content.setCreated_at( Tools.getTimeStamp() );
            content.save();

            System.out.println("save content:"+content.getId()+":"+categoryId);

            // set category
            ContentCategory.set( content.getId(), categoryId );
        }

        // redirect
        Tools.redirect("");
    }

    public String viewAllContent() throws Exception
    {
        // this is how we will be using
        // get the user with id 1
        dataOut = Content.findAllContent(); //get result using user id
        System.out.println("size is: "+dataOut.size());
        return this.SUCCESS;
    }
    public String viewAllParticipatedContent() throws Exception
    {
        // this is how we will be using
        // get the user with id 1
        dataOut = Content.findAllParticipatedContent(); //get result using user id
        System.out.println("size is: "+dataOut.size());
        return this.SUCCESS;
    }

    public String viewAllPublicContent() throws Exception
    {
        // this is how we will be using
        // get the user with id
        SessionLib.set("id",0);
        output = Category.findCategory();
        dataOut = Content.findAllPublicContent(); //get result using user id
        System.out.println("size is: "+dataOut.size());
        return this.SUCCESS;
    }

    public String viewAllTopContent() throws Exception
    {
        // this is how we will be using
        // get the user with id
        SessionLib.set("id",0);
        output = Category.findCategory();
        dataOut = Content.findAllTopContent(); //get result using user id
        System.out.println("size is: "+dataOut.size());
        return this.SUCCESS;
    }

    public String viewContent() throws Exception
    {
        // this is how we will be using
        // get the user with id 1
        SessionLib.set("id",0);
        Content content = Content.findByID(Integer.parseInt(Tools.get("id"))); //get result using user id
        dataOut.add(content);
        output = Category.findCategory();
        SessionLib.set("id", content.getId());
        return this.SUCCESS;
    }

    public String viewDetail() throws Exception
    {
        // this is how we will be using
        // get the user with id 1
        SessionLib.set("id",0);
        Content content = Content.findByID(Integer.parseInt(Tools.get("id"))); //get result using user id
        dataOut.add(content);
        comment = Comment.find();
        SessionLib.set("id", content.getId());
        return this.SUCCESS;
    }

    public String viewAllUndiscussedContent() throws Exception
    {
        // this is how we will be using
        // get the user with id 1
        dataOut = Content.findAllUndiscussedContent(); //get result using user id
        return this.SUCCESS;
    }

    public void deleteContent() throws Exception
    {
        Content content = Content.findByID(Tools.toInt(Tools.get("id")));
        content.delete(); //delete
        SessionLib.unset("id");
        int type = Integer.parseInt(Tools.get("type"));
        if(type==1)
        {
            Tools.redirect("my-boards?type=1");
        }
        if(type==2)
        {
            Tools.redirect("my-patient-case?type=2");
        }
        if(type==3)
        {
            Tools.redirect("my-discussion?type=3");
        }
        if(type==4)
        {
            Tools.redirect("my-patient-question?type=4");
        }
        if(type==5)
        {
            Tools.redirect("my-article?type=5");
        }
        if(type==6)
        {
            Tools.redirect("my-health-tips?type=6");
        }
    }

    public String searchByCategory() throws Exception
    {
        // this is how we will be using
        // get the user with id 1

        dataOut = Content.findByCategory(); //get result using user id
        return this.SUCCESS;
    }

    public String searchByKeyWord() throws Exception
    {
        // this is how we will be using
        // get the user with id 1

        dataOut = Content.findByKeyWord(); //get result using user id
        return this.SUCCESS;
    }

    public Vector<Content> getcontents() {
        return dataOut;
    }

    public void setcontents(Vector<Content> dataOut) {
        this.dataOut = dataOut;
    }

    public Vector<Category> getOutput() {
        return output;
    }

    public void setOutput(Vector<Category> output) {
        this.output = output;
    }


}
