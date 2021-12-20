from math import gcd

alphabet=['_','a', 'b','c','d','e', 'f', 'g', 'h', 'i', 'j', 'k', 'l','m','n','o','p','q','r','s','t','u','v','w','x','y','z']
k = 2
l = 3
p = 37
q = 61
n = p * q

euler = (p - 1) * (q - 1)
print(f'euler={euler}')

pt = "london"
print(f'plain text={pt}')

pt_blocks = [pt[i:i+k] for i in range(0, len(pt), k)]
print(f'plain text blocks={pt_blocks}')


def get_e():
    for e in range(3,euler, 2):
        if gcd(e, euler) == 1:
            return e
    return -1

e = get_e()
print(f'e={e}')

pub_key = (n,e)
print(f'public key={pub_key}')

d = e ** -1 % euler
print(f'private key={d}')

def get_pos(char):
    for i, v in enumerate(alphabet):
        if v == char:
            return i
    return -1

print(get_pos('b'))

bs=[]
for block in pt_blocks:
    x = get_pos(block[0]) * 27 + get_pos(block[1])
    bs.append(x)

print(f'numerical equivalent of plain text blocks={bs}')

cs = []
for b in bs:
    x = b ** e % n
    cs.append(x)

print(f'numerical equivalent of cyphertext blocks = {cs}')

def char_from_b10(nr):
    rests = []
    while nr != 0:
        rests.append(int(nr % 27))
        nr /= 27

    res = ""
    rests.reverse()
    for i in rests:
        res += alphabet[i]
    return res


ct_blocks=[]
for c in cs:
    x = char_from_b10(c)
    ct_blocks.append(x)

print(f'cyphertext blocks={ct_blocks}')
