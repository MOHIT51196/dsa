package string_matching.rabin_karp;

public class RabinKarp {

    private final int mod = Integer.MAX_VALUE;
    private final int base = 26;

    // 'abc' like 10^2 * a + 10^1 * b + 10^0 * c
    // trick is hash = (10 * existing_hash + next_element) % mod
    // mod to avoid overflow
    private int polyHash(String pattern, int len){
        int hash = 0;
        for(int i = 0; i<len; i++) hash = (base * hash + pattern.charAt(i)) % mod;
        return hash;
    }

    public boolean match(String text, String pattern){
        int pLen = pattern.length();
        int tLen = text.length();

        int pHash = polyHash(pattern, pLen);
        int tHash = polyHash(text, pLen);

//        System.out.println("phash , thash = " + pHash + " , " + tHash);

        // required to reduce  - (10^(n-1) * prev_element)
        int maxBase = 1;
        for(int i = 0; i< pLen - 1; i++) {
            maxBase = (maxBase * base) % mod;
        }

        for (int i = 0; i <= tLen - pLen; i++) {

            if(pHash == tHash){
                int j = 0;
                for (; j < pLen && text.charAt(i + j) == pattern.charAt(j); j++);
                if(j == pLen) return true;
            }

            if(i < tLen - pLen) {
                tHash = (base * (tHash - (text.charAt(i) * maxBase) ) + text.charAt(i + pLen) ) % mod;
                if(tHash < 0) tHash += mod; // negative modulo fix
            }

        }

        return false;
    }

    public static void main(String[] args) {
        String text = "Mohit Malhotra";
        String pattern = "hit";

        RabinKarp rk = new RabinKarp();
        System.out.println("hash M = " + rk.polyHash("M", 1));
        System.out.println("hash i = " + rk.polyHash("i", 1));
        System.out.println("String matching status = " + rk.match(text, pattern));
    }
}
