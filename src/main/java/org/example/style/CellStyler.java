package org.example.style;

import java.util.Set;

import org.example.grid.CellView;
import org.example.grid.CellKind;

public interface CellStyler {

    void style(CellView cellView);

    Set<CellKind> getConcernedKinds();
}
