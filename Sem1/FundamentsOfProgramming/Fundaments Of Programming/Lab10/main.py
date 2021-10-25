from Domain.validators import ShipValidator
from Repo.ship_repo import ShipRepository
from Services.game_service import GameService
from UI.console import ConsoleGame

if __name__ == '__main__':
    player_ships = ShipRepository(ShipValidator)
    ai_ships = ShipRepository(ShipValidator)
    game_service = GameService(player_ships, ai_ships)
    console = ConsoleGame(game_service)
    console.run()