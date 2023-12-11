package com.example.trabalhoapipaises.dao;

import java.util.ArrayList;

public interface IGenericDao<Object>{
    long insert(Object obj);
    ArrayList<Object> getAll();
}
