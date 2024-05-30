# Kmeans and DBSCAN algorithms implementation in Java
[Wiki:Kmeans](https://en.wikipedia.org/wiki/K-means_clustering)  
[Wiki:DBSCAN](https://en.wikipedia.org/wiki/DBSCAN)
```
$ pip install matplotlib
```
```
$ javac me/takinrom/ml/Main.java
```
## Kmeans:
```
$ (echo 150 ; cat ./data/iris.data) | java me.takinrom.ml.Main 4 Kmeans 3 | python Draw.py ./data/iris.data 0 2 3
```
### Windows:
```
$ (echo 150 & type .\data\iris.data) | java me.takinrom.ml.Main 4 Kmeans 3 | python Draw.py .\data\iris.data 0 2 3
```
## DBSCAN:
```
$ (echo 150 ; cat ./data/iris.data) | java me.takinrom.ml.Main 4 DBSCAN 0.836660026534 5 | python Draw.py ./data/iris.data 0 2 3
```
### Windows:
```
$ (echo 150 & type .\data\iris.data) | java me.takinrom.ml.Main 4 DBSCAN 0.836660026534 5 | python Draw.py .\data\iris.data 0 2 3
```