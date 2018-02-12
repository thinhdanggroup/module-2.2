// main.c
#include "hash_table.h"
#include "stdio.h"
#include "stdlib.h"
int main()
{
    ht_hash_table *ht = ht_new();
    char data[] = {10, 12, 8, 5, 3, 2, 1};
    // for (int i=0;i<2;i++) {
    //     char *tmp = malloc(sizeof(char));
    //     printf("%d\n",data[i]);
    //     *tmp = data[i];
    //     printf("%d\n",*tmp);

    //     ht_insert(ht, tmp, tmp);
    //     printf("adsasd\n");
    // }

    ht_insert(ht, "10", "t");
    ht_insert(ht, "12", "k");
    ht_insert(ht, "8", "d");
    ht_insert(ht, "5", "n");
    ht_insert(ht, "3", "a");
    ht_insert(ht, "15", "t");
    // ht_insert(ht, "22", "t");
    // ht_insert(ht, "1", "t");

    ht_delete(ht, "15");
    char *b = malloc(sizeof(char));
    b = ht_search(ht, "10");
    printf("%c\n", *b);
    b = ht_search(ht, "12");
    printf("%c\n", *b);
    b = ht_search(ht, "8");
    printf("%c\n", *b);
    b = ht_search(ht, "5");
    printf("%c\n", *b);
    // b = ht_search(ht, "3");
    // printf("%c\n", *b);
    // b = ht_search(ht, "15");
    // if (b != NULL)
    //     printf("%c\n", *b);
    // else
    //     printf("khong tim thay\n");
    ht_del_hash_table(ht);
    // ht_del_hash_table(ht);
}