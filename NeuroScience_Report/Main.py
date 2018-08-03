import numpy as np
import numpy.random as rd
import Data_Manager as dm
import Learning_Neural_Network as NN
import Plot_Data as pd

def main():
    # 射影前の次元：d_X
    d_X = 5
    # 射影後の次元：d_Y
    d_Y = 3
    # 学習率：η
    eta = 0.000001
    # 学習回数：n
    n = 10000
    # データ数：data_num
    data_num = 10000

    # 正規乱数によってデータを生成し、行列に結合。各行が１つのデータの組、列数が次元数である。
    X = dm.normalRandomGenerator(data_num, d_X)

    # 正規乱数によって求めた行列Xの分散共分散行列を求める。
    Q_x = np.cov(X, rowvar=True, bias=True)

    # 重み行列(行数：射影前の次元、列数：射影後の次元)を正規乱数で初期化
    W = rd.rand(d_X, d_Y)

    # 各学習回数におけるLSEのリスト
    LSE_n = []
    for i in range(n):
        W, LSE = NN.learningNeuralNetwork(eta, Q_x, X, W)
        LSE_n.append(LSE)
    # 固有値、固有ベクトルを求めてeigとVに格納
    eig, V = np.linalg.eig(Q_x)
    # 小数を指数表示ではなく、小数の形で表示
    np.set_printoptions(suppress=True)

    print("分散共分散行列Q_x：\n" + str(Q_x) + "\n")
    print("重みW：\n" + str(W) + "\n")
    print("分散共分散行列Q_xの固有ベクトルV：\n" + str(V) + "\n")
    # Wとxの内積
    for j in range(d_X):
        for k in range(d_Y):
            Wk = W[:, k]
            vj = V[:, j]
            print("W" + str(k+1) + "とV" + str(j+1) + "の内積：" + str(np.dot(Wk, vj)))
        print("\n")

    # LSEのグラフ描画
    pd.PlotData(LSE_n)


if __name__ == '__main__':
    main()