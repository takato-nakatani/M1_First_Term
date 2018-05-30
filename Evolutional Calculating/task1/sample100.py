# -*- coding: utf-8 -*-
"""
Created on Tue May 29 19:11:12 2018

@author: TAKATO
"""


'''
・テストファースト
    ものを作る前にまず、テストから着手する手法

・ユニットテスト
    プログラムを構成する比較的小さな単位が個々の機能を正しく果たしているかどうかをチェック
'''

def prime(x):
    if x < 2: 
        return []
    
    primes = [i for i in range(x + 1)]
    primes[1] = 0
    
    for prime in primes:
        for i in range(2, prime):
            if prime % i == 0:
                primes[prime] = 0
                break      
            else:
                continue
            
    return [prime for prime in primes if prime != 0]

if __name__ == '__main__':
    print(prime(100))