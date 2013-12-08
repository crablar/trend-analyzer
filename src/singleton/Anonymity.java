package singleton;

import pojos.Entity;
import pojos.Field;
import pojos.InvalidQueryException;
import pojos.Results;

import java.util.HashMap;
import java.util.Map;

/**
 * User: jeffreymeyerson
 * Date: 11/16/13
 * Time: 11:17 AM
 */

/*
 * "Let T(A1...An) be a table and QI be a quasi-identifier associated with it.
 * T is said to satisfy k-anonymity w/r/t QI iff each sequence of values in T[QI]
 * appears with at least k occurrences in T[QI]."
 */

public class Anonymity {

    private static Map<Field, Map<Object, Integer>> attributeCounts = new HashMap<>();

    public static boolean assertAnonymity(Results results) throws InvalidQueryException {
        for(Entity entity : results.resultSet){
            for(Map.Entry<Field, Object> entry : entity.attributes.entrySet()){
                Field field = entry.getKey();
                Object attribute = entry.getValue();
                Map<Object, Integer> attributeCountMap = attributeCounts.get(field);
                if(attributeCountMap == null){
                    attributeCountMap = new HashMap<>();
                    attributeCountMap.put(attribute, 1);
                    attributeCounts.put(field, attributeCountMap);
                }
                Integer count = attributeCountMap.get(attribute);
                if(count == null){
                    attributeCounts.get(field).put(attribute, 1);
                }
                else{
                    attributeCounts.get(field).put(attribute, count++);
                }
            }
        }
        for(Map<Object, Integer> entry : attributeCounts.values()){
            for(Map.Entry<Object, Integer> e : entry.entrySet()){
                if(e.getValue() < Configuration.k_value){
                    return false;
                }
            }
        }
        return true;
    }

    public static void clear(){
        attributeCounts.clear();
    }

}