# -*- coding: utf-8 -*-
"""
Created on Tue May 29 18:54:39 2018

@author: TAKATO
"""
import unittest
from sample import prime 

class TestPrime(unittest.TestCase):

    def test_prime(self):

        value1 = 10
        expected = [2, 3, 5, 7]
        actual = prime(value1)
        self.assertEqual(expected, actual)

if __name__ == "__main__":
    unittest.main()