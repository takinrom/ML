import matplotlib.pyplot as plt

try:
    n = int(input())
    x = []
    y = []
    z = []

    for i in range(n):
        X, Y, Z = map(float, input()[1:-1].split(', '))
        x.append(X)
        y.append(Y)
        z.append(Z)

    while True:
        c, m = map((lambda x: x[1:-1]), input().split(' : '))
        m = tuple(map(int, m.split(', ')))
        cx = []
        cy = []
        cz = []
        c = c[1:-1].split('), (')
        for i in c:
            X, Y, Z = map(float, i.split(', '))
            print(X, Y, Z)
            cx.append(X)
            cy.append(Y)
            cz.append(Z)
        fig = plt.figure()
        ax = fig.add_subplot(projection='3d')
        ax.scatter(x + cx, y + cy, z + cz, c = list(m) + ([len(cx)] * len(cx)))
        plt.show()
except EOFError:
    print("End of file")
