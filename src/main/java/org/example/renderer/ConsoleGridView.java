package org.example.renderer;

public class ConsoleGridView implements GridRenderer<Void> {
    private static final Void VOID = null;

    @Override
    public void render() {
    }

    @Override
    public Void getView() {
        return VOID;
    }
}
