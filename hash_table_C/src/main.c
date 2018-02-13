// main.c
#include "hash_table.h"
#include "stdio.h"
#include "stdlib.h"
int main()
{
    ht_hash_table *ht = ht_new();

    ht_insert(ht, "10", "thinh");
    ht_insert(ht, "12", "kaka");
    ht_insert(ht, "8", "dog");
    ht_insert(ht, "5", "nothing");
    ht_insert(ht, "3", "apple");
    ht_insert(ht, "15", "true");

    ht_delete(ht, "15");
    char *b = malloc(sizeof(char));
    b = ht_search(ht, "10");
    printf("%s\n", b);
    b = ht_search(ht, "12");
    printf("%s\n", b);
    b = ht_search(ht, "8");
    printf("%s\n", b);
    b = ht_search(ht, "5");
    printf("%s\n", b);
    b = ht_search(ht, "13");
    if (b != NULL)
        printf("%c\n", *b);
    else
        printf("khong tim thay\n");
    ht_del_hash_table(ht);
    b = ht_search(ht, "13");
    if (b != NULL)
        printf("%c\n", *b);
    else
        printf("khong tim thay\n");
}