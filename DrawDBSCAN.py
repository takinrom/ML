import matplotlib.pyplot as plt
n = int(input())
x = []
y = []

for i in range(n):
    X, Y = map(float, input()[1:-1].split(', '))
    x.append(X)
    y.append(Y)

clusters = list(map(int, input()[1: -1].split(', ')))
plt.scatter(x, y, c=clusters)
plt.show()

