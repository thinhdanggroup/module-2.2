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
        String key ="1";
        HashTable.ht_insert(addr, key, "thinh");
        System.out.println("Value "+key + " : " + HashTable.ht_search(addr,key));

        key="2";
        HashTable.ht_insert(addr, key, "dang");
        System.out.println("Value "+key + " : " + HashTable.ht_search(addr, key));

        key="1";
        HashTable.ht_delete(addr, key);
        System.out.println("After delete 1 and search 1 : " + HashTable.ht_search(addr,key));

        key="3";
        System.out.println("Search key is not exist : " + HashTable.ht_search(addr,key));

        HashTable.ht_del_hash_table(addr);
        System.out.println("Search key 2 : " + HashTable.ht_search(addr, "2"));
    }
}