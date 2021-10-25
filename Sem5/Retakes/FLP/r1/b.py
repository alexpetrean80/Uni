class Nod:
    def __init__(self, e):
        self.e = e
        self.urm = None


class Lista:
    def __init__(self):
        self.prim = None


'''
crearea unei liste din valori citite pana la 0
'''


def creareLista():
    lista = Lista()
    lista.prim = creareLista_rec()
    return lista


def creareLista_rec():
    x = int(input("x="))
    if x == 0:
        return None
    else:
        nod = Nod(x)
        nod.urm = creareLista_rec()
        return nod


'''
tiparirea elementelor unei liste
'''


def tipar(lista):
    tipar_rec(lista.prim)


def tipar_rec(nod):
    if nod != None:
        print(nod.e)
        tipar_rec(nod.urm)


def delete_elem_from_list(el: int, l: Lista):
    nl = Lista()
    nl.prim = delete_elem_from_list_rec(el, l.prim)
    return nl


def delete_elem_from_list_rec(el: int, n: Nod):
    if n is None:
        return None
    if n.e == el:
        return delete_elem_from_list_rec(el, n.urm)
    nn = Nod(n.e)
    nn.urm = delete_elem_from_list_rec(el, n.urm)
    return nn


if __name__ == "__main__":
    l = creareLista()

    e = int(input("Element to be removed:"))

    print("Initial list:")
    tipar(l)

    nl = delete_elem_from_list(e, l)
    print("List after deletion:")
    tipar(nl)
