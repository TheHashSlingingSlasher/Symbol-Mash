/*
 * Name: Alec Farfan
 * Date: 03/20/15
 * Purpose: Matrix Class
 * Description: Class representing a matrix object with various matrix 
 *              computations.
 *              - Matrix Addition
 *              - Matrix Subtraction
 *              - Matrix Multiplication
 *              - Scalar Multiplication
 *              - Matrix Transpose
 *              - Matrix Inverse
 *              - Row Echelon Form
 *              - Reduced Row Echelon Form
 *              - LU Factorization
 */

package math.linearalgebra;

// Import libraries
import java.util.ArrayList;

public class Matrix{
    
    // Data fields
    private float array[][]; // 2D array representing a matrix
    private int rows;        // m rows of the matrix
    private int collumns;    // n columns of the matrix
    
    /**
     * Default constructor for Matrix class
     * Initializes array to null and the rows and collumns to 0
     */
    public Matrix(){
        
        array = null;
        rows = collumns = 0;
        
    }
    
    /**
     * Constructor for Matrix class. Declares an m x n matrix where m is the 
     * number of rows and n is the number of columns. Also sets the fields rows
     * and columns to the values passed in through the parameters 
     * @param row      // The number of rows in the matrix
     * @param collumn  // The number of columns in the matrix
     */
    public Matrix(int row,int collumn){
        
        array = new float[row][collumn];
        this.rows = row;
        this.collumns = collumn;
        
    }
    
    /**
     * Setter method for the array field. Sets the array of the calling object
     * to the value passed into the array parameter
     * @param array The array to be assigned to the calling objects array field
     */
    public void set_array(float [][]array){
        
        this.array = array;
        
    }
    
    /**
     * Getter method for the array field.
     * @return The calling object's array field
     */
    public float[][] get_array(){
        
        return array;
        
    }
    
    /**
     * Setter method for the rows field. Sets the calling object's rows field to
     * the value passed into the row parameter
     * @param rows The value to be assigned to the calling object's rows field
     */
    public void set_rows(int rows){
    
        this.rows = rows;
        
    }
    
    /**
     * Getter method for the rows field
     * @return The calling object's rows field
     */
    public int get_rows(){ 
    
        return rows;
    
    }
    
    /**
     * Setter method for the columns field. Sets the calling object's columns
     * field to the value passed into the collumn parameter
     * @param collumn The value to be assigned to the calling object's column 
     *                field
     */
    public void set_collumns(int collumn){
        
        this.collumns = collumn;
        
    }
    
    /**
     * Getter method for the column field.
     * @return The calling object's column field
     */
    public int get_collumns(){ 
    
        return collumns;
    
    }
    
    /**
     * Method to initialize the array field of an object created with the
     * default constructor
     * @param row      // The number of rows in the new 2D array
     * @param collumn  // The number of columns in the new 2D array
     */
    public void set_dimensions(int row, int collumn){
    
        array = new float[row][collumn];
        
    }
    
    /**
     * Method to change the value of one of a matrices entries. 
     * @param value   // Value to replace a matrices entry with
     * @param row     // The row index i of the matrix
     * @param collumn // The column index j of the matrix
     */
    public void set_element(float value,int row,int collumn){
    
        array[row][collumn] = value;
        
    }
    
    /**
     * Method to retrieve the value of one of a matrices entries.
     * @param row     // The row index i of the matrix
     * @param collumn // The column index j of the matrix
     * @return        // The value of a matrixes entry at the position
     */
    public float get_element(int row,int collumn){ 
   
        return array[row][collumn];
    
    }
    
    /**
     * Method for printing out all of the entries of a matrix. Prints each row
     * on its own line.
     */
    public void print(){
    
        for(int i=0;i<rows;i++){
            for(int j=0;j<collumns;j++){
                System.out.print(array[i][j] + " ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
        
    }
    
    /**
     * Method for adding two matrices (matrix addition).
     * @param a The first matrix
     * @param b The second matrix
     * @return  The result of a + b
     */
    public Matrix matrix_addition(Matrix a, Matrix b){
        
        // Declare Matrix to hold the result c = a + b
        Matrix result = new Matrix();
        
        // Verify that a and b are of the same dimension
        if((a.get_rows() == b.get_rows())&&a.get_collumns() == b.get_collumns()){
            // Set the dimensions of the resultant matrix
            result.set_dimensions(rows, collumns);
            result.set_rows(rows);
            result.set_collumns(collumns);
            
            // Sum all of the components of a and b and store the results in c
            for(int i=0;i<rows;i++){
                for(int j=0;j<collumns;j++){
                    result.set_element(a.get_element(i,j) + b.get_element(i,j), i, j);
                }
            }
        }
        
        return result;
       
    }
    
    /**
     * Method for subtracting a matrix from another (matrix subtraction)
     * @param a The first matrix
     * @param b The second matrix
     * @return  The result of a - b
     */
    public Matrix matrix_subtraction(Matrix a,Matrix b){
        
        // Declare Matrix to hold the result c = a - b
        Matrix result = new Matrix();
        
        // Verify that a and b are of the same dimension
        if((a.get_rows()==b.get_rows())&&a.get_collumns()==b.get_collumns()){
            // Set the dimensions of the resultant matrix
            result.set_dimensions(rows, collumns);
            result.set_rows(rows);
            result.set_collumns(collumns);
            
            // Sum all of the components of a and b and store the results in c
            for(int i=0;i<rows;i++){
                for(int j=0;j<collumns;j++){
                    result.set_element(a.get_element(i,j) - b.get_element(i,j),i,j);
                }
            }
        }
        
        return result;
       
    }
    
    /**
     * Method for multiplying two matrices (Matrix Multiplication)
     * @param a The first matrix a
     * @param b The second matrix b
     * @return  The result c = ab
     */
    public Matrix matrix_multiplication(Matrix a,Matrix b){
    
        Matrix result = new Matrix();
        
        // Verify that m x n = n x k
        if(a.get_collumns()==b.get_rows()){
            // Set the dimensions of the resultant matrix as m x k
            result.set_dimensions(a.get_rows(),b.get_collumns());
            result.set_rows(a.get_rows());
            result.set_collumns(b.get_collumns());
            
            // Multiply the matrices and store the result in c
            int k = 0;
            for(int i=0;i<a.get_rows();i++){
                for(int j=0;j<b.get_collumns();j++){
                    float element = 0;
                    for(int l=0;l<b.get_rows();l++){
                        element += a.get_element(i,l)*b.get_element(l,k);
                    }
                    k++;
                    if(k>b.get_collumns()-1){
                        k = 0;
                    }
                    result.set_element(element,i,j);
                }
            }
        }
        
        return result;
        
    }
    
    /**
     * Method for multiplying a matrix by a real number (Scalar Multiplication)
     * @param a       // The matrix to be scaled
     * @param c       // The number to scale the matrix by
     * @return        // The result b = ca
     */
    public Matrix scalar_multiplication(Matrix a, float c){
        
        // Initialize the resultant matrix to a
        Matrix result = a;     
        
        // Increase each entry of the matrix by a-times
        for(int i=0;i<rows;i++){
            for(int j=0;j<collumns;j++){
                result.set_element(c*result.get_element(i,j),i,j);
            }
        }
        
        return result;
    
    }
    
    /**
     * Method for transposing a matrix (Matrix Transpose)
     * @param a The matrix to be transposed
     * @return The transpose a^t
     */
    public Matrix matrix_transpose(Matrix a){
       
        // Initialize the resultant matrix with opposite row and column values
        Matrix result = new Matrix(a.get_collumns(),a.get_rows());
        
        // Switch the columns with the rows
        for(int i=0;i<result.get_rows();i++){
            for(int j=0;j<result.get_collumns();j++){
                result.set_element(a.get_element(j,i),i,j);
            }
        }
        
        return result;
        
    }
    
    /**
     * Method for transforming a matrix to its row echelon form(Row Echelon Form)
     * @param a The matrix to reduce to row echelon form
     * @return The row echelon form of a REF(a)
     */
    public Matrix row_echelon_form(Matrix a){
    
        Matrix result = a;
        
        for(int i=0;i<rows;i++){
            boolean pivot_found = false;
            int pivot_row = 0;
            
            // Look for a 1 directly underneath
            for(int b=i;b<rows&&pivot_found==false;b++){
                if(array[b][i]==1){
                    pivot_found = true;
                    pivot_row = b;
                }
            }   
            // If no pivot is directly availible, find most divisible row
            if(!pivot_found){
                int max_frequency = 0;
                int index_of_max = i;
                for(int c=i;c<rows;c++){
                    int count = 0;
                    for(int d=i+1;d<collumns;d++){
                        if(array[c][i]<=array[c][d] && (array[c][d]%array[c][i])==0){
                            count++;
                        }
                    }
                    if(count>max_frequency){
                        max_frequency = count;
                        index_of_max = c;
                    }
                }
                pivot_row = index_of_max;
                result = reduce_pivot(result,pivot_row,array[pivot_row][i]);         
            }
            result = result.interchange_rows(result, pivot_row, i);
            for(int j=i+1;j<rows;j++){
                float scalar = (array[j][i]/array[i][i]);
                float new_row[] = scale_row(result,i,result.get_collumns(),(array[j][i]/array[i][i])*-1);
               result = add_row(result,j,new_row); 
            }
        }
        result = result.clear_negative_zeros(result);
        return result;
        
    }
    
    /**
     * Method for transforming a matrix to its reduced row echelon form
     * (Reduced Row Echelon Form)
     * @param a The matrix to be transformed to reduced row echelon form
     * @return  The reduced row echelon form of a RREF(a)
     */
    public Matrix reduced_row_echelon_form(Matrix a){
    
        Matrix result = a.row_echelon_form(a);
        
        for(int i=result.get_rows()-1;i>=0;i--){
            for(int j=i;j>0;j--){
                float scalar = result.get_element(j-1,i)/result.get_element(i,i);
                float new_row[] = scale_row(result,i,result.get_collumns(),scalar*-1);
                result = add_row(result,j-1,new_row);
            }
        }
        
        return result;
        
    }
    
    /**
     * Method for interchanging two rows of a matrix
     * @param a    The matrix to be modified
     * @param row1 The first row to be interchanged
     * @param row2 The second row to be interchanged
     * @return     The matrix a with row1 interchanged with row2
     */
    public Matrix interchange_rows(Matrix a,int row1,int row2){
        
        Matrix result = a;
        int cols = a.get_collumns();
        float temp_array[] = new float[cols];
        
        for(int i=0;i<cols;i++){
            temp_array[i] = result.get_element(row1,i);
        }
        for(int i=0;i<cols;i++){
            result.set_element(result.get_element(row2,i),row1,i);
        }
        for(int i=0;i<cols;i++){
            result.set_element(temp_array[i],row2,i);
        }
        
        return result;
        
    }
    
    /**
     * Method for reducing the pivot row in the row echelon form algorithm
     * @param a      The matrix to be modified
     * @param row    The row the pivot is contained in
     * @param scalar The scalar to divide the pivot by
     * @return       The matrix a with its pivot row turned into a leading 1
     */
    public Matrix reduce_pivot(Matrix a,int row,float scalar){
    
        Matrix result = a;
        for(int i=0;i<result.get_collumns();i++){
            result.set_element(result.get_element(row,i)/scalar, row,i);
        }
        return result;
        
    }
    
    /**
     * Method for multiplying a single row by a scalar
     * @param a       The matrix to be modified
     * @param row_num The row to be multiplied by a scalar
     * @param size    The number of entries to be scaled
     * @param scalar  The real number to scale the given row by
     * @return        The matrix a with the given row scaled by the given scalar
     */
    public float[] scale_row(Matrix a,int row_num, int size,float scalar){
  
        float new_row[] = new float[size];
        
        for(int i=0;i<size;i++){
            new_row[i] = a.get_element(row_num, i) * scalar;
        }
        return new_row;
        
    }
    
    /**
     * Method for adding a row to another
     * @param a        The matrix to be modified
     * @param row_num  The row of the matrix to be modified
     * @param row      The row to be added to another
     * @return         The matrix a after the row has been added to another
     */
    public Matrix add_row(Matrix a,int row_num,float[] row){
    
        Matrix result = a;
        for(int i=0;i<result.get_collumns();i++){
            result.set_element(result.get_element(row_num,i)+row[i],row_num,i);
        }
        return result;
        
    }
    
    /**
     * Method to remove entries with value -0.0 and replace them with 0.0
     * Caused by the definition of negative numbers in Java
     * @param a The matrix to be modified
     * @return  Matrix a with any -0.0's replaced with 0.0
     */
    public Matrix clear_negative_zeros(Matrix a){
    
        Matrix result = a;
        for(int i=0;i<rows;i++){
            for(int j=0;j<collumns;j++){
                if(result.get_element(i,j)==-0.0){
                    result.set_element(0,i,j);
                }
            }
        }
        
        return result;
    }
    
    /** 
     * Method for finding the inverse of a matrix (Matrix Inverse)
     * @param a The matrix a to find the invese of
     * @return The matrix a^-1
     */
    public Matrix matrix_inverse(Matrix a){
    
        Matrix merge = new Matrix(a.get_rows(),a.get_collumns()*2);
        for(int i=0;i<a.get_rows();i++){
            for(int j=0;j<a.get_collumns();j++){
                merge.set_element(a.get_element(i,j),i,j);
            }
        }
        int modulo = merge.get_rows();
        for(int i=0;i<merge.get_rows();i++){
            for(int j=a.get_collumns();j<merge.get_collumns();j++){
                if(j%modulo==0){
                    merge.set_element(1,i,j);
                }
                else{
                    merge.set_element(0,i,j);
                }
            }
            modulo++;
        }
        merge = reduced_row_echelon_form(merge);
        Matrix result = new Matrix(a.get_rows(),a.get_collumns());
        for(int i=0;i<result.get_rows();i++){
            for(int j=0;j<result.get_collumns();j++){
                result.set_element(merge.get_element(i,j+result.get_rows()),i,j);
            }
        }
        return result;
        
    }
    
    /**
     * Method for finding the LU factorization of a matrix (LU Factorization)
     * @param a The matrix to be factored
     * @return  An ArrayList of 2 matrices lu = a
     */
    public ArrayList<Matrix> lu_decompisition(Matrix a){
    
        ArrayList<Matrix> lu = new ArrayList();
        ArrayList<Matrix> elementaries = new ArrayList();
        
        Matrix result = a;
        for(int i=0;i<rows-1;i++){
            elementaries.add(a.identity_matrix(a));
            boolean pivot_found = false;
            int pivot_row = 0;
            
            // Look for a 1 directly underneath
            for(int b=i;b<rows&&pivot_found==false;b++){
                if(array[b][i]==1){
                    pivot_found = true;
                    pivot_row = b;
                }
            }   
            // If no pivot is directly availible, find most divisible row
            if(!pivot_found){
                int max_frequency = 0;
                int index_of_max = i;
                for(int c=i;c<rows;c++){
                    int count = 0;
                    for(int d=i+1;d<collumns;d++){
                        if(array[c][i]<=array[c][d] && (array[c][d]%array[c][i])==0){
                            count++;
                        }
                    }
                    if(count>max_frequency){
                        max_frequency = count;
                        index_of_max = c;
                    }
                }
                pivot_row = index_of_max;
            }
            result = result.interchange_rows(result, pivot_row, i);
            elementaries.set(i,elementaries.get(i).interchange_rows(elementaries.get(i),pivot_row,i));
            for(int j=i+1;j<rows;j++){
                float scalar = (array[j][i]/array[i][i]);
                if(scalar!=0){
                    float result_new_row[] = scale_row(result,i,result.get_collumns(),(array[j][i]/array[i][i])*-1);
                    float elementary_new_row[] = scale_row(elementaries.get(i),i,elementaries.get(i).get_collumns(),(array[j][i]/array[i][i])*-1);
                    result = add_row(result,j,result_new_row); 
                    elementaries.set(i,add_row(elementaries.get(i),j,elementary_new_row));
                }       
            }
        }
        result.clear_negative_zeros(result);
        for(int i=0;i<elementaries.size();i++){
            elementaries.set(i,elementaries.get(i).clear_negative_zeros(elementaries.get(i)));
            elementaries.set(i,elementaries.get(i).matrix_inverse(elementaries.get(i)));
        }
        Matrix l = elementaries.get(0);
        for(int i=1;i<elementaries.size();i++){
            l = l.matrix_multiplication(l,elementaries.get(i));
        }   
        lu.add(l);
        lu.add(result);
     
        return lu;
        
    }
    
    /**
     * Method for finding an identity matrix
     * @param a The matrix to find an identity for
     * @return  The identity matrix of a (In)
     */
    public Matrix identity_matrix(Matrix a){
    
        Matrix result = new Matrix(a.get_rows(),a.get_collumns());    
        
        result.set_element(1,0,0);
        for(int i=1;i<result.get_collumns();i++){
             result.set_element(0,i,i);    
        }

        int modulo = 1;
        for(int i=1;i<result.get_rows();i++){
            for(int j=1;j<result.get_collumns();j++){
                if(j==i){
                    result.set_element(1,i,j);   
                }
                else{
                    result.set_element(0,i,j);
                }
            }
            modulo++;
        }
        return result;
        
    }
    
    /**
     * Method for calculating the determinant of a matrix
     * @param a The matrix to find the determinant of
     * @return  The determinant of a det(a)
     */
    public float determinant(Matrix a){
    
        if(a.get_rows()==2){
            return (a.get_element(0,0)*a.get_element(1,1)-a.get_element(0,1)*a.get_element(1,1));
        }
        else{
            float best[] = find_best(a);
            for(int i=0;i<a.get_collumns();i++){
                Matrix new_determinant = a.reduce_determinant(a,best[a.get_collumns()-3],best[a.get_collumns()-2],i);
                best[i] *= new_determinant.determinant(new_determinant);
            }
        }
        
        return 0;
    }
    
    /**
     * Finds the row or column with the most 0's to use in the determinant 
     * algorithm
     * @param a The matrix a to find the best row of
     * @return  An array of floats containing the best row
     */
    public float[] find_best(Matrix a){
    
        float best[] = new float[a.get_rows()+2];
        int max = 0, place = 0, position = 0;
        
        for(int i=0;i<a.get_rows();i++){
            int row_zeros = 0, collumn_zeros = 0;
            for(int j=0;j<a.get_collumns();j++){
                if(a.get_element(i,j)==0){
                    row_zeros++;
                }
                if(a.get_element(j,i)==0){
                    collumn_zeros++;
                }
            }
            if(row_zeros>max){
                max = row_zeros;
                place = i;
                position = 0;
            }
            if(collumn_zeros>max){
                max = collumn_zeros;
                place = i;
                position = 1;
            }
            
        }
        if(position==0){
            for(int i=0;i<a.get_rows();i++){
                for(int j=0;j<a.get_rows();j++){
                    if(i==place){
                        best[j] = a.get_element(i,j);
                    }
                }
            }
        }
        else{
            for(int i=0;i<a.get_rows();i++){
                for(int j=0;j<a.get_rows();j++){
                    if(j==place){
                        best[i] = a.get_element(i,j);
                    }
                }
            }
        }
        best[a.get_rows()] = place;
        best[a.get_rows()+1] = position;
        
        return best;
        
    }
    
    /**
     * Method for finding the minor of a matrix
     * @param a        The matrix to search for the minor
     * @param place    The index 
     * @param position The row to ignore
     * @param index    The column to ignore
     * @return         A matrix representing the proper minor
     */
    public Matrix reduce_determinant(Matrix a,float place,float position,int index){
    
        Matrix result = new Matrix(a.get_rows()-1,a.get_rows()-1);
        
        if(position==0){
            for(int i=0;i<a.get_rows();i++){
                for(int j=0;j<a.get_collumns();j++){
                    if(!(i==place||j==index)){
                        result.set_element(a.get_element(i,j),i,j);
                    }
                }
            }
        }
        else{
            for(int i=0;i<a.get_rows();i++){
                for(int j=0;j<a.get_collumns();j++){
                    if(!(j==place||i==index)){
                        result.set_element(a.get_element(i,j),i,j);
                    }
                }
            }
        }
        
        return result;
    }
 
    /**
     * Creates a string representing the matrix to be printed to the terminal
     * screen. Each row of the matrix is printed on it's own line.
     * @return A string representing the matrix given in the string
     */
    public String to_string(){
        
        String matrix_string = "    ";
        
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < collumns; j++){
                matrix_string += array[i][j] + " ";
            }
            if(i != rows-1)
                matrix_string += "\n    ";
        }
        
        return matrix_string;
        
    }
    
}
