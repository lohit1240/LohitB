/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuplespace;

import java.io.Serializable;

/**
 *
 * @author lohit
 */
public class TupleSpace implements Serializable {

    public int id;
    public String name;
    public boolean inp;

    public TupleSpace(String name,int id, boolean inp) {
        this.id = id;
        this.name = name;
        this.inp = inp;
    }
    
    
    
    }
    

