import matplotlib.pyplot as plt
from sys import argv

if len(argv) < 2:
    dimension = 2
else:
    if argv[1] == '2':
        dimension = 2
    elif argv[1] == '3':
        dimension = 3
    else:
        print("Draw.py: Incorrect dimension")
        exit()

n = int(input())

data = [list(map(float, input().split())) for i in range(n)]
clusters = list(map(int, input()[1:-1].split(', ')))

if dimension == 2:
    plt.scatter([data[i][0] for i in range(n)], [data[i][1] for i in range(n)], c=clusters)
else:
    fig = plt.figure()
    ax = fig.add_subplot(projection='3d')
    ax.scatter([data[i][0] for i in range(n)], [data[i][1] for i in range(n)], [data[i][2] for i in range(n)], c=clusters)
plt.show()
