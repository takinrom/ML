import matplotlib.pyplot as plt
n = int(input())
x = []
y = []
z = []

for i in range(n):
    X, Y, Z = map(float, input()[1:-1].split(', '))
    x.append(X)
    y.append(Y)
    z.append(Z)

clusters = list(map(int, input()[1: -1].split(', ')))
fig = plt.figure()
ax = fig.add_subplot(projection='3d')
ax.scatter(x, y, z, c=clusters)
plt.show()

