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

public class AnonymityVerifier {

    public static boolean assertAnonymity(Results results) throws InvalidQueryException {
        Map<Field, Map<Object, Integer>> attributeCounts = new HashMap<>();
        for(Entity entity : results.resultSet){
            for(Map.Entry<Field, Object> entry : entity.attributes.entrySet()){
                Field field = entry.getKey();
                Object attribute = entry.getValue();
                Map<Object, Integer> attributeCountMap = attributeCounts.get(field);
                if(attributeCountMap == null){
                    attributeCountMap = new HashMap<>();
                    attributeCountMap.put(attribute, 0);
                    attributeCounts.put(field, attributeCountMap);
                }
                Integer count = attributeCountMap.get(attribute);
                if(count == null){
                    attributeCounts.get(field).put(attribute, 0);
                }
                else{
                    attributeCounts.get(field).put(attribute, count++);
                }
            }
        }
        for(Map<Object, Integer> entry : attributeCounts.values()){
            for(Integer i : entry.values()){
                if(i < Configuration.k_value){
                    return false;
                }
            }
        }
        return true;
    }

}
