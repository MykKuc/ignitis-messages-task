/*
 * This file is generated by jOOQ.
 */
package com.mykolas.ignitismessagetask.jooqdatabase;


import com.mykolas.ignitismessagetask.jooqdatabase.tables.Messages;
import com.mykolas.ignitismessagetask.jooqdatabase.tables.Users;

import java.util.Arrays;
import java.util.List;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class App extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>APP</code>
     */
    public static final App APP = new App();

    /**
     * The table <code>APP.MESSAGES</code>.
     */
    public final Messages MESSAGES = Messages.MESSAGES;

    /**
     * The table <code>APP.USERS</code>.
     */
    public final Users USERS = Users.USERS;

    /**
     * No further instances allowed
     */
    private App() {
        super("APP", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.asList(
            Messages.MESSAGES,
            Users.USERS
        );
    }
}
