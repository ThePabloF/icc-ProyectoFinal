package ec.edu.ups.Estructura.solver.solverImpl;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import ec.edu.ups.Estructura.models.Cell;
import ec.edu.ups.Estructura.models.CellState;
import ec.edu.ups.Estructura.models.SolveResult;
import ec.edu.ups.Estructura.solver.MazeSolver;

public class MazeSolverDFS implements MazeSolver {
    private Set<Cell> visitadas = new LinkedHashSet<>();

    private List<Cell> camino = new ArrayList<>();

    public SolveResult getPath(Cell[][] paramArrayOfCell, Cell paramCell1, Cell paramCell2) {
        this.visitadas.clear();
        this.camino.clear();
        dfs(paramArrayOfCell, paramCell1.row, paramCell1.col, paramCell2);
        return new SolveResult(new ArrayList<>(this.visitadas), new ArrayList<>(this.camino));
    }

    private boolean dfs(Cell[][] paramArrayOfCell, int paramInt1, int paramInt2, Cell paramCell) {
        if (!isValid(paramArrayOfCell, paramInt1, paramInt2))
            return false;
        Cell cell = paramArrayOfCell[paramInt1][paramInt2];
        if (this.visitadas.contains(cell))
            return false;
        this.visitadas.add(cell);
        if (cell.equals(paramCell)) {
            this.camino.add(cell);
            return true;
        }
        if (dfs(paramArrayOfCell, paramInt1 + 1, paramInt2, paramCell) ||
                dfs(paramArrayOfCell, paramInt1 - 1, paramInt2, paramCell) ||
                dfs(paramArrayOfCell, paramInt1, paramInt2 + 1, paramCell) ||
                dfs(paramArrayOfCell, paramInt1, paramInt2 - 1, paramCell)) {
            this.camino.add(cell);
            return true;
        }
        return false;
    }

    private boolean isValid(Cell[][] paramArrayOfCell, int paramInt1, int paramInt2) {
        return (paramInt1 >= 0 && paramInt1 < paramArrayOfCell.length && paramInt2 >= 0 && paramInt2 < (paramArrayOfCell[0]).length && (paramArrayOfCell[paramInt1][paramInt2]).state != CellState.WALL);
    }
}

