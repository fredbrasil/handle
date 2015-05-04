package com.br.havecontrol.dao.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.br.havecontrol.common.ConstantDatabase;
import com.br.havecontrol.common.util.NumberUtil;
import com.br.havecontrol.dao.BaseDao;
import com.br.havecontrol.entity.EntityBase;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @param <T> <b>Classe persistida</b>
 * @author Fred
 */
public class BaseDaoImpl<T extends EntityBase> extends SQLiteOpenHelper implements BaseDao<T> {

    private Class<T> clazz;
    private Context context;

    /**
     * :: Construtor :: Cria o banco de dados
     *
     * @param clazz   <b>Classe que ser� persistida</b>
     * @param context <b>Contexto da aplica��o</b>
     */
    public BaseDaoImpl(Class<T> clazz, Context context) {
        super(context, ConstantDatabase.DATABASE_NAME, null, ConstantDatabase.DATABASE_VERSION);
        this.clazz = clazz;
        this.context = context;
    }

    /**
     * :: Cria as tabelas e triggers
     *
     * @param database <b>Instancia do banco de dados</b>
     * @return
     */
    @Override
    public void onCreate(SQLiteDatabase database) {

		/* TABLE */

        Log.i("Create Table: ", ConstantDatabase.createTableTipo());
        database.execSQL(ConstantDatabase.createTableTipo());

        Log.i("Create Table: ", ConstantDatabase.createTablePlace());
        database.execSQL(ConstantDatabase.createTablePlace());

        Log.i("Create Table: ", ConstantDatabase.createTablePlaceMaps());
        database.execSQL(ConstantDatabase.createTablePlaceMaps());

        Log.i("Create Table: ", ConstantDatabase.createTableCost());
        database.execSQL(ConstantDatabase.createTableCost());
		
		
		/* TRIGGER */
        Log.i("Create Trigger: ", ConstantDatabase.createFK_Place_Category());
        database.execSQL(ConstantDatabase.createFK_Place_Category());

        Log.i("Create Trigger: ", ConstantDatabase.createFK_PlaceMaps_Place());
        database.execSQL(ConstantDatabase.createFK_PlaceMaps_Place());

        Log.i("Create Trigger: ", ConstantDatabase.createFK_Cost_Category());
        database.execSQL(ConstantDatabase.createFK_Cost_Category());

        Log.i("Create Trigger: ", ConstantDatabase.createFK_Cost_Place());
        database.execSQL(ConstantDatabase.createFK_Cost_Place());

    }

    /**
     * :: Drop nas tabelas e triggers e recria os mesmos
     *
     * @param database   <b>Instancia do banco de dados</b>
     * @param oldVersion <b>Vers�o anterior do banco de dados</b>
     * @param newVersion <b>Vers�o nova do banco de dados</b>
     * @return
     */
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
	  
		/* TRIGGER */
        Log.i("Drop Trigger: ", ConstantDatabase.dropFK_Place_Category());
        database.execSQL(ConstantDatabase.dropFK_Place_Category());

        Log.i("Drop Trigger: ", ConstantDatabase.dropFK_PlaceMaps_Place());
        database.execSQL(ConstantDatabase.dropFK_PlaceMaps_Place());

        Log.i("Drop Trigger: ", ConstantDatabase.dropFK_Cost_Place());
        database.execSQL(ConstantDatabase.dropFK_Cost_Place());

        Log.i("Drop Trigger: ", ConstantDatabase.dropFK_Cost_Category());
        database.execSQL(ConstantDatabase.dropFK_Cost_Category());
		
		/* TABLE */
        Log.i("Drop Table: ", ConstantDatabase.dropTableCost());
        database.execSQL(ConstantDatabase.dropTableCost());

        Log.i("Drop Table: ", ConstantDatabase.dropTablePlaceMaps());
        database.execSQL(ConstantDatabase.dropTablePlaceMaps());

        Log.i("Drop Table: ", ConstantDatabase.dropTablePlace());
        database.execSQL(ConstantDatabase.dropTablePlace());

        Log.i("Drop Table: ", ConstantDatabase.dropTableCategory());
        database.execSQL(ConstantDatabase.dropTableCategory());

        onCreate(database);
    }

    /**
     * :: Close the connection of database
     *
     * @param
     * @return
     */
    public void close() {
        this.close();
    }

    /**
     * :: Insert the entity in database
     *
     * @param entity <b>Entity that will be inserted</b>
     * @param values <b>Map of fields and values</b>
     * @return
     */
    @Override
    public void insert(T entity, ContentValues values) {

        Log.i("Start Insert Table: ", entity.nameTable());

        SQLiteDatabase db = getWritableDatabase();
        long insertId = db.insertOrThrow(getValueMethod(EntityBase.NAME_TABLE), null, values);

        if (insertId == -1) {
            //TODO lan�a exce��o
            //Util.showAlertDialogMessage("Insert", "n�o foi possivel inserir", context);
        }

        db.close();

        Log.i("Finish Insert Table: ", entity.nameTable());
    }

    /**
     * :: Delete the entity
     *
     * @param entity <b>Entity that will be deleted</b>
     * @return
     */
    @Override
    public void delete(T entity) {

        Log.i("Start Delete Table: ", entity.nameTable());

        SQLiteDatabase db = getWritableDatabase();
        StringBuilder where = new StringBuilder(getValueMethod(EntityBase.COLUMN_ID)).append(" = ").append(entity.getId());
        long deleteRow = db.delete(getValueMethod(EntityBase.NAME_TABLE), where.toString(), null);

        if (deleteRow == -1) {
            //TODO lan�a exce��o
            //Util.showAlertDialogMessage("Delete", "n�o foi possivel deletar", context);
        }

        db.close();

        Log.i("Finish Delete Table: ", entity.nameTable());
    }

    /**
     * :: Update the entity
     *
     * @param entity
     * @param values <b>The fields and values that will be updated</b>
     * @return
     */
    @Override
    public void update(T entity, ContentValues values) {

        Log.i("Start Update Table: ", entity.nameTable());

        SQLiteDatabase db = getWritableDatabase();
        StringBuilder where = new StringBuilder(getValueMethod(EntityBase.COLUMN_ID)).append(" = ?");
        long updateRow = db.update(getValueMethod(EntityBase.NAME_TABLE), values, where.toString(), new String[]{String.valueOf(entity.getId())});

        if (updateRow == -1) {
            //TODO lan�a exce��o
            //Util.showAlertDialogMessage("Update", "n�o foi possivel atualizar", context);
        }

        db.close();

        Log.i("Finish Update Table: ", entity.nameTable());
    }

    /**
     * :: Metodo que busca a entidade a partir do ID
     *
     * @param id <b>Id da Entidade</b>
     * @return Entidade correspondente ao id
     */
    @Override
    public T findById(Long id) {

        // obtem o nome da tabela
        String nameTable = getValueMethod(EntityBase.NAME_TABLE);

        Log.i("Start FindById Table: ", nameTable);

        SQLiteDatabase db = getReadableDatabase();

        // monta where "id = ?"
        StringBuilder where = new StringBuilder(getValueMethod(EntityBase.COLUMN_ID)).append(" = ?");

        Cursor cursor = db.query(nameTable,
                null, where.toString(), new String[]{String.valueOf(id)}, null, null, null);

        // move para o primeiro registro
        cursor.moveToFirst();

        T entity = null;

        if (cursor.getCount() > 0) {
            entity = cursorToObject(cursor);
        }

        cursor.close();

        db.close();

        Log.i("Finish FindById Table: ", nameTable);

        return entity;
    }

    /**
     * :: Metodo que busca a entidade a partir do valor da coluna
     *
     * @param valor  <b>Valor que ser� utilizado para encontrar a Entidade</b>
     * @param column <b>Coluna que ser� buscado o registro na tabela</b>
     * @return Entidade correspondente ao valor
     */
    @Override
    public T findByIdAndColumn(String valor, String column) {

        // obtem o nome da tabela
        String nameTable = getValueMethod(EntityBase.NAME_TABLE);

        Log.i("Start findByIdAndColumn Table: ", nameTable);

        SQLiteDatabase db = getReadableDatabase();

        // monta where "id = ?"
        StringBuilder where = new StringBuilder(column).append(" = ?");

        Cursor cursor = db.query(nameTable,
                null, where.toString(), new String[]{valor}, null, null, null);

        // move para o primeiro registro
        cursor.moveToFirst();

        T entity = null;

        if (cursor.getCount() > 0) {
            entity = cursorToObject(cursor);
        }

        cursor.close();

        db.close();

        Log.i("Finish findByIdAndColumn Table: ", nameTable);

        return entity;
    }

    /**
     * :: Metodo que busca as entidades a partir do ID da coluna
     *
     * @param id     <b>Id da Entidade</b>
     * @param column <b>Coluna que cont�m o id</b>
     * @return Entidade correspondente ao id
     */
    @Override
    public List<T> findAllByIdAndColumn(String id, String column) {

        // obtem o nome da tabela
        String nameTable = getValueMethod(EntityBase.NAME_TABLE);

        Log.i("Start findAllByIdAndColumn Table: ", nameTable);

        SQLiteDatabase db = getReadableDatabase();
        List<T> listEntity = new ArrayList<T>();

        StringBuilder where = new StringBuilder(column).append(" = ?");

        Cursor cursor = db.query(nameTable,
                null, where.toString(), new String[]{id}, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            T entity = cursorToObject(cursor);
            listEntity.add(entity);
            cursor.moveToNext();
        }
        cursor.close();

        db.close();

        Log.i("Finish findAllByIdAndColumn Table: ", nameTable);

        return listEntity;
    }

    /**
     * :: Metodo que busca todos os dados da entidade
     *
     * @param
     * @return Lista de registro da Entidade
     */
    @Override
    public List<T> findAll() {

        // obtem o nome da tabela
        String nameTable = getValueMethod(EntityBase.NAME_TABLE);

        Log.i("Start FindAll Table: ", nameTable);

        SQLiteDatabase db = getReadableDatabase();
        List<T> listEntity = new ArrayList<T>();

        Cursor cursor = db.query(nameTable,
                null, null, null, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            T entity = cursorToObject(cursor);
            listEntity.add(entity);
            cursor.moveToNext();
        }
        cursor.close();

        db.close();

        Log.i("Finish FindAll Table: ", nameTable);

        return listEntity;
    }

    /**
     * :: Metodo que busca todos os dados da entidade ordenados por um campo
     *
     * @param orderBy Nome da coluna que ser� ordenada
     * @return Lista de registro da Entidade
     */
    @Override
    public List<T> findAllOrderBy(String orderBy) {

        // get table name
        String nameTable = getValueMethod(EntityBase.NAME_TABLE);

        Log.i("Start FindAllOrderBy Table: ", nameTable);

        SQLiteDatabase db = getReadableDatabase();
        List<T> listEntity = new ArrayList<T>();

        Cursor cursor = db.query(nameTable,
                null, null, null, null, null, orderBy);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            T entity = cursorToObject(cursor);
            listEntity.add(entity);
            cursor.moveToNext();
        }
        cursor.close();

        db.close();

        Log.i("Finish FindAllOrderBy Table: ", nameTable);

        return listEntity;
    }

    /**
     * :: Return the entity with required values
     *
     * @param mapColumnValue <b>Map fields and values</b>
     * @return Return the entity with required values
     */
    @Override
    public List<T> findAllByColumnAndValue(Map<String, String> mapColumnValue) {

        String nameTable = getValueMethod(EntityBase.NAME_TABLE);

        Log.i("Start findAllByColumnAndValue Table: ", nameTable);

        SQLiteDatabase db = getReadableDatabase();
        List<T> listEntity = new ArrayList<T>();

        StringBuilder where = new StringBuilder(" 1 = 1 ");
        String[] values = new String[mapColumnValue.size()];
        int count = 0;

        for (String key : mapColumnValue.keySet()) {
            where.append(" and ").append(key).append(" = ? ");
            values[count] = mapColumnValue.get(key);
            count++;
        }

        Cursor cursor = db.query(nameTable,
                null, where.toString(), values, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            T entidade = cursorToObject(cursor);
            listEntity.add(entidade);
            cursor.moveToNext();
        }
        cursor.close();

        db.close();

        Log.i("Finish findAllByColumnAndValue Table: ", nameTable);

        return listEntity;
    }

    /**
     * :: Verify if the entity exist in database
     *
     * @param entity         <b>Entity will be verified</b>
     * @param mapColumnValue <b>Map fields and values</b>
     * @return True if exist and false otherwise
     */
    @Override
    public boolean existEntity(T entity, Map<String, String> mapColumnValue) {

        String nameTable = getValueMethod(EntityBase.NAME_TABLE);

        Log.i("Start existEntity Table: ", nameTable);

        SQLiteDatabase db = getReadableDatabase();
        List<T> listEntity = new ArrayList<T>();

        StringBuilder where = new StringBuilder(" 1 = 1 ");
        String[] values = new String[mapColumnValue.size()];
        int count = 0;

        for (String key : mapColumnValue.keySet()) {
            where.append(" and ").append(key).append(" = ? ");
            values[count] = mapColumnValue.get(key);
            count++;
        }

        Cursor cursor = db.query(nameTable,
                null, where.toString(), values, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            T entidade = cursorToObject(cursor);
            listEntity.add(entidade);
            cursor.moveToNext();
        }
        cursor.close();

        db.close();

        Log.i("Finish existEntity Table: ", nameTable);

        if (listEntity.isEmpty() || (entity.getId() != null && listEntity.get(NumberUtil.ZERO).getId().equals(entity.getId()))) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * :: Metodo que aplica reflection para recuperar o valor
     * do metodo da entidade
     *
     * @param nameMethod <b>Nome do metodo da entidade</b>
     * @return Resultado da execu��o do metodo da entidade
     */
    protected String getValueMethod(String nameMethod) {

        try {

            Log.i("Classe:", clazz.getSimpleName());

            Method method = clazz.getMethod(nameMethod, null);
            Constructor ct = clazz.getConstructor(null);
            Object object = ct.newInstance(null);
            Object value = method.invoke(object, null);

            Log.i("Metodo getValueMethod:: ", value.toString());

            return value.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * :: Metodo que retorna o contexto da aplica��o
     *
     * @param
     * @return Contexto da aplica��o
     */
    public Context getContext() {
        return context;
    }

    /**
     * :: Metodo que monta a entidade
     *
     * @param cursor <b>Cursor com os registro do banco de dados</b>
     * @return Entidade populada
     */
    protected T cursorToObject(Cursor cursor) {
        return null;
    }
}
