package ec.edu.ups.Estructura.solver.solverImpl;
import ec.edu.ups.Estructura.models.Cell;
import ec.edu.ups.Estructura.models.CellState;
import ec.edu.ups.Estructura.models.SolveResult;
import ec.edu.ups.Estructura.solver.MazeSolver;
import java.util.*;

public class MazeSolverRecursivoCompletoBT implements MazeSolver {
    private Set<Cell> visited = new LinkedHashSet<>();

    private List<Cell> camino = new ArrayList<>();

    public SolveResult getPath(Cell[][] paramArrayOfCell, Cell paramCell1, Cell paramCell2) {
        this.visited.clear();
        this.camino.clear();
        findPath(paramArrayOfCell, paramCell1.row, paramCell1.col, paramCell2);
        Collections.reverse(this.camino);
        return new SolveResult(new ArrayList<>(this.visited), new ArrayList<>(this.camino));
    }

    private boolean findPath(Cell[][] paramArrayOfCell, int paramInt1, int paramInt2, Cell paramCell) {
        if (!isValid(paramArrayOfCell, paramInt1, paramInt2))
            return false;
        Cell cell = paramArrayOfCell[paramInt1][paramInt2];
        if (this.visited.contains(cell))
            return false;
        this.visited.add(cell);
        this.camino.add(cell);
        if (cell.equals(paramCell))
            return true;
        if (findPath(paramArrayOfCell, paramInt1 + 1, paramInt2, paramCell) ||
                findPath(paramArrayOfCell, paramInt1, paramInt2 + 1, paramCell) ||
                findPath(paramArrayOfCell, paramInt1 - 1, paramInt2, paramCell) ||
                findPath(paramArrayOfCell, paramInt1, paramInt2 - 1, paramCell))
            return true;
        this.camino.remove(this.camino.size() - 1);
        return false;
    }

    private boolean isValid(Cell[][] paramArrayOfCell, int paramInt1, int paramInt2) {
        return (paramInt1 >= 0 && paramInt1 < paramArrayOfCell.length && paramInt2 >= 0 && paramInt2 < (paramArrayOfCell[0]).length && (paramArrayOfCell[paramInt1][paramInt2]).state != CellState.WALL);
    }
}
