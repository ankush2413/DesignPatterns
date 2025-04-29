#---------------------------------------Chain Of Responsibility------------------------------------------------------------------------------------
"""
The Chain of Responsibility design pattern is a behavioral pattern that allows a request to be passed along a chain of handlers. 
Each handler in the chain can decide whether to process the request or pass it to the next handler.
The sender of the request doesn’t know which handler will handle it — it just sends it into the chain.
"""
#------------------------------------------key Points---------------------------------------------------------------------------

"""
Purpose:	Decouple sender and receiver.
Real life eg:	Customer service, event handling, logging systems.
Benefits:	Flexible, dynamic flow of responsibility, reduces tight coupling (loose coupling).
Drawbacks:	Harder to trace the flow if the chain is long.
"""

#---------------------------------------Code-----------------------------------------------------------------------------------
"""
We are handling support tickets based on their severity.

    🛠️ Level 1: Basic Support

    🧠 Level 2: Technical Support

    👔 Level 3: Management Support
"""

# Abstract Handler
class SupportHandler:
    def __init__(self, level):
        self.level = level
        self.next_handler = None

    def set_next(self, next_handler):
        self.next_handler = next_handler

    def handle_request(self, level, message):
        if self.level == level:
            self.process(message)
        elif self.next_handler:
            self.next_handler.handle_request(level, message)
        else:
            print("No handler found for this level!")

    def process(self, message):
        pass

# Concrete Handlers
class BasicSupport(SupportHandler):
    def process(self, message):
        print(f"[Basic Support] Handling request: {message}")

class TechnicalSupport(SupportHandler):
    def process(self, message):
        print(f"[Technical Support] Handling request: {message}")

class ManagementSupport(SupportHandler):
    def process(self, message):
        print(f"[Management Support] Handling request: {message}")


# Main - Building the Chain
if __name__ == "__main__":
    basic = BasicSupport(level=1)
    technical = TechnicalSupport(level=2)
    management = ManagementSupport(level=3)

    basic.set_next(technical)
    technical.set_next(management)

    # Client sends a request
    print("Sending Level 1 Request:")
    basic.handle_request(level=1, message="Password reset issue.")

    print("\nSending Level 2 Request:")
    basic.handle_request(level=2, message="Cannot connect to server.")

    print("\nSending Level 3 Request:")
    basic.handle_request(level=3, message="Budget approval needed.")

    print("\nSending Level 4 Request:")
    basic.handle_request(level=4, message="Unknown issue.")
