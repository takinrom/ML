import matplotlib.pyplot as plt
from sys import argv
from csv import reader 

def printHelp():
    print("Usage: python Draw.py <data file> [column numbers]\nExample: python Draw.py ./data/data.csv 1 2 4\n\tDraw 3d projection of first, second and fourth columns\n")

if len(argv) < 4:
    printHelp()
    exit()

file_name = argv[1]
X = None
Y = None
Z = None
try:
    X = int(argv[2])
    Y = int(argv[3])
    if (len(argv) > 4):
        Z = int(argv[4])
except ValueError:
    print("Incorrect number format");
    exit()

x = []
y = []
z = []
n = 0
with open(file_name, 'r', encoding='utf-8') as file:
    data = reader(file, delimiter=',')
    if Z is not None:
        for i in data:
            x.append(float(i[X]))
            y.append(float(i[Y]))
            z.append(float(i[Z]))
    else:
        for i in data:
            x.append(float(i[X]))
            y.append(float(i[Y]))


clusters = list(map(int, input()[1:-1].split(', ')))

if Z is not None:
    fig = plt.figure()
    ax = fig.add_subplot(projection='3d')
    ax.scatter(x, y, z, c=clusters)
    plt.show()
else:
    plt.scatter(x, y, c=clusters)
    plt.show()
