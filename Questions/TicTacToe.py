from abc import ABC, abstractmethod
import random

# WE have set the default board size to 3, but we can modify it if we want
# --- Player Strategy Interface --- Using Strategy Design Pattern
class PlayerStrategy(ABC):
    @abstractmethod
    def get_move(self, board):
        pass

# --- Human Player Strategy ---
class HumanPlayer(PlayerStrategy):
    def __init__(self, name, symbol):
        self.name = name
        self.symbol = symbol

    def get_move(self, board):
        while True:
            try:
                row = int(input(f"{self.name} ({self.symbol}) enter row: "))
                col = int(input(f"{self.name} ({self.symbol}) enter col: "))
                if board.is_valid_move(row, col):
                    return row, col
                else:
                    print("Invalid move. Try again.")
            except ValueError:
                print("Invalid input. Enter numbers.")

# --- AI Player Strategy ---
class AIPlayer(PlayerStrategy):
    def __init__(self, name, symbol):
        self.name = name
        self.symbol = symbol

    def get_move(self, board):
        print(f"{self.name} ({self.symbol}) is making a move...")
        empty_cells = [(i, j) for i in range(board.size) for j in range(board.size) if board.grid[i][j] == ' ']
        return random.choice(empty_cells)

# --- Board Class ---
class Board:
    def __init__(self, size=3):
        self.size = size
        self.grid = [[' ' for _ in range(size)] for _ in range(size)]

    def display(self):
        for row in self.grid:
            print('|'.join(row))
            print('-' * (2 * self.size - 1))

    def is_valid_move(self, row, col):
        return 0 <= row < self.size and 0 <= col < self.size and self.grid[row][col] == ' '

    def make_move(self, row, col, symbol):
        if self.is_valid_move(row, col):
            self.grid[row][col] = symbol
            return True
        return False

    def check_winner(self, symbol):
        for i in range(self.size):
            if all(self.grid[i][j] == symbol for j in range(self.size)) or \
               all(self.grid[j][i] == symbol for j in range(self.size)):
                return True
        if all(self.grid[i][i] == symbol for i in range(self.size)) or \
           all(self.grid[i][self.size - i - 1] == symbol for i in range(self.size)):
            return True
        return False

    def is_full(self):
        return all(cell != ' ' for row in self.grid for cell in row)

# --- Game Context ---
class GameContext:
    def __init__(self, player1: PlayerStrategy, player2: PlayerStrategy, board_size=3):
        self.board = Board(board_size)
        self.players = [player1, player2]
        self.turn = 0

    def switch_turn(self):
        self.turn = 1 - self.turn

    def start_game(self):
        while True:
            self.board.display()
            current_player = self.players[self.turn]
            row, col = current_player.get_move(self.board)
            self.board.make_move(row, col, current_player.symbol)

            if self.board.check_winner(current_player.symbol):
                self.board.display()
                print(f"{current_player.name} wins!")
                break
            elif self.board.is_full():
                self.board.display()
                print("It's a draw!")
                break

            self.switch_turn()

# --- Run Game ---
if __name__ == '__main__':
    p1 = HumanPlayer("Alice", "X")
   # p2 = HumanPlayer("Jones", "X")
    p2 = AIPlayer("Computer", "O")
    game = GameContext(p1, p2)
    game.start_game()
