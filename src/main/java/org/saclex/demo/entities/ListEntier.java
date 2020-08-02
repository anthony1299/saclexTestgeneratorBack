package org.saclex.demo.entities;

import java.util.ArrayList;
import java.util.List;

public class ListEntier {
    private List<Integer> listNumber=new ArrayList <>(  );
    private List<String> listString=new ArrayList <>(  );

    public ListEntier() {
    }

    public ListEntier(List < Integer > listNumber) {
        this.listNumber = listNumber;
    }

    public List < String > getListString() {
        return listString;
    }

    public void setListString(List < String > listString) {
        this.listString = listString;
    }

    public List < Integer > getListNumber() {
        return listNumber;
    }

    public void setListNumber(List < Integer > listNumber) {
        this.listNumber = listNumber;
    }
}
