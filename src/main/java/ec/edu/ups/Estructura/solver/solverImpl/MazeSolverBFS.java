package ec.edu.ups.Estructura.solver.solverImpl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import ec.edu.ups.Estructura.models.Cell;
import ec.edu.ups.Estructura.models.CellState;
import ec.edu.ups.Estructura.models.SolveResult;
import ec.edu.ups.Estructura.solver.MazeSolver;

public class MazeSolverBFS implements MazeSolver {
    public SolveResult getPath(Cell[][] paramArrayOfCell, Cell paramCell1, Cell paramCell2) {
        int i = paramArrayOfCell.length, j = (paramArrayOfCell[0]).length;
        boolean[][] arrayOfBoolean = new boolean[i][j];
        HashMap<Object, Object> hashMap = new HashMap<>();
        LinkedList<Cell> linkedList = new LinkedList();
        ArrayList<Cell> arrayList1 = new ArrayList();
        Cell cell1 = paramArrayOfCell[paramCell1.row][paramCell1.col];
        Cell cell2 = paramArrayOfCell[paramCell2.row][paramCell2.col];
        linkedList.add(cell1);
        arrayOfBoolean[cell1.row][cell1.col] = true;
        while (!linkedList.isEmpty()) {
            Cell cell = linkedList.poll();
            arrayList1.add(cell);
            if (cell.equals(cell2))
                break;
            for (int[] arrayOfInt : new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } }) {
                int k = cell.row + arrayOfInt[0];
                int m = cell.col + arrayOfInt[1];
                if (k >= 0 && k < i && m >= 0 && m < j) {
                    Cell cell4 = paramArrayOfCell[k][m];
                    if (!arrayOfBoolean[k][m] && cell4.state != CellState.WALL) {
                        arrayOfBoolean[k][m] = true;
                        hashMap.put(cell4, cell);
                        linkedList.add(cell4);
                    }
                }
            }
        }
        ArrayList<Cell> arrayList2 = new ArrayList();
        Cell cell3 = cell2;
        while (hashMap.containsKey(cell3)) {
            arrayList2.add(cell3);
            cell3 = (Cell)hashMap.get(cell3);
        }
        if (cell3.equals(cell1)) {
            arrayList2.add(cell1);
            Collections.reverse(arrayList2);
        } else {
            arrayList2.clear();
        }
        return new SolveResult(arrayList1, arrayList2);
    }
}
