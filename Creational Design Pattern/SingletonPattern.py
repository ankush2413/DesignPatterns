#----------------------------Singleton Design Pattern---------------------------------------------------------------------------

"""
The Singleton Pattern ensures that a class has only one instance throughout the program —
and it provides a global point of access to that instance.
"Only one object of this class should ever exist — no matter how many times I try to create it."
"""

#-----------Use Case-----------------------------------------------------------------------------------------------------------
"""
When you need to manage shared resources like:
    A database connection
    A logger
    A configuration manager
    A cache
"""
#------------------------------------------------Code--------------------------------------------------------------------------

class Singleton:
    _instance = None

    # __new__() is the method that creates a new instance of a class. (It is called before __init__)
    def __new__(cls): 
        if cls._instance is None:
            print("Creating the instance...")
            cls._instance = super(Singleton, cls).__new__(cls)
        return cls._instance

if __name__ == '__main__':

    a = Singleton()
    b = Singleton()
    print(a is b)  # ✅ True: both point to the same instance

