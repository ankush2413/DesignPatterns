#-----------------------------------Strategy Design Pattern----------------------------------------------------------------------

"""
The Strategy Pattern lets you define a family of algorithms, encapsulate each one, and swap them out at runtime.
This allows your code to pick a strategy dynamically without modifying the object that uses it.

"""

#-----------------------------------------Benefits--------------------------------------------------------------------------------
"""
Flexible: Easily switch between algorithms
Clean code: No messy if/else or switch cases
Scalable: Add new strategies without modifying old code
Testable: Each strategy is a separate class, easy to test
"""
#-------------------------------Some Use Cases--------------------------------------------------------------------------
"""
1. Payment Systems (Switch between Paypal,credit card, UPI etc.)
2. Navigation Routing Apps (Choose between shortest path, avoid tools, scenic route etc.)
3. Data sorting/search algo based on the dataset size choose algorithm.
"""

"""
Use the Strategy Pattern when:

    You have multiple interchangeable algorithms.

    You want to follow the Open/Closed Principle (open for extension, closed for modification).

    You want to remove if-else or switch-case clutter from your main logic.
"""

#---------------------------------------------Code------------------------------------------------------------------------------

from abc import ABC, abstractmethod

# Strategy Interface
class PaymentStrategy(ABC):
    @abstractmethod
    def pay(self, amount):
        pass

# Concrete Strategies
class CreditCardPayment(PaymentStrategy):
    def pay(self, amount):
        print(f"Paid ₹{amount} using Credit Card.")

class PayPalPayment(PaymentStrategy):
    def pay(self, amount):
        print(f"Paid ₹{amount} using PayPal.")

class UpiPayment(PaymentStrategy):
    def pay(self, amount):
        print(f"Paid ₹{amount} using UPI.")

# Context Class
class ShoppingCart:
    def __init__(self, strategy: PaymentStrategy):
        self.strategy = strategy

    def checkout(self, amount):
        self.strategy.pay(amount)

# Client Code
if __name__ == "__main__":
    cart1 = ShoppingCart(CreditCardPayment())
    cart1.checkout(1000)

    cart2 = ShoppingCart(UpiPayment())
    cart2.checkout(500)

    # Dynamically change strategy
    cart2.strategy = PayPalPayment()
    cart2.checkout(300)
