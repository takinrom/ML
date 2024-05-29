from sys import argv
from csv import reader

if len(argv) < 2:
    print("PrintData.py: file not given")
    exit()

with open(argv[1], 'r') as file:
    for i in reader(file):
        data = i[:-1]
        print(' '.join((data[1], data[2], data[3])))