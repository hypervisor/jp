package Engine;

import Engine.Input;

public interface GameObject {
    void update(Input i, float deltaTime);
    void render(Drawing d);
}
