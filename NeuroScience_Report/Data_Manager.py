import numpy as np
import numpy.random as rd

# data_numberにデータの個数、*argは可変長引数で入力値の次元の数だけ引数に設定
def normalRandomGenerator(data_number, dimension):
    #正規乱数をdata_number個だけ生成
    X = np.empty([0, data_number])
    for i in range(dimension):
        d = (dimension - i) * rd.randn(1, data_number)
        X = np.r_[X, d]

    return X