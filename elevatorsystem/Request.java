package elevatorsystem;
import java.util.Objects;
import elevatorsystem.enums.RequestType;

public class Request {
    private final int floor;
    private final RequestType type;

    public Request(int floor,RequestType type)
    {
        this.floor = floor;
        this.type = type;
    }

    public int getFloor()
    {
        return floor;
    }

    public RequestType getType()
    {
        return type;
    }

}
