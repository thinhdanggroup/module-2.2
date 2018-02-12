public class HashTable {
    native long ht_new(); /* (1) */

    native void ht_insert(long ht, String key, String value);

    native String ht_search(long ht, String key);

    native void ht_delete(long ht, String key);

    native void ht_del_hash_table(long ht);
    static {
        System.loadLibrary("hash_table");
    }

    static public void main(String argv[]) {
        HashTable HashTable = new HashTable();
        long addr = HashTable.ht_new(); /* (3) */
        // System.out.println("Size : " + HashTable.get_size(addr));
        HashTable.ht_insert(addr, "1", "thinh");
        System.out.println("Value : " + HashTable.ht_search(addr,"1"));
        HashTable.ht_insert(addr, "2", "k");
        System.out.println("Value : " + HashTable.ht_search(addr, "2"));
        HashTable.ht_delete(addr, "1");

        String c = HashTable.ht_search(addr, "1");
        System.out.println("Value : " + HashTable.ht_search(addr,"3"));
        // System.out.println("Value : " + HashTable.ht_search(addr, '3'));
    }
}