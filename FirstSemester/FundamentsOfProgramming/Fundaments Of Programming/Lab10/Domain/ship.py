from dataclasses import dataclass


class Ship:
    def __init__(self, position: bool, *coordinates: int, damage=0, sunk=False):
        self.__length = abs(coordinates[0] - coordinates[1])
        self.__position = position
        self.__damage = damage
        self.__sunk = sunk
        self.__coordinates = {'x': coordinates[0], 'y': [1]}

    @property
    def length(self):
        return self.__length

    @property
    def position(self):
        return self.__position

    @property
    def damage(self):
        return self.__damage

    @damage.setter
    def damage(self, damage):
        self.__damage = damage

    @property
    def sunk(self):
        return self.__sunk

    @property
    def coordinates(self):
        return self.__coordinates

    def __eq__(self, other):
        return self.length == other.length and self.coordinates == other.coordinates and self.position == other.position and self.damage == other.damage
