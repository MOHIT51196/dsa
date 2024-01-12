package google.unique_email_address;

import java.util.LinkedHashSet;
import java.util.Set;

public class Solution {
    
    private String normalise(String email){
        StringBuffer bufName = new StringBuffer();
        StringBuffer bufDomain = new StringBuffer();



        boolean isDomainDetected = false;
        for(char c : email.toCharArray()){
            if(c == '.' ) continue;
            if(c == '+' || c == '@') break;            
            bufName.append(c);
        }
        for(int i = email.length() - 1; i>=0; i--){
            char c = email.charAt(i);
            if(c == '@') break;
            
            bufDomain.append(c);
        }
        
        return bufName.toString() + "@" +bufDomain.reverse().toString();
    }
    public int numUniqueEmails(String[] emails) {
        Set<String> set = new LinkedHashSet<>();
        for(String email : emails) set.add(normalise(email));
        return set.size();
    }
}