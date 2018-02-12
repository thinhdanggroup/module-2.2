#/bin/bash
if [ $1 -eq '1' ]
then 
echo compile java
javac HashTable.java
javah HashTable
elif [ $1 -eq '2' ]
then
echo compile C
gcc -c hash_table.c -I/usr/lib/jvm/java-8-oracle/include -I/usr/lib/jvm/java-8-oracle/include/linux -fPIC
gcc -o libhash_table.so -shared hash_table.o
elif [ $1 -eq '3' ]
then
echo run
java -Djava.library.path=. HashTable
elif [ $1 -eq '4' ]
then
echo Do all
javac HashTable.java
javah HashTable
gcc -c demo.c -I/usr/lib/jvm/java-8-oracle/include -I/usr/lib/jvm/java-8-oracle/include/linux -fPIC
gcc -o libhash_table.so -shared demo.o
java -Djava.library.path=. HashTable
fi