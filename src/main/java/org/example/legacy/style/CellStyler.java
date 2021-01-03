package org.example.legacy.style;

import java.util.Set;

import org.example.legacy.grid.CellView;
import org.example.legacy.grid.CellKind;

public interface CellStyler {

    void style(CellView cellView);

    Set<CellKind> getConcernedKinds();
}
