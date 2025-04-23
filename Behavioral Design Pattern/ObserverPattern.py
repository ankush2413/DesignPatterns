
#----------------------------------------Observer Design Pattern-----------------------------------------


"""
We use this pattern generally:

When one object (the subject) changes state, all dependent objects (observers) are automatically notified.
Observer Pattern defines a one-to-many relationship between objects
"""
#----------------------------------------------------------------------------------------------------------------

"""
Terminology:

Subject -> The main object being observed
Observer -> Listens to the subject and gets notified of changes
ConcreteSubject -> The real class implementing the subject
ConcreteObserver -> Classes that react to changes

"""

#---------------------------------------------------------------------------------------------------------------------

"""
------Some Use Cases-------


Event systems / notification services

Real-time updates (stocks, weather apps)

Chat apps or collaboration tools

MVC pattern (View observes Model)

"""
#--------------------------------------------------Code------------------------------------------------------------------


from abc import ABC, abstractmethod

# Observer Interface
class Observer(ABC):
    @abstractmethod
    def update(self, video_title):
        pass

# Subject Interface
class Subject(ABC):
    @abstractmethod
    def subscribe(self, observer: Observer):
        pass

    @abstractmethod
    def unsubscribe(self, observer: Observer):
        pass

    @abstractmethod
    def notify_observers(self, video_title):
        pass

# Concrete Subject: YouTubeChannel
class YouTubeChannel(Subject):
    def __init__(self):
        self.subscribers = []

    def subscribe(self, observer: Observer):
        self.subscribers.append(observer)

    def unsubscribe(self, observer: Observer):
        self.subscribers.remove(observer)

    def notify_observers(self, video_title):
        for observer in self.subscribers:
            observer.update(video_title)

    def upload_video(self, title):
        print(f"\n📺 New Video Uploaded: {title}")
        self.notify_observers(title)

# Concrete Observer: User
class User(Observer):
    def __init__(self, name):
        self.name = name

    def update(self, video_title):
        print(f"🔔 {self.name} got notified: New video - {video_title}")

# Client Code
if __name__ == "__main__":
    channel = YouTubeChannel()

    ankush = User("Ankush")
    rahul = User("Rahul")

    channel.subscribe(ankush)
    channel.subscribe(rahul)

    channel.upload_video("Video one Uploaded")

    channel.unsubscribe(rahul)
    channel.upload_video("Video two uploaded")


