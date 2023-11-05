Feature: MatrixMathematics
  Scenario: Calculate Matrix determinant
    Given i have a Matrix defined as :
    | 1 | 2 | 3 |
    | 4 | 5 | 6 |
    | 7 | 8 | 9 |
    When I calculate  Determinant  of the matrix
    Then the result should be 0