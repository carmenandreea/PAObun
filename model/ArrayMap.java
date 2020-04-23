/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.util.*;
/**
 *
 * @author Windows10
 * @param <K>
 * @param <V>
 */

public class ArrayMap<K,V> extends AbstractMap {

   /* @Override
    public Set entrySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
    
    class ArrayMapEntry<K, V> implements Map.Entry {

        K key;
        V value;
        
        public ArrayMapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
        
        @Override
        public K getKey()
        {
            return this.key;
        }
        
        @Override
        public V getValue()
        {
            return this.value;
        }
        
        @Override
        public V setValue(Object value)
        {
            V old = this.value;
            this.value = (V)value;
            return old;
        }
        
        @Override
        public String toString()
        {
            return this.key + " = " + this.value;
        }
        
        
        @Override
          public boolean equals(Object o)
        {
            if (!(o instanceof Map.Entry)) 
                return false;
            Map.Entry e = (Map.Entry) o;
            return (key == null ? e.getKey() == null : key.equals(e.getKey())) && (value == null ? e.getValue() == null : value.equals(e.getValue()));
        }
        
        @Override
        public int hashCode() 
        {
            int keyHash = (key == null ? 0 : key.hashCode());
            int valueHash = (value == null ? 0 : value.hashCode());
            return keyHash ^ valueHash;
        }
    
    }
    
    
    private Set entries = null;

    private ArrayList list;

    public ArrayMap() 
    {
        list = new ArrayList();
    }

    public ArrayMap(Map map) 
    {
        list = new ArrayList();
        putAll(map);
    }

    public ArrayMap(int initialCapacity) 
    {
        list = new ArrayList(initialCapacity);
    }
    
    /**
     *
     * @return
     */
    @Override public Set<Entry<K, V>> entrySet() 
    {
        if (entries == null) 
        {
            entries = new AbstractSet() {
                @Override
                public void clear() 
                {
                    list.clear();
                }

                @Override
                public Iterator iterator() 
                {
                    return list.iterator();
                }

                @Override
                public int size() 
                {
                    return list.size();
                }
            };
        }
        return entries;
    }

    @Override
    public int size()
    {
        return list.size();
    }
    
    
    @Override
    public V put(Object key, Object value) 
    {
        int size = list.size();
        ArrayMapEntry entry = null;
        int i;
        if (key == null) 
        {
            for (i = 0; i < size; i++) 
            {
                entry = (ArrayMapEntry) (list.get(i));
                if (entry.getKey() == null) 
                {
                    break;
                }
            }
        } 
        else 
        {
            for (i = 0; i < size; i++) 
            {
                entry = (ArrayMapEntry) (list.get(i));
                if (key.equals(entry.getKey())) 
                {
                    break;
                }
            }
        }
        
        Object oldValue = null;
        if (i < size) 
        {
            oldValue = entry.getValue();
            entry.setValue(value);
        }
        else 
        {
            list.add(new ArrayMapEntry(key, value));
        }
        return (V)oldValue;
    }
    
    @Override
    public boolean containsKey(Object key)
    {
        int size = list.size();
        ArrayMapEntry entry = null;
        int i;
        if (key == null) 
        {
            for (i = 0; i < size; i++) 
            {
                entry = (ArrayMapEntry) (list.get(i));
                if (entry.getKey() == null) 
                {
                    break;
                }
            }
        } 
        else 
        {
            for (i = 0; i < size; i++) 
            {
                entry = (ArrayMapEntry) (list.get(i));
                if (key.equals(entry.getKey())) 
                {
                    break;
                }
            }
        }
        
        if (i < size) 
        {
            return true;
        }
        else 
        {
            return false;
        }
    }
    @Override
    public V get(Object key)
    {
        int size = list.size();
        ArrayMapEntry entry = null;
        int i;
        if (key == null) 
        {
            for (i = 0; i < size; i++) 
            {
                entry = (ArrayMapEntry) (list.get(i));
                if (entry.getKey() == null) 
                {
                    break;
                }
            }
        } 
        else 
        {
            for (i = 0; i < size; i++) 
            {
                entry = (ArrayMapEntry) (list.get(i));
                if (key.equals(entry.getKey())) 
                {
                    break;
                }
            }
        }
        
        Object oldValue = null;
        if (i < size) 
        {
            oldValue = entry.getValue();
        }
        return (V)oldValue;
    }
    
}
        