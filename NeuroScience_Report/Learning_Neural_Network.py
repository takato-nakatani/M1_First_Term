import numpy as np


# ニューラルネットを実装するクラス。
def learningNeuralNetwork(eta, Q_x, X, W):
    # 出力Yを内積で計算
    Y = np.dot(W.T, X)

    # XとXの転置行列の内積したもの(dot_X_XT)、YとYの転置行列の内積したもの(dot_Y_YT)を計算。
    dot_X_XT = np.dot(X, X.T)
    dot_Y_YT = np.dot(Y, Y.T)

    # dot_Y_YTの対角成分を取得
    diag_dot_Y_YT = np.diag(dot_Y_YT)

    # Θ_nの計算式。第1項はYとYの転置行列の内積の対角行列、第2項は対角成分を0とした上半三角行列
    Theta = np.diag(diag_dot_Y_YT) + 2 * np.triu(dot_Y_YT, 1)


    # Wの更新式。O(η^2)は非常に小さいので今回は無視する。
    W = W + eta * (np.dot(dot_X_XT, W) - np.dot(W, Theta))
    LSE = calcLSE(W, Q_x)

    return W, LSE


# LSEを計算するメソッド。
def calcLSE(W, Q_x):
    # WとWの転置行列の内積の逆行列を計算
    inv_diag_W_WT = np.linalg.inv(np.dot(W.T, W))

    # Wの正射影Pを求める。
    P = np.dot(np.dot(W, inv_diag_W_WT), W.T)

    # Pと同じ大きさの単位行列Iを生成
    I = np.identity(P.shape[0])
    # LSE算出
    LSE = np.trace(np.dot(I-P, Q_x))

    return LSE
