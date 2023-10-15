package com.test;

import com.exception.NoSquareException;
import com.model.Matrix;
import com.service.MatrixMathematics;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixMathematicsTest {
    @Test
    public void TestDeterminant() throws NoSquareException {
        double[][] matrice3D = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double[][] matrice2D = {{1, 2},{4, 9}};
        double[][] matrice1D = {{1}};
        Matrix mat1 = new Matrix(matrice1D);
        Matrix mat2 = new Matrix(matrice2D);
        Matrix mat3 = new Matrix(matrice3D);
        assertEquals(MatrixMathematics.determinant(mat1), 1);
        assertEquals(MatrixMathematics.determinant(mat2), 1);
        assertEquals(MatrixMathematics.determinant(mat3), 0);
    }
    @Test
        public void TestShape(){
            double[][] matrice = {{1, 2}, {4, 5}, {7, 8}};
            Matrix mat = new Matrix(matrice);
            assertThrows(NoSquareException.class,() -> {MatrixMathematics.determinant(mat);});
    }
}