import java.io.Serializable;

public class ArgumentObject implements Serializable {
    private String argument;
    private String type;

    public String getArgument() {
        return argument;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }

    public ArgumentObject(String argument, String type) {
        this.argument = argument;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
