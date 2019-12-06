package Day6;

import java.util.List;

public class Orbit {
    String name;
    List<Orbit> directOrbit;

    public Orbit(String name, List<Orbit> directOrbit) {
        this.name = name;
        this.directOrbit = directOrbit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Orbit> getDirectOrbit() {
        return directOrbit;
    }

    public void setDirectOrbit(List<Orbit> directOrbit) {
        this.directOrbit = directOrbit;
    }
}
