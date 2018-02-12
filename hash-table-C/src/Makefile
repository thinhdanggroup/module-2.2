file = hash_table

all: main.o hash_table.o prime.o
	gcc -o app.out main.o hash_table.o prime.o -lm

main.o: main.c hash_table.h prime.h
	gcc -c main.c hash_table.h prime.h

hash_table.o: hash_table.c hash_table.h prime.h
	gcc -c hash_table.c hash_table.h prime.h

prime.o: prime.c prime.h 
	gcc -c prime.c prime.h
# app: main.o inc.o
#     cc -o app main.o inc.o

# main.o: main.c inc.h
#     cc -c main.c inc.h

# inc.o: inc.c inc.h
#     cc -c inc.c inc.h