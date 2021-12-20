from math import gcd
from copy import deepcopy

alphabet=['_','a', 'b','c','d','e', 'f', 'g', 'h', 'i', 'j', 'k', 'l','m','n','o','p','q','r','s','t','u','v','w','x','y','z']
k = 2
l = 3
p = 37
q = 61
n = p * q

euler = (p - 1) * (q - 1)
print(f'euler={euler}')

ct = "bds_oo_hh"
print(f'cipher text={ct}')

ct_blocks = [ct[i:i+l] for i in range(0, len(ct), l)]
print(f'cipher text blocks={ct_blocks}')


def get_e():
    for e in range(3,euler, 2):
        if gcd(e, euler) == 1:
            return e
    return -1

e = get_e()
print(f'e={e}')

pub_key = (n,e)
print(f'public key={pub_key}')

def eea(r0, r1):
    initial_r0 = deepcopy(r0)
    s0, t0, s1, t1 = 1, 0, 0, 1
    not_first = False
    s, t, r, prev_r, q = 0, 0, 0, 0 ,0

    while True:
        if not_first:
            s0, t0 = s1,t1
            s1, t1 = s,t
            prev_r = r
            r0=r1
            r1=r
        r = r0 % r1
        if r == 0:
            break
        q = r0 // r1
        s = s0 - q * s1
        t = t0 - q *t1

        not_first = True

    return prev_r, t1 % initial_r0

_, d =eea(euler, e)

print(f'private key={d}')

def get_pos(char):
    for i, v in enumerate(alphabet):
        if v == char:
            return i
    return -1

cs=[]
for block in ct_blocks:
    x = get_pos(block[0]) * 27 ** 2 + get_pos(block[1]) * 27 + get_pos(block[2])
    cs.append(x)

print(f'numerical equivalent of cipher text blocks={cs}')

bs = []
for c in cs:
    b = c ** d % n
    bs.append(b)

print(f'numerical equivalent of plain text blocks={bs}')

def char_from_b10(nr):
    rests=[]
    while nr != 0:
        rests.append(int(nr%27))
        nr /=27
    res = ""
    rests.reverse()
    for i in rests:
        res += alphabet[i]
    return res

pt_blocks = []
for b in bs:
    x = char_from_b10(b)
    pt_blocks.append(x)

print(f'plain text blocks={pt_blocks}')
