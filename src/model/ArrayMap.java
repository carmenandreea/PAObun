/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.util.*;
import java.lang.*;
import java.io.*;
import java.time.*;
import java.time.format.*;
import javax.swing.*;
import javax.swing.event.*;
/**
 *
 * @author Windows10
 */

public class ArrayMap<K,V> extends AbstractMap {

    @Override
    public Set entrySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    class ArrayMapEntry<K, V> implements Map.Entry {

        K key;
        V value;
        
        public ArrayMapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
        
        public K getKey()
        {
            return this.key;
        }
        
        public V getValue()
        {
            return this.value;
        }
        
        public V setValue(Object value)
        {
            V old = this.value;
            this.value = (V)value;
            return old;
        }
        
        public String toString()
        {
            return this.key + " = " + this.value;
        }
    }
}
        