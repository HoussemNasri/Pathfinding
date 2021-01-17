package org.example.widget;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

import org.example.grid.cell.AstarCell;
import org.example.grid.cell.BaseCell;
import org.example.grid.cell.CellType;
import org.example.grid.cell.DijkstraCell;
import org.example.widget.skin.AstarCellWidgetSkin;
import org.example.widget.skin.CellWidgetSkin;
import org.example.widget.skin.DijkstraCellWidgetSkin;

public class CellWidget extends PathfindingControl {
    private ObjectProperty<CellType> cellTypeProperty = new SimpleObjectProperty<>();
    private BaseCell cellModel;

    public CellWidget(BaseCell cellModel) {
        this.cellModel = cellModel;
        setCellType(cellModel.getType());
        getStyleClass().setAll(DEFAULT_STYLE_CLASS);
    }

    private CellWidgetSkin mapToSkin() {
        if (cellModel instanceof AstarCell) {
            return new AstarCellWidgetSkin(this);
        } else if (cellModel instanceof DijkstraCell) {
            return new DijkstraCellWidgetSkin(this);
        }
        return null;
    }

    /**************************************************************************
     *
     * Public API
     *
     *************************************************************************/

    /**
     * {@inheritDoc}
     */
    @Override
    protected Skin<?> createDefaultSkin() {
        return new AstarCellWidgetSkin(this);
    }

    public ObjectProperty<CellType> cellTypePropertyProperty() {
        return cellTypeProperty;
    }

    public void setCellType(CellType cellType) {
        cellTypePropertyProperty().set(cellType);
    }

    public CellType getCellType() {
        return cellTypeProperty.get();
    }

    /***************************************************************************
     *                                                                         *
     * Stylesheet Handling                                                     *
     *                                                                         *
     **************************************************************************/

    private static final String DEFAULT_STYLE_CLASS = "cell-widget";

    @Override
    public String getUserAgentStylesheet() {
        return getUserAgentStylesheet(CellWidget.class, "cellwidget.css");
    }
}
