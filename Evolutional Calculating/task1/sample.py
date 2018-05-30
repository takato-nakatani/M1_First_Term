# -*- coding: utf-8 -*-
"""
Created on Tue May 29 17:58:42 2018

@author: TAKATO
"""

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
    str_x = input()
    x = int(str_x)
    print(prime(x))