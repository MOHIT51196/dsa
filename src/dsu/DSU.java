package dsu;

public interface DSU {
    public boolean isConnected(int n1, int n2);
    public int find(int n);
    public void union(int n1, int n2);
}
