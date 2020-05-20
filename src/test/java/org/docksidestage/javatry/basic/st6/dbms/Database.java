package org.docksidestage.javatry.basic.st6.dbms;

public abstract class Database {
    public abstract String buildPagingQuery(int pageSize, int pageNumber);
    protected int calcOffSet(int pageSize, int pageNumber){
        return pageSize * (pageNumber - 1);
    };
}
