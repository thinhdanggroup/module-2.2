public class HashTable {
    native long ht_new(); /* (1) */

    native int get_size(long addr_pointer);

    native void ht_insert(long ht, char key, char value);

    native char ht_search(long ht, char key);

    native void ht_delete(long ht, char key);

    static {
        System.loadLibrary("hash_table");
    }

    static public void main(String argv[]) {
        HashTable HashTable = new HashTable();
        long addr = HashTable.ht_new(); /* (3) */
        // System.out.println("Size : " + HashTable.get_size(addr));
        HashTable.ht_insert(addr, '1', 't');
        System.out.println("Value : " + HashTable.ht_search(addr, '1'));
        HashTable.ht_insert(addr, '2', 'k');
        System.out.println("Value : " + HashTable.ht_search(addr, '2'));
        HashTable.ht_delete(addr, '1');

        char c = HashTable.ht_search(addr, '3');
        System.out.println("Value : " + c);
        // HashTable
    }
}