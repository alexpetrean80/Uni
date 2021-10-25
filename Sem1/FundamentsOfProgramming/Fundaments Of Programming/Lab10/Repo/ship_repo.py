class ShipRepository:
    def __init__(self, validator_class):
        self.__ships = list()
        self.__validator = validator_class

    def save(self, ship):
        self.__validator.validate(ship)
        if not self.find_by_coordinates(ship.coordinates):
            self.ships.append(ship)

    def find_by_coordinates(self, coordinates):
        for ship in self.ships:
            if ship.coordinates == coordinates:
                return ship
        return None

    def update_damage_on_hit(self, damaged_ship):
        for ship in self.ships:
            if ship == damaged_ship:
                ship.damage -= 1
                break

    @property
    def ships(self):
        return self.__ships[:]







