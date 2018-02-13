
#include <jni.h>
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <ctype.h>
#define HT_PRIME_1 5
#define HT_PRIME_2 7
#define HT_INITIAL_BASE_SIZE 5

typedef struct
{
    char *key;
    char *value;
} ht_item;

typedef struct
{
    int size;
    int count;
    ht_item **items;
    int base_size;
} ht_hash_table;

static ht_item HT_DELETED_ITEM = {NULL, NULL};
//declare
void ht_insert(ht_hash_table *ht, const char *key, const char *value);
char *ht_search(ht_hash_table *ht, const char *key);
void ht_delete(ht_hash_table *h, const char *key);
ht_hash_table *ht_new();
// static void ht_del_item(ht_item* i);
void ht_del_hash_table(ht_hash_table *ht);

int is_prime(const int x)
{
    if (x < 2)
    {
        return -1;
    }
    if (x < 4)
    {
        return 1;
    }
    if ((x % 2) == 0)
    {
        return 0;
    }
    for (int i = 3; i <= floor(sqrt((double)x)); i += 2)
    {
        if ((x % i) == 0)
        {
            return 0;
        }
    }
    return 1;
}
int next_prime(int x)
{
    while (is_prime(x) != 1)
    {
        x++;
    }
    return x;
}

static ht_item *ht_new_item(const char *k, const char *v)
{
    ht_item *i = malloc(sizeof(ht_item));
    i->key = strdup(k);
    i->value = strdup(v);
    return i;
}

ht_hash_table *ht_new_sized(const int base_size)
{
    ht_hash_table *ht = malloc(sizeof(ht_hash_table));
    ht->base_size = base_size;

    ht->size = next_prime(ht->base_size);

    ht->count = 0;
    ht->items = calloc((size_t)ht->size, sizeof(ht_item *));
    return ht;
}


JNIEXPORT jlong JNICALL Java_HashTable_ht_1new(JNIEnv *env, jobject jobj)
{
    ht_hash_table *my_struct = ht_new_sized(HT_INITIAL_BASE_SIZE);
    long lp = my_struct;
    return lp;
}

static int ht_hash(const char *s, const int a, const int m)
{
    long hash = 0;
    const int len_s = strlen(s);
    for (int i = 0; i < len_s; i++)
    {
        hash += (long)pow(a, len_s - (i + 1)) * s[i];
        hash = hash % m;
    }
    return (int)hash;
}
static void ht_del_item(ht_item *i)
{
    free(i->key);
    free(i->value);
    free(i);
}

void ht_del_hash_table(ht_hash_table *ht)
{
    for (int i = 0; i < ht->size; i++)
    {
        ht_item *item = ht->items[i];
        if (item != NULL && item != &HT_DELETED_ITEM)
        {
            ht_del_item(item);
        }
    }
    free(ht->items);
    free(ht);
}
static int ht_get_hash(
    const char *s, const int num_buckets, const int attempt)
{
    const int hash_a = ht_hash(s, HT_PRIME_1, num_buckets);
    const int hash_b = ht_hash(s, HT_PRIME_2, num_buckets);
    return (hash_a + (attempt * (hash_b + 1))) % num_buckets;
}

// Resize

static void ht_resize(ht_hash_table *ht, const int base_size)
{
    if (base_size < HT_INITIAL_BASE_SIZE)
    {
        return;
    }
    ht_hash_table *new_ht = ht_new_sized(base_size);
    for (int i = 0; i < ht->size; i++)
    {
        ht_item *item = ht->items[i];
        if (item != NULL && item != &HT_DELETED_ITEM)
        {
            ht_insert(new_ht, item->key, item->value);
        }
    }

    ht->base_size = new_ht->base_size;
    ht->count = new_ht->count;

    // To delete new_ht, we give it ht's size and items
    const int tmp_size = ht->size;
    ht->size = new_ht->size;
    new_ht->size = tmp_size;

    ht_item **tmp_items = ht->items;
    ht->items = new_ht->items;
    new_ht->items = tmp_items;

    ht_del_hash_table(new_ht);
}

static void ht_resize_up(ht_hash_table *ht)
{
    const int new_size = ht->base_size * 2;
    ht_resize(ht, new_size);
}

static void ht_resize_down(ht_hash_table *ht)
{
    const int new_size = ht->base_size / 2;
    ht_resize(ht, new_size);
}
ht_hash_table *rebuild_ht_hash_table(long addr)
{
    ht_hash_table *ht;
    ht = addr;
    return ht;
}
// Insert
void ht_insert(ht_hash_table *ht, const char *key, const char *value)
{
    const int load = ht->count * 100 / ht->size;
    if (load > 70)
    {
        ht_resize_up(ht);
    }
    ht_item *item = ht_new_item(key, value);
    int index = ht_get_hash(item->key, ht->size, 0);
    ht_item *cur_item = ht->items[index];
    int i = 1;
    while (cur_item != NULL)
    {
        index = ht_get_hash(item->key, ht->size, i);
        cur_item = ht->items[index];
        i++;
    }
    ht->items[index] = item;
    ht->count++;
}
// Search
char *ht_search(ht_hash_table *ht, const char *key)
{
    int index = ht_get_hash(key, ht->size, 0);
    ht_item *item = ht->items[index];
    int i = 1;
    while (item != NULL)
    {
        if (item != &HT_DELETED_ITEM)
        {
            if (strcmp(item->key, key) == 0)
            {
                return item->value;
            }
            index = ht_get_hash(key, ht->size, i);
            item = ht->items[index];
            i++;
            if (i == 10)
                break;
        }
        else
        {
            break;
        }
    }
    return "NULL";
}

// DELETE
void ht_delete(ht_hash_table *ht, const char *key)
{
    const int load = ht->count * 100 / ht->size;
    if (load < 10)
    {
        ht_resize_down(ht);
    }
    int index = ht_get_hash(key, ht->size, 0);
    ht_item *item = ht->items[index];
    int i = 1;
    while (item != NULL)
    {
        if (item != &HT_DELETED_ITEM)
        {
            if (strcmp(item->key, key) == 0)
            {
                ht_del_item(item);
                ht->items[index] = &HT_DELETED_ITEM;
                break;
            }
        }
        index = ht_get_hash(key, ht->size, i);
        item = ht->items[index];
        i++;
    }
    ht->count--;
}

JNIEXPORT void JNICALL Java_HashTable_ht_1insert(JNIEnv *env, jobject jobj, jlong addr, jstring key_c, jstring  value_c)
{
    // Init value
    ht_hash_table *ht = rebuild_ht_hash_table(addr);
    const char *key= (*env)->GetStringUTFChars(env,key_c,0);
    const char *value= (*env)->GetStringUTFChars(env,value_c,0);

	//need to release this string when done with it in order to
	//avoid memory leak
	
    ht_insert(ht,key,value);
    (*env)->ReleaseStringUTFChars(env, key_c, key);
    (*env)->ReleaseStringUTFChars(env, value_c, value);
}

JNIEXPORT jchar JNICALL Java_HashTable_ht_1search(JNIEnv *env, jobject jobj, jlong addr, jstring key_c)
{
    ht_hash_table *ht = rebuild_ht_hash_table(addr);
    char *key= (*env)->GetStringUTFChars(env,key_c,0);
    char * buf =ht_search(ht,key);
    jstring jstr;
    char temp[10];
    strcpy(temp,buf);
	
	return (*env)->NewStringUTF(env, temp);

}

JNIEXPORT void JNICALL Java_HashTable_ht_1delete(JNIEnv *env, jobject jobj, jlong addr, jstring key_c)
{
    ht_hash_table *ht = rebuild_ht_hash_table(addr);
    char *key= (*env)->GetStringUTFChars(env,key_c,0);
    ht_delete(ht,key);
}

JNIEXPORT void JNICALL Java_HashTable_ht_1del_1hash_1table(JNIEnv *env, jobject jobj, jlong addr)
{
    ht_hash_table *ht = rebuild_ht_hash_table(addr);

    ht_del_hash_table(ht);
}