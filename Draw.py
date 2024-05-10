import matplotlib.pyplot as plt

try:
    n = int(input())
    x = []
    y = []

    for i in range(n):
        a, b = map(float, input()[1:-1].split(', '))
        x.append(a)
        y.append(b)

    while True:
        c, m = map((lambda x: x[1:-1]), input().split(' : '))
        m = tuple(map(int, m.split(', ')))
        cx = []
        cy = []
        c = c[1:-1].split('), (')
        for i in c:
            a, b = map(float, i.split(', '))
            print(a, b)
            cx.append(a)
            cy.append(b)
        plt.scatter(x + cx, y + cy, c = list(m) + ([len(cx)] * len(cx)))
        plt.show()
except EOFError:
    print("End of file")
