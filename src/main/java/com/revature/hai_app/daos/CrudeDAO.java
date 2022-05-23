package com.revature.hai_app.daos;

import java.util.*;

public interface CrudeDAO<T>{
    void save (T obj);
    void update(T Obj);
    void delete(String id);

    T getByID(String id);
    List<T> getAll();

}