#!/usr/bin/env python3
from random import randint, choice

def is_prime(nr):
    if nr <= 1:
        return False

    if nr == 2 or nr == 3:
        return True

    for i in range(2, int(nr/2) + 1):
        if nr % i == 0:
            return False
    return True

def get_rnd_prime(min,max):
    primes = [i for i in range(min,max) if is_prime(i)]
    return choice(primes)

def comp_seq(n, s, t, b):
    a = pow(b, t, n)

    prev_a = None

    if a == 1:
        return True



    for i in range(0, s + 1):
        prev_a = a

        a = (a * a) % n
        if a == n - 1:
            a = -1

        if a == 1 and (prev_a == -1 or prev_a == n):
            print(1)
            return True

    return False

n = 2 ** 102 - 1

n_copy = n
s = 0
n_copy -= 1

while n_copy % 2 == 0:
    n_copy //= 2
    s += 1

t = n_copy

base = get_rnd_prime(2,n - 1)

print(f'random prime base: {base}')

is_spp = comp_seq(n, s, t, base)
if not is_spp:
    print(f'{n} is composite.\n')
else:
    print(f'{n} is probably prime.\n')



